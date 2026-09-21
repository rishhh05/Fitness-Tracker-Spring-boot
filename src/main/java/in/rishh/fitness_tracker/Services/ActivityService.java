package in.rishh.fitness_tracker.Services;

import in.rishh.fitness_tracker.Dto.ActivityRequest;
import in.rishh.fitness_tracker.Dto.ActivityResponse;
import in.rishh.fitness_tracker.Repository.ActivityRepository;
import in.rishh.fitness_tracker.Entities.Activity;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ActivityService {
    private final ActivityRepository activityRepository;
    private final UserService userService;

    public ActivityResponse createActivity(String userId, ActivityRequest activityRequest){

        Activity activity = Activity.builder()
                .user(userService.findUser(userId))
                .caloriesBurnt(activityRequest.getCaloriesBurnt())
                .duration(activityRequest.getDuration())
                .startTime(activityRequest.getStartTime())
                .type(activityRequest.getActivityType())
                .additionalMetrics(activityRequest.getAdditionalMetrics())
                .build();

        Activity activity1 = activityRepository.save(activity) ;
        return mapToResponse(activity1);
    }
    public List<ActivityResponse> getUserActivites(String userId){
        List<Activity> activityList = activityRepository.findByUserId(userId).orElseThrow(()-> new RuntimeException("No Activity exist for the UserId"+ userId));
        return activityList
                .stream()
                .map(activity -> mapToResponse(activity))
                .collect(Collectors.toList());
    }
    // utility function
    private ActivityResponse mapToResponse(Activity activity){
        ActivityResponse activityResponse = new ActivityResponse();

        activityResponse.setUserId(activity.getUser().getId());
        activityResponse.setActivityId(activity.getUser().getId());
        activityResponse.setType(activity.getType());
        activityResponse.setStartTime(activity.getStartTime());
        activityResponse.setDuration(activity.getDuration());
        activityResponse.setAdditionalMetrics(activity.getAdditionalMetrics());
        activityResponse.setMessage("Activity Created !");

        return activityResponse;
    }
}
