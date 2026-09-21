package in.rishh.fitness_tracker.Repository;

import in.rishh.fitness_tracker.Dto.ActivityResponse;
import in.rishh.fitness_tracker.Entities.Activity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ActivityRepository extends JpaRepository<Activity,String> {


    Optional<List<Activity>> findByUserId (String userId);
}
