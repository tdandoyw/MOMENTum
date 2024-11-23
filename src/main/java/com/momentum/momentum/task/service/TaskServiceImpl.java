package com.momentum.momentum.task.service;

import com.momentum.momentum.task.dto.TaskRequestDto;
import com.momentum.momentum.task.dto.TaskResponseDto;
import com.momentum.momentum.task.dto.TaskResponsePageDto;
import com.momentum.momentum.task.mapper.TaskMapper;
import com.momentum.momentum.task.model.Task;
import com.momentum.momentum.task.model.TimeFrame;
import com.momentum.momentum.task.persistence.TaskDao;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.UUID;

@Service
@Transactional(readOnly = true)
public class TaskServiceImpl implements TaskService{

    private final TaskDao taskDao;
    private final TaskMapper taskMapper;

    public TaskServiceImpl(
            TaskDao taskDao,
            TaskMapper taskMapper
    ) {
        this.taskDao = taskDao;
        this.taskMapper = taskMapper;
    }

    @Override
    public List<TaskResponseDto> findAllEntitiesByTimeFrame(TimeFrame timeFrame) {
        return taskMapper.toTaskResponseDtoList(
                taskDao.findAllByTimeFrame(timeFrame)
        );
    }

    @Override
    public Page<TaskResponsePageDto> findPage(Pageable pageable) {
        return taskDao.findAll(pageable).map(taskMapper::toTaskResponsePageDto);
    }

    @Override
    public TaskResponseDto getById(UUID id) {
        return taskMapper.toTaskResponseDto(
                taskDao.findById(id)
                        .orElseThrow(() -> new EntityNotFoundException(id.toString()))
        );
    }

    @Override
    @Transactional
    public TaskResponseDto create(TaskRequestDto task) {
        Task savedTask = taskDao.save(taskMapper.toTaskEntity(task));
        return taskMapper.toTaskResponseDto(savedTask);
    }

    @Override
    @Transactional
    public TaskResponseDto update(UUID id, TaskRequestDto task) {
        Task savedTask = taskMapper.toTaskEntity(getById(id));

        savedTask.setTitle(task.title());
        savedTask.setDescription(task.description());
        savedTask.setTimeFrame(task.timeFrame());
        savedTask.setCompleted(task.completed());
        savedTask.setStartTime(task.startTime());

        taskDao.save(savedTask);
        return taskMapper.toTaskResponseDto(savedTask);
    }

    @Override
    @Transactional
    public void deleteById(UUID id) {
        taskDao.deleteById(id);
    }
}
