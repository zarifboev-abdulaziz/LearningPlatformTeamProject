package uz.pdp.helper;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import uz.pdp.model.Lesson;
import uz.pdp.model.User;

import javax.persistence.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor

public class LessonComment {
    private Integer id;
    private User user;
    private Lesson lesson;
    private String body;

}
