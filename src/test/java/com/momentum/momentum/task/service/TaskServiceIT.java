package com.momentum.momentum.task.service;

import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.jdbc.SqlGroup;

@SqlGroup({
        @Sql(
                scripts = {"/db/migration/insert_data.sql"},
                executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD),
        @Sql(
                scripts = {"/db/migration/clean_data.sql"},
                executionPhase = Sql.ExecutionPhase.AFTER_TEST_METHOD)
})
public class TaskServiceIT {
}
