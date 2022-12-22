package Backend;

public class NumbersSystem {
    private final CheckingAlg checkingAlg;

    public NumbersSystem() {
        checkingAlg = new CheckingAlg();
    }

    public String binDec(String n) {
        long s = 0, i = 0;
        if (checkingAlg.isBinary(n)) {
            long num = Long.parseLong(n);
            while (num > 0) {
                if (num % 10 == 1) {
                    int m = 1;
                    for (int j = 0; j < i; j++) {
                        m *= 2;
                    }
                    s += m;
                }
                i++;
                num /= 10;
            }
            return String.valueOf(s);
        } else return "It isn't a binary number";
    }

    public String decBin(String n) {
        if (checkingAlg.isDecimal(n)) {
            long num = Long.parseLong(n);
            if (num == 0) {
                return "0";
            } else {
                StringBuilder binaryNumber = new StringBuilder();
                long quotient = num;

                while (quotient > 0) {
                    long remainder = quotient % 2;
                    binaryNumber.append(remainder);
                    quotient /= 2;
                }
                binaryNumber.reverse();

                return binaryNumber.toString();
            }
        } else return "Please write a positive decimal number";
    }
}
