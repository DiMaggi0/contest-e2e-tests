package api.responses.task;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;

@Getter
public class TaskResponse {

    Integer id;

    String title;

    String description;

    Integer level;

    String langs;

    Owner owner;

    @JsonProperty(value = "sent_solutions")
    Integer sentSolutions;

    String message;
}
