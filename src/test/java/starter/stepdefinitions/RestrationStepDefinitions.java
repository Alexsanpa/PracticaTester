package starter.stepdefinitions;

import io.cucumber.datatable.DataTable;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.actions.*;
import net.serenitybdd.screenplay.ensure.Ensure;
import net.serenitybdd.screenplay.targets.Target;
import starter.tasks.registration.CheckNewAccountCreated;
import starter.tasks.registration.DoNotSendAllRequeredInformation;
import starter.tasks.registration.RegisterUser;
import starter.ui.registration.RegisterForm;
import starter.ui.registration.RegisterPage;


import java.net.URISyntaxException;
import java.util.List;

public class RestrationStepDefinitions {
    String expecteName = "";
    String ExpectedLastName = "";
    String ExpectedAge = "";
    String ExpectedEmail = "";
    String ExpectedCountry = "";

    @Given("{actor} wants to sign up in the application")
    public void wantsToSignUpInTheAplication(Actor actor) throws URISyntaxException {
        actor.attemptsTo(
                Open.browserOn(new RegisterPage())
        );
    }

    @When("{actor} sends the  requeried inormation to sign up")
    public void pepitoSendsTheRequeriedInormationToSignUp(Actor actor, DataTable userInfo) throws URISyntaxException {
        List<List<String>> rows = userInfo.asLists(String.class);

        for (List<String> columns : rows) {
            expecteName = columns.get(0);
            ExpectedLastName = columns.get(1);
            ExpectedAge = columns.get(2);
            ExpectedCountry = columns.get(3);
            ExpectedEmail = columns.get(4);

        }

        actor.attemptsTo(
                new RegisterUser(expecteName, ExpectedLastName, ExpectedAge, ExpectedCountry, ExpectedEmail)
        );
    }

    @Then("{actor} should have a new account created")
    public void pepitoShouldHaveANewAccountCreated(Actor actor) {
        actor.attemptsTo(
                new CheckNewAccountCreated(expecteName, ExpectedLastName)
        );
    }

    @When("{actor} does not send the required information")
    public void doesNotSendTheRequiredInformation(Actor actor) {
        actor.attemptsTo(
                new DoNotSendAllRequeredInformation(expecteName, ExpectedLastName)

        );
    }

    @Then("{actor} should be told all fields are required")
    public void ShouldBeToldAllFieldsAreRequired(Actor actor) {
        actor.attemptsTo(
                Ensure.that(RegisterForm.TEXT_ALERT)
                        .text().contains("Por favor diligencie todos los campos")

        );
    }

}
