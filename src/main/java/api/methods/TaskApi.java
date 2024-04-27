package api.methods;

import api.responses.task.TaskResponse;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import static io.restassured.RestAssured.given;

@Service
@RequiredArgsConstructor
public class TaskApi {
    public TaskResponse[] getTasksList() {

        return convertStringtoObject(given()
                .get("http://127.0.0.1:8000/api/v1/task/")
                .asString(), TaskResponse[].class);
    }

    public <T> T convertStringtoObject(String json, Class<T> clazz) {
        try {
            return new ObjectMapper().readValue(json, clazz);
        } catch (JsonProcessingException e) {
            throw new IllegalArgumentException("Ошибка конвертации json " + e.getMessage());
        }
    }
}
