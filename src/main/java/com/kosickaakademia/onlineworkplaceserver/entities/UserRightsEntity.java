package com.kosickaakademia.onlineworkplaceserver.entities;

import lombok.Data;

import javax.persistence.*;

@Data
@Entity
@Table(name = "user_rights")
public class UserRightsEntity {
    @Id()
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private boolean removeFromWorkplace;
    private boolean addToWorkplace;
    private boolean archiveElement;
    private boolean changeRights;

    @OneToOne
    private WorkplaceEntity workplaceEntity;

    @OneToOne
    private UserEntity userEntity;
}
