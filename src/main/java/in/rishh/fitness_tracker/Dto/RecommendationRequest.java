package in.rishh.fitness_tracker.Dto;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.List;

@Data
@AllArgsConstructor
public class RecommendationRequest {
    private String userId;
    private String activityId;
    private List<String> improvements;
    private List<String> suggestions;
    private List<String> safety;
}
