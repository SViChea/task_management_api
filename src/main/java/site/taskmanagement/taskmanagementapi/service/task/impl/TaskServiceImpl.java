package site.taskmanagement.taskmanagementapi.service.task.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;
import site.taskmanagement.taskmanagementapi.dto.task.TaskRequest;
import site.taskmanagement.taskmanagementapi.dto.task.TaskResponse;
import site.taskmanagement.taskmanagementapi.dto.task.UpdateTask;
import site.taskmanagement.taskmanagementapi.mapper.TaskMapper;
import site.taskmanagement.taskmanagementapi.model.Priority;
import site.taskmanagement.taskmanagementapi.model.Progress;
import site.taskmanagement.taskmanagementapi.model.Project;
import site.taskmanagement.taskmanagementapi.model.Task;
import site.taskmanagement.taskmanagementapi.repository.PriorityRepository;
import site.taskmanagement.taskmanagementapi.repository.ProgressRepository;
import site.taskmanagement.taskmanagementapi.repository.ProjectRepository;
import site.taskmanagement.taskmanagementapi.repository.TaskRepository;
import site.taskmanagement.taskmanagementapi.service.task.TaskService;

import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
public class TaskServiceImpl implements TaskService {

    private final TaskRepository taskRepository;
    private final TaskMapper taskMapper;
    private final ProjectRepository projectRepository;
    private final PriorityRepository priorityRepository;
    private final ProgressRepository progressRepository;

    @Override
    public TaskResponse getTaskById(long id) {
        Task task = taskRepository.findById(id).orElseThrow(
                () -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Task Not Found")
        );
        return taskMapper.toTaskResponse(task);
    }

    @Override
    public List<TaskResponse> getTaskByProjectId(long projectId) {
        Project project = projectRepository.findById(projectId).orElseThrow(
                () -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Project Not Found")
        );

        List<Task> tasks = taskRepository.findByProject(project).orElseThrow(
                () -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Task Not Found")
        );

        return taskMapper.toTaskResponseList(tasks);
    }

    @Override
    public TaskResponse createTask(TaskRequest taskRequest) {
        Priority priority = priorityRepository.findById(taskRequest.priorityId()).orElseThrow(
                () -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Priority Not Found")
        );

        Progress progress = progressRepository.findById(taskRequest.progressId()).orElseThrow(
                () -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Progress Not Found")
        );

        Project project = projectRepository.findById(taskRequest.projectId()).orElseThrow(
                () -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Project Not Found")
        );

        Task task = new Task();
        task.setTitle(taskRequest.title());
        task.setDescription(taskRequest.description());
        task.setCreatedDate(LocalDateTime.now());
        task.setDueDate(taskRequest.dueDate());
        task.setPriority(priority);
        task.setProgress(progress);
        task.setProject(project);

        taskRepository.save(task);
        return taskMapper.toTaskResponse(task);
    }

    @Override
    public TaskResponse updateTask(UpdateTask updateTask) {
        Task task = taskRepository.findById(updateTask.id()).orElseThrow(
                () -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Task Not Found")
        );

        if(updateTask.priorityId().describeConstable().isEmpty()){
            Priority priority = priorityRepository.findById(updateTask.priorityId()).orElseThrow(
                    () -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Priority Not Found")
            );
            task.setPriority(priority);
        }


        Progress progress = progressRepository.findById(updateTask.progressId()).orElseThrow(
                () -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Progress Not Found")
        );

        task.setTitle(updateTask.title());
        task.setDescription(updateTask.description());
        task.setDueDate(updateTask.dueDate());
        task.setProgress(progress);

        taskRepository.save(task);

        return taskMapper.toTaskResponse(task);
    }

    @Override
    public void deleteTaskById(long id) {
        Task task = taskRepository.findById(id).orElseThrow(
                () -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Task Not Found")
        );

        taskRepository.delete(task);
    }

    @Override
    public void deleteTaskByProjectId(long projectId) {
        Project project = projectRepository.findById(projectId).orElseThrow(
                () -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Project Not Found")
        );

        taskRepository.deleteTasksByProject(project);
    }
}
