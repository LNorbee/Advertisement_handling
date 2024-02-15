package hu.flowacademy.ads.controller;

import hu.flowacademy.ads.model.Ad;
import hu.flowacademy.ads.model.User;
import hu.flowacademy.ads.service.AdService;
import hu.flowacademy.ads.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class WebController {

    @Autowired
    private UserService userService;
    @Autowired
    private AdService adService;

    @GetMapping("/")
    public String index(Model model){
        model.addAttribute("users", userService.listAllUser());
        return "mainPage";
    }

    @GetMapping("/user_registration")
    public String addCustomer(Model model) {
        User user = new User();
        model.addAttribute("user", user);
        return "userRegister_form";
    }

    @PostMapping("/user_registration")
    public String saveCustomer(@ModelAttribute("user") User user) {
        userService.addUser(user);
        return "redirect:/";
    }

    @GetMapping("/ad_registration")
    public String addAdvertisement(Model model) {
        Ad ad = new Ad();
        model.addAttribute("ad", ad);
        return "adRegister_form";
    }

    @PostMapping("/ad_registration")
    public String saveAdvertisement(@ModelAttribute("ad") Ad ad,
                                    @ModelAttribute("userName") String userName) {
        adService.createAdForUser(userName, ad);
        return "redirect:/";
    }
}
