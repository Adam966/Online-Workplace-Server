package com.kosickaakademia.onlineworkplaceserver.entities.workplaceelement;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.kosickaakademia.onlineworkplaceserver.entities.LabelEntity;
import com.kosickaakademia.onlineworkplaceserver.entities.UserEntity;
import com.kosickaakademia.onlineworkplaceserver.entities.WorkplaceEntity;
import lombok.Data;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "thread")
@Data
public class ThreadEntity extends WorkplaceElementEntity {
    private String description;

    public ThreadEntity() {
        super();
    }

    @ManyToMany(cascade = CascadeType.ALL)
    @JoinTable(
            name = "thread_label",
            joinColumns = @JoinColumn(name = "label_id"),
            inverseJoinColumns = @JoinColumn(name = "thread_id"))
    private List<LabelEntity> assignedLabels;

    @ManyToMany(cascade = CascadeType.ALL)
    @JoinTable(
            name = "thread_user",
            joinColumns = @JoinColumn(name = "user_id"),
            inverseJoinColumns = @JoinColumn(name = "thread_id"))
    private List<UserEntity> assignedUsers;
}
