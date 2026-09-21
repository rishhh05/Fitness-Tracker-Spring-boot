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
public class ActivityResponse {
    private String activityId;
    private String userId;
    private ActivityType type;
    private LocalDateTime startTime;
    private Map<String, Object> additionalMetrics;
    private Integer duration;
    private String message;
}
