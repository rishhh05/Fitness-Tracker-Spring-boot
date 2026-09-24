package in.rishh.fitness_tracker.Services;

import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.Authentication;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.security.oauth2.jwt.JwtClaimsSet;
import org.springframework.security.oauth2.jwt.JwtEncoder;
import org.springframework.security.oauth2.jwt.JwtEncoderParameters;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.util.List;

@Data
@RequiredArgsConstructor
@Service
public class JwtService {

    private final JwtEncoder jwtEncoder; // our custom bean will be injected

    @Value("${jwt.issuer}")
    private String issuer;

    private Long jwtExpiry = 172800L; // 48hrs -> 48*60*60 seconds

    public String generateToken(
            Authentication authentication
    ){
        Instant now = Instant.now();
        List<String> authorities = authentication.getAuthorities()
                .stream()
                .map(role -> role.getAuthority())
                .toList();

        JwtClaimsSet claims = JwtClaimsSet.builder()
                .issuer(issuer)
                .issuedAt(now)
                .expiresAt(now.plusSeconds(jwtExpiry))
                .subject(authentication.getName()) // fetches name from Principal
                .claim("roles", authorities)
                .build();

        Jwt jwt = jwtEncoder.encode(JwtEncoderParameters.from(claims));

        return jwt.getTokenValue();
    }
}
