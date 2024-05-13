package solution;

import api.database.ContestDatabaseAutoConfiguration;
import api.database.entities.Solution;
import api.database.functions.SolutionFunctions;
import api.methods.SolutionApi;
import api.responses.solution.SolutionResponse;
import io.qameta.allure.Description;
import io.qameta.allure.Epic;
import io.qameta.allure.Feature;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

import java.util.Arrays;

import static io.qameta.allure.Allure.step;
import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest(classes = { ContestDatabaseAutoConfiguration.class, SolutionApi.class })
@ActiveProfiles(profiles = "test")
@Epic("Solution API")
@Feature("Solution GET")
@DisplayName("Тесты на просмотр отправленных решений к задаче")
public class SolutionGetTests {

    @Autowired
    private SolutionFunctions solutionFunctions;

    @Autowired
    private SolutionApi solutionApi;

    @Test
    @DisplayName("Получаем все отправленные решения к данной задаче")
    @Description("GET /api/v1/solution/?task_id={taskId}")
    public void getTaskSolutions() {

        var givenSolutions = step("GIVEN: Получены отправленные решения к задаче",
                () -> solutionApi.getTaskSolutions(251).getBody().as(SolutionResponse[].class));

        var givenDatabaseSolutions = step("WHEN: Получены решения к данной задаче из таблицы solution",
                () -> solutionFunctions.getTaskSolutionsByTaskId(251));

        step("THEN: Параметры полученных решений соответствуют ожидаемым",
                () -> assertAll(

                        () -> step("Количество полученных решений совпадает с количеством из базы",
                                () -> assertEquals(givenSolutions.length, givenDatabaseSolutions.size())),

                        () -> step("file совпадает с полученными из базы значениями",
                                () -> assertEquals(Arrays.stream(givenSolutions).map(a -> a.getFile().substring(7)).sorted().toList(),
                                        givenDatabaseSolutions.stream().map(Solution::getFile).sorted().toList())),

                        () -> step("lang совпадает с полученными из базы значениями",
                                () -> assertEquals(Arrays.stream(givenSolutions).map(SolutionResponse::getLang).sorted().toList(),
                                        givenDatabaseSolutions.stream().map(Solution::getLang).sorted().toList())),

                        () -> step("points совпадает с полученными из базы значениями",
                                () -> assertEquals(Arrays.stream(givenSolutions).map(SolutionResponse::getPoints).sorted().toList(),
                                        givenDatabaseSolutions.stream().map(Solution::getPoints).sorted().toList())),

                        () -> step("status совпадает с полученными из базы значениями",
                                () -> assertEquals(Arrays.stream(givenSolutions).map(SolutionResponse::getStatus).sorted().toList(),
                                        givenDatabaseSolutions.stream().map(Solution::getStatus).sorted().toList())),

                        () -> step("passed_tests совпадает с полученными из базы значениями",
                                () -> assertEquals(Arrays.stream(givenSolutions).map(SolutionResponse::getPassedTests).sorted().toList(),
                                        givenDatabaseSolutions.stream().map(Solution::getPassedTests).sorted().toList()))
                ));

    }

    @Test
    @DisplayName("Получаем несуществующие решения задачи")
    @Description("GET /api/v1/solution/?task_id={taskId}")
    public void getTaskNonExistingSolutions() {
        var givenSolutions = step("GIVEN: Получаем все отправленные решения к задаче",
                () -> solutionApi.getTaskSolutions(24)
                        .getBody().as(SolutionResponse[].class));

        var givenDatabaseSolutions = step("AND: Получаем все существующие решения из таблицы solution",
                () -> solutionFunctions.getTaskSolutionsByTaskId(24));

        step("THEN: Параметры полученных решений соответствуют ожидаемым",
                () -> assertAll(

                        () -> step("Количество полученных решений равно нулю",
                                () -> assertEquals(givenSolutions.length, givenDatabaseSolutions.size()))
                ));
    }

    @Test
    @DisplayName("Получаем решения несуществующей задачи")
    @Description("GET /api/v1/solution/?task_id={taskId}")
    public void getNonExistingTaskSolutions() {

        var givenSolutions = step("WHEN: Получаем все отправленные решения к несуществующей задаче",
                () -> solutionApi.getTaskSolutions(10000));

        step("THEN: Полученные параметры соответствуют ожидаемым",
                () -> assertAll(

                        () -> step("Код ответа соответствует полученному коду",
                                () -> assertEquals(givenSolutions.getStatusCode(), 200)),

                        () -> step("Тело ответа соответствует ожидаемому",
                                () -> assertEquals(givenSolutions.getBody().asString(), "[]"))
                ));

    }

}
