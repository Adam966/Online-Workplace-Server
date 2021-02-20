package com.kosickaakademia.onlineworkplaceserver.entities;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;

import javax.persistence.*;
import java.util.List;

@Data
@Entity
@Table(name = "workplace")
public class WorkplaceEntity {
    @Id()
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;
    private String description;
    private String colorOfElement;
    private String backgroundColor;
    private Long adminId;
    private Long photo;

    @JsonBackReference
    @ManyToMany(mappedBy = "userWorkplaces", targetEntity = UserEntity.class, cascade = CascadeType.ALL)
    private List<UserEntity> workplaceUsers;

    @JsonBackReference(value = "workplaceLabels")
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "workplaceEntity")
    private List<LabelEntity> workplaceLabels;
}
