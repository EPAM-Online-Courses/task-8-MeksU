package efs.task.unittests;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.EnumSource;

import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class PlannerTest {
    Planner planner = new Planner();

    @BeforeEach
    public void resetPlanner() {
        planner = new Planner();
    }

    @ParameterizedTest(name = "Activity Level = {0}")
    @EnumSource(ActivityLevel.class)
    void shouldCalculateCorrectDailyCaloriesDemand(ActivityLevel activityLevel)
    {
        // Given
        User user = TestConstants.TEST_USER;
        int expectedDemand = TestConstants.CALORIES_ON_ACTIVITY_LEVEL.get(activityLevel);

        // When
        int calculatedDemand = planner.calculateDailyCaloriesDemand(user, activityLevel);

        // Then
        assertEquals(expectedDemand, calculatedDemand);
    }

    @Test
    void shouldCalculateCorrectDailyIntake()
    {
        // Given
        User user = TestConstants.TEST_USER;
        DailyIntake expectedDailyIntake = TestConstants.TEST_USER_DAILY_INTAKE;

        // When
        DailyIntake calculatedDailyIntake = planner.calculateDailyIntake(user);

        // Then
        assertAll(
                () -> assertEquals(expectedDailyIntake.getCalories(), calculatedDailyIntake.getCalories()),
                () -> assertEquals(expectedDailyIntake.getProtein(), calculatedDailyIntake.getProtein()),
                () -> assertEquals(expectedDailyIntake.getFat(), calculatedDailyIntake.getFat()),
                () -> assertEquals(expectedDailyIntake.getCarbohydrate(), calculatedDailyIntake.getCarbohydrate())
        );
    }
}
