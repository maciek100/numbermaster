package numbermaster.dto;

public record NumberResponse(
        long number,
        long requestId,
        boolean isPrime,
        boolean isMersenne,
        String message) {
}
