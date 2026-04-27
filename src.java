public class QuantityMeasurementApp {

    // Inner class representing Feet measurement
    static class Feet {
        private final double value; // immutable

        // Constructor
        public Feet(double value) {
            this.value = value;
        }

        // Getter (optional)
        public double getValue() {
            return value;
        }

        // Override equals() method
        @Override
        public boolean equals(Object obj) {

            // 1. Same reference check (reflexive)
            if (this == obj) {
                return true;
            }

            // 2. Null check
            if (obj == null) {
                return false;
            }

            // 3. Type check
            if (this.getClass() != obj.getClass()) {
                return false;
            }

            // 4. Cast safely
            Feet other = (Feet) obj;

            // 5. Compare using Double.compare()
            return Double.compare(this.value, other.value) == 0;
        }
    }

    // Main method
    public static void main(String[] args) {

        Feet f1 = new Feet(1.0);
        Feet f2 = new Feet(1.0);

        boolean result = f1.equals(f2);

        System.out.println("Are values equal? " + result);
    }
}
