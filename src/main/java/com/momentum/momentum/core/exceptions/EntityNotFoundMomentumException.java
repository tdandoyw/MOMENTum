package com.momentum.momentum.core.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.http.ProblemDetail;
import org.springframework.web.ErrorResponseException;

import java.util.UUID;

public class EntityNotFoundMomentumException extends ErrorResponseException {
    public EntityNotFoundMomentumException(String entity, UUID id) {
        super(HttpStatus.NOT_FOUND,
                ProblemDetail.forStatusAndDetail(
                        HttpStatus.NOT_FOUND, (entity + " with ID " + id.toString() + " does not exist.")),
                null);
                super.setTitle("MOMENTUMEx-001 : Entity not found");
    }
}
