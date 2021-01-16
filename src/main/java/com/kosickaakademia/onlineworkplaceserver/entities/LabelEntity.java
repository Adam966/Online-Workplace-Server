package com.kosickaakademia.onlineworkplaceserver.entities;

import com.fasterxml.jackson.annotation.JsonBackReference;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Getter
@Setter
@Table(name = "label")
public class LabelEntity {
    @Id()
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;
    private String color;

    @JsonBackReference
    @ManyToOne(cascade = CascadeType.ALL)
    @JoinTable(
            name = "workplace_label",
            joinColumns = @JoinColumn(name = "label_id"),
            inverseJoinColumns = @JoinColumn(name = "workplace_id")
    )
    private WorkplaceEntity workplaceEntity;
}
