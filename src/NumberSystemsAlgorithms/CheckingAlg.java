package NumberSystemsAlgorithms;

public class CheckingAlg {
    public CheckingAlg() {
    }

    public boolean isBinary(String n) {
        String[] part = n.split("");
        boolean res = false;
        int i = 0;
        try {
            while (i < part.length && !res) {
                if (part[i].equals("1") || part[i].equals("0")) {
                    i++;
                } else res = true;
            }
            return !res;
        } catch (NumberFormatException exception) {
            return false;
        }
    }

    public boolean isDecimal(String n) {
        try {
            if (Long.parseLong(n) >= 0) {
                return true;
            }

        } catch (NumberFormatException e) {
            return false;
        }
        return false;
    }

    public boolean isOctal(String n) {
        String[] part = n.split("");
        boolean res = false;
        int i = 0;
        try {
            Integer.parseInt(n);
            while (i < part.length && !res) {
                if (!(part[i].equals("8") || part[i].equals("9"))) {
                    i++;
                } else res = true;
            }
            return !res;
        } catch (NumberFormatException e) {
            return false;
        }
    }

    public boolean isHex(String n) {
        String[] parts = n.split("");
        String[] parts1 = new String[parts.length];
        boolean res = false;
        int i = 0;
        while (!res && i < parts.length) {
            parts1[i] = parts[i].toUpperCase();
            switch (parts1[i]) {
                case "A", "B", "C", "D", "E", "F", "0", "1", "2", "3", "4", "5", "6", "7", "8", "9" -> i++;
                default -> res = true;
            }
        }
        return !res;
    }
}
