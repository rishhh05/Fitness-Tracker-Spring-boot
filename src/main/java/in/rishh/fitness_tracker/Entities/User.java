package in.rishh.fitness_tracker.Entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder // allows to create User object using builder pattern. uses @AllArgsConstructor backstage.
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(name = "user_id")
    private String id;

    @Column(name = "first_name")
    private String firstName;

    @Column(name = "last_name")
    private String lastName;

    @Column(unique = true,nullable = false)
    private String email;

    @Column(name = "hashed_password")
    private String hashedPassword;

    @Column(name = "created_at")
    @CreationTimestamp // when the insert query is sent the timestamp is auto-captured in the DB
    private LocalDateTime createdAt;

    @Column(name = "updated_at")
    @UpdateTimestamp // when the insert/update query is sent the timestamp is auto-captured in the DB
    private LocalDateTime updatedAt;

    @ManyToMany
    @JoinTable(
            name = "user_roles",
            joinColumns = @JoinColumn(name = "user_id"),
            inverseJoinColumns = @JoinColumn(name = "role_id")
    )
    private Set<Role> roles = new HashSet<>(); //[ROLE_ADMIN, ROLE_USER]

    @OneToMany(
            mappedBy = "user", // other side of the relationship is mapped by the field named user.
            cascade = CascadeType.ALL,
            orphanRemoval = true
    )
    @JsonIgnore
    private List<Activity> activities = new ArrayList<>();

    @OneToMany(
            mappedBy = "user", // other side of the relationship is mapped by the field named user.
            cascade = CascadeType.ALL,
            orphanRemoval = true
    )
    @JsonIgnore
    private List<Recommendation> recommendations = new ArrayList<>();
}
