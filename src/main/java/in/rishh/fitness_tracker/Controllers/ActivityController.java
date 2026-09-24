package in.rishh.fitness_tracker.Controllers;

import in.rishh.fitness_tracker.Dto.ActivityRequest;
import in.rishh.fitness_tracker.Dto.ActivityResponse;
import in.rishh.fitness_tracker.Services.ActivityService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/activities")
@RequiredArgsConstructor
public class ActivityController {
    private final ActivityService activityService;

    @PostMapping
    public ResponseEntity<ActivityResponse> createActivity(@RequestBody ActivityRequest activityRequest){
        ActivityResponse activityResp = activityService.createActivity(activityRequest.getUserId(),activityRequest);
        return ResponseEntity.ok(activityResp);
    }

    @GetMapping // (activities/?id=xxx)
    public ResponseEntity<List<ActivityResponse>> getAllActivities(@RequestParam(name = "id") String userId ){
        List<ActivityResponse> activityResponse = activityService.getUserActivites(userId);
        return ResponseEntity.ok(activityResponse);
    }
}
