package me.supcheg.carcontrol.service;

import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import me.supcheg.carcontrol.auth.JwtTokenUtil;
import me.supcheg.carcontrol.entity.User;
import me.supcheg.carcontrol.repository.UserRepository;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Optional;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class UserService {
    private final UserRepository repository;
    private final JwtTokenUtil tokenUtil;
    private final PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

    public String register(@RequestParam String username, @RequestParam String email, @RequestParam String password) {
        Optional<User> byEmail = repository.findByEmail(email);
        if (byEmail.isPresent()) {
            throw new IllegalStateException("User with email=" + email + " already registered");
        }

        User user = new User(UUID.randomUUID(), username, email, passwordEncoder.encode(password));
        repository.save(user);
        return tokenUtil.generateToken(user.getUniqueId());
    }

    public String login(@RequestParam String email, @RequestParam String password) {
        Optional<User> byEmail = repository.findByEmail(email);
        if (byEmail.isEmpty()) {
            throw new EntityNotFoundException("User with email=" + email + " not found");
        }

        User user = byEmail.get();

        if (!passwordEncoder.matches(password, user.getEncodedPassword())) {
            throw new IllegalArgumentException("password");
        }

        return tokenUtil.generateToken(user.getUniqueId());
    }
}
