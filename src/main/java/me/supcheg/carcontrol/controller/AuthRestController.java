package me.supcheg.carcontrol.controller;

import lombok.RequiredArgsConstructor;
import me.supcheg.carcontrol.auth.JwtTokenUtil;
import me.supcheg.carcontrol.entity.User;
import me.supcheg.carcontrol.repository.UserRepository;
import me.supcheg.carcontrol.service.UserService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Optional;
import java.util.UUID;

@RestController
@RequestMapping("api/v1/auth")
@RequiredArgsConstructor
public class AuthRestController {
    private final UserService service;

    @GetMapping("register")
    public String register(@RequestParam String username, @RequestParam String email, @RequestParam String password) {
        return service.register(username, email, password);
    }

    @GetMapping("login")
    public String login(@RequestParam String email, @RequestParam String password) {
        return service.login(email, password);
    }
}
