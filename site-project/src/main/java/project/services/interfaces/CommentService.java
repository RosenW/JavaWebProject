package project.services.interfaces;

import project.models.CommentModel;
import project.models.MemeModel;

import java.util.List;

public interface CommentService {
    void addComment(CommentModel commentModel);

    List<CommentModel> getAllComemnts();
}
