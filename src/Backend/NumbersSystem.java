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

    public String decAno(String n, int p) {
        StringBuilder hex = new StringBuilder();
        if (checkingAlg.isDecimal(n)) {
            if (n.equals("0")) {
                return n;
            } else {
                long num = Long.parseLong(n);
                while (num != 0) {
                    long remainder = num % p;
                    if (remainder < 10) {
                        hex.append((char) (remainder + '0'));
                    } else {
                        hex.append((char) (remainder - 10 + 'A'));
                    }
                    num /= p;
                }
                hex.reverse();
                return hex.toString();
            }
        } else return "Please write a positive decimal number";
    }

    public String hexBin(String n) {
        if (checkingAlg.isHex(n)) {
            String[] part1 = n.split("");
            String []part = new String[part1.length];
            StringBuilder bin = new StringBuilder();
            for (int i = 0; i < part.length; i++) {
                part[i]=part1[i].toUpperCase();
                try{
                    if (Long.parseLong(part[i])<8)
                    {
                        bin.append('0').append(decAno(part[i], 2));
                    }
                    else{
                        bin.append(decAno(part[i], 2));
                    }
                }catch (NumberFormatException e)
                {
                    switch (part[i]) {
                        case "A" -> bin.append(decAno("10", 2));
                        case "B" -> bin.append(decAno("11", 2));
                        case "C" -> bin.append(decAno("12", 2));
                        case "D" -> bin.append(decAno("13", 2));
                        case "E" -> bin.append(decAno("14", 2));
                        case "F" -> bin.append(decAno("15", 2));
                    }
                }
            }
            return bin.toString();
        } else {
            return "Please write a hexal number";
        }
    }
}
