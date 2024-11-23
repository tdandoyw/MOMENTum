package com.momentum.momentum.task.mapper;

import com.momentum.momentum.task.dto.TaskRequestDto;
import com.momentum.momentum.task.dto.TaskResponseDto;
import com.momentum.momentum.task.dto.TaskResponsePageDto;
import com.momentum.momentum.task.model.Task;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class TaskMapper {

    public Task toTaskEntity(TaskRequestDto dto) {
        return new Task.Builder()
                .title(dto.title())
                .description(dto.description())
                .timeFrame(dto.timeFrame())
                .completed(dto.completed())
                .startTime(dto.startTime())
                .build();
    }

    public Task toTaskEntity(TaskResponseDto dto) {
        return new Task.Builder()
                .id(dto.id())
                .title(dto.title())
                .description(dto.description())
                .timeFrame(dto.timeFrame())
                .completed(dto.completed())
                .startTime(dto.startTime())
                .build();
    }
    
    public TaskResponseDto toTaskResponseDto(Task task) {
        return new TaskResponseDto(
                task.getId(),
                task.getTitle(),
                task.getDescription(),
                task.getTimeFrame(),
                task.isCompleted(),
                task.getStartTime());
    }

    public TaskResponsePageDto toTaskResponsePageDto(Task task) {
        return new TaskResponsePageDto(
                task.getId(),
                task.getTitle(),
                task.getTimeFrame(),
                task.isCompleted(),
                task.getStartTime()
        );
    }

    public List<TaskResponseDto> toTaskResponseDtoList(List<Task> tasks) {
        List<TaskResponseDto> taskResponseDtos = new ArrayList<>();
        for (Task task : tasks) {
            taskResponseDtos.add(toTaskResponseDto(task));
        }
        return taskResponseDtos;
    }
}
