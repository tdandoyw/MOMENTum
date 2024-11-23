package com.momentum.momentum.task.dto;

import com.momentum.momentum.task.model.TimeFrame;

import java.time.DayOfWeek;
import java.util.UUID;

public record TaskResponsePageDto(
        UUID id,
        String title,
        TimeFrame timeFrame,
        boolean completed,
        DayOfWeek startTime
) {
}
