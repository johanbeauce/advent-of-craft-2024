package eid;

import java.time.LocalDate;

import static java.util.Objects.requireNonNull;

public class Elf {
    private Sex sex;
    private LocalDate birthday;

    private Elf(Builder builder) {
        sex = builder.sex;
        birthday = builder.birthday;
        requireNonNull(sex);
        requireNonNull(birthday);
    }

    public static Builder builder() {
        return new Builder();
    }

    public Sex getSex() {
        return sex;
    }

    public LocalDate getBirthday() {
        return birthday;
    }

    public static final class Builder {
        private Sex sex;
        private LocalDate birthday;

        private Builder() {
        }

        public static Builder builder() {
            return new Builder();
        }

        public Builder sex(Sex val) {
            sex = val;
            return this;
        }

        public Builder birthday(LocalDate val) {
            birthday = val;
            return this;
        }

        public Elf build() {
            return new Elf(this);
        }
    }
}
