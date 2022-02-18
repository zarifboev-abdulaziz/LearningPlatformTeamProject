package uz.pdp.model;

import lombok.*;

import javax.persistence.*;
import java.sql.Time;
import java.sql.Timestamp;
import java.time.LocalDateTime;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity(name = "lessons")
public class Lesson {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "title")
    private String title;
    @Column(name = "order_number")
    private Integer orderNumber;
    @Column(name = "body", columnDefinition = " text")
    private String body;
    @Column(name = "created_at", columnDefinition = " timestamp default now()")
    private Timestamp createdAt = Timestamp.valueOf(LocalDateTime.now());
    @Column(name = "updated_at", columnDefinition = " timestamp default now()")
    private Timestamp updatedAt = Timestamp.valueOf(LocalDateTime.now());

    @ManyToOne()
    private Module module;

}
