package edu.hw3;

import java.util.List;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import static edu.hw3.Task2.clusterize;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

public class Task2Test {

    @ParameterizedTest @DisplayName("Test with balanced brackets in string")
    @CsvSource({"({[()]})[[]],({[()]}),2", "((()))(())()()(()()),((())),5"})
    public void clusterize_shouldClusterizeStringWithBalancedBrackets(
        String input, String expectedFirstCluster, int expectedSize
    ) {
        //Act
        List<String> result = clusterize(input);

        //Assert
        assertThat(result.size()).isEqualTo(expectedSize);
        assertThat(result.get(0)).isEqualTo(expectedFirstCluster);
    }

    @ParameterizedTest @DisplayName("Test with balanced brackets in string")
    @CsvSource({"()[,()", "))[],[]", ")[](,[]"})
    public void clusterize_shouldHandleInputWithBalancedAndUnbalancedPartOfString(
        String input, String expected
    ) {
        //Act
        List<String> result = clusterize(input);

        //Assert
        assertThat(result.get(0)).isEqualTo(expected);
    }

    @ParameterizedTest @DisplayName("Test with unbalanced brackets is string") @CsvSource({"([)]", ")(", "((", "))"})
    public void clusterize_shouldNotClusterizeStringWithUnbalancedBrackets(String unbalancedString) {
        //Act
        List<String> result = clusterize(unbalancedString);

        //Assert
        assertThat(result.size()).isEqualTo(0);
    }

    @Test @DisplayName("Test with empty string") public void clusterize_shouldHandleInputEmptyString() {
        //Arrange
        String input = "";

        //Act
        List<String> result = clusterize(input);

        //Assert
        assertThat(0).isEqualTo(result.size());
    }

    @Test @DisplayName("Test with characters without brackets")
    public void clusterize_shouldHandleInputWithoutBracketsOnlyChars() {
        String input = "abcdefg";
        List<String> result = clusterize(input);

        assertThat(0).isEqualTo(result.size());
    }

}
