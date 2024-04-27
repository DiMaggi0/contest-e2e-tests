package task;

import api.database.ContestDatabaseAutoConfiguration;
import api.database.functions.ProfileFunctions;
import api.database.functions.SolutionFunctions;
import api.database.functions.TaskFunctions;
import api.database.functions.TestFunctions;
import api.methods.TaskApi;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

import java.util.Arrays;

import static io.restassured.RestAssured.given;

@SpringBootTest(classes = { ContestDatabaseAutoConfiguration.class, TaskApi.class })
@ActiveProfiles(profiles = "test")
public class TaskGetTests {

    @Autowired
    private TaskFunctions taskFunctions;

    @Autowired
    private TaskApi taskApi;

    @Test
    public void TaskGetTest() {
        System.out.println(taskApi.getTasksList()[0]);
    }
}
