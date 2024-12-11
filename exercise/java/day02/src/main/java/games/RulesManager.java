package games;

import io.vavr.collection.Map;

public class RulesManager {
    private final Map<Integer, String> rules;

    public RulesManager(Map<Integer, String> rules) {
        this.rules = rules;
    }

    public Map<Integer, String> getRules() {
        return rules;
    }
}
