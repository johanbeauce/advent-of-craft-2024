package communication;

public record MessageInformations(
        Reindeer reindeer,
        Location currentLocation,
        int numbersOfDaysForComingBack,
        int numberOfDaysBeforeChristmas) {
}
