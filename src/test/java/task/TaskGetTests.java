package task;

import api.database.ContestDatabaseAutoConfiguration;
import api.database.functions.ProfileFunctions;
import api.database.functions.SolutionFunctions;
import api.database.functions.TaskFunctions;
import api.database.functions.TestFunctions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Profile;
import org.springframework.test.context.ActiveProfiles;

import static io.restassured.RestAssured.given;

@SpringBootTest(classes = { ContestDatabaseAutoConfiguration.class  })
@ActiveProfiles(profiles = "test")
public class TaskGetTests {

    @Autowired
    private TaskFunctions taskFunctions;

    @Autowired
    private ProfileFunctions profileFunctions;

    @Autowired
    private SolutionFunctions solutionFunctions;

    @Autowired
    private TestFunctions testFunctions;

    @Test
    public void TaskGetTest() {
        //System.out.println(given().get("http://127.0.0.1:8000/api/v1/task/").statusCode());
        System.out.println(taskFunctions.getTaskByTaskId(1));
        System.out.println(profileFunctions.getProfileById(1));
        System.out.println(solutionFunctions.getSolutionById(2));
        System.out.println(testFunctions.getTestById(1));
    }
}
