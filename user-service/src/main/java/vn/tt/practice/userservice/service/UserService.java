package vn.tt.practice.userservice.service;

import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import vn.tt.practice.userservice.dto.UserDTO;
import vn.tt.practice.userservice.model.User;
import vn.tt.practice.userservice.repository.UserRepo;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepo userRepo;

    private final PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

    public User register(UserDTO userDTO) {
        if (userRepo.findByEmail(userDTO.getEmail()).isPresent()) {
            throw new IllegalArgumentException("Email already exists");
        }

        return userRepo.save(User.builder()
                .email(userDTO.getEmail())
                .password(passwordEncoder.encode(userDTO.getPassword()))
                .role(false)
                .build());

    }

    public UserDTO login(String email, String password) {
        Optional<User> user = userRepo.findByEmail(email);

        if (user.isEmpty() || !passwordEncoder.matches(password, user.get().getPassword())) {
            throw new RuntimeException("Username or password is incorrect");
        }
        return UserDTO.builder()
                .id(user.get().getId())
                .email(user.get().getEmail())
                .username(user.get().getEmail())
                .expirationDate(864000000)
//                .role(false)
                .build();
    }

//    public void logout(String email) {
//        ResponseCookie cookie = ResponseCookie.from("jwt","");
//    }




}
