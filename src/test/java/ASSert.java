import com.codeborne.selenide.Configuration;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Selenide.*;

public class ASSert {

    @BeforeAll
    static void beforeAll() {
        Configuration.pageLoadStrategy = "eager";
        Configuration.baseUrl = "https://github.com";
        Configuration.browserSize = "2560x1440";
        Configuration.holdBrowserOpen = false;

    }
    @Test
    void testHUB() {
        open("");
        $(".search-input").$(("[placeholder='Search or jump to...']")).click();
        //$("[placeholder='Search or jump to...']").click();
        $("#query-builder-test").setValue("selenide").pressEnter();
        //$("[class='Link__StyledLink-sc-14289xe-0 bJBoUI']").click();
        $$("[data-testid='results-list']").first().$("span").click();
        $("#repository-container-header").shouldHave(text("""
                selenide
                /
                selenide
                """));
        $("#wiki-tab").click();
        //$("#wiki-body").shouldHave(text("Soft Assertions"));
        //$("a.internal.present[href='/selenide/selenide/wiki/SoftAssertions']").click();
        $(".wiki-more-pages-link button").click();
        $$("[data-filterable-for='wiki-pages-filter'] li").findBy(text("SoftAssertions")).$("a").click();
        $("#wiki-body").shouldHave(text("@ExtendWith({SoftAssertsExtension.class})\n" +
                "class Tests {\n" +
                "  @Test\n" +
                "  void test() {\n" +
                "    Configuration.assertionMode = SOFT;\n" +
                "    open(\"page.html\");\n" +
                "\n" +
                "    $(\"#first\").should(visible).click();\n" +
                "    $(\"#second\").should(visible).click();\n" +
                "  }\n" +
                "}")).shouldHave(text("class Tests {\n" +
                "  @RegisterExtension \n" +
                "  static SoftAssertsExtension softAsserts = new SoftAssertsExtension();\n" +
                "\n" +
                "  @Test\n" +
                "  void test() {\n" +
                "    Configuration.assertionMode = SOFT;\n" +
                "    open(\"page.html\");\n" +
                "\n" +
                "    $(\"#first\").should(visible).click();\n" +
                "    $(\"#second\").should(visible).click();\n" +
                "  }\n" +
                "}"));

    }

}