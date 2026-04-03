package site.taskmanagement.taskmanagementapi.init;

import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import site.taskmanagement.taskmanagementapi.model.Priority;
import site.taskmanagement.taskmanagementapi.repository.PriorityRepository;

@Component
@RequiredArgsConstructor
public class PriorityInit {

    private final PriorityRepository priorityRepository;

    @PostConstruct
    public void init() {
        if(priorityRepository.findAll().isEmpty()) {
            Priority urgent = new Priority();
            urgent.setTitle("Urgent");
            priorityRepository.save(urgent);

            Priority important = new Priority();
            important.setTitle("Important");
            priorityRepository.save(important);

            Priority medium = new Priority();
            medium.setTitle("Medium");
            priorityRepository.save(medium);

            Priority low = new Priority();
            low.setTitle("Low");
            priorityRepository.save(low);
        }
    }
}
