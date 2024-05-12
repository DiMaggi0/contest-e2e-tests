package api.database.functions;

import api.database.entities.Solution;
import api.database.repos.SolutionRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class SolutionFunctions {

    private final SolutionRepository solutionRepository;

    public List<Solution> getTaskSolutionsByTaskId(Integer taskId) {
        return solutionRepository.findAllByTaskId(taskId);
    }
}
