package api.methods;

import api.requests.task.TaskRequest;
import api.responses.task.TaskResponse;
import io.qameta.allure.Step;
import io.restassured.response.Response;
import lombok.RequiredArgsConstructor;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Service;

import static api.TestUtils.*;
import static io.restassured.RestAssured.given;

@Service
@RequiredArgsConstructor
public class TaskApi {

    public TaskResponse[] getTasksList() {
        return convertStringtoObject(given()
                .get("http://127.0.0.1:8000/api/v1/task/")
                .asString(), TaskResponse[].class);
    }

    @Step("Неотображаемый step")
    public Response getTaskById(Integer id) {
        return given()
                .pathParam("id", id)
                .get("http://127.0.0.1:8000/api/v1/task/{id}");
    }

    @Step("[POST/api/v1/task] Создаем задачу с переданными параметрами")
    public Response createNewTask(TaskRequest taskRequest) {
        return given()
                .header("Authorization", "Token " + getUserAuthToken("dmitry", "12345"))
                .contentType("application/json")
                .body(taskRequest)
                .post("http://127.0.0.1:8000/api/v1/task/");

    }

    @Step("[PUT/api/v1/task/{id}] Изменяем параметры задачи с id = {id}")
    public Response updateExistingTask(Integer id, TaskRequest taskRequest) {
        return given()
                .contentType("application/json")
                .pathParam("id", id)
                .body(taskRequest)
                .put("http://127.0.0.1:8000/api/v1/task/{id}");
    }


}
