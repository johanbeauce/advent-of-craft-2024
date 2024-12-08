package communication;

public class SantaCommunicator {
    private final int numberOfDaysToRest;
    private final int numberOfDayBeforeChristmas;

    public SantaCommunicator(int numberOfDaysToRest,
                             int numberOfDayBeforeChristmas) {
        this.numberOfDaysToRest = numberOfDaysToRest;
        this.numberOfDayBeforeChristmas = numberOfDayBeforeChristmas;
    }

    public String composeMessage(Reindeer reindeer) {
        var daysBeforeReturn = daysBeforeReturn(reindeer.numbersOfDaysForComingBack());

        return "Dear %s, please return from %s in %d day(s) to be ready and rest before Christmas.".formatted(
                reindeer.name(),
                reindeer.currentLocation(),
                daysBeforeReturn);
    }

    public boolean isOverdue(Reindeer reindeer,
                             Logger logger) {
        if (daysBeforeReturn(reindeer.numbersOfDaysForComingBack()) <= 0) {
            logger.log("Overdue for %s located %s.".formatted(
                    reindeer.name(),
                    reindeer.currentLocation()));
            return true;
        }
        return false;
    }

    private int daysBeforeReturn(int numbersOfDaysForComingBack) {
        return numberOfDayBeforeChristmas - numbersOfDaysForComingBack - numberOfDaysToRest;
    }
}