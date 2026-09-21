package in.rishh.fitness_tracker.Repository;

import in.rishh.fitness_tracker.Entities.Role;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface RoleRepository extends JpaRepository<Role,Long> {

    Optional<Role> findByName(String name);
}
