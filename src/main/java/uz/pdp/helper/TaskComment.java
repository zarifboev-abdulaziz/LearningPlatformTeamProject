package uz.pdp.helper;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import uz.pdp.model.Task;
import uz.pdp.model.User;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor

public class TaskComment {
    private Integer id;
    private User user;
    private Task task;
    private String body;

}