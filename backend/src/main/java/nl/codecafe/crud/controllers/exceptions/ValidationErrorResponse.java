package nl.codecafe.crud.controllers.exceptions;

import java.time.Instant;
import java.util.Map;

public record ValidationErrorResponse(
        String code,
        String message,
        Map<String, String> fieldErrors,
        Instant timestamp
) {}

