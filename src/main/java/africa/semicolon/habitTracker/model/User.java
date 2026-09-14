package africa.semicolon.habitTracker.model;


import javax.annotation.processing.Generated;

import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;

@Entity

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
