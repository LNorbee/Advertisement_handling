package hu.flowacademy.ads;

import hu.flowacademy.ads.model.Ad;
import hu.flowacademy.ads.model.User;
import hu.flowacademy.ads.repository.AdRepository;
import hu.flowacademy.ads.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cglib.core.Local;
import org.springframework.context.annotation.Bean;

import java.time.LocalDate;

@SpringBootApplication
public class AdsApplication {

	@Autowired
	private UserRepository userRepository;

	@Autowired
	private AdRepository adRepository;

	public static void main(String[] args) {
		SpringApplication.run(AdsApplication.class, args);
	}

	@Bean
	public CommandLineRunner runner (){
		return args -> {
			if(userRepository.findAll().isEmpty()){
				User user = new User("kaba", "Kasza Blanka", LocalDate.of(2024, 2, 3));
				userRepository.save(user);

				User user1 = new User("probi", "Proba Robert", LocalDate.now());
				userRepository.save(user1);

				Ad ad = new Ad("Ruha", 1500, "baba body", user, LocalDate.now());
				adRepository.save(ad);
			}
		};
	}

}
