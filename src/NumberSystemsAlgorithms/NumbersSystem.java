package NumberSystemsAlgorithms;

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

    public String hexDec(String n) {
        if (checkingAlg.isHex(n))
        {
            if (n.equals("0"))
            {
                return n;
            }
            else{
                String[] part1 = n.split("");
                String[] part=new String[part1.length];
                char []array= new char[part.length];
                long []ascii=new long[part.length];
                int i=0;
                int sum=0;
                while (i< part.length){
                    part[i]=part1[i].toUpperCase();
                    array[i]=part[i].charAt(0);
                    ascii[i]= array[i];
                    if(ascii[i]>47 && ascii[i]<58) {
                        int m = 1;
                        for (int j = part.length - i - 1; j > 0; j--) {
                            m *= 16;
                        }
                        sum += Long.parseLong(part[i]) * m;
                    }
                    if (ascii[i]>64 && ascii[i]<71){
                        int m = 1;
                        for (int j = part.length - i - 1; j > 0; j--) {
                            m *= 16;
                        }
                        sum += (long)(array[i]-55) * m;
                    }
                    i++;
                }
                return String.valueOf(sum);
            }
        }
        return "Write a hexal number";
    }
}
