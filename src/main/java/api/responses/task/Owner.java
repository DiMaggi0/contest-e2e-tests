package api.responses.task;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;

@Getter
public class Owner {

    Integer id;

    User user;

    Integer points;

    @JsonProperty(value = "solved_tasks")
    Integer solvedTasks;
}