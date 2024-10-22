package calculator.split;

import static calculator.split.StringSplit.getCalFormula;
import static calculator.split.StringSplit.getSeparator;
import static calculator.split.StringSplit.splitCalFormulaBySeparator;
import static calculator.split.StringSplit.stringToNumber;
import static org.junit.jupiter.api.Assertions.assertArrayEquals;
import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class StringSplitTest {
    @Test
    @DisplayName("구분자 문자열에서 '\n'로 구분자를 올바르게 추출했는지 검증")
    void extractCustomSeparatorByEndOfLine() {
        String string = "//abc\n123";
        String customSeparator = "abc,:";

        assertEquals(customSeparator, getSeparator(string));
    }

    @Test
    @DisplayName("구분자를 문자열에서 '\\n'로 구분자를 올바르게 추출했는지 검증")
    void extractCustomSeparatorByEscapeAndN() {
        String string = "//abc\\n123";
        String customSeparator = "abc,:";

        assertEquals(customSeparator, getSeparator(string));
    }

    @Test
    @DisplayName("구분자를 문자열에서 '\n'로 계산해야 하는 문자열을 올바르게 추출했는지 검증")
    void extractCustomCalFormulaByEndOfLine() {
        String string = "//abc\n123";
        String calFormula = "123";

        assertEquals(calFormula, getCalFormula(string));
    }

    @Test
    @DisplayName("구분자를 문자열에서 '\\n'로 계산해야 하는 문자열을 올바르게 추출했는지 검증")
    void extractCustomCalFormulaWithEscapeAndN() {
        String string = "//abc\\n123";
        String calFormula = "123";

        assertEquals(calFormula, getCalFormula(string));
    }

    @Test
    @DisplayName("커스텀 구분자를 가진 문자열에서 첫번째로 나오는 '\n'로 문자열을 자른다.")
    void splitAtFirstEndOfLine() {
        String string = "//abc\n\n123";
        String customSeparator = "abc,:";
        String calFormula = "\n123";

        assertEquals(customSeparator, getSeparator(string));
        assertEquals(calFormula, getCalFormula(string));
    }

    @Test
    @DisplayName("문자열에서 '\n' 뒷 부분이 빈 문자열인 경우")
    void endOfLineAndCalFormulaIsEmpty() {
        String string = "//abc\n";
        String calFormula = "";

        assertEquals(calFormula, getCalFormula(string));
    }

    @Test
    @DisplayName("문자열에서 '\\n' 뒷 부분이 빈 문자열인 경우")
    void escapeAndNAndCalFormulaIsEmpty() {
        String string = "//abc\\n";
        String calFormula = "";

        assertEquals(calFormula, getCalFormula(string));
    }

    @Test
    @DisplayName("기본 구분자 검증")
    void testDefaultSeparator() {
        String string = "1:2,3";
        String defaultSeparator = ",:";

        assertEquals(defaultSeparator, getSeparator(string));
    }

    @Test
    @DisplayName("기본 구분자를 가진 문자열에서 계산해야 하는 문자열 반환 검증")
    void testDefaultCalFormula() {
        String input = "1,2::3";
        String defaultCalFormula = "1,2::3";

        assertEquals(input, defaultCalFormula);
    }

    @Test
    @DisplayName("구분자로 계산해야 하는 문자열을 끊고, 배열을 반환한다.")
    void testSplitStringBySeparator() {
        String calFormula = "12a34b5c";
        String separator = "abc";

        int[] expected = new int[]{12, 34, 5, 0};

        assertArrayEquals(expected, splitCalFormulaBySeparator(calFormula, separator));
    }

    @Test
    @DisplayName("계산할 문자열이 빈 경우에는 길이가 1인 배열 [0]을 반환한다.")
    void emptyString() {
        String calFormula = "";
        String separator = "a";

        int[] expected = new int[]{0};

        assertArrayEquals(expected, splitCalFormulaBySeparator(calFormula, separator));
    }

    @Test
    @DisplayName("계산할 문자열이 구분자로만 이루어진 경우에는 0으로 채워진 배열을 반환한다.")
    void containWithOnlySeparator() {
        String calFormula = "abcabc";
        String separator = "abc";
        int[] expected = new int[]{0, 0, 0, 0, 0, 0, 0};

        assertArrayEquals(expected, splitCalFormulaBySeparator(calFormula, separator));
    }

    @Test
    @DisplayName("문자열을 숫자로 변환한다.")
    void testStringToNumber() {
        String calFormula = "12";
        int expected = 12;

        assertEquals(expected, stringToNumber(calFormula));
    }

    @Test
    @DisplayName("빈 문자열을 숫자 '0'으로 변환한다.")
    void emptyStringToZero() {
        String calFormula = "";
        int expected = 0;
        assertEquals(expected, stringToNumber(calFormula));
    }
}
