package com.kosickaakademia.onlineworkplaceserver.entities.workplaceelement;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.kosickaakademia.onlineworkplaceserver.entities.LabelEntity;
import com.kosickaakademia.onlineworkplaceserver.entities.UserEntity;
import lombok.*;

import javax.persistence.*;
import java.util.Date;
import java.util.List;

@Entity
@Table(name = "note")
@Data
public class NoteEntity extends WorkplaceElementEntity {
    private String description;
    private Date dueDate;

    @ManyToMany(cascade = CascadeType.ALL)
    @JoinTable(
            name = "note_label",
            joinColumns = @JoinColumn(name = "note_id"),
            inverseJoinColumns = @JoinColumn(name = "label_id"))
    private List<LabelEntity> assignedLabels;

    @ManyToMany(cascade = CascadeType.ALL)
    @JoinTable(
            name = "note_user",
            joinColumns = @JoinColumn(name = "note_id"),
            inverseJoinColumns = @JoinColumn(name = "user_id"))
    private List<UserEntity> assignedUsers;
}
