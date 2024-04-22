package api.database.functions;

import api.database.repos.TaskRepository;
import api.database.entities.Task;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class TaskFunctions {

    private final TaskRepository taskRepository;

    public Task getTaskByTaskId(Long taskId) {
        return taskRepository.getReferenceById(taskId);
    }

}
