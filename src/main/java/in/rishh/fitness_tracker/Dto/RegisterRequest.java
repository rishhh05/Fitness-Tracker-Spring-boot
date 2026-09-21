package in.rishh.fitness_tracker.Dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class RegisterRequest {
    @NotBlank(message = "Email is mandatory")
    @Email(message = "Invalid Email")
    private String email;

    @NotBlank(message = "Password is mandatory")
    private String Password;
    private String firstName;
    private String lastName;
}
