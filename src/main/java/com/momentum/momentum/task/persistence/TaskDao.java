package com.momentum.momentum.task.persistence;

import com.momentum.momentum.task.model.Task;
import com.momentum.momentum.task.model.TimeFrame;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.UUID;

public interface TaskDao extends JpaRepository<Task, UUID> {

    List<Task> findAllByTimeFrame(TimeFrame timeFrame);
}
