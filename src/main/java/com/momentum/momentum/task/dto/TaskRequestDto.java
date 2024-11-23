package com.momentum.momentum.task.dto;

import com.momentum.momentum.task.model.TimeFrame;

import java.time.DayOfWeek;
import java.util.UUID;

public record TaskRequestDto(
        UUID id,
        String title,
        String description,
        TimeFrame timeFrame,
        boolean completed,
        DayOfWeek startTime
) {
}