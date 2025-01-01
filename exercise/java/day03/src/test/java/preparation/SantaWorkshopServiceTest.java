package preparation;

import com.github.javafaker.Faker;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.DisplayNameGeneration;
import org.junit.jupiter.api.DisplayNameGenerator;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import static java.lang.Long.MAX_VALUE;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

@DisplayNameGeneration(DisplayNameGenerator.ReplaceUnderscores.class)
@DisplayName("Given a SantaWorkshopService")
class SantaWorkshopServiceTest {
    private static final String RECOMMENDED_AGE = "recommendedAge";
    private SantaWorkshopService service;
    private Faker faker;
    private String giftName;
    private double weight;
    private String color;
    private String material;

    @BeforeEach
    void setUp() {
        service = new SantaWorkshopService();
        faker = new Faker();
    }

    @Nested
    class Given_a_random_gift {

        @BeforeEach
        void setUp() {
            giftName = faker.commerce().productName();
            color = faker.color().name();
            material = faker.options().option("Cotton", "Wood", "Metal");
        }

        @Nested
        class Weight_under_5 {

            @BeforeEach
            void setUp() {
                weight = faker.number().randomDouble(3, 0, 5);
            }

            @Test
            void prepare_gift_with_valid_toy_should_instantiate_it() {
                var gift = service.prepareGift(giftName, weight, color, material);

                assertThat(gift)
                        .isNotNull();
            }

            @Test
            void retrieve_attribute_on_gift() {
                var gift = service.prepareGift(giftName, weight, color, material);
                gift.addAttribute(RECOMMENDED_AGE, "3");

                assertThat(gift.getRecommendedAge())
                        .isEqualTo(3);
            }
        }

        @Nested
        class Weight_over_5 {
            @BeforeEach
            void setUp() {
                weight = faker.number().randomDouble(3, 6, MAX_VALUE);
            }

            @Test
            void fails_for_a_too_heavy_gift() {
                assertThatThrownBy(() -> service.prepareGift(giftName, weight, color, material))
                        .isInstanceOf(IllegalArgumentException.class)
                        .hasMessage("Gift is too heavy for Santa's sleigh");
            }
        }
    }
}
