package ru.netology.web;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.Selectors;
import com.codeborne.selenide.Selenide;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.Keys;

import java.time.Duration;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.$$;

class RegistrationTest {

    public String generateDate(int days, String pattern) {
        return LocalDate.now().plusDays(days).format(DateTimeFormatter.ofPattern(pattern));
    }

    @Test
    void shouldRegistered() {
        String planingDate = generateDate(4, "dd.MM.yyyy");

        Selenide.open("http://localhost:9999");
        $(".input__box > [placeholder='Город']").setValue("Томск");
        $("[data-test-id='date'] [type='tel']").press(Keys.chord(Keys.SHIFT, Keys.HOME), Keys.DELETE)
                .setValue(planingDate);
        $("[data-test-id='name'] input").setValue("Воропаев Артемий");
        $("[data-test-id='phone'] input").setValue("+79131038871");
        $$(".form-field").find(Condition.text("Я соглашаюсь с условиями обработки и использования" +
                " моих персональных данных")).click();
        $(".button__icon ~ .button__text").click();
        // $(".notification__title + .notification__content").find(Condition.text("Встреча успешно забронирована на "))
        $(Selectors.withText("Встреча успешно забронирована на")).shouldBe(Condition.visible, Duration.ofSeconds(15));
    }
}
