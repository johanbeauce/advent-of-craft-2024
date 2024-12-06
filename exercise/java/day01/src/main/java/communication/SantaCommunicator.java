package communication;

public class SantaCommunicator {
    private final int numberOfDaysToRest;

    public SantaCommunicator(int numberOfDaysToRest) {
        this.numberOfDaysToRest = numberOfDaysToRest;
    }

    public String composeMessage(MessageInformations messageInformations) {

        var daysBeforeReturn = daysBeforeReturn(messageInformations.numbersOfDaysForComingBack(),
                messageInformations.numberOfDaysBeforeChristmas());

        return "Dear %s, please return from %s in %d day(s) to be ready and rest before Christmas.".formatted(
                messageInformations.reindeer().name(),
                messageInformations.currentLocation().name(),
                daysBeforeReturn);
    }

    public boolean isOverdue(MessageInformations messageInformations, Logger logger) {
        if (daysBeforeReturn(messageInformations.numbersOfDaysForComingBack(), messageInformations.numberOfDaysBeforeChristmas()) <= 0) {
            logger.log("Overdue for %s located %s.".formatted(
                    messageInformations.reindeer().name(),
                    messageInformations.currentLocation().name()));
            return true;
        }
        return false;
    }

    private int daysBeforeReturn(int numbersOfDaysForComingBack, int numberOfDaysBeforeChristmas) {
        return numberOfDaysBeforeChristmas - numbersOfDaysForComingBack - numberOfDaysToRest;
    }
}