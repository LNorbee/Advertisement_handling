package hu.flowacademy.ads.service;

import hu.flowacademy.ads.dto.UserResponseDTO;
import hu.flowacademy.ads.exceptionHandler.ForbiddenException;
import hu.flowacademy.ads.exceptionHandler.NotFoundException;
import hu.flowacademy.ads.model.User;
import hu.flowacademy.ads.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    public User addUser(User user) {
        if (!userIsExist(user.getUserName())) {
            if (user.getCreationDate() == null) {
                user.setCreationDate(LocalDate.now());
            }
            return userRepository.save(user);
        } else {
            throw new ForbiddenException(String.format("User already exists with this username: %s", user.getUserName()));
        }
    }

    public boolean userIsExist(String userName) {
        return userRepository.existsById(userName);
    }

    public User modifyUser(User user) {
        if (userIsExist(user.getUserName())) {
            if (user.getCreationDate() == null) {
                user.setCreationDate(LocalDate.now());
            }
            return userRepository.save(user);
        } else {
            throw new NotFoundException(String.format("User not found with this Username: %s", user.getUserName()));
        }
    }

    public void deleteUser(String userName) {
        if (userIsExist(userName)) {
            userRepository.deleteById(userName);
        } else {
            throw new NotFoundException(String.format("User not found with this Username: %s", userName));
        }
    }

    public UserResponseDTO listUserByUserName(String userName) {
        Optional<User> userOptional = userRepository.findById(userName);
        return userOptional.map(user -> {
            UserResponseDTO userResponseDTO = new UserResponseDTO();
            userResponseDTO.setUserName(user.getUserName());
            userResponseDTO.setFullName(user.getFullName());
            userResponseDTO.setCreationDate(user.getCreationDate());
            return userResponseDTO;
        }).orElseThrow(() ->
                new NotFoundException(String.format("User not found with this Username: %s", userName))
        );
    }

    public List<UserResponseDTO> listAllUser() {
        List<User> userList = userRepository.findAll();
        return userList.stream().map(user -> {
            UserResponseDTO userResponseDTO = new UserResponseDTO();
            userResponseDTO.setUserName(user.getUserName());
            userResponseDTO.setFullName(user.getFullName());
            userResponseDTO.setCreationDate(user.getCreationDate());
            return userResponseDTO;
        }).collect(Collectors.toList());
    }

}
