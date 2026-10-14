import static org.junit.jupiter.api.Assertions.*;
import java.math.BigDecimal;
import org.junit.jupiter.api.Test;

class TaxCalculationServiceTest {
    private final TaxCalculationService service = new TaxCalculationService();

    @Test
    void calculatesTaxWithExemption() {
        var result = service.calculate(new TaxCalculationService.TaxRequest(
                new BigDecimal("1000.00"), new BigDecimal("100.00"),
                new BigDecimal("0.065")));
        assertEquals(new BigDecimal("900.00"), result.netTaxableAmount());
        assertEquals(new BigDecimal("58.50"), result.taxAmount());
        assertEquals(new BigDecimal("958.50"), result.totalAmount());
    }

    @Test
    void roundsToCents() {
        var result = service.calculate(new TaxCalculationService.TaxRequest(
                new BigDecimal("10.00"), BigDecimal.ZERO, new BigDecimal("0.0755")));
        assertEquals(new BigDecimal("0.76"), result.taxAmount());
    }

    @Test
    void rejectsExemptionLargerThanAmount() {
        assertThrows(IllegalArgumentException.class, () -> service.calculate(
                new TaxCalculationService.TaxRequest(new BigDecimal("10"),
                        new BigDecimal("11"), new BigDecimal("0.05"))));
    }

    @Test
    void rejectsInvalidRate() {
        assertThrows(IllegalArgumentException.class, () -> service.calculate(
                new TaxCalculationService.TaxRequest(new BigDecimal("10"),
                        BigDecimal.ZERO, new BigDecimal("1.1"))));
    }
}
