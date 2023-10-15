package edu.hw2;

import edu.hw2.Task4.CallingInfo;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

public class Task4Test {

    @Test
    @DisplayName("Test for checking correct returning class and methode name")
    void callingInfo_shouldReturnCurrentClassNameAndMethodName() {
        // Arrange
        String expectedClassName = this.getClass().getName();
        String expectedMethodName = "callingInfo_shouldReturnCurrentClassNameAndMethodName";

        // Act
        CallingInfo callingInfo = CallingInfo.callingInfo();

        // Assert
        assertThat(callingInfo.className()).isEqualTo(expectedClassName);
        assertThat(callingInfo.methodName()).isEqualTo(expectedMethodName);
    }
}
