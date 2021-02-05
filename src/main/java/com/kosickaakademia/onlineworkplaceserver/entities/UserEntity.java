package com.kosickaakademia.onlineworkplaceserver.entities;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.kosickaakademia.onlineworkplaceserver.entities.workplaceelement.NoteEntity;
import com.kosickaakademia.onlineworkplaceserver.entities.workplaceelement.WorkplaceElementEntity;
import lombok.Data;

import javax.persistence.*;
import java.util.List;

@Data
@Entity
@Table(name = "user")
public  class UserEntity {
    @Id()
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;
    private String surname;

    @Column(unique = true)
    private String email;

    private String password;

    @JsonBackReference
    @ManyToMany(cascade = CascadeType.ALL)
    @JoinTable(
            name = "workplace_user",
            joinColumns = @JoinColumn(name = "user_id"),
            inverseJoinColumns = @JoinColumn(name = "workplace_id")
    )
    private List<WorkplaceEntity> userWorkplaces;

    @JsonBackReference(value = "workplace-entity")
    @ManyToMany(mappedBy = "assignedUsers", targetEntity = NoteEntity.class, cascade = CascadeType.ALL)
    private List<WorkplaceElementEntity> workplaceElementEntity;
}
