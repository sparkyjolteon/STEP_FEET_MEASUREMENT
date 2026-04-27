public class QuantityMeasurementApp {

    // -------------------- ENUM --------------------
    enum LengthUnit {

        FEET(1.0),                      // base unit
        INCH(1.0 / 12.0),               // 1 inch = 1/12 feet
        YARD(3.0),                      // 1 yard = 3 feet
        CM(0.393701 / 12.0);            // 1 cm = 0.393701 inch → convert to feet

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

    // -------------------- MAIN METHOD --------------------
    public static void main(String[] args) {

        // Feet ↔ Inches
        QuantityLength q1 = new QuantityLength(1.0, LengthUnit.FEET);
        QuantityLength q2 = new QuantityLength(12.0, LengthUnit.INCH);

        System.out.println(q1 + " == " + q2 + " ? " + q1.equals(q2));

        // Yards ↔ Feet
        QuantityLength q3 = new QuantityLength(1.0, LengthUnit.YARD);
        QuantityLength q4 = new QuantityLength(3.0, LengthUnit.FEET);

        System.out.println(q3 + " == " + q4 + " ? " + q3.equals(q4));

        // CM ↔ Inches
        QuantityLength q5 = new QuantityLength(2.54, LengthUnit.CM); // 2.54 cm = 1 inch
        QuantityLength q6 = new QuantityLength(1.0, LengthUnit.INCH);

        System.out.println(q5 + " == " + q6 + " ? " + q5.equals(q6));

        // Mixed comparison
        QuantityLength q7 = new QuantityLength(36.0, LengthUnit.INCH);
        QuantityLength q8 = new QuantityLength(1.0, LengthUnit.YARD);

        System.out.println(q7 + " == " + q8 + " ? " + q7.equals(q8));

        System.out.println("\nProgram continues...");
    }
}
