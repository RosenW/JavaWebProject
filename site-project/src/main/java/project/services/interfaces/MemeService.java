package project.services.interfaces;

import project.entities.Meme;
import project.entities.User;
import project.models.MemeModel;

import java.util.List;

public interface MemeService {
    List<MemeModel> getAllMemes();

    void addMeme(MemeModel memeModel);

    MemeModel findOne(Long id);

    List<MemeModel> getUserMemes(User user);
}
