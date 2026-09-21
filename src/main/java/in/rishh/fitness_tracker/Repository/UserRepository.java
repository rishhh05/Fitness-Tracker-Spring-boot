package in.rishh.fitness_tracker.Repository;

import in.rishh.fitness_tracker.Entities.User;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User,String> {

    @EntityGraph(attributePaths = "roles")
    Optional<User> findByEmail(String email);
}
