package hu.flowacademy.ads.service;

import hu.flowacademy.ads.dto.UserResponseDTO;
import hu.flowacademy.ads.model.User;
import hu.flowacademy.ads.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class StaticsService {

    @Autowired
    private UserRepository userRepository;

    public List<UserResponseDTO> userListWithoutAd(String name) {
        List<User> userList = userRepository.findByFullNameContaining(name);

        List<UserResponseDTO> userResponseDTOList =
                userList.stream().map(user -> {
                    UserResponseDTO userResponseDTO = new UserResponseDTO();
                    userResponseDTO.setUserName(user.getUserName());
                    userResponseDTO.setFullName(user.getFullName());
                    userResponseDTO.setCreationDate(user.getCreationDate());
                    return userResponseDTO;
                }).toList();

        return userResponseDTOList;
    }

    public List<UserResponseDTO> userWithoutAd(String fullName) {
        List<User> userList = userRepository.findByFullNameLike(fullName);

        List<UserResponseDTO> userResponseDTOList =
                userList.stream().map(user -> {
                    UserResponseDTO userResponseDTO = new UserResponseDTO();
                    userResponseDTO.setUserName(user.getUserName());
                    userResponseDTO.setFullName(user.getFullName());
                    userResponseDTO.setCreationDate(user.getCreationDate());
                    return userResponseDTO;
                }).toList();

        return userResponseDTOList;
    }

}
