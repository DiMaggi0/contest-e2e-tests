package api.database.functions;

import api.database.entities.Test;
import api.database.repos.TestRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class TestFunctions {

    private final TestRepository testRepository;

    public Test getTestById(Integer id) {
        return testRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Тест с заданным id не существует"));
    }
}
