package project.services.interfaces;

import org.springframework.security.core.userdetails.UserDetailsService;
import project.entities.Meme;
import project.entities.User;
import project.models.RegisterUserModel;

public interface UserService extends UserDetailsService {
    void register(RegisterUserModel registerUserModel);

    int like(Long id);

    int dislike(Long id);

    User getCurrentUser();
}
