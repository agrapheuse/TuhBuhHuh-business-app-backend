package be.kdg.integration5.business_app.business_app_backend.adapters.in.web.responses;

import java.time.LocalDateTime;
import java.util.Map;

public record HistoricalDataDto(
        Map<LocalDateTime, Map<String, Double>> measurements
) {
}
