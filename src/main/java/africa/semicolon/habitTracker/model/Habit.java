package africa.semicolon.habitTracker.model;

import jakarta.persistence.*;
import lombok.*;
import africa.semicolon.habitTracker.model.User;
import java.time.LocalDateTime;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "habits")
@Builder
public class Habit {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String name;

    @ManyToOne
    private User user;

    private LocalDateTime created_at = LocalDateTime.now();

    private String description;

    }