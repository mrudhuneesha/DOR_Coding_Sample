import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.Objects;

/**
 * Illustrative tax calculation service using a configurable rate.
 * Original portfolio example; not based on an employer's source code.
 */
public final class TaxCalculationService {
    private static final BigDecimal ONE = BigDecimal.ONE;

    public TaxResult calculate(TaxRequest request) {
        Objects.requireNonNull(request, "request must not be null");
        validateNonNegative(request.taxableAmount(), "taxableAmount");
        validateNonNegative(request.exemptAmount(), "exemptAmount");
        validateNonNegative(request.taxRate(), "taxRate");
        if (request.taxRate().compareTo(ONE) > 0) {
            throw new IllegalArgumentException("taxRate must be at most 1");
        }
        if (request.exemptAmount().compareTo(request.taxableAmount()) > 0) {
            throw new IllegalArgumentException("exemptAmount exceeds taxableAmount");
        }

        BigDecimal netTaxable = request.taxableAmount().subtract(request.exemptAmount());
        BigDecimal tax = netTaxable.multiply(request.taxRate())
                .setScale(2, RoundingMode.HALF_UP);
        BigDecimal total = netTaxable.add(tax).setScale(2, RoundingMode.HALF_UP);
        return new TaxResult(netTaxable.setScale(2, RoundingMode.HALF_UP), tax, total);
    }

    private static void validateNonNegative(BigDecimal amount, String field) {
        Objects.requireNonNull(amount, field + " must not be null");
        if (amount.signum() < 0) {
            throw new IllegalArgumentException(field + " must not be negative");
        }
    }

    public record TaxRequest(BigDecimal taxableAmount, BigDecimal exemptAmount,
                             BigDecimal taxRate) { }

    public record TaxResult(BigDecimal netTaxableAmount, BigDecimal taxAmount,
                            BigDecimal totalAmount) { }
}
