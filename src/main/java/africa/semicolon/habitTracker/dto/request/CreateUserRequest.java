package africa.semicolon.habitTracker.dto.request;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;

public class CreateUserRequest {

    @NotBlank(message = "Username cannot be empty.")
    private String username;

    @NotBlank
    @Email(message = "Invalid email format.")
    private String email;

}
