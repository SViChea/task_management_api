package site.taskmanagement.taskmanagementapi.init;

import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import site.taskmanagement.taskmanagementapi.model.Progress;
import site.taskmanagement.taskmanagementapi.repository.ProgressRepository;

@Component
@RequiredArgsConstructor
public class ProgressInit {

    private final ProgressRepository progressRepository;

    @PostConstruct
    public void init() {
        Progress notStarted = new Progress();
        notStarted.setTitle("Not Started");
        progressRepository.save(notStarted);

        Progress inProgress = new Progress();
        inProgress.setTitle("In Progress");
        progressRepository.save(inProgress);

        Progress completed = new Progress();
        completed.setTitle("Completed");
        progressRepository.save(completed);
    }
}
