package hu.flowacademy.ads.controller;

import hu.flowacademy.ads.dto.UserResponseDTO;
import hu.flowacademy.ads.model.User;
import hu.flowacademy.ads.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserService userService;

    @PostMapping
    @RequestMapping("/add")
    @ResponseStatus(HttpStatus.CREATED)
    public User addUser(@RequestBody User user) {
        return userService.addUser(user);
    }

    @PatchMapping
    @RequestMapping("/modify")
    public User modifyUser(@RequestBody User user) {
        return userService.modifyUser(user);
    }

    @DeleteMapping
    @RequestMapping("/{userName}/delete")
    @ResponseStatus(HttpStatus.ACCEPTED)
    public void deleteUserByUserName(@PathVariable String userName){
        userService.deleteUser(userName);
    }

    /**
     * Give back the Users without Ads.
     * @return List<UserResponseDTO>
     */
    @GetMapping
    @RequestMapping("/list")
    public List<UserResponseDTO> listAllUser(){
        return userService.listAllUser();
    }

    /**
     * Give back the User with the given userName, and with its Ads.
     * @param userName
     * @return User
     */
    @GetMapping
    @RequestMapping("/{userName}/list")
    public UserResponseDTO userByUserName(@PathVariable String userName){
        return userService.listUserByUserName(userName);
    }

}
