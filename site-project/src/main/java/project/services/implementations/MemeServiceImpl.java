package project.services.implementations;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import project.entities.Meme;
import project.entities.User;
import project.models.MemeModel;
import project.repository.MemeRepository;
import project.services.interfaces.MemeService;
import project.services.interfaces.TypeService;
import project.services.interfaces.UserService;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;

@Service
public class MemeServiceImpl implements MemeService {

    private final MemeRepository memeRepository;

    private final ModelMapper modelMapper;

    private final TypeService typeService;

    private final UserService userService;

    @Autowired
    public MemeServiceImpl(MemeRepository memeRepository, ModelMapper modelMapper, TypeService typeService, UserService userService) {
        this.memeRepository = memeRepository;
        this.modelMapper = modelMapper;
        this.typeService = typeService;
        this.userService = userService;
    }

    @Override
    public List<MemeModel> getAllMemes() {
        List<Meme> memes = memeRepository.findAll();
        List<MemeModel> memeModels = new ArrayList<>();
        for (Meme meme : memes) {
            MemeModel curMemeModel = new MemeModel();
            modelMapper.map(meme, curMemeModel);
            curMemeModel.setType(meme.getType().getName());
            curMemeModel.setUser(meme.getUser().getEmail());
            memeModels.add(curMemeModel);
        }
        return memeModels;
    }

    @Override
    public void addMeme(MemeModel memeModel) {
        Meme meme = new Meme();
        modelMapper.map(memeModel, meme);
        meme.setType(typeService.getType(memeModel.getType()));
        meme.setUser(userService.getCurrentUser());
        memeRepository.save(meme);
    }

    @Override
    public MemeModel findOne(Long id) {
        MemeModel memeModel = new MemeModel();
        Meme meme = memeRepository.findOne(id);
        modelMapper.map(meme, memeModel);
        memeModel.setType(meme.getType().getName());
        return memeModel;
    }

    @Override
    public List<MemeModel> getUserMemes(User user) {
        List<Meme> memes = memeRepository.findAllByUser(user);
        List<MemeModel> memeModels = new ArrayList<>();
        for (Meme meme : memes) {
            MemeModel curMemeModel = new MemeModel();
            modelMapper.map(meme, curMemeModel);
            curMemeModel.setType(meme.getType().getName());
            memeModels.add(curMemeModel);
        }
        return memeModels;
    }
}
