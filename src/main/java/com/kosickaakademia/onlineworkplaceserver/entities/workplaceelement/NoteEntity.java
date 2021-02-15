package com.kosickaakademia.onlineworkplaceserver.entities.workplaceelement;

import lombok.*;

import javax.persistence.*;
import java.util.Date;

@EqualsAndHashCode(callSuper = true)
@Entity
@DiscriminatorValue("note")
@Data
public class NoteEntity extends WorkplaceElementEntity {
    private String description;
    private Date dueDate;
}
