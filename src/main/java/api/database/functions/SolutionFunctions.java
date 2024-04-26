package api.database.functions;

import api.database.entities.Solution;
import api.database.repos.SolutionRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class SolutionFunctions {

    private final SolutionRepository solutionRepository;

    public Solution getSolutionById(Integer id) {
        return solutionRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Решение с заданным id не найдено"));
    }
}
