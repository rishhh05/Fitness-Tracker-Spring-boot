package in.rishh.fitness_tracker.Repository;

import in.rishh.fitness_tracker.Entities.Recommendation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface RecommendationRepository extends JpaRepository<Recommendation,String> {

    Optional<List<Recommendation>> findByUserId(String userId);

    Optional<List<Recommendation>> findByActivityId(String activityId);
}
