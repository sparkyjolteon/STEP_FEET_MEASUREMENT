public class QuantityMeasurementApp {

    // -------------------- ENUM --------------------
    enum LengthUnit {

        FEET(1.0),
        INCH(1.0 / 12.0),
        YARD(3.0),
        CM(0.393701 / 12.0);

        private final double toFeetFactor;

        LengthUnit(double toFeetFactor) {
            this.toFeetFactor = toFeetFactor;
        }

        public double toFeet(double value) {
            return value * toFeetFactor;
        }

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

        // Convert to base unit (feet)
        private double toFeet() {
            return unit.toFeet(value);
        }

        // ---------------- ADD METHOD ----------------

        // Instance method (preferred)
        public QuantityLength add(QuantityLength other) {

            if (other == null) {
                throw new IllegalArgumentException("Other quantity cannot be null");
            }

            // Step 1: Convert both to feet
            double thisFeet = this.toFeet();
            double otherFeet = other.toFeet();

            // Step 2: Add
            double sumFeet = thisFeet + otherFeet;

            // Step 3: Convert back to this unit
            double resultValue = this.unit.fromFeet(sumFeet);

            // Step 4: Return new object
            return new QuantityLength(resultValue, this.unit);
        }

        // Static version (optional)
        public static QuantityLength add(QuantityLength q1, QuantityLength q2) {
            return q1.add(q2);
        }

        // ---------------- EQUALS ----------------
        @Override
        public boolean equals(Object obj) {

            if (this == obj) return true;

            if (obj == null || getClass() != obj.getClass())
                return false;

            QuantityLength other = (QuantityLength) obj;

            return Double.compare(this.toFeet(), other.toFeet()) == 0;
        }

        @Override
        public String toString() {
            return value + " " + unit;
        }
    }

    // -------------------- MAIN --------------------
    public static void main(String[] args) {

        // Example 1: 1 foot + 12 inches = 2 feet
        QuantityLength q1 = new QuantityLength(1.0, LengthUnit.FEET);
        QuantityLength q2 = new QuantityLength(12.0, LengthUnit.INCH);

        QuantityLength result1 = q1.add(q2);
        System.out.println(q1 + " + " + q2 + " = " + result1);

        // Example 2: 1 yard + 1 foot = 4 feet (in yard unit → 1.333 yard)
        QuantityLength q3 = new QuantityLength(1.0, LengthUnit.YARD);
        QuantityLength q4 = new QuantityLength(1.0, LengthUnit.FEET);

        QuantityLength result2 = q3.add(q4);
        System.out.println(q3 + " + " + q4 + " = " + result2);

        // Example 3: 2.54 cm + 1 inch = 2 inches
        QuantityLength q5 = new QuantityLength(2.54, LengthUnit.CM);
        QuantityLength q6 = new QuantityLength(1.0, LengthUnit.INCH);

        QuantityLength result3 = q5.add(q6);
        System.out.println(q5 + " + " + q6 + " = " + result3);

        System.out.println("\nProgram continues...");
    }
}
