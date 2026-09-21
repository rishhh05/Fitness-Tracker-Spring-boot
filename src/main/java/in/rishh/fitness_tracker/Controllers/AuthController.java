package in.rishh.fitness_tracker.Controllers;

import in.rishh.fitness_tracker.Dto.LoginRequest;
import in.rishh.fitness_tracker.Dto.LoginResponse;
import in.rishh.fitness_tracker.Dto.RegisterRequest;
import in.rishh.fitness_tracker.Dto.RegisterResponse;
import in.rishh.fitness_tracker.Services.JwtService;
import in.rishh.fitness_tracker.Services.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.management.relation.RoleNotFoundException;

@RestController
@RequestMapping("api/auth")
@RequiredArgsConstructor // lombok annotation for constructor injection
public class AuthController {
    private final UserService userService;
    private final JwtService jwtService;
    private final AuthenticationManager authenticationManager;

    @PostMapping("/register")
    public ResponseEntity<RegisterResponse> register(@RequestBody RegisterRequest registerRequest) throws RoleNotFoundException {
        RegisterResponse registerResponse = userService.register(registerRequest);
        return ResponseEntity.ok(registerResponse);
    }

    @PostMapping("/login")
    public ResponseEntity<LoginResponse> login(@RequestBody LoginRequest loginRequest){
        Authentication authenticationRequest = UsernamePasswordAuthenticationToken.unauthenticated(
                loginRequest.getEmail(),
                loginRequest.getPassword()
        );
        Authentication authenticationObj = authenticationManager.authenticate(authenticationRequest);

        String token = jwtService.generateToken(authenticationObj);

        LoginResponse loginResponse = new LoginResponse(token);

        return ResponseEntity.ok(loginResponse);
    }
}
