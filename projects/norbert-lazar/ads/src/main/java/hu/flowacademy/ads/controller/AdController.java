package hu.flowacademy.ads.controller;

import hu.flowacademy.ads.model.Ad;
import hu.flowacademy.ads.service.AdService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/ad")
public class AdController {

    @Autowired
    private AdService adService;

    @PostMapping
    @RequestMapping("/add/{userName}")
    public Ad createAdForUser(@PathVariable String userName,
                              @RequestBody Ad ad){
        return adService.createAdForUser(userName, ad);
    }

    @PatchMapping
    @RequestMapping("/{id}/modify")
    public Ad modifyAd(@PathVariable Long id,
                       @RequestBody Ad ad){
        return adService.modifyAd(id, ad);
    }

    @DeleteMapping
    @RequestMapping("/{id}/delete")
    public void deleteAd(@PathVariable Long id){
        adService.deleteAd(id);
    }

    @GetMapping
    @RequestMapping("/{userName}/list")
    public List<Ad> listAdsByUserName(@PathVariable String userName){
        return adService.listAdsByUserName(userName);
    }

    @GetMapping
    @RequestMapping("/{id}")
    public Ad listAdById(@PathVariable Long id){
        return adService.listAdById(id);
    }
}
