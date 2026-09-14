package africa.semicolon.habitTracker.repository;

import africa.semicolon.habitTracker.model.User;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {
    boolean existsByEmail(@NotBlank @Email(message = "Invalid email format.") String email);
}
