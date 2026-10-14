# Java Tax Calculation Coding Sample

**Author:** Mrudhuneesha S.  
**Purpose:** Original illustrative coding sample for the Washington State Department of Revenue Application Development – Journey application.

## Overview

`TaxCalculationService` calculates a simplified tax amount from a taxable amount, exemption, and decimal tax rate. It demonstrates:

- Object-oriented design and immutable request/result records (Java 17+)
- `BigDecimal` arithmetic for monetary values
- Input validation and meaningful exceptions
- Separation of calculation logic from calling code
- JUnit 5 unit tests covering normal, rounding, and invalid inputs

## Calculation

`netTaxable = taxableAmount - exemptAmount`  
`tax = round(netTaxable * taxRate, 2)`  
`total = netTaxable + tax`

For example, a $1,000 amount with a $100 exemption and a 6.5% rate produces $58.50 tax.

## How to review

The implementation is in `TaxCalculationService.java`. Unit tests are in `TaxCalculationServiceTest.java` and require JUnit 5. Java 17+ is required for records. You can compile the implementation with:

```sh
javac TaxCalculationService.java
```

To execute the tests, use a Java 17+ project with JUnit Jupiter 5 configured.

## Scope and limitations

This is a **fictional, simplified example**, not a production tax engine and not an implementation of Washington tax law or FAST GenTax. Rates and exemptions are caller-provided, and real tax calculations require jurisdiction-specific rules, effective dates, exemptions, and auditing. It contains no proprietary employer code.
