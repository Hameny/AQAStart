package lesson2;

import com.codeborne.selenide.*;
import org.openqa.selenium.Cookie;
import org.openqa.selenium.Keys;

import java.io.File;
import java.io.FileNotFoundException;
import java.time.Duration;

import static com.codeborne.selenide.CollectionCondition.*;
import static com.codeborne.selenide.Condition.*;
import static com.codeborne.selenide.Condition.empty;
import static com.codeborne.selenide.Selectors.*;
import static com.codeborne.selenide.Selenide.*;

public class Snippets {
    void browser_command_examples(){

        open(); //- открытие страницы
        open("relax.by", AuthenticationType.BASIC,new BasicAuthCredentials("pavel",
                "123")); //Аутентификация на сайте в табличке вверху

        back();//-назад
        refresh();//-перезагрузка

        clearBrowserCookies();//-очистка куки
        clearBrowserLocalStorage();//-очистка LocalStorage
        executeJavaScript("sessionStorage.clear();");//-sessionStorage

        confirm(); //- ok в сообщении ошибки
        dismiss();//- cancel in alert dialog

        closeWindow(); //-закрыть окно текущее
        closeWebDriver(); // - закрыть хром полностью
        switchTo().frame("new");//-переход на табличку на странице
        switchTo().defaultContent();//-перейти на дефолтную страницу
        switchTo().window("onliner"); //-переход на страницу с названием

        var cookie = new Cookie("foo","bar");
        WebDriverRunner.getWebDriver().manage().addCookie(cookie);
    }
    void selectors_examples(){
        $("div").click();
        element("div").click();

        $("div",2).click(); //-выбрать объекты 3-го div
        $x("//h1//div").click(); //-xpath
        $(byXpath("//h1//div")).click();//-xpath

        $(byText("full text")).click();//- поиск по всему тексту
        $(withText("ull text")).click();//- поиск по части строки
        $(byTagAndText("div","full text"));
        $(withTagAndText("div","ull text"));

        $("").parent();//-родитель
        $("").sibling(1);//-поиск вниз по родителю
        $("").preceding(1);//-поиск вверх по родителю
        $("").closest("div");
        $("").ancestor("div");
        $("div:last-child");

        //$(byId("myText")).click(); = $("#mytext").click();
        //$(byAttribute("myText")).click(); = $("[mytext]").click();
        //$(byClassName("red")).click(); = $(".red").click();

    }
    void actions(){
        $("").click();//-нажатие мышки
        $("").doubleClick();//-двойное нажатие мышки
        $("").contextClick();//-нажатие правой кнопки мыши

        $("").hover();//-подвести мышку

        $("").setValue("text");//-добавить в начало
        $("").append("text");//-добавить в конец
        $("").clear();//-удаляют значение
        $("").setValue("");//--удаляют значение

        $("div").sendKeys("c");//-выбрать элемент и нажать быструю клавишу
        Selenide.actions().sendKeys("c").perform();//-нажать быструю клавишу
        Selenide.actions().sendKeys(Keys.chord(Keys.CONTROL,"f")).perform();//-Ctrl + F
        $("html").sendKeys(Keys.chord(Keys.CONTROL,"f"));//-

        $("").pressEnter();//-
        $("").pressEscape();//-
        $("").pressTab();//-

        Selenide.actions().moveToElement($("div")).clickAndHold().moveByOffset(300,200).release().perform();//-выбрать и выделить текст

        $("").selectOption("dropdown_option");//-дропдаун
        $("").selectRadio("radio_options");//-радио баттон

    }
    void assertion(){
        $("").shouldBe(visible);
        $("").shouldNotBe(visible);
        $("").shouldHave(text("abc"));
        $("").shouldNotHave(text("abc"));
        $("").should(appear);
        $("").shouldNotBe(appear);

        //Длинная задержка времени
        $("").shouldBe(visible, Duration.ofSeconds(30));
    }

    void conditions_examples() {
        $("").shouldBe(visible);
        $("").shouldBe(hidden);

        $("").shouldHave(text("abc"));
        $("").shouldHave(exactText("abc"));
        $("").shouldHave(textCaseSensitive("abc"));
        $("").shouldHave(exactTextCaseSensitive("abc"));
        $("").should(matchText("[0-9]abc$"));//-содержит регулярное выражение

        $("").shouldHave(cssClass("red"));//-проверка наличия класса
        $("").shouldHave(cssValue("font-size", "12"));//-проверка свойства элемента

        $("").shouldHave(value("25"));
        $("").shouldHave(exactValue("25"));
        $("").shouldBe(empty);

        $("").shouldHave(attribute("disabled"));
        $("").shouldHave(attribute("name", "example"));
        $("").shouldHave(attributeMatching("name", "[0-9]abc$"));

        $("").shouldBe(checked); // проверка чек-бокса на включенность

        // Warning! Only checks if it is in DOM, not if it is visible! You don't need it in most tests!
        $("").should(exist);

        // Warning! Checks only the "disabled" attribute! Will not work with many modern frameworks
        $("").shouldBe(disabled);
        $("").shouldBe(enabled);
    }

    void collections_examples() {

        $$("div"); // does nothing!

        $$x("//div"); // by XPath

        // selections
        $$("div").filterBy(text("123")).shouldHave(size(1));
        $$("div").excludeWith(text("123")).shouldHave(size(1));

        $$("div").first().click();
        elements("div").first().click();
        // $("div").click();
        $$("div").last().click();
        $$("div").get(1).click(); // the second! (start with 0)
        $("div", 1).click(); // same as previous
        $$("div").findBy(text("123")).click(); //  finds first

        // assertions
        $$("").shouldHave(size(0));
        $$("").shouldBe(CollectionCondition.empty); // the same

        $$("").shouldHave(texts("Alfa", "Beta", "Gamma"));
        $$("").shouldHave(exactTexts("Alfa", "Beta", "Gamma"));

        $$("").shouldHave(textsInAnyOrder("Beta", "Gamma", "Alfa"));
        $$("").shouldHave(exactTextsCaseSensitiveInAnyOrder("Beta", "Gamma", "Alfa"));

        $$("").shouldHave(itemWithText("Gamma")); // only one text

        $$("").shouldHave(sizeGreaterThan(0));
        $$("").shouldHave(sizeGreaterThanOrEqual(1));
        $$("").shouldHave(sizeLessThan(3));
        $$("").shouldHave(sizeLessThanOrEqual(2));


    }

    void file_operation_examples() throws FileNotFoundException {

        File file1 = $("a.fileLink").download(); // only for <a href=".."> links
        File file2 = $("div").download(DownloadOptions.using(FileDownloadMode.FOLDER)); // more common options, but may have problems with Grid/Selenoid

        File file = new File("src/test/resources/readme.txt");
        $("#file-upload").uploadFile(file);
        $("#file-upload").uploadFromClasspath("readme.txt");
        // don't forget to submit!
        $("uploadButton").click();
    }

    void javascript_examples() {
        executeJavaScript("alert('selenide')");
        executeJavaScript("alert(arguments[0]+arguments[1])", "abc", 12);
        long fortytwo = executeJavaScript("return arguments[0]*arguments[1];", 6, 7);

    }
}
