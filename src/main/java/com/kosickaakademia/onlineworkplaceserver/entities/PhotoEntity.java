package com.kosickaakademia.onlineworkplaceserver.entities;

import lombok.Data;
import javax.persistence.*;

@Data
@Entity
@Table(name = "workplace_photo")
public class PhotoEntity {
    @Id()
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Lob
    private byte[] picture;
}