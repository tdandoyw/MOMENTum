package com.momentum.momentum.task.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;

import java.time.DayOfWeek;
import java.util.Objects;
import java.util.UUID;

@Entity
public class Task {

    @Id
    @Column(updatable = false, unique = true)
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @NotNull private String title;

    private String description;

    @NotNull private TimeFrame timeFrame;

    @NotNull private boolean completed = false;

    private DayOfWeek startTime;

    public Task() {
        // Leave empty for Spring Boot
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public @NotNull String getTitle() {
        return title;
    }

    public void setTitle(@NotNull String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public @NotNull TimeFrame getTimeFrame() {
        return timeFrame;
    }

    public void setTimeFrame(@NotNull TimeFrame timeFrame) {
        this.timeFrame = timeFrame;
    }

    @NotNull
    public boolean isCompleted() {
        return completed;
    }

    public void setCompleted(@NotNull boolean completed) {
        this.completed = completed;
    }

    public DayOfWeek getStartTime() {
        return startTime;
    }

    public void setStartTime(DayOfWeek startTime) {
        this.startTime = startTime;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Task task = (Task) o;
        return Objects.equals(id, task.id);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(id);
    }

    @Override
    public String toString() {
        return "Task{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", description='" + description + '\'' +
                ", timeFrame=" + timeFrame +
                ", completed=" + completed +
                ", StartTime='" + startTime + '\'' +
                '}';
    }


    public static final class Builder {
        private UUID id;
        private @NotNull String title;
        private String description;
        private @NotNull TimeFrame timeFrame;
        private @NotNull boolean completed;
        private DayOfWeek startTime;

        public Builder() {
        }

        public static Builder Task() {
            return new Builder();
        }

        public Builder id(UUID id) {
            this.id = id;
            return this;
        }

        public Builder title(String title) {
            this.title = title;
            return this;
        }

        public Builder description(String description) {
            this.description = description;
            return this;
        }

        public Builder timeFrame(TimeFrame timeFrame) {
            this.timeFrame = timeFrame;
            return this;
        }

        public Builder completed(boolean completed) {
            this.completed = completed;
            return this;
        }

        public Builder startTime(DayOfWeek startTime) {
            this.startTime = startTime;
            return this;
        }

        public Task build() {
            Task task = new Task();
            task.setId(id);
            task.setTitle(title);
            task.setDescription(description);
            task.setTimeFrame(timeFrame);
            task.setCompleted(completed);
            task.setStartTime(startTime);
            return task;
        }
    }
}
