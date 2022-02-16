package rtuitlab.auth.controller;

import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;
import rtuitlab.auth.models.dto.UserRegisterModel;
import rtuitlab.auth.models.jpa.User;
import rtuitlab.auth.repositories.RoleRepository;
import rtuitlab.auth.services.UserService;

import java.util.List;

@RestController
@AllArgsConstructor
public class AuthController {
    private UserService userService;
    private RoleRepository roleRepository;

    @PostMapping("/register")
    public List<User> register(@RequestBody UserRegisterModel userRegisterModel) {
        userService.create(new User(0, userRegisterModel.getUsername(), userRegisterModel.getPassword(), roleRepository.findByName(userRegisterModel.getRole())));
        return userService.findAll();
    }
}
