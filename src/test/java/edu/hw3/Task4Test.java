package edu.hw3;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.ValueSource;
import static edu.hw3.Task4.arabicToRoman;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

public class Task4Test {

    @ParameterizedTest
    @DisplayName("Test for numbers in range")
    @CsvSource({"1,I", "15,XV", "115,CXV", "617,DCXVII", "1337,MCCCXXXVII"})
    void arabicToRoman_shouldCorrectlyConvertNumbersInAvailableRange(int arabicValue, String expectedRoman) {
        // Act
        String romanResult = arabicToRoman(arabicValue);

        //Assert
        assertThat(romanResult).isEqualTo(expectedRoman);
        // 1337          617           115
        // 1000 = M      500 = D       100 = C
        // 300 = CCC     100 = C       10 = X
        // 30 = XXX      10 = X        5 = V
        // 7 = VII       7 = VII
    }

    @ParameterizedTest
    @DisplayName("Test for numbers out of range")
    @ValueSource(ints = {-1000, 0, 4000, 9000})
    void arabicToRoman_shouldHandleInputOutOfAvailableRange(int numberOutOfRange) {
        //Act
        String result = arabicToRoman(numberOutOfRange);

        //Assert
        assertThat(result).contains("Error:");
    }
}
