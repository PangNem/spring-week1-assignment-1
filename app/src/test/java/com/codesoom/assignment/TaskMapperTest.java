package com.codesoom.assignment;

import static org.assertj.core.api.Assertions.assertThat;

import com.codesoom.assignment.models.Task;
import java.io.IOException;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class TaskMapperTest {

    @Test
    @DisplayName("Task를 String으로 변환")
    void toJsonWith() throws IOException {
        TaskMapper taskMapper = new TaskMapper();

        String actual = taskMapper.toJsonWith(new Task(0L, "watch ashal youtube"));

        assertThat(actual).isEqualTo("{\"id\":0,\"title\":\"watch ashal youtube\"}");
    }

    @Test
    @DisplayName("TaskMap을 String으로 변환")
    void toJson() {
        TaskMapper taskMapper = new TaskMapper();

        taskMapper.toJsonWith(new TaskMap());
    }
}
