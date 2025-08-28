package numbermaster.model;

import java.time.LocalDateTime;
public record MersennePrimeRecord  (
    long mersennePrime,
    int exponent,
    LocalDateTime discoveredAt,
    String comment) {}

