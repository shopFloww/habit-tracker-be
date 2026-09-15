package africa.semicolon.habitTracker.model;

import jakarta.persistence.*;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.antlr.v4.runtime.misc.NotNull;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
    public class User {
        @Id
        @GeneratedValue(strategy= GenerationType.UUID)
        @Column(name="user_id")
        private String userId;

        @Column(name="userName")
        @NotNull
        private String name;

         @NotBlank
        @Email
         @Column(name="email")
        private String email;










}
