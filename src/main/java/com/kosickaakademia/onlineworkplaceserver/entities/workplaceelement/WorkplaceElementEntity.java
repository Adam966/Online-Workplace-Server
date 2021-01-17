package com.kosickaakademia.onlineworkplaceserver.entities.workplaceelement;

import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonTypeInfo;
import com.kosickaakademia.onlineworkplaceserver.entities.WorkplaceEntity;
import lombok.Data;

import javax.persistence.*;

@MappedSuperclass
@JsonTypeInfo(use = JsonTypeInfo.Id.NAME, property = "type")
@JsonSubTypes({
    @JsonSubTypes.Type(value = NoteEntity.class, name = "note"),
    @JsonSubTypes.Type(value = ThreadEntity.class, name = "thread"),
    @JsonSubTypes.Type(value = CheckListEntity.class, name = "checklist")
})
@Data
public abstract class WorkplaceElementEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    protected Long id;

    @ManyToOne()
    protected WorkplaceEntity workplaceEntity;

    protected String name;
}
