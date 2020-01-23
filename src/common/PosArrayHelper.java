package common;

public class PosArrayHelper {

    public static String[] emptyPosArray() {
        final String[] actualPosArray = new String[9];
        for (int i = 0; i < actualPosArray.length; i++) {
            actualPosArray[i] = "";
        }
        return actualPosArray;
    }
}
