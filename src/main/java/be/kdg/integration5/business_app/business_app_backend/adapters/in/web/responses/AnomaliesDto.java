package be.kdg.integration5.business_app.business_app_backend.adapters.in.web.responses;

import java.util.Map;

public record AnomaliesDto(
        Map<String, Map<String, String>> anomalies
) {
}
