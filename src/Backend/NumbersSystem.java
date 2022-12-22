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

    public String decAno(String n, int p) {
        if (checkingAlg.isDecimal(n)) {
            if (n.equals("0")) {
                return n;
            } else {
                StringBuilder binaryNumber = new StringBuilder();
                long quotient = Long.parseLong(n);

                while (quotient > 0) {
                    long remainder = quotient % p;
                    binaryNumber.append(remainder);
                    quotient /= p;
                }
                binaryNumber.reverse();

                return binaryNumber.toString();
            }
        } else return "Please write a positive decimal number";
    }

    public String octDec(String n) {
        if (checkingAlg.isOctal(n)) {
            if (n.equals("0")) {
                return n;
            } else {
                String[] part = n.split("");
                long[] num = new long[part.length];
                long sum = 0;
                int i = 0;
                while (i < num.length) {
                    num[i] = Long.parseLong(part[i]);
                    int m = 1;
                    for (int j = part.length - i - 1; j > 0; j--) {
                        m *= 8;
                    }
                    sum += num[i] * m;
                    i++;
                }
                return String.valueOf(sum);
            }

        } else return "Please write a octal number";
    }
}
