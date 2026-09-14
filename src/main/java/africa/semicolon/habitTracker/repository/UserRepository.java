package africa.semicolon.habitTracker.repository;

import africa.semicolon.habitTracker.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {


}
