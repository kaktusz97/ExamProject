package oop.utils;

public class Util {

    private Util() {
    }

    public final static boolean isBetween(int min, int max, int target) {
        return target >= min && target <= max;
    }

    public final static boolean isNotNull(Object o) {
        return !(o == null);
    }

}
