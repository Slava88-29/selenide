package ru.netology.web;

import com.codeborne.selenide.SelenideElement;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.Keys;

import java.time.Duration;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import static com.codeborne.selenide.Condition.*;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.open;

class CallbackTest {
    @Test
    void shouldTest() {
        String date = LocalDate.now().plusDays(4).format(DateTimeFormatter.ofPattern("dd.MM.yyyy"));
        open("http://localhost:9999");
        SelenideElement form = $(".form");
        form.$("[data-test-id=city] input").setValue("Москва");
        form.$("[data-test-id=date] input").doubleClick();
        form.$("[data-test-id=date] input").sendKeys(Keys.DELETE);
        form.$("[data-test-id=date] input").setValue(date);
        form.$("[data-test-id=name] input").setValue("Иван Иванов");
        form.$("[data-test-id=phone] input").setValue("+79110000000");
        form.$("[data-test-id=agreement]").click();
        form.$(".button").click();
        $("[data-test-id=notification]").shouldBe(visible,Duration.ofSeconds(15));
        $("[data-test-id=notification]").shouldHave(text(date));

    }
}

