package project.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import project.models.MemeModel;
import project.models.RegisterUserModel;
import project.services.interfaces.MemeService;
import project.services.interfaces.UserService;

import javax.validation.Valid;
import java.util.Collections;
import java.util.List;

@Controller
public class UserController {

    private final UserService userService;

    private final MemeService memeService;

    @Autowired
    public UserController(UserService userService, MemeService memeService) {
        this.userService = userService;
        this.memeService = memeService;
    }

    @GetMapping("/login")
    public String getLoginPage(@RequestParam(required = false) String error, Model model) {
        if (error != null) {
            model.addAttribute("error", "Invalid Credentials");
        }
        return "login";
    }

    @GetMapping("/register")
    public String getRegisterPage(@ModelAttribute RegisterUserModel registerUserModel) {
        return "register";
    }

    @PostMapping("/register")
    public String postRegisterPage(@Valid @ModelAttribute RegisterUserModel registerUserModel, BindingResult bindingResult) {

        if(bindingResult.hasErrors()){
            return "register";
        }

        userService.register(registerUserModel);
        return "redirect:/login";
    }

    @GetMapping("/{id}/like")
    @ResponseBody
    public int getLikeMeme(@PathVariable Long id) {
        return userService.like(id);
    }

    @GetMapping("/{id}/dislike")
    @ResponseBody
    public int getDislikeMeme(@PathVariable Long id) {
        return userService.dislike(id);
    }

    @GetMapping("/my-memes")
    public String getMyMemesPage(Model model) {
        List<MemeModel> allMemes = memeService.getUserMemes(userService.getCurrentUser());
        Collections.reverse(allMemes);
        model.addAttribute("memes", allMemes);
        return "my-memes";
    }
}
