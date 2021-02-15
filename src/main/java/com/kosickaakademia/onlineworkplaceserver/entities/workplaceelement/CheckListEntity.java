package com.kosickaakademia.onlineworkplaceserver.entities.workplaceelement;

import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.*;
import java.util.List;

@EqualsAndHashCode(callSuper = true)
@Entity()
@DiscriminatorValue("checkList")
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
}
