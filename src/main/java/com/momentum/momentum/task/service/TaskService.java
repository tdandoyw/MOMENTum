package com.momentum.momentum.task.service;

import com.momentum.momentum.task.dto.TaskRequestDto;
import com.momentum.momentum.task.dto.TaskResponseDto;
import com.momentum.momentum.task.dto.TaskResponsePageDto;
import com.momentum.momentum.task.model.TimeFrame;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.UUID;

public interface TaskService {

    List<TaskResponseDto> findAllEntitiesByTimeFrame(TimeFrame timeFrame);

    Page<TaskResponsePageDto> findPage(Pageable pageable);

    TaskResponseDto getById(UUID id);

    TaskResponseDto create(TaskRequestDto task);

    TaskResponseDto update(UUID id, TaskRequestDto task);

    void deleteById(UUID id);
}
