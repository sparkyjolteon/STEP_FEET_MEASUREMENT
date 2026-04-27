public class QuantityMeasurementApp {

    // -------------------- ENUM --------------------
    enum LengthUnit {

        FEET(1.0),                      // base unit
        INCH(1.0 / 12.0),               // 1 inch = 1/12 feet
        YARD(3.0),                      // 1 yard = 3 feet
        CM(0.393701 / 12.0);            // 1 cm → inch → feet

        private final double toFeetFactor;

        LengthUnit(double toFeetFactor) {
            this.toFeetFactor = toFeetFactor;
        }

        // Convert given value to feet
        public double toFeet(double value) {
            return value * toFeetFactor;
        }

        // Convert from feet to this unit
        public double fromFeet(double feetValue) {
            return feetValue / toFeetFactor;
        }
    }

    // -------------------- CLASS --------------------
    static class QuantityLength {

        private final double value;
        private final LengthUnit unit;

        public QuantityLength(double value, LengthUnit unit) {
            if (unit == null) {
                throw new IllegalArgumentException("Unit cannot be null");
            }
            if (!Double.isFinite(value)) {
                throw new IllegalArgumentException("Value must be finite");
            }

            this.value = value;
            this.unit = unit;
        }

        // Convert current object to another unit
        public double convertTo(LengthUnit targetUnit) {

            if (targetUnit == null) {
                throw new IllegalArgumentException("Target unit cannot be null");
            }

            // Step 1: Convert to base (feet)
            double valueInFeet = unit.toFeet(value);

            // Step 2: Convert to target unit
            return targetUnit.fromFeet(valueInFeet);
        }

        // Static conversion method
        public static double convert(double value, LengthUnit source, LengthUnit target) {

            if (source == null || target == null) {
                throw new IllegalArgumentException("Units cannot be null");
            }

            if (!Double.isFinite(value)) {
                throw new IllegalArgumentException("Value must be finite");
            }

            // Convert to base (feet)
            double feet = source.toFeet(value);

            // Convert to target
            return target.fromFeet(feet);
        }

        // Equality (from UC4)
        @Override
        public boolean equals(Object obj) {
            if (this == obj) return true;
            if (obj == null || getClass() != obj.getClass()) return false;

            QuantityLength other = (QuantityLength) obj;

            return Double.compare(
                this.unit.toFeet(this.value),
                other.unit.toFeet(other.value)
            ) == 0;
        }

        @Override
        public String toString() {
            return value + " " + unit;
        }
    }

    // -------------------- MAIN --------------------
    public static void main(String[] args) {

        // Instance conversion
        QuantityLength q1 = new QuantityLength(1.0, LengthUnit.FEET);
        double inches = q1.convertTo(LengthUnit.INCH);
        System.out.println("1 foot in inches = " + inches);

        // Static conversion
        double yardsToInches = QuantityLength.convert(1.0, LengthUnit.YARD, LengthUnit.INCH);
        System.out.println("1 yard in inches = " + yardsToInches);

        double cmToFeet = QuantityLength.convert(30.48, LengthUnit.CM, LengthUnit.FEET);
        System.out.println("30.48 cm in feet = " + cmToFeet);

        // Equality still works
        QuantityLength q2 = new QuantityLength(12.0, LengthUnit.INCH);
        System.out.println("1 foot == 12 inches ? " + q1.equals(q2));

        System.out.println("\nProgram continues...");
    }
}
