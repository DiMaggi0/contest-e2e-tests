package solution;

import api.database.ContestDatabaseAutoConfiguration;
import api.database.functions.SolutionFunctions;
import api.methods.SolutionApi;
import io.qameta.allure.Epic;
import io.qameta.allure.Feature;
import org.junit.jupiter.api.DisplayName;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

@SpringBootTest(classes = { ContestDatabaseAutoConfiguration.class, SolutionApi.class })
@ActiveProfiles(profiles = "test")
@Epic("Solution API")
@Feature("Solution GET")
@DisplayName("Тесты на просмотр отправленного решения")
public class SolutionGetTests {

    @Autowired
    private SolutionFunctions solutionFunctions;

    @Autowired
    private SolutionApi solutionApi;
}
