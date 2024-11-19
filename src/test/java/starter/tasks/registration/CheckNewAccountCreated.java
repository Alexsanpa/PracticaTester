package starter.tasks.registration;

import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.Task;
import net.serenitybdd.screenplay.actions.Switch;
import net.serenitybdd.screenplay.ensure.Ensure;

import starter.ui.registration.SummaryPage;


public class CheckNewAccountCreated implements Task {
    private final String expectedName;
    private final String expectedLastName;

    public CheckNewAccountCreated(String expectedName, String expectedLastName){
        this.expectedName = expectedName;
        this.expectedLastName = expectedLastName;
    }
    @Override
    public <T extends Actor> void performAs(T actor) {
        actor.attemptsTo(

                Switch.toWindowTitled("Summary"),
                Ensure.that(SummaryPage.LABEL_NAME)
                        .text().contains(expectedName),
                Ensure.that(SummaryPage.LABEL_LAST_NAME)
                        .text().contains(expectedLastName)
        );
    }
}
