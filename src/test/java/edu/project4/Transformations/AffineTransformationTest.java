package edu.project4.Transformations;

import edu.project4.BaseObjects.Point;
import java.util.Random;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.RepeatedTest;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

public class AffineTransformationTest {
    @RepeatedTest(5)
    @DisplayName("getTransformation - new Point not equals original")
    public void getTransformation_AffineTrans_NewNotEqualsOriginal() {
        //Arrange
        AffineTransformation transformation = new AffineTransformation(new Random());

        for (int i = 0; i < 10; i++) {
            Point originalPoint = new Point(Math.random(), Math.random());

            //Act
            Point transformedPoint = transformation.getTransformation(originalPoint);

            //Assert
            assertThat(transformedPoint).isNotNull();
            assertThat(transformedPoint).isNotEqualTo(originalPoint);
        }
    }

}
