package api;

import api.requests.auth.TokenRequestBody;
import api.requests.task.TaskRequest;
import api.responses.auth.TokenResponseBody;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.commons.lang3.RandomStringUtils;
import org.springframework.lang.Nullable;

import java.util.Random;

import static io.restassured.RestAssured.given;

public class TestUtils {

    public static <T> T convertStringtoObject(String json, Class<T> clazz) {
        try {
            return new ObjectMapper().readValue(json, clazz);
        } catch (JsonProcessingException e) {
            throw new IllegalArgumentException("Ошибка конвертации json " + e.getMessage());
        }
    }

    public static TaskRequest generateTaskRequestBody(Integer id, String description, String title, String langs, Integer level, @Nullable String owner) {
        return new TaskRequest()
                .id(id)
                .description(description)
                .langs(langs)
                .level(level)
                .title(title)
                .owner(owner);
    }

    public static String getUserAuthToken(String username, String password) {
        return "Token " + convertStringtoObject(given()
                .contentType("application/json")
                .body(new TokenRequestBody()
                        .username(username)
                        .password(password))
                .post("http://127.0.0.1:8000/api/v1/login/").asString(), TokenResponseBody.class).getToken();
    }
}
