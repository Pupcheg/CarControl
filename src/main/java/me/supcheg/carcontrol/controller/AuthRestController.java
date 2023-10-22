package me.supcheg.carcontrol.controller;

import lombok.RequiredArgsConstructor;
import me.supcheg.carcontrol.service.UserService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/v1/auth")
@RequiredArgsConstructor
public class AuthRestController {
    private final UserService service;

    @GetMapping("register")
    public ResponseEntity<?> register(@RequestParam String username, @RequestParam String email, @RequestParam String password) {
        return service.register(username, email, password);
    }

    @GetMapping("login")
    public ResponseEntity<?> login(@RequestParam String email, @RequestParam String password) {
        return service.login(email, password);
    }
}
