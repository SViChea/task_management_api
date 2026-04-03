package site.taskmanagement.taskmanagementapi.service.task;

import site.taskmanagement.taskmanagementapi.dto.task.TaskRequest;
import site.taskmanagement.taskmanagementapi.dto.task.TaskResponse;
import site.taskmanagement.taskmanagementapi.dto.task.UpdateTask;

import java.util.List;

public interface TaskService {

    TaskResponse getTaskById(long id);

    List<TaskResponse> getTaskByProjectId(long projectId);

    TaskResponse createTask(TaskRequest taskRequest);

    TaskResponse updateTask(UpdateTask updateTask);

    void deleteTaskById(long id);

    void deleteTaskByProjectId(long projectId);
}
