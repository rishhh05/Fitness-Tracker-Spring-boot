package in.rishh.fitness_tracker.exceptionHandlers;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import javax.management.relation.RoleNotFoundException;
import java.util.HashMap;
import java.util.Map;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(BadCredentialsException.class)
    public ResponseEntity<Map<String,String>> handleBadCredentials(BadCredentialsException ex){
        Map<String,String> errors = new HashMap<>();
            errors.put("error","Unauthorized");
            errors.put("message", ex.getMessage());

        return ResponseEntity
                .status(HttpStatus.UNAUTHORIZED)
                .body(errors);
    }
    @ExceptionHandler(RuntimeException.class)
    public ResponseEntity<Map<String,String>> handleRuntimeExceptions(RuntimeException ex){
        Map<String,String> errors = new HashMap<>();
            errors.put("error","Not Found");
            errors.put("message", ex.getMessage());

        return ResponseEntity
                .status(HttpStatus.NOT_FOUND)
                .body(errors);
    }
}
