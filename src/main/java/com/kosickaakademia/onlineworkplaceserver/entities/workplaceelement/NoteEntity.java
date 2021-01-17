package com.kosickaakademia.onlineworkplaceserver.entities.workplaceelement;

import com.kosickaakademia.onlineworkplaceserver.entities.LabelEntity;
import com.kosickaakademia.onlineworkplaceserver.entities.UserEntity;
import com.kosickaakademia.onlineworkplaceserver.entities.WorkplaceEntity;
import lombok.Data;

import javax.persistence.*;
import java.util.Date;
import java.util.List;

@Entity
@Data
public class NoteEntity implements WorkplaceElementEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;
    private String description;
    private Date dueDate;

    @ManyToOne()
    private WorkplaceEntity workplaceEntity;

    @ManyToMany(cascade = CascadeType.ALL)
    @JoinTable(
            name = "note_label",
            joinColumns = @JoinColumn(name = "label_id"),
            inverseJoinColumns = @JoinColumn(name = "note_id"))
    private List<LabelEntity> assignedLabels;

    @ManyToMany(cascade = CascadeType.ALL)
    @JoinTable(
            name = "note_user",
            joinColumns = @JoinColumn(name = "user_id"),
            inverseJoinColumns = @JoinColumn(name = "note_id"))
    private List<UserEntity> assignedUsers;
}
