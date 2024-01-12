package lesson1;

import com.codeborne.selenide.Configuration;
import org.junit.jupiter.api.Test;

import java.util.concurrent.locks.Condition;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Selenide.*;


public class LoginTests {

    @Test
    void succsessfullLoginTest(){
        Configuration.holdBrowserOpen = true; //- чтобы браузер не закрывался
        open("https://coursehunter.net/sign-in");
        $(".auth-title").shouldHave(text("What’s new?"));
        $("[name=email]").setValue("pavel.2006.91@mail.ru");
        $("[name=password]").setValue("Ha7meny!").pressEnter();

    }
}
