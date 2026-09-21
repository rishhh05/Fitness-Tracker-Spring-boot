package in.rishh.fitness_tracker.Dto;

import in.rishh.fitness_tracker.Entities.ActivityType;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.Map;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ActivityRequest {
    private String userId;
    private Integer caloriesBurnt;
    private Integer duration;
    private LocalDateTime startTime;
    private ActivityType activityType;
    private Map<String,Object> additionalMetrics;
}
