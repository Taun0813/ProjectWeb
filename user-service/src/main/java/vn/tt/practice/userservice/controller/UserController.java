package vn.tt.practice.userservice.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import vn.tt.practice.userservice.dto.UserDTO;
import vn.tt.practice.userservice.model.User;
import vn.tt.practice.userservice.repository.UserRepo;
import vn.tt.practice.userservice.service.UserService;
import vn.tt.practice.userservice.util.JWTUtil;


import java.time.Duration;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@Controller
@RequiredArgsConstructor
@RestController
@RequestMapping("/v1/api/user")
//@CrossOrigin(origins = "*")
@Slf4j
public class UserController {

    private final UserService userService;
    private final UserRepo userRepo;
    private final JWTUtil jwtUtil;
    private final StringRedisTemplate redisTemplate;


    @GetMapping("/{id}")
    public ResponseEntity<Optional<User>> getUser(@PathVariable String id) {
        return ResponseEntity.ok(userRepo.findById(id));
    }

    @GetMapping
    public ResponseEntity<List<User>> getUsers() {
        return ResponseEntity.ok(userRepo.findAll());
    }

    @PostMapping("/register")
    public ResponseEntity<String> registerUser(@Valid @RequestBody UserDTO userDTO) {
        try {
            return ResponseEntity.ok("Register successful! ID: " + userService.register(userDTO).getId());
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @PostMapping("/login")
    public ResponseEntity<?> loginUser(@RequestBody UserDTO userDTO) {

        try {
            String token = jwtUtil.generateToken(userDTO);
            redisTemplate.opsForValue().set(token, "valid", Duration.ofHours(1));
            System.out.println(token);
            Map<String, Object> response = new HashMap<>();
            response.put("user", userService.login(userDTO.getEmail(), userDTO.getPassword()));
            response.put("token", token);
            return ResponseEntity.ok(response);
        } catch (BadCredentialsException ex) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED)
                    .body(Map.of("error", "Invalid email or password"));
        } catch (Exception ex) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(Map.of("error", "Login failed"));
        }
    }

    @GetMapping("/logout")
    public ResponseEntity<String> logoutUser(@RequestHeader("Authorization") String authHeader) {
        try {
            String token = authHeader.replace("Bearer ", "");

            if (!Boolean.TRUE.equals(redisTemplate.hasKey(token))) {
                return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Token invalid or already logged out");
            }
            // Optional: lấy user để ghi log
            String email = jwtUtil.extractUsername(token);
            System.out.println("Logging out user: " + email);

            redisTemplate.delete(token);
            return ResponseEntity.ok("Logout successful");

        } catch (Exception ex) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Logout failed");
        }
    }

}
