package hu.flowacademy.ads.service;

import hu.flowacademy.ads.dto.AdsResponseDTO;
import hu.flowacademy.ads.exceptionHandler.NotFoundException;
import hu.flowacademy.ads.model.Ad;
import hu.flowacademy.ads.model.User;
import hu.flowacademy.ads.repository.AdRepository;
import hu.flowacademy.ads.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class AdService {
    @Autowired
    private AdRepository adRepository;

    @Autowired
    private UserRepository userRepository;

    public Ad createAdForUser(String userName, Ad ad) {
        Optional<User> existUser = userRepository.findById(userName);

        if (existUser.isPresent()) {
            User user = existUser.get();
            ad.setUser(user);
            ad.setCreationDate(LocalDate.now());
            return adRepository.save(ad);
        } else {
            throw new NotFoundException(String.format("User not found with this Username: %s", userName));
        }
    }

    public Ad modifyAd(Long id, Ad ad) {
        Optional<Ad> optionalAd = adRepository.findById(id);
        if (optionalAd.isPresent()) {

            Ad existAd = optionalAd.get();

            if (existAd.getCreationDate() == null) {
                existAd.setCreationDate(LocalDate.now());
            }

            existAd.setTitle(ad.getTitle());
            existAd.setPrice(ad.getPrice());
            existAd.setDescription(ad.getDescription());

            return adRepository.save(existAd);
        } else {
            throw new NotFoundException(String.format("Not found Ad with this Id: %s", id));

        }
    }

    public void deleteAd(Long id) {
        Optional<Ad> adOptional = adRepository.findById(id);
        adOptional.orElseThrow(() ->
                new NotFoundException(String.format("Not found Ad with this Id: %s", id))
        );
        adRepository.deleteById(id);
    }

    public List<Ad> listAdsByUserName(String userName) {
        Optional<List<Ad>> existUserWithAd = Optional.ofNullable(adRepository.findByUserUserName(userName));
        return existUserWithAd.orElseThrow(() ->
                new NotFoundException(String.format("User not found with this username: %s", userName))
        );
    }

    public Ad listAdById(Long id) {
        Optional<Ad> adOptional = adRepository.findById(id);
        return adOptional.orElseThrow(() ->
                new NotFoundException(String.format("Not found Ad with this Id: %s", id))
        );
    }

    public List<AdsResponseDTO> adList(){
        List<Ad> adList = adRepository.findAll();

        return adList.stream().map(ad -> {
            AdsResponseDTO adsResponseDTO = new AdsResponseDTO();
            adsResponseDTO.setId(ad.getId());
            adsResponseDTO.setTitle(ad.getTitle());
            adsResponseDTO.setPrice(ad.getPrice());
            adsResponseDTO.setDescription(ad.getDescription());
            adsResponseDTO.setCreationDate(ad.getCreationDate());
            adsResponseDTO.setUserName(ad.getUser().getUserName());
            return adsResponseDTO;
        }).collect(Collectors.toList());
    }

}
