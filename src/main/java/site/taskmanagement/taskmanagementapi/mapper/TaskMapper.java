package site.taskmanagement.taskmanagementapi.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;
import site.taskmanagement.taskmanagementapi.dto.task.TaskResponse;
import site.taskmanagement.taskmanagementapi.model.Task;

import java.util.List;

@Mapper(componentModel = "spring")
public interface TaskMapper {
    @Mappings({
            @Mapping(source = "progress.title", target = "progressName"),
            @Mapping(source = "priority.title", target = "priorityName")
    })
    TaskResponse toTaskResponse(Task task);

    List<TaskResponse> toTaskResponseList(List<Task> tasks);
}
