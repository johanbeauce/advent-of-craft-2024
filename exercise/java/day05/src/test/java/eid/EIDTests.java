package eid;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.time.LocalDate;
import java.util.stream.Stream;

import static org.assertj.core.api.Assertions.assertThat;

class EIDTests {
    EIDGenerator eidGenerator;
    Elf elf;

    public static Stream<Arguments> getBirthdayYear() {
        return Stream.of(Arguments.of(
                Elf.builder()
                        .sex(Sex.SLOUBI)
                        .birthday(LocalDate.parse("1912-01-02"))
                        .build(), "112"),
                Arguments.of(Elf.builder()
                        .sex(Sex.GAGNA)
                        .birthday(LocalDate.parse("1914-01-02"))
                        .build(), "214"),
                Arguments.of(Elf.builder()
                        .sex(Sex.CATACT)
                        .birthday(LocalDate.parse("2045-01-02"))
                        .build(), "345"));
    }

    @ParameterizedTest
    @MethodSource("getBirthdayYear")
    void EID_contains_birthday_year(Elf elf,
                                    String expected) {
        eidGenerator = new EIDGenerator(elf);
        assertThat(eidGenerator.generateEID())
                .startsWith(expected);
    }
}