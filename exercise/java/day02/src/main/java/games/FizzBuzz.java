package games;

import io.vavr.control.Option;

import static io.vavr.control.Option.none;
import static io.vavr.control.Option.some;

public class FizzBuzz {
    public static final int MIN = 1;
    public static final int MAX = 100;

    private final RulesManager rulesManager;

    public FizzBuzz(RulesManager rulesManager) {
        this.rulesManager = rulesManager;
    }

    public Option<String> convert(int input) {
        return isOutOfRange(input)
                ? none()
                : some(convertSafely(input));
    }

    private String convertSafely(Integer input) {
        return rulesManager.getRules()
                .find(p -> is(p._1, input))
                .map(p -> p._2)
                .getOrElse(input.toString());
    }

    private boolean is(Integer divisor, Integer input) {
        return input % divisor == 0;
    }

    private boolean isOutOfRange(Integer input) {
        return input < MIN || input > MAX;
    }
}