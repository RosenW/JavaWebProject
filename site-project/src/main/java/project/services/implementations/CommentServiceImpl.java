package project.services.implementations;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import project.entities.Comment;
import project.entities.User;
import project.models.CommentModel;
import project.repository.CommentRepository;
import project.repository.MemeRepository;
import project.services.interfaces.CommentService;
import project.services.interfaces.UserService;
import java.util.ArrayList;
import java.util.List;

@Service
public class CommentServiceImpl implements CommentService {

    private final CommentRepository commentRepository;
    private final MemeRepository memeRepository;
    private final ModelMapper modelMapper;
    private final UserService userService;

    @Autowired
    public CommentServiceImpl(CommentRepository commentRepository, MemeRepository memeRepository, ModelMapper modelMapper, UserService userService) {
        this.commentRepository = commentRepository;
        this.memeRepository = memeRepository;
        this.modelMapper = modelMapper;
        this.userService = userService;
    }

    @Override
    public void addComment(CommentModel commentModel) {
        Comment comment = new Comment();
        comment.setContent(commentModel.getContent());
        comment.setMeme(memeRepository.findOne(commentModel.getMemeId()));
        comment.setUser((User) userService.loadUserByUsername(commentModel.getUserName()));
        commentRepository.saveAndFlush(comment);
    }

    @Override
    public List<CommentModel> getAllComemnts() {
        List<Comment> comments = commentRepository.findAll();
        List<CommentModel> commentModels = new ArrayList<>();
        for (Comment comment : comments) {
            CommentModel curCommentModel = new CommentModel();
            modelMapper.map(comment, curCommentModel);
            curCommentModel.setMemeId(comment.getMeme().getId());
            curCommentModel.setUserName(comment.getUser().getEmail());
            commentModels.add(curCommentModel);
        }
        return commentModels;
    }
}
