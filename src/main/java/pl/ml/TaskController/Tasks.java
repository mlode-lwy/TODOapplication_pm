package pl.ml.TaskController;

import lombok.Data;
import lombok.NoArgsConstructor;
import pl.ml.TaskStatus;

import javax.persistence.*;

/**
 * Task Entity
 * @author pmatusiak
 */

@Data
@NoArgsConstructor
@Entity
@Table(name = "tasks")
public class Tasks {

    @Id
    @Column(name="TASK_ID")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int task_id;

    @Column(name="TASK_NAME")
    private String taskName;

    @Column(name="TASK_DESC")
    private String taskDescription;

    @Column(name="DUE_DATE")
    private String dueDate;

    @Column(name="STATUS")
    @Enumerated(EnumType.STRING)
    private TaskStatus status;

    @Column(name="USER_ID")
    private int userID;
}
