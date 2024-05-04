package api.methods;

import api.requests.task.TaskRequest;
import api.responses.task.TaskResponse;
import io.restassured.response.Response;
import lombok.RequiredArgsConstructor;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Service;

import static api.TestUtils.convertStringtoObject;
import static io.restassured.RestAssured.given;

@Service
@RequiredArgsConstructor
public class TaskApi {

    public TaskResponse[] getTasksList() {
        return convertStringtoObject(given()
                .get("http://127.0.0.1:8000/api/v1/task/")
                .asString(), TaskResponse[].class);
    }

    public Response getTaskById(Integer id) {
        return given()
                .pathParam("id", id)
                .get("http://127.0.0.1:8000/api/v1/task/{id}");
    }

    public TaskResponse createNewTask(Integer id, String description, String title, String langs, Integer level, @Nullable String owner) {
        return convertStringtoObject(given()
                .header("Authorization", "Token 6cef3f7d3f1ea1aba0d2a3fc6321dd86286fa0e6")
                .contentType("application/json")
                .body(new TaskRequest()
                        .id(id)
                        .description(description)
                        .langs(langs)
                        .level(level)
                        .title(title)
                        .owner(owner)
                )
                .post("http://127.0.0.1:8000/api/v1/task/")
                .asString(), TaskResponse.class);

    }


}
