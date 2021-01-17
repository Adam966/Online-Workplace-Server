package com.kosickaakademia.onlineworkplaceserver.entities.workplaceelement;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.kosickaakademia.onlineworkplaceserver.entities.LabelEntity;
import lombok.Data;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "checklist")
@Data
public class CheckListEntity extends WorkplaceElementEntity {
    public CheckListEntity() {
        super();
    }

    @OneToMany(cascade = CascadeType.ALL)
    @JoinTable(
            name = "checklist_task",
            joinColumns = @JoinColumn(name = "checklist_id"),
            inverseJoinColumns = @JoinColumn(name = "task_id")
    )
    private List<TaskEntity> taskEntities;

    @JsonBackReference
    @ManyToMany(cascade = CascadeType.ALL)
    @JoinTable(
            name = "checklist_label",
            joinColumns = @JoinColumn(name = "checklist_id"),
            inverseJoinColumns = @JoinColumn(name = "thread_id"))
    private List<LabelEntity> assignedLabels;
}
