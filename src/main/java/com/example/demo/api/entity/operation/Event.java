package com.example.demo.api.entity.operation;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "EVENT")
public class Event {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(length = 100)
    private String title;

    @Column(length = 3000)
    private String description;

    @Column(length = 300)
    private String url;

    @Column(length = 500)
    private String image_url;

    private LocalDateTime createAt;

    private LocalDateTime modifiedAt;
}
