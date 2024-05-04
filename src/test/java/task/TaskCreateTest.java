package task;

import api.database.ContestDatabaseAutoConfiguration;
import api.database.functions.TaskFunctions;
import api.methods.TaskApi;
import io.qameta.allure.Epic;
import io.qameta.allure.Feature;
import org.apache.commons.lang3.RandomStringUtils;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Random;

import static io.qameta.allure.Allure.step;
import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest(classes = { ContestDatabaseAutoConfiguration.class, TaskApi.class })
@ActiveProfiles(profiles = "test")
@Epic("Task API")
@Feature("Task POST")
@DisplayName("Методы для создания задач")
public class TaskCreateTest {
    @Autowired
    private TaskApi taskApi;

    @Autowired
    private TaskFunctions taskFunctions;

    @Test
    @DisplayName("Создаем задачу со всеми параметрами")
    public void createNewTaskTest() {
        var createdTask = step("GIVEN: Создана задача со всеми необходимыми параметрами",
                () -> taskApi.createNewTask(
                        1,
                        RandomStringUtils.random(50, true, false),
                        RandomStringUtils.random(15, true, true),
                        "C++|Java",
                        new Random().nextInt(1, 3),
                        null
                ));

        var givenDatabaseTask = step("WHEN: Получена информация о задаче из таблицы task",
                () -> taskFunctions.getTaskByTaskId(createdTask.getId()));

        step("THEN: Параметры созданной задачи соответствуют ожидаемым",
                () -> assertAll(
                        () -> step("Поле id заполнено", () -> assertNotNull(givenDatabaseTask.getId())),
                        () -> step("Поле title = указывали при создании задачи",
                                () -> assertEquals(createdTask.getTitle(),
                                        givenDatabaseTask.getTitle())),
                        () -> step("Поле description = указывали при создании задачи",
                                () -> assertEquals(createdTask.getDescription(),
                                        givenDatabaseTask.getDescription())),
                        () -> step("Поле langs = указывали при создании задачи",
                                () -> assertEquals(createdTask.getLangs(),
                                        givenDatabaseTask.getLangs())),
                        () -> step("Поле level = указывали при создании задачи",
                                () -> assertEquals(createdTask.getLevel(),
                                        givenDatabaseTask.getLevel())),
                        () -> step("Поле sent_solutions равно нулю",
                                () -> assertEquals(createdTask.getSentSolutions(),
                                        givenDatabaseTask.getSentSolution())),
                        () -> step("Owner_id = id пользователя, создавшего задачу",
                                () -> assertEquals(createdTask.getOwner().getId(),
                                        givenDatabaseTask.getOwnerId())),
                        () -> step("Время создания задачи соответствует ожидаемому",
                                () -> assertTrue(LocalDateTime.now().minusMinutes(1L).isBefore(givenDatabaseTask.getCreatedAt())))

                ));

    }
}
