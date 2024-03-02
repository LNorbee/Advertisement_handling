package hu.flowacademy.ads.repository;

import hu.flowacademy.ads.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface UserRepository extends JpaRepository<User, String> {

    List<User> findByFullNameLike(String fullName);

    List<User> findByFullNameContaining(String name);

    boolean existsById(String userName);
}
