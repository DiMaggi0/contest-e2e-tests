package api.responses.test;

import api.responses.task.TaskResponse;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;

@Getter
public class TestResponse {

    private Integer id;

    private String status;

    private TaskResponse task;

    private String input;

    private String output;

    @JsonProperty(value = "test_number")
    private Integer testNumber;

}
