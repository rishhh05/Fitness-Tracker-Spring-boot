package in.rishh.fitness_tracker.Entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.JdbcTypeCode;
import org.hibernate.annotations.UpdateTimestamp;
import org.hibernate.type.SqlTypes;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Activity{

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private String id;

    @Column(name = "calories_burnt")
    private Integer caloriesBurnt;

    private Integer duration;

    @Column(name = "created_at")
    @CreationTimestamp
    private LocalDateTime createdAt;

    @Column(name = "updated_at")
    @UpdateTimestamp
    private LocalDateTime updatedAt;

    @Column(name = "start_time")
    private LocalDateTime startTime;

    @Enumerated(value = EnumType.STRING)
    private ActivityType type;

    @JdbcTypeCode(SqlTypes.JSON)
    @Column(columnDefinition = "json", name = "additional_metrics")
    private Map<String,Object> additionalMetrics;

    @ManyToOne(fetch = FetchType.LAZY) // lazy initialization so that even when the user is accessed the activity doesnt get fetched simultaneously thus reducing load on db
    @JoinColumn(name = "user_id",nullable = false, foreignKey = @ForeignKey(name = "fk_activity_user")) // // Creates a foreign key column named user_id
    @JsonIgnore // stops jackson's json serialisation for this field as this will cause an infinite loop.
    private User user;

    @OneToMany(mappedBy = "activity",cascade = CascadeType.ALL,orphanRemoval = true)
    private List<Recommendation> recommendations = new ArrayList<>();


}
