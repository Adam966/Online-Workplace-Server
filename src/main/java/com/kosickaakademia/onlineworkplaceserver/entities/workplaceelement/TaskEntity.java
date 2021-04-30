package com.kosickaakademia.onlineworkplaceserver.entities.workplaceelement;
import com.kosickaakademia.onlineworkplaceserver.entities.UserEntity;
import lombok.Data;
import javax.persistence.*;
import java.util.List;

@Entity()
@Table(name = "task")
@Data
public class TaskEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String description;
    private boolean isCompleted;

    @OneToMany(cascade = CascadeType.ALL)
    @JoinTable(
            name = "task_user",
            joinColumns = @JoinColumn(name = "task_id"),
            inverseJoinColumns = @JoinColumn(name = "user_id"))
    private List<UserEntity> assignedUsers;
}
