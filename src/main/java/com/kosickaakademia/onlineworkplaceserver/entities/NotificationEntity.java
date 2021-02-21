package com.kosickaakademia.onlineworkplaceserver.entities;

import com.kosickaakademia.onlineworkplaceserver.enums.NotificationType;
import lombok.AllArgsConstructor;
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

    @ManyToOne
    private WorkplaceEntity workplaceEntity;

    @OneToOne
    private UserEntity senderUser;

    @OneToOne
    private UserEntity recipientUser;
}

