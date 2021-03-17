import com.codeborne.selenide.CollectionCondition;
import com.codeborne.selenide.Configuration;
import com.codeborne.selenide.Selectors;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.io.TempDir;

import java.io.File;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Selectors.by;
import static com.codeborne.selenide.Selectors.byText;
import static com.codeborne.selenide.Selenide.*;

public class DemoQaForm {

    String
            firstName = "Maksim",
            lastName = "Demsky",
            userEmail = "maksimdemsky@gmail.com",
            mobile = "7777777777",
            birth = "07 March,1997",
            hobby1 = "Sports",
            hobby2 = "Music",
            address = "Minsk, Belarus",
            state = "NCR",
            city = "Delhi",
            stateAndCity = "NCR Delhi",
            fullName = "Maksim Demsky",
            hobbies = "Sports, Music",
            acceptFormText = "Thanks for submitting the form";


    @BeforeAll
    static void setup() {
        Configuration.startMaximized = true;
    }

    @Test
    void TestForm() {

        open("https://demoqa.com/automation-practice-form");
        $("#firstName").setValue(firstName);
        $("#lastName").setValue(lastName);
        $("#userEmail").setValue(userEmail);
        $(byText("Male")).click();
        $("#userNumber").setValue(mobile);
        $("#dateOfBirthInput").click();
        $(".react-datepicker__month-select").click();
        $(byText("March")).click();
        $(".react-datepicker__year-select").click();
        $(byText("1997")).click();
        $(".react-datepicker__month").find(byText("7")).click();
        $("#subjectsInput").setValue("Ph").pressEnter();
        $("#hobbiesWrapper").find(byText(hobby1)).click();
        $("#hobbiesWrapper").find(byText(hobby2)).click();
        $("#uploadPicture").uploadFile(new File("src/test/resources/123.jpg"));
        $("#currentAddress").setValue(address);
        $("#stateCity-wrapper").find(byText("Select State")).click();
        $(byText(state)).click();
        $("#stateCity-wrapper").find(byText("Select City")).click();
        $(byText(city)).click();
        $("#submit").click();

        //Проверка формы
        $(".modal-content").shouldHave(text(acceptFormText),
                text(fullName),
                text(userEmail),
                text(mobile),
                text("Male"),
                text(birth),
                text("Physics"),
                text(hobbies),
                text("123.jpg"),
                text(address),
                text(stateAndCity));
        $("#closeLargeModal").click();

    }


}
