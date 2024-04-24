package task;

import api.database.ContestDatabaseAutoConfiguration;
import api.database.functions.TaskFunctions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Profile;
import org.springframework.test.context.ContextConfiguration;

import static io.restassured.RestAssured.given;

@SpringBootTest(classes = { ContestDatabaseAutoConfiguration.class  })
@ContextConfiguration(classes = ContestDatabaseAutoConfiguration.class)
public class TaskGetTests {

    @Autowired
    private TaskFunctions taskFunctions;

    @Test
    public void TaskGetTest() {
        //System.out.println(given().get("http://127.0.0.1:8000/api/v1/task/").statusCode());
        System.out.println(taskFunctions.getTaskByTaskId(1L));
    }
}
