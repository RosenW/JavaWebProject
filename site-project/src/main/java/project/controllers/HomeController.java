package project.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import project.models.MemeModel;
import project.services.interfaces.MemeService;
import project.services.interfaces.UserService;

import java.util.Collections;
import java.util.List;

@Controller
public class HomeController {
    private final MemeService memeService;

    private final UserService userService;

    @Autowired
    public HomeController(MemeService memeService, UserService userService) {
        this.memeService = memeService;
        this.userService = userService;
    }

    @GetMapping("/")
    public String getHomePage(Model model) {
        List<MemeModel> allMemes = memeService.getAllMemes();
        Collections.reverse(allMemes);
        model.addAttribute("memes", allMemes);
        return "home";
    }
}
