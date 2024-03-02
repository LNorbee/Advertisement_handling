package hu.flowacademy.ads.repository;


import hu.flowacademy.ads.model.Ad;
import hu.flowacademy.ads.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface AdRepository extends JpaRepository<Ad, Long> {

    List<Ad> findByUserUserName(String userName);

    List<Ad> findByPriceGreaterThan(Integer price);

    List<Ad> findByPriceLessThan(Integer price);

    List<Ad> findByTitleContaining(String title);

}
