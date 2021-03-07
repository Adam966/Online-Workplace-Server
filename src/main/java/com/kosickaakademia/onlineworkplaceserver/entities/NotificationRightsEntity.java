package com.kosickaakademia.onlineworkplaceserver.entities;

import lombok.Data;

import javax.persistence.*;

@Entity
@Data
@Table(name = "notification_rights")
public class NotificationRightsEntity {
    @Id()
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private boolean addedToElement;
    private boolean sentMessage;
    private boolean dueDate;
    private boolean removedFromElement;

    @OneToOne
    private WorkplaceEntity workplaceEntity;

    @OneToOne
    private UserEntity userEntity;
}
