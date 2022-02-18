package uz.pdp.model;

import lombok.*;

import javax.persistence.*;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity(name = "courses")
public class Course {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @Column(name = "name")
    private String name;
    @Column(name = "description", columnDefinition = " text")
    private String description;
    @Column(name = "is_active", columnDefinition = " boolean default true")
    private boolean isActive;
    @Column(name = "price", columnDefinition = " double precision default 1000")
    private double price;
    @Column(name = "image_path")
    private String imagePath;
    @Column(name = "created_at", columnDefinition = " timestamp default now()")
    private Timestamp createdAt = Timestamp.valueOf(LocalDateTime.now());
    @Column(name = "updated_at", columnDefinition = " timestamp default now()")
    private Timestamp updatedAt = Timestamp.valueOf(LocalDateTime.now());

    @OneToMany(mappedBy = "course")
    private List<Module> modules;

    @ManyToMany(mappedBy = "courses", fetch = FetchType.LAZY)
    private List<User> users;


}
