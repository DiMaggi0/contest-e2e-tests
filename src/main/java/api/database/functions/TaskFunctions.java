package api.database.functions;

import api.database.repos.TaskRepository;
import api.database.entities.Task;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.repository.query.FluentQuery;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class TaskFunctions {

    private final TaskRepository taskRepository;


    public Task getTaskByTaskId(Long taskId) {
        return taskRepository.findById(taskId).orElseThrow(
                () -> new IllegalArgumentException("Не найдено записи с id = {taskId}"));
    }

}
