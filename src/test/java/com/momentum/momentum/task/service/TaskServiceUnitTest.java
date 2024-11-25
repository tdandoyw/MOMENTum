package com.momentum.momentum.task.service;

import com.momentum.momentum.core.exceptions.EntityNotFoundMomentumException;
import com.momentum.momentum.task.dto.TaskRequestDto;
import com.momentum.momentum.task.dto.TaskResponseDto;
import com.momentum.momentum.task.mapper.TaskMapper;
import com.momentum.momentum.task.model.Task;
import com.momentum.momentum.task.model.TimeFrame;
import com.momentum.momentum.task.persistence.TaskDao;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.DayOfWeek;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

@ExtendWith(MockitoExtension.class)
class TaskServiceUnitTest {

    @Mock
    TaskDao taskDao;

    @Mock
    TaskMapper taskMapper;

    @InjectMocks
    TaskServiceImpl taskService;

    final UUID taskId = UUID.fromString("11111111-1111-1111-1111-111111111111");
    final UUID taskId2 = UUID.fromString("22222222-2222-2222-2222-222222222222");

    Task setUpTask(UUID id) {
        return new Task.Builder()
                .id(id)
                .build();
    }

    TaskResponseDto setUpTaskDto(UUID id) {
        return new TaskResponseDto(id, null, null, null, true, null);
    }

    @DisplayName("method getById")
    @Nested
    class getByIdTesting {

        @DisplayName("Valid ID")
        @Test
        void SHOULD_RETURN_ENTITY() {
            Task task = setUpTask(taskId);
            Mockito.when(taskDao.findById(taskId))
                    .thenReturn(Optional.ofNullable(task));
            Mockito.when(taskMapper.toTaskResponseDto(task))
                    .thenReturn(setUpTaskDto(taskId));

            assertThat(
                    taskService.getById(taskId).id()
            ).isEqualTo(taskId);
        }

        @DisplayName("Invalid ID")
        @Test
        void SHOULD_THROW_MOMENTUM_EXCEPTION() {
            Mockito.when(taskDao.findById(taskId))
                    .thenReturn(Optional.empty());

            assertThatThrownBy(() -> taskService.getById(taskId)).isInstanceOf(EntityNotFoundMomentumException.class);
        }
    }

    @DisplayName("method findAllEntitiesByTimeFrame")
    @Nested
    class findAllEntitiesByTimeFrameTesting {

        Task setUpTask(UUID id) {
            return new Task.Builder()
                    .id(id)
                    .timeFrame(TimeFrame.MONTHLY)
                    .build();
        }

        List<Task> setUpTaskList() {
            return List.of(
                    setUpTask(taskId),
                    setUpTask(taskId2));
        }

        TaskResponseDto setUpTaskDto(UUID id) {
            return new TaskResponseDto(id, null, null, TimeFrame.MONTHLY, true, null);
        }

        @DisplayName("With existing TimeFrame")
        @Test
        void SHOULD_RETURN_LIST_OF_ENTITIES() {
            List<Task> taskList = setUpTaskList();
            Mockito.when(taskDao.findAllByTimeFrame(TimeFrame.MONTHLY))
                    .thenReturn(taskList);
            Mockito.when(taskMapper.toTaskResponseDtoList(taskList))
                    .thenReturn(
                            List.of(
                                    setUpTaskDto(taskId),
                                    setUpTaskDto(taskId2)
                            )
                    );

            assertThat(taskService.findAllEntitiesByTimeFrame(TimeFrame.MONTHLY))
                    .extracting("id")
                    .containsExactlyInAnyOrder(taskId, taskId2);
        }
    }

    @DisplayName("method create")
    @Nested
    class createTesting {

        TaskRequestDto setUpTaskRequestDto() {
            return new TaskRequestDto(taskId, null, null, null, true, null);
        }

        @DisplayName("With correct data")
        @Test
        void SHOULD_CREATE_ENTITY() {
            Task task = setUpTask(taskId);
            TaskRequestDto taskRequestDto = setUpTaskRequestDto();
            Mockito.when(taskMapper.toTaskEntity(taskRequestDto)).thenReturn(task);
            Mockito.when(taskDao.save(task)).thenReturn(task);
            Mockito.when(taskMapper.toTaskResponseDto(task)).thenReturn(setUpTaskDto(taskId));

            assertThat(taskService.create(taskRequestDto).id()).isEqualTo(taskId);
        }
    }

    @DisplayName("method update")
    @Nested
    class updateTesting {

        TaskRequestDto setUpTaskRequestDto(UUID id) {
            return new TaskRequestDto(id, "moof", "poof", TimeFrame.DAILY, true, DayOfWeek.MONDAY);
        }

        TaskResponseDto setUpTaskDto(UUID id) {
            return new TaskResponseDto(id, "moof", "poof", TimeFrame.DAILY, true, DayOfWeek.MONDAY);
        }

        Task setUpTask(UUID id) {
            return new Task.Builder()
                    .id(id)
                    .title("moof")
                    .description("poof")
                    .timeFrame(TimeFrame.DAILY)
                    .completed(true)
                    .startTime(DayOfWeek.MONDAY)
                    .build();
        }

        @DisplayName("With correct data & ID")
        @Test
        void SHOULD_UPDATE_ENTITY() {
            Task task = setUpTask(taskId);
            TaskRequestDto taskRequestDto = setUpTaskRequestDto(taskId);
            TaskResponseDto taskResponseDto = setUpTaskDto(taskId);
            Mockito.when(taskDao.findById(taskId)).thenReturn(Optional.ofNullable(task));
            Mockito.when(taskMapper.toTaskResponseDto(task)).thenReturn(taskResponseDto);
            Mockito.when(taskMapper.toTaskEntity(taskResponseDto)).thenReturn(task);
            Mockito.when(taskDao.save(task)).thenReturn(task);


            assertThat(taskService.update(taskId, taskRequestDto)).isEqualTo(taskResponseDto);
        }

        @DisplayName("With incorrect ID")
        @Test
        void SHOULD_THROW_MOMENTUM_EXCEPTION() {
            TaskRequestDto taskRequestDto = setUpTaskRequestDto(taskId);
            assertThatThrownBy(() -> taskService.update(taskId, taskRequestDto)).isInstanceOf(EntityNotFoundMomentumException.class);
        }
    }
}