package github.magyarzoli;

@SuppressWarnings("unused")
public class NumberConvertParameters {

    static Object[] hunNumberNameResultParameters() {
        return new Object[]{
                new Object[]{1L, "egy"},
                new Object[]{10L, "tíz"},
                new Object[]{100L, "száz"},
                new Object[]{1000L, "ezer"},
                new Object[]{10000L, "tízezer"},
                new Object[]{100000L, "százezer"},
                new Object[]{1000000L, "millió"},
                new Object[]{10000000L, "tízmillió"},
                new Object[]{100000000L, "százmillió"},
                new Object[]{1000000000L, "milliárd"},
                new Object[]{10000000000L, "tízmilliárd"},
                new Object[]{100000000000L, "százmilliárd"}
        };
    }

    static Object[] reverseArrayParameters() {
        return new Object[]{
                new Object[]{
                        new int[]{0, 9 ,1, 8, 2, 7, 3, 6},
                        new int[]{0, 9, 1, 8, 2, 7, 3, 6, 0, 0, 0, 0}},
                new Object[]{
                        new int[]{1, 2, 3},
                        new int[]{1, 2, 3, 0, 0, 0, 0, 0, 0, 0, 0, 0}},
                new Object[]{
                        new int[]{9, 9, 8, 8, 7, 7, 6, 6, 5, 5, 4, 4},
                        new int[]{9, 9, 8, 8, 7, 7, 6, 6, 5, 5, 4, 4}}
        };
    }

    static Object[] reviewNumberParameters() {
        return new Object[]{
                new Object[]{1, 1, 1, new int[]{1, 2, 3, 2}, 3, "-", "-nullkétszázhuszonkét-"},
                new Object[]{0, 2, 2, new int[]{0 ,0, 0, 0}, 9, "", "*"},
                new Object[]{2, 2, 0, new int[]{1, 9, 9, 1}, 6, "-", "nullszázkilencvenkilenc-"},
                new Object[]{8, 7, 5, new int[]{4, 5, 9, 7, 8, 9, 0, 1, 2}, 12, "", "nullkilencszáztizenkét"},
                new Object[]{0, 0, 0, new int[]{0, 0, 1, 1}, 3, "", "*"}
        };
    }

    static Object[] hyphenParameters() {
        return new Object[]{
                new Object[]{new int[]{1, 2, 3, 2}, 3, "-"},
                new Object[]{new int[]{0, 0, 0, 0}, 9, ""},
                new Object[]{new int[]{1, 9, 9, 1}, 6, "-"},
                new Object[]{new int[]{4, 5, 9, 7, 8, 9, 0, 1, 2}, 12, ""},
                new Object[]{new int[]{0, 0, 1, 1}, 3, ""}
        };
    }

    static Object[] branchesParameters() {
        return new Object[]{
                new Object[]{0, 0, 0, "", "", "nulla"},
                new Object[]{1, 0, 0, "", "", "egy"},
                new Object[]{0, 1, 0, "", "", "tíz"},
                new Object[]{0, 0, 1, "", "", "száz"},
                new Object[]{0, 2, 0, "-", "", "-nullhúsz"},
                new Object[]{1, 2, 3, "", "ezer", "nullháromszázhuszonegyezer"},
                new Object[]{4, 5, 6, "-", "száz", "-nullhatszázötvennégyszáz"}
        };
    }
}