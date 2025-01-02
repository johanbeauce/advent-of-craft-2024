package eid;

public class EIDGenerator {

    private final Elf elf;

    public EIDGenerator(Elf elf) {
        this.elf = elf;
    }

    public String generateEID() {
        return getSex() + getBirthday();
    }

    private String getSex() {
        return switch (elf.getSex()) {
            case SLOUBI -> "1";
            case GAGNA -> "2";
            case CATACT -> "3";
        };
    }

    private String getBirthday() {
        var year = elf.getBirthday().getYear();
        return String.valueOf(year).substring(2);
    }
}
