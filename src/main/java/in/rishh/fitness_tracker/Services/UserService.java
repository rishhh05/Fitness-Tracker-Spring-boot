package in.rishh.fitness_tracker.Services;

import in.rishh.fitness_tracker.Dto.RegisterRequest;
import in.rishh.fitness_tracker.Dto.RegisterResponse;
import in.rishh.fitness_tracker.Entities.Role;
import in.rishh.fitness_tracker.Entities.User;
import in.rishh.fitness_tracker.Repository.RoleRepository;
import in.rishh.fitness_tracker.Repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import javax.management.relation.RoleNotFoundException;
import java.util.HashSet;

@Service
@RequiredArgsConstructor
public class UserService {
    private final UserRepository userRepository;
    private final RoleRepository roleRepository;
    private final PasswordEncoder passwordEncoder;

    public RegisterResponse register(RegisterRequest registerRequest) {
        String hashedPassword = passwordEncoder.encode(registerRequest.getPassword());
        User user = User.builder()
                .email(registerRequest.getEmail())
                .firstName(registerRequest.getFirstName())
                .lastName(registerRequest.getLastName())
                .hashedPassword(hashedPassword)
                .roles(new HashSet<>())
                .build();

        Role role = roleRepository.findByName("ROLE_USER")
                .orElseThrow( () -> new RuntimeException("Given Role doesnt Exist in the DB"));
        user.getRoles().add(role); // by default all the users will be given role of user

        User user1 = userRepository.save(user);

        return mapToResponse(user1);
    }

    public User findUser(String userId){
            User user = userRepository.findById(userId)
                    .orElseThrow(()-> new RuntimeException("Invalid User"));
            return user;
    }
    // utility function
    private RegisterResponse mapToResponse(User user){
        RegisterResponse registerResponse = new RegisterResponse();

        registerResponse.setUserId(user.getId());
        registerResponse.setEmail(user.getEmail());
        registerResponse.setFirstName(user.getFirstName());
        registerResponse.setLastName(user.getLastName());
        registerResponse.setMessage("User Created!");

        return registerResponse;
    }
}
