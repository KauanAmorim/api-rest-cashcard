package nauakavlis.polaris;

import nauakavlis.polaris.domain.model.CashCard;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.json.JsonTest;
import org.springframework.boot.test.json.JacksonTester;

import java.io.IOException;

import static org.assertj.core.api.Assertions.assertThat;

@JsonTest
public class CashCardJsonTest {

    @Autowired
    private JacksonTester<CashCard> json;

    private final String CashCardJsonPath = "/domain/model/cashcard/cashcard-expected.json";

    @Test
    void cashCardSerializationTest() throws IOException {
        Long cashCardId = 99L;
        Double cashCardAmount = 123.45;

        Integer cashCardIdExpected = 99;
        Double cashCardAmountExpected = 123.45;

        String jsonIdMap = "@.id";
        String jsonAmountMap = "@.amount";

        CashCard cashCard = new CashCard(cashCardId, cashCardAmount);
        assertThat(json.write(cashCard)).isStrictlyEqualToJson(CashCardJsonPath);
        assertThat(json.write(cashCard))
                .hasJsonPathNumberValue(jsonIdMap)
                .extractingJsonPathNumberValue(jsonIdMap)
                .isEqualTo(cashCardIdExpected);
        assertThat(json.write(cashCard))
                .hasJsonPathNumberValue(jsonAmountMap)
                .extractingJsonPathNumberValue(jsonAmountMap)
                .isEqualTo(cashCardAmountExpected);
    }

    @Test
    void cashCardDeserializationTest() throws IOException {

        Long cashCardId = 99L;
        Double cashCardAmount = 123.45;

        Long cashCardIdExpected = 99L;
        Double cashCardAmountExpected = 123.45;

        String expected = """
                {
                    "id":99,
                    "amount":123.45
                }
                """;

        assertThat(json.parse(expected))
                .isEqualTo(new CashCard(cashCardId, cashCardAmount));
        assertThat(json.parseObject(expected).id()).isEqualTo(cashCardIdExpected);
        assertThat(json.parseObject(expected).amount()).isEqualTo(cashCardAmountExpected);
    }
}
