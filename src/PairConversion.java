import java.time.LocalDate;

public record PairConversion(String base_code, String target_code, double conversion_rate, double conversion_result, LocalDate requestTime) {
}
