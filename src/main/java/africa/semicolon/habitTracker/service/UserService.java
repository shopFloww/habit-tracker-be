package africa.semicolon.habitTracker.service;

import africa.semicolon.habitTracker.dto.request.CreateUserRequest;
import africa.semicolon.habitTracker.dto.response.UserResponse;
import africa.semicolon.habitTracker.exceptions.DuplicateEmailException;
import africa.semicolon.habitTracker.exceptions.UserNotFoundException;
import africa.semicolon.habitTracker.model.User;
import africa.semicolon.habitTracker.repository.UserRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class UserService {
    private final UserRepository userRepository;

    @Transactional
    public UserResponse createUser(CreateUserRequest request) {

        if (userRepository.existsByEmail(request.getEmail())) {
            throw new DuplicateEmailException("Email already exists: " + request.getEmail());
        }


            User user = new User();
            user.setName(request.getUsername());
            user.setEmail(request.getEmail());

            User saved = userRepository.save(user);

            return new UserResponse(
                    saved.getUserId(),
                    saved.getName(),
                    saved.getEmail()
            );

    }


    @Transactional
    public UserResponse getUserById(String id) {
        return userRepository.findById(id)
                .map(user -> new UserResponse(user.getUserId(), user.getName(), user.getEmail()))
                .orElseThrow(() -> new UserNotFoundException("User not found with id: " + id));
    }


    @Transactional
    public List<UserResponse> getAllUsers() {
        return userRepository.findAll()
                .stream()
                .map(user -> new UserResponse(
                        user.getUserId(),
                        user.getName(),
                        user.getEmail()
                ))
                .toList();
    }
}