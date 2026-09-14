package africa.semicolon.habitTracker.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.*;

@Entity
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class User {
    @Id
    @GeneratedValue(strategy=GenerationType.UUID)
    @Column(name="user_id")
    private String userId;

    @Column(name="userName")
    @NotBlank

    private String name;

    @Column(name="email")
    @NotBlank
    @Email
    private String email;


}
