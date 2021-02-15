package com.kosickaakademia.onlineworkplaceserver.entities.workplaceelement;

import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.*;

@EqualsAndHashCode(callSuper = true)
@Entity
@DiscriminatorValue("thread")
@Data
public class ThreadEntity extends WorkplaceElementEntity {
    private String description;

    public ThreadEntity() {
        super();
    }
}
