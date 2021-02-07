package com.kosickaakademia.onlineworkplaceserver.entities;

import com.fasterxml.jackson.annotation.JsonBackReference;
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
    private String backgroundColor;
    private Long adminId;
    private String colorOfElement;
    private Long photo;

    @JsonBackReference
    @ManyToMany(mappedBy = "userWorkplaces", targetEntity = UserEntity.class, cascade = CascadeType.ALL)
    private List<UserEntity> workplaceUsers;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "workplaceEntity")
    private List<LabelEntity> workplaceLabels;
}
