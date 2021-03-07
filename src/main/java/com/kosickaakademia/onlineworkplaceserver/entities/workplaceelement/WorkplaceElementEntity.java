package com.kosickaakademia.onlineworkplaceserver.entities.workplaceelement;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonTypeInfo;
import com.kosickaakademia.onlineworkplaceserver.entities.LabelEntity;
import com.kosickaakademia.onlineworkplaceserver.entities.UserEntity;
import com.kosickaakademia.onlineworkplaceserver.entities.WorkplaceEntity;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.Check;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import java.util.Date;
import java.util.List;

@JsonTypeInfo(use = JsonTypeInfo.Id.NAME, property = "type")
@JsonSubTypes({
        @JsonSubTypes.Type(value=NoteEntity.class, name = "note"),
        @JsonSubTypes.Type(value=ThreadEntity.class, name = "thread"),
        @JsonSubTypes.Type(value=CheckListEntity.class, name = "checklist")
})
@Entity
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name = "type")
@Data
public class WorkplaceElementEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    protected Long id;

    @JsonBackReference
    @ManyToOne()
    protected WorkplaceEntity workplaceEntity;

    protected String name;
    protected Date creationTime;
    protected boolean archived;

    @ManyToMany(cascade = CascadeType.ALL)
    @JoinTable(
            name = "element_label",
            joinColumns = @JoinColumn(name = "element_id"),
            inverseJoinColumns = @JoinColumn(name = "label_id"))
    private List<LabelEntity> assignedLabels;

    @ManyToMany(cascade = CascadeType.ALL)
    @JoinTable(
            name = "element_user",
            joinColumns = @JoinColumn(name = "element_id"),
            inverseJoinColumns = @JoinColumn(name = "user_id"))
    private List<UserEntity> assignedUsers;

    @PrePersist
    protected void onCreate() {
        creationTime = new Date();
    }

    @PreUpdate
    protected void onUpdate() {
        creationTime = new Date();
    }
}
