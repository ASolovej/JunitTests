import com.codeborne.selenide.Condition;
import com.codeborne.selenide.Configuration;
import org.junit.jupiter.api.*;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.*;

import java.util.stream.Stream;

import static com.codeborne.selenide.Selenide.*;

public class CityLinkTests {

    @BeforeEach
    public void setUp() {
        Configuration.holdBrowserOpen = true;
        Configuration.pageLoadTimeout = 100000;
        Configuration.browserSize = "1920x1080";
        open("https://www.citilink.ru/catalog/smartfony/");
    }

    @CsvSource({
            "Xiaomi, Xiaomi",
            "Huawei, Huawei",
            "Samsung, Samsung"
    })
    @ParameterizedTest(name = "Поиск товара по фильтру {0}")
    @Tags({@Tag("CRITICAL"), @Tag("UI_TEST")})
    public void checkSearchByCategory(String search, String resultsSearch) {
        $("[data-meta-name='FilterSearch__input']").setValue(search);
        $("[data-meta-name=FilterLabel]").$("label").click();
        $$("[data-meta-name='SnippetProductHorizontalLayout']").first().shouldHave(Condition.text(resultsSearch));
    }


    @CsvFileSource(resources = "/data.csv")
    @ParameterizedTest(name = "Поиск товара по фильтру {0}")
    @Tags({@Tag("CRITICAL"), @Tag("UI_TEST")})
    public void checkSearchByCategory1(String search, String resultsSearch) {
        $("[data-meta-name='FilterSearch__input']").setValue(search);
        $("[data-meta-name=FilterLabel]").$("label").click();
        $$("[data-meta-name='SnippetProductHorizontalLayout']").first().shouldHave(Condition.text(resultsSearch));
    }

    @ValueSource(strings = {"Xiaomi", "Huawei", "Samsing"})
    @ParameterizedTest(name = "Поиск товара по фильтру {0}")
    @Tags({@Tag("CRITICAL"), @Tag("UI_TEST")})
    public void checkSearchByCategory2(String search) {
        $("[data-meta-name='FilterSearch__input']").setValue(search);
        $("[data-meta-name=FilterLabel]").$("label").click();
    }


    static Stream<Arguments> selenideDataProvider() {
        return Stream.of(
                Arguments.of("Xiaomi", "Xiaomi"),
                Arguments.of("Huawei", "Huawei"),
                Arguments.of("Samsung", "Samsung")
        );
    }

    @MethodSource("selenideDataProvider")
    @ParameterizedTest(name = "Поиск товара по фильтру {0}")
    @Tags({@Tag("CRITICAL"), @Tag("UI_TEST")})
    public void checkSearchByCategory3(String search, String resultsSearch) {
        $("[data-meta-name='FilterSearch__input']").setValue(search);
        $("[data-meta-name=FilterLabel]").$("label").click();
        $$("[data-meta-name='SnippetProductHorizontalLayout']").first().shouldHave(Condition.text(resultsSearch));
    }


    @Test
    public void check() {
        System.out.println("Hello");
    }
}
