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

import java.sql.ConnectionBuilder;
import java.time.LocalDateTime;
import java.util.List;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Recommendation {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private String id;

    private String type;

    @Column(length = 2000)
    private String recommendation;

    @JdbcTypeCode(SqlTypes.JSON)
    @Column(columnDefinition = "json")
    private List<String> safety;

    @JdbcTypeCode(SqlTypes.JSON)
    @Column(columnDefinition = "json")
    private List<String> improvements;

    @JdbcTypeCode(SqlTypes.JSON)
    @Column(columnDefinition = "json")
    private List<String> suggestions;

    @Column(name = "created_at")
    @CreationTimestamp
    private LocalDateTime createdAt;

    @Column(name = "updated_at")
    @UpdateTimestamp
    private LocalDateTime updatedAt;

    @ManyToOne(fetch = FetchType.LAZY) // lazy initialization so that even when the user is accessed the recommendation doesnt get fetched simultaneously thus reducing load on db
    @JoinColumn(name = "user_id",nullable = false, foreignKey = @ForeignKey(name = "fk_recommendation_user")) // // Creates a foreign key column named user_id
    @JsonIgnore // stops jackson's json serialisation for this field as this will cause an infinite loop.
    private User user;

    @ManyToOne(fetch = FetchType.LAZY)// lazy initialization so that even when the activity is accessed the recommendation doesnt get fetched simultaneously thus reducing load on db
    @JoinColumn(name = "activity_id",nullable = false, foreignKey = @ForeignKey(name = "fk_recommendation_activity")) // // Creates a foreign key column named user_id
    @JsonIgnore // stops jackson's json serialisation for this field as this will cause an infinite loop.
    private Activity activity;

}
