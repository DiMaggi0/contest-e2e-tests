package task;

import api.database.ContestDatabaseConfiguration;
import api.database.functions.TaskFunctions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static io.restassured.RestAssured.given;

@SpringBootTest(classes = ContestDatabaseConfiguration.class)
public class TaskGetTests {

    @Autowired
    private TaskFunctions taskFunctions;

    @Test
    public void TaskGetTest() {
        //System.out.println(given().get("http://127.0.0.1:8000/api/v1/task/").statusCode());
        System.out.println(taskFunctions.getTaskByTaskId(1L));
    }
}
