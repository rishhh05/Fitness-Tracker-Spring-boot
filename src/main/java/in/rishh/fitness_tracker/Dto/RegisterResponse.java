package in.rishh.fitness_tracker.Dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class RegisterResponse {
    private String userId;
    private String firstName;
    private String lastName;
    private String email;
    private String message; //"user created !"
}
