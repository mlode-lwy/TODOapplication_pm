package pl.ml.TaskController;

import lombok.Data;
import lombok.NoArgsConstructor;

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
    @Column(name="task_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int task_id;

    @Column(name="TASK_NAME")
    private String taskName;

    @Column(name="TASK_DESC")
    private String taskDescription;

    @Column(name="DUE_DATE")
    private String dueDate;

    @Column(name="STATUS")
    private String status;
}
