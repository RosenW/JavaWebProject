package project.services.implementations;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import project.entities.Meme;
import project.entities.Role;
import project.entities.User;
import project.models.MemeModel;
import project.models.RegisterUserModel;
import project.repository.MemeRepository;
import project.repository.UserRepository;
import project.services.interfaces.MemeService;
import project.services.interfaces.UserService;

@Service
@Transactional
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;

    private final MemeRepository memeRepository;

    private final RoleServiceImpl roleService;

    private final ModelMapper modelMapper;

    @Autowired
    public UserServiceImpl(UserRepository userRepository, MemeRepository memeRepository, RoleServiceImpl roleService, ModelMapper modelMapper) {
        this.userRepository = userRepository;
        this.memeRepository = memeRepository;
        this.roleService = roleService;
        this.modelMapper = modelMapper;
    }

    public void register(RegisterUserModel registerUserModel) {
        BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder();

        User user = new User();

        this.modelMapper.map(registerUserModel, user);
        user.setPassword(bCryptPasswordEncoder.encode(registerUserModel.getPassword()));

        Role userRole = this.roleService.findByName("ROLE_USER");

        user.addRole(userRole);

        this.userRepository.save(user);
    }

    @Override
    public int like(Long id) {
        Meme meme = memeRepository.findOne(id);
        User curUser = getCurrentUser();
        curUser.addToLiked(meme);
        userRepository.saveAndFlush(curUser);
        return meme.getPoints();
    }

    @Override
    public int dislike(Long id) {
        Meme meme = memeRepository.findOne(id);
        User curUser = getCurrentUser();
        curUser.addToDisliked(meme);
        userRepository.saveAndFlush(curUser);
        return meme.getPoints();
    }

    @Override
    public User getCurrentUser() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        return userRepository.findByEmail(authentication.getName());
    }


    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userRepository.findByEmail(username);

        if (user == null) {
            throw new UsernameNotFoundException("Invalid User");
        } else {
            return user;
        }
    }
}
