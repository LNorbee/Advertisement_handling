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
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;

@Controller
public class WebController {

    @Autowired
    private UserService userService;
    @Autowired
    private AdService adService;

    @GetMapping("/")
    public String index(Model model){
        model.addAttribute("users", userService.listAllUser());
        return "index";
    }

    //---------------USER----------------//

    //LIST----//
    @GetMapping("/users")
    public String users(Model model){
        model.addAttribute("users", userService.listAllUser());
        return "users";
    }

    //REGISTRATION-----//
    @GetMapping("/user_registration")
    public String addCustomer(Model model) {
        User user = new User();
        model.addAttribute("user", user);
        return "userRegister_form";
    }

    @PostMapping("/user_registration")
    public String saveCustomer(@ModelAttribute("user") User user) {
        userService.addUser(user);
        return "redirect:/users";
    }

    //EDIT-----//
    @GetMapping("/user_modify")
    public String modifyUser(Model model){
        User user = new User();
        model.addAttribute("user", user);
        return "userModify_form";
    }

    @PostMapping("/user_modify")
    public String modifyUser(@ModelAttribute("user") User user) {
        userService.modifyUser(user);
        return "redirect:/users";
    }

    //DELETE----//
    @PostMapping("/user_delete")
    public String deleteUser(@ModelAttribute("userName") String userName) {
        userService.deleteUser(userName);
        return "redirect:/users";
    }

    //---------------ADS----------------//

    //SAVE----//
    @GetMapping("/ad_registration/{userName}")
    public String addAdvertisement(Model model,
                                   @PathVariable("userName") String userName) {
        Ad ad = new Ad();
        model.addAttribute("ad", ad);
        model.addAttribute("userName", userName);
        return "adRegister_form";
    }

    @PostMapping("/ad_registration")
    public String saveAdvertisement(@ModelAttribute("ad") Ad ad,
                                    @ModelAttribute("userName") String userName) {
        adService.createAdForUser(userName, ad);
        return "redirect:/users";
    }

    //LIST BY USERNAME-----//
    @GetMapping("/advertisements/{userName}")
    public String listAdvertisement(@PathVariable String userName,
                                    Model model) {
        model.addAttribute("adList", adService.listAdsByUserName(userName));
        return "advertisements";

    }

    //LIST ALL ADS-----//
    @GetMapping("/advertisements_all")
    public String adsResponseDTOList(Model model){
        model.addAttribute("adList", adService.adList());
        return "advertisements_all";
    }

    //EDIT----//
    @GetMapping("/ad_modify/{id}")
    public String editAdvertisement(Model model,
                                   @PathVariable("id") Long id) {
        Ad ad = new Ad();
        model.addAttribute("ad", ad);
        model.addAttribute("id", id);
        return "adModify_form";
    }

    @PostMapping("/ad_modify")
    public String editAdvertisement(@ModelAttribute("ad") Ad ad,
                                    @ModelAttribute("id") Long id) {
        adService.modifyAd(id, ad);
        return "redirect:/users";
    }

    //DELETE----//
    @PostMapping("/ad_delete")
    public String deleteAd(@ModelAttribute("id") Long id,
                           @ModelAttribute("userName") String userName) {
        adService.deleteAd(id);
        return "redirect:/users";
//        return "redirect:/advertisements/{" + userName + "}";
    }
}
