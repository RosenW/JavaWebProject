package project.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import project.models.CommentModel;
import project.models.MemeModel;
import project.services.interfaces.CommentService;
import project.services.interfaces.MemeService;
import project.services.interfaces.TypeService;
import project.services.interfaces.UserService;

import java.util.List;

@Controller
@RequestMapping("/meme")
public class MemeController {

    private final MemeService memeService;

    private final TypeService typeService;

    private final CommentService commentService;

    private final UserService userService;

    @Autowired
    public MemeController(MemeService memeService, TypeService typeService, CommentService commentService, UserService userService) {
        this.memeService = memeService;
        this.typeService = typeService;
        this.commentService = commentService;
        this.userService = userService;
    }

    @GetMapping("/add")
    public String getAddMemePage(Model model) {
        model.addAttribute("types", typeService.getTypes());
        return "add-meme";
    }

    @PostMapping("/add")
    public String postAddMemePage(@ModelAttribute MemeModel memeModel) {
        memeService.addMeme(memeModel);
        return "redirect:/";
    }

    @GetMapping("/comments/{id}")
    public String getCommentPage(@ModelAttribute CommentModel commentModel, @PathVariable long id, Model model) {
        MemeModel memeModel = memeService.findOne(id);
        List<CommentModel> comments = commentService.getAllComemnts();
        model.addAttribute("meme", memeModel);
        model.addAttribute("comments", comments);
        return "comments";
    }

    @PostMapping("/comments/{id}")
    public String postCommentPage(@ModelAttribute CommentModel commentModel, @PathVariable long id) {
        commentModel.setMemeId(id);
        commentModel.setUserName(userService.getCurrentUser().getEmail());
        commentService.addComment(commentModel);
        return "redirect:/meme/comments/" + id ;
    }
}
