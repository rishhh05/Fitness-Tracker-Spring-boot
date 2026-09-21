package in.rishh.fitness_tracker.Services;

import in.rishh.fitness_tracker.Dto.RecommendationRequest;
import in.rishh.fitness_tracker.Entities.Activity;
import in.rishh.fitness_tracker.Entities.Recommendation;
import in.rishh.fitness_tracker.Entities.User;
import in.rishh.fitness_tracker.Repository.ActivityRepository;
import in.rishh.fitness_tracker.Repository.RecommendationRepository;
import in.rishh.fitness_tracker.Repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class RecommendationService {

    private final UserRepository userRepository;
    private final ActivityRepository activityRepository;
    private final RecommendationRepository recommendationRepository;

    public Recommendation generateRecommendation(RecommendationRequest request) {
        User user = userRepository.findById(request.getUserId())
                .orElseThrow(() -> new RuntimeException("User Not Found: " + request.getUserId()));

        Activity activity = activityRepository.findById(request.getActivityId())
                .orElseThrow(() -> new RuntimeException("Activity Not Found: " + request.getActivityId()));

        Recommendation recommendation = Recommendation.builder()
                .user(user)
                .activity(activity)
                .improvements(request.getImprovements())
                .suggestions(request.getSuggestions())
                .safety(request.getSafety())
                .build();

        return recommendationRepository.save(recommendation);
    }

    public List<Recommendation> getUserRecommendation(String userId) {
        return recommendationRepository.findByUserId(userId)
                .orElseThrow(()-> new RuntimeException("No Recommendation for UserId : "+ userId));
    }

    public List<Recommendation> getActivityRecommendation(String activityId) {
        return recommendationRepository.findByActivityId(activityId)
                .orElseThrow(()-> new RuntimeException("No Recommendation for ActivityId : "+ activityId));
    }
}
