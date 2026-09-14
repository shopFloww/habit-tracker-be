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

@Service
@RequiredArgsConstructor
public class UserService {
    private final UserRepository userRepository;

    @Transactional
    public UserResponse createUser(CreateUserRequest request) {
        if (userRepository.existsByEmail(request.email())) {
            throw new DuplicateEmailException("Email already exists: " + request.email());
        }

        try {
            User user = new User(request.name(), request.email());
            User saved = userRepository.save(user);
            return new UserResponse(saved.getId(), saved.getName(), saved.getEmail());
        } catch (DataIntegrityViolationException ex) {
            throw new DuplicateEmailException("Email already exists: " + request.email());
        }
    }


    @Transactional(readOnly = true)
    public UserResponse getUserById(Long id) {
        return userRepository.findById(id)
                .map(user -> new UserResponse(user.getId(), user.getName(), user.getEmail()))
                .orElseThrow(() -> new UserNotFoundException("User not found with id: " + id));
    }


}