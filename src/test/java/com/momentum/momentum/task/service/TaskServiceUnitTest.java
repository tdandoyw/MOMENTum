package com.momentum.momentum.task.service;

import com.momentum.momentum.task.model.Task;
import com.momentum.momentum.task.persistence.TaskDao;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;
import java.util.UUID;

import static org.assertj.core.api.Assertions.assertThat;

@ExtendWith(MockitoExtension.class)
class TaskServiceUnitTest {

    @Mock
    TaskDao taskDao;

    @InjectMocks
    TaskServiceImpl taskService;

    final UUID taskId = UUID.fromString("1");

    Task setUpTask(UUID id) {
        return new Task.Builder()
                .id(id)
                .build();
    }

    @DisplayName("method getById")
    @Nested
    class getByIdTesting {

        @DisplayName("Valid ID")
        @Test
        void SHOULD_RETURN_ENTITY() {
            Mockito.when(taskDao.findById(taskId))
                    .thenReturn(Optional.ofNullable(setUpTask(taskId)));

            assertThat(
                    taskService.getById(taskId).id()
            ).isEqualTo(taskId);
        }

        @DisplayName("invalid ID")
        @Test
        void SHOULD_THROW_MOMENTUM_EXCEPTION() {}
    }
    @Test
    void findAllEntitiesByTimeFrame() {
    }

    @Test
    void findPage() {
    }

    @Test
    void getById() {
    }

    @Test
    void create() {
    }

    @Test
    void update() {
    }

    @Test
    void deleteById() {
    }
}