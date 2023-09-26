package github.magyarzoli;

public class NumberConvertAuxiliary {

    public static String addNullString(String excepted, long number) {
        String numberName = String.valueOf(number);
        String negative = "null";
        StringBuilder expectedNull = new StringBuilder();
        int count = 0;
        if (numberName.toCharArray()[0] == '-') {
            negative = "minusz";
            numberName = numberName.substring(1, (numberName.length() - 1));
        }
        for (int i = 0; i < numberName.length(); i += 3) {
            count++;
        }
        if (4 - count >= 0) {
            expectedNull.append("null".repeat(Math.max(0, (4 - count))));
        }
        return negative + expectedNull.toString() + excepted;
    }
}