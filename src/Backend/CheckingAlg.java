package Backend;

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
            if (Long.parseLong(n) > 0) {
                return true;
            }

        } catch (NumberFormatException e) {
            return false;
        }
        return false;
    }
    public boolean isOctal(String n){
        String []part=n.split("");
        boolean res=false;
        int i=0;
        try{
            Integer.parseInt(n);
            while (i < part.length && !res) {
                if (!(part[i].equals("8") || part[i].equals("9"))) {
                    i++;
                } else res = true;
            }
            return !res;
        }catch (NumberFormatException e) {
            return false;
        }
    }
}
