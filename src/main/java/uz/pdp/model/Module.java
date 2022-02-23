package uz.pdp.model;

import lombok.*;

import javax.persistence.*;
import java.sql.Time;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;


@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity(name = "modules")
public class Module {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @ManyToOne()
    private Course course;

    @Column(name = "title")
    private String title;
    @Column(name = "order_number")
    private Integer orderNumber;
    @Column(name = "created_at", columnDefinition = " timestamp default now()")
    private Timestamp createdAt = Timestamp.valueOf(LocalDateTime.now());
    @Column(name = "updated_at", columnDefinition = " timestamp default now()")
    private Timestamp updatedAt = Timestamp.valueOf(LocalDateTime.now());

    @OneToMany(mappedBy = "module", cascade = CascadeType.REMOVE, fetch = FetchType.EAGER)
    private List<Lesson> lessons = new ArrayList<>();

}
