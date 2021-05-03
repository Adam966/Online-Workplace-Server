package com.kosickaakademia.onlineworkplaceserver.entities;

import com.kosickaakademia.onlineworkplaceserver.enums.NotificationType;

import lombok.Data;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import java.util.Date;

@Entity(name = "notification")
@Data
public class NotificationEntity {
    @Id()
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private NotificationType type;
    private String description;
    private boolean fresh;

    @CreationTimestamp
    private Date creationTime;

    @ManyToOne(fetch = FetchType.EAGER)
    private WorkplaceEntity workplaceEntity;

    @OneToOne(fetch = FetchType.EAGER)
    private UserEntity senderUser;

    @OneToOne(fetch = FetchType.EAGER)
    private UserEntity recipientUser;
}

