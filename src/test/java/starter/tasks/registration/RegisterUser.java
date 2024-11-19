package starter.tasks.registration;

import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.Task;
import net.serenitybdd.screenplay.actions.Click;
import net.serenitybdd.screenplay.actions.Enter;
import net.serenitybdd.screenplay.actions.SelectFromOptions;
import net.serenitybdd.screenplay.actions.Upload;
import net.serenitybdd.screenplay.targets.Target;
import starter.ui.registration.RegisterForm;

import java.net.URISyntaxException;

public class RegisterUser implements Task {
    private final String expecteName;
    private final String expectedLastName;
    private final String expectedAge;
    private final String ExpectedEmail;
    private final String ExpectedCountry;

public RegisterUser(String expectedName,String expectedLastName,String expectedAge, String ExpectedCountry,  String ExpectedEmail){
    this.expecteName = expectedName;
    this.expectedLastName = expectedLastName;
    this.expectedAge = expectedAge;
    this.ExpectedEmail = ExpectedEmail;
    this.ExpectedCountry = ExpectedCountry;

}
    @Override
    public <T extends Actor> void performAs(T actor) {
        try {
            actor.attemptsTo(
                    Enter.theValue(expecteName).into(RegisterForm.INPUT_NAME),
                    Enter.theValue(expectedLastName).into(RegisterForm.INPUT_LASTNAME),
                    Enter.theValue(expectedAge).into(RegisterForm.INPUT_AGE),
                    SelectFromOptions.byVisibleText(ExpectedCountry).from(RegisterForm.SELECT_COUNTRY),
                    Click.on(RegisterForm.INPUT_GENERE),
                    Enter.theValue(ExpectedEmail).into(RegisterForm.INPUT_EMAIL),
                    Click.on(RegisterForm.INPUT_MONDAY),
                    Click.on(RegisterForm.INPUT_THURSDAY),
                    Upload.theClasspathResource("picture/img.png").to(RegisterForm.UPLOAD_PICTURE),
                    Click.on(RegisterForm.BUTTON_SAVE)
            );
        } catch (URISyntaxException e) {
            throw new RuntimeException(e);
        }

    }
}
