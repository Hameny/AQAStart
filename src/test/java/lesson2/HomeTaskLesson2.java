package lesson2;

import com.codeborne.selenide.Configuration;
import com.codeborne.selenide.DragAndDropOptions;
import com.codeborne.selenide.SelenideElement;
import org.junit.jupiter.api.Test;

import static com.codeborne.selenide.Condition.*;
import static com.codeborne.selenide.Selectors.byText;
import static com.codeborne.selenide.Selenide.*;

public class HomeTaskLesson2 {
    SelenideElement title = $("div");
    @Test
    void task1(){
        Configuration.holdBrowserOpen = true;
        open("https://github.com");
        $(byText("Solutions")).hover();
        $(byText("Enterprise")).click();
        title.shouldHave(text("To build, scale, and deliver secure software."));
    }

    @Test
    void searchJUnit5CodeTest() {
        open("https://the-internet.herokuapp.com/drag_and_drop");
        // option 1:
        //$("#column-a").dragAndDrop((DragAndDropOptions) $("#column-b"));
        actions().moveToElement($(byText("A"))).clickAndHold().moveByOffset(250, 0).release().perform();
        $("#column-b").shouldHave(text("A"));
        $("#column-a").shouldHave(text("B"));
    }
}
