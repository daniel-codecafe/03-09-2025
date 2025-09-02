package nl.codecafe.crud.controllers.exceptions;

import java.time.Instant;

public record ErrorResponse(
        String code,
        String message,
        Instant timestamp
) {}
