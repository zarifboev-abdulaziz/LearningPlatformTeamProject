package uz.pdp.payload;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class TaskDto {
    private Integer taskId;
    private String title;
    private String body;
    private String option1;
    private String option2;
    private String option3;
    private String option4;
    private Integer trueOption;

}
