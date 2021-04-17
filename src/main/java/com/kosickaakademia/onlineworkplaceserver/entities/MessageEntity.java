package com.kosickaakademia.onlineworkplaceserver.entities;

import com.kosickaakademia.onlineworkplaceserver.entities.workplaceelement.WorkplaceElementEntity;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import java.util.Date;

@Entity
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "message")
public class MessageEntity {
    @Id()
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String description;

    @CreationTimestamp
    private Date timestamp;

    @ManyToOne
    private UserEntity senderUser;

    @ManyToOne
    private WorkplaceElementEntity thread;
}
