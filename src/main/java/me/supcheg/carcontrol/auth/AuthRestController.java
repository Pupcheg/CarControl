package me.supcheg.carcontrol.auth;

import lombok.AllArgsConstructor;
import me.supcheg.carcontrol.entity.User;
import me.supcheg.carcontrol.repository.UserRepository;
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
@AllArgsConstructor
public class AuthRestController {
    private final UserRepository repository;
    private final JwtTokenUtil tokenUtil;
    private final PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

    @GetMapping("register")
    public String register(@RequestParam String username, @RequestParam String email, @RequestParam String password) {
        Optional<User> byEmail = repository.findByEmail(email);
        if (byEmail.isPresent()) {
            throw new IllegalArgumentException(email);
        }

        User user = new User(UUID.randomUUID(), username, email, passwordEncoder.encode(password));
        repository.save(user);
        return tokenUtil.generateToken(user.getUniqueId());
    }

    @GetMapping("login")
    public String login(@RequestParam String email, @RequestParam String password) {
        Optional<User> byEmail = repository.findByEmail(email);
        if (byEmail.isEmpty()) {
            throw new IllegalArgumentException(email);
        }

        User user = byEmail.get();

        if (!passwordEncoder.matches(password, user.getEncodedPassword())) {
            throw new IllegalArgumentException("password");
        }

        return tokenUtil.generateToken(user.getUniqueId());
    }
}
