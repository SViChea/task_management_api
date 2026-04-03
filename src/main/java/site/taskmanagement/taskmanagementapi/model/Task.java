package site.taskmanagement.taskmanagementapi.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Entity
@Getter
@Setter
@NoArgsConstructor
@Table(name = "tasks")
public class Task {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;

    @Column(nullable = false)
    String title;

    String description;

    @Column(nullable = false)
    LocalDateTime dueDate;

    @Column(nullable = false)
    LocalDateTime createdDate;

    @ManyToOne
    Progress progress;

    @ManyToOne
    Priority priority;

    @ManyToOne
    Project project;
}
