import communication.Location;
import communication.MessageInformations;
import communication.Reindeer;
import communication.SantaCommunicator;
import doubles.TestLogger;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class SantaCommunicatorTest {
    private static final String DASHER = "Dasher";
    private static final String NORTH_POLE = "North Pole";

    private final int numberOfDaysToRest = 2;
    private final int numberOfDayBeforeChristmas = 24;
    private final TestLogger logger = new TestLogger();
    private SantaCommunicator communicator;

    @Nested
    class Given_a_santa_communicator {

        @BeforeEach
        void setup() {
            communicator = new SantaCommunicator(numberOfDaysToRest);
        }

        @Nested
        class And_a_message_informations {

            private MessageInformations messageInformations;
            private int numbersOfDaysForComingBack;
            private Reindeer reindeer;
            private Location currentLocation;

            @BeforeEach
            void setUp() {
                reindeer = new Reindeer(DASHER);
                currentLocation = new Location(NORTH_POLE);
            }

            @Test
            void composeMessage() {
                numbersOfDaysForComingBack = 5;
                messageInformations = new MessageInformations(reindeer, currentLocation, numbersOfDaysForComingBack, numberOfDayBeforeChristmas);

                var composedMessage = communicator.composeMessage(messageInformations);

                assertThat(composedMessage)
                        .isEqualTo("Dear Dasher, please return from North Pole in 17 day(s) to be ready and rest before Christmas.");
            }

            @Test
            void shouldDetectOverdueReindeer() {
                numbersOfDaysForComingBack = numberOfDayBeforeChristmas;
                messageInformations = new MessageInformations(reindeer, currentLocation, numbersOfDaysForComingBack, numberOfDayBeforeChristmas);

                var overdue = communicator.isOverdue(
                        messageInformations,
                        logger);

                assertThat(overdue)
                        .isTrue();
                assertThat(logger.getLog())
                        .isEqualTo("Overdue for Dasher located North Pole.");
            }

            @Test
            void shouldReturnFalseWhenNoOverdue() {
                numbersOfDaysForComingBack = numberOfDaysToRest - 1;
                messageInformations = new MessageInformations(reindeer, currentLocation, numbersOfDaysForComingBack, numberOfDayBeforeChristmas);

                var overdue = communicator.isOverdue(
                        messageInformations,
                        logger);

                assertThat(overdue)
                        .isFalse();
            }
        }
    }
}