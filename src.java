public class QuantityMeasurementApp {

    // -------------------- ENUM --------------------
    enum LengthUnit {

        FEET(1.0),           // base unit
        INCH(1.0 / 12.0);    // 1 inch = 1/12 feet

        private final double toFeetFactor;

        LengthUnit(double toFeetFactor) {
            this.toFeetFactor = toFeetFactor;
        }

        public double toFeet(double value) {
            return value * toFeetFactor;
        }
    }

    // -------------------- CLASS --------------------
    static class QuantityLength {

        private final double value;
        private final LengthUnit unit;

        // Constructor
        public QuantityLength(double value, LengthUnit unit) {

            if (unit == null) {
                throw new IllegalArgumentException("Unit cannot be null");
            }

            this.value = value;
            this.unit = unit;
        }

        // Convert to base unit (feet)
        private double toFeet() {
            return unit.toFeet(value);
        }

        // Override equals()
        @Override
        public boolean equals(Object obj) {

            // Same reference
            if (this == obj) return true;

            // Null or different type
            if (obj == null || getClass() != obj.getClass())
                return false;

            QuantityLength other = (QuantityLength) obj;

            // Compare after conversion
            return Double.compare(this.toFeet(), other.toFeet()) == 0;
        }

        @Override
        public String toString() {
            return value + " " + unit;
        }
    }

    // -------------------- MAIN METHOD --------------------
    public static void main(String[] args) {

        // Cross-unit comparison
        QuantityLength q1 = new QuantityLength(1.0, LengthUnit.FEET);
        QuantityLength q2 = new QuantityLength(12.0, LengthUnit.INCH);

        System.out.println(q1 + " == " + q2 + " ? " + q1.equals(q2));

        // Same-unit comparison
        QuantityLength q3 = new QuantityLength(1.0, LengthUnit.INCH);
        QuantityLength q4 = new QuantityLength(1.0, LengthUnit.INCH);

        System.out.println(q3 + " == " + q4 + " ? " + q3.equals(q4));

        // Different values
        QuantityLength q5 = new QuantityLength(2.0, LengthUnit.FEET);

        System.out.println(q1 + " == " + q5 + " ? " + q1.equals(q5));

        System.out.println("\nProgram continues...");
    }
}
