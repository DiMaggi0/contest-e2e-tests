package test;

import api.database.ContestDatabaseAutoConfiguration;
import api.database.functions.TaskFunctions;
import api.database.functions.TestFunctions;
import api.methods.TaskApi;
import api.methods.TestApi;
import api.requests.test.TestRequest;
import api.responses.task.TaskResponse;
import io.qameta.allure.Description;
import io.qameta.allure.Epic;
import io.qameta.allure.Feature;
import org.apache.commons.lang3.RandomStringUtils;
import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

import java.util.List;
import java.util.Random;

import static api.TestUtils.convertStringtoObject;
import static api.TestUtils.generateTaskRequestBody;

@SpringBootTest(classes = { ContestDatabaseAutoConfiguration.class, TestApi.class, TaskApi.class })
@ActiveProfiles(profiles = "test")
@TestInstance(value = TestInstance.Lifecycle.PER_CLASS)
@Epic("Task API")
@Feature("Task PUT")
@DisplayName("Тесты на редактирование тестов к задаче")
public class TestUpdateTests {

    @Autowired
    private TestApi testApi;

    @Autowired
    private TestFunctions testFunctions;

    @Autowired
    private TaskApi taskApi;

    @Autowired
    private TaskFunctions taskFunctions;

    private Integer newTaskId;

    @BeforeEach
    public void createTestingTask() {
        newTaskId = convertStringtoObject(taskApi.createNewTask(generateTaskRequestBody(
                1,
                RandomStringUtils.random(50, true, false),
                RandomStringUtils.random(15, true, true),
                "C++|Java",
                new Random().nextInt(1, 3),
                null
        ), "dmitry", "12345").asString(), TaskResponse.class).getId();

        testApi.createTests(new TestRequest()
                .taskId(newTaskId)
                .tests(List.of("1", "2", "3", "4")), "dmitry", "12345");
    }

    @Test
    @DisplayName("Редактируем тесты к созданной задаче")
    @Description("PUT /api/v1/test/?task_id={taskId}")
    public void updateExistingTests() {

    }

    @Test
    @DisplayName("Редактируем несуществующие тесты")
    @Description("PUT /api/v1/test/?task_id={taskId}")
    public void updateNonExistingTests() {

    }

    @Test
    @DisplayName("Редактируем тесты под пользователем, не создававшем задачу")
    @Description("PUT /api/v1/test/?task_id={taskId}")
    public void updateExistingTestsByNotCreatorTaskUser() {

    }

    @Test
    @DisplayName("Редактируем тесты у несуществующей задачи")
    @Description("PUT /api/v1/test/?task_id={taskId}")
    public void updateNonExistingTaskTests() {

    }

    @Test
    @DisplayName("Редактируем тесты с некорректными параметрами в body")
    @Description("PUT /api/v1/test/?task_id={taskId}")
    public void updateTestsWithNullParameters() {

    }

    @AfterEach
    public void deleteCreatedTask() {
        testFunctions.deleteTestsByTaskId(newTaskId);
        taskFunctions.deleteTaskById(newTaskId);

    }
}
