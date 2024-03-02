package hu.flowacademy.ads.controller;

import hu.flowacademy.ads.dto.UserResponseDTO;
import hu.flowacademy.ads.model.Ad;
import hu.flowacademy.ads.repository.AdRepository;
import hu.flowacademy.ads.service.StaticsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/statics")
public class StaticsController {

    @Autowired
    private AdRepository adRepository;

    @Autowired
    private StaticsService staticsService;

    @GetMapping
    @RequestMapping("/price-greater-than/{price}")
    public List<Ad> priceGreaterThan(@PathVariable Integer price){
        return adRepository.findByPriceGreaterThan(price);
    }

    @GetMapping
    @RequestMapping("/price-greater-than-4000")
    public List<Ad> priceGreaterThan4000(){
        return adRepository.findByPriceGreaterThan(4000);
    }

    @GetMapping
    @RequestMapping("/price-less-than/{price}")
    public List<Ad> priceLessThan(@PathVariable Integer price){
        return adRepository.findByPriceLessThan(price);
    }

    @GetMapping
    @RequestMapping("/price-less-than-3000")
    public List<Ad> priceLessThan3000(){
        return adRepository.findByPriceLessThan(3000);
    }

    /**
     * A teljes nevet meg kell adni.
     * Hozzá tartozó Ad nélkül listáz
     * @param fullName
     * @return User List
     */
    @GetMapping
    @RequestMapping("/name/{fullName}")
    public List<UserResponseDTO> findNameLike(@PathVariable String fullName){
        return staticsService.userWithoutAd(fullName);
    }

    /**
     * A teljes név egy részlete alapján keres.
     * Hozzá tartozó Ad nélkül listáz
     * @param name
     * @return User List
     */
    @GetMapping
    @RequestMapping("/name-contains/{name}")
    public List<UserResponseDTO> findNameContains(@PathVariable String name){
        return staticsService.userListWithoutAd(name);
    }

    @GetMapping
    @RequestMapping("/ad-title/{title}")
    public List<Ad> findAdByTitle(@PathVariable String title){
        return adRepository.findByTitleContaining(title);
    }


}
