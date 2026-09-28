package africa.semicolon.habitTracker.dtos;

import africa.semicolon.habitTracker.model.User;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Setter
@Getter
public class HabitResponseDto {
    private Long id;

    private String name;

    private User user;

    private LocalDateTime created_at = LocalDateTime.now();

    private String description;

}
