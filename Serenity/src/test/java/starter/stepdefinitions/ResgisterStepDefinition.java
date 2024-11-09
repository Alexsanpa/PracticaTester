package starter.stepdefinitions;

import io.cucumber.java.en.Given;
import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.Performable;
import net.serenitybdd.screenplay.actions.*;
import net.serenitybdd.screenplay.ensure.Ensure;
import net.serenitybdd.screenplay.targets.Target;

import java.net.URISyntaxException;

public class ResgisterStepDefinition {

    @Given("{actor} quiere ingresar a la aplicacion de manera correcta")
    public void quiroIngresarAplicacion(Actor actor)throws URISyntaxException {
        String expectedName = "Guilmar";
        String expectedLastname = "Sanchez";
        String expectedAge = "26";
        String expectedCountry = "Colombia";
        String expectedEmail = "pepito@gmail.com";

        actor.attemptsTo(
                Open.url("http://127.0.0.1:5500/registration/register.html"),
                Enter.theValue(expectedName).into("//input[@id='name']"),
                Enter.theValue(expectedLastname).into("(//input[@id='last-name'])"),
                Enter.theValue(expectedAge).into("(//input[@id='age'])"),
                SelectFromOptions.byVisibleText(new String[]{expectedCountry}).from("(//select[@id='country'])"),
                Click.on("//input[@id='sex-m']"),
                Enter.theValue(expectedEmail).into("(//input[@id='email'])"),
                Click.on("(//input[@id='monday'])"),
                Upload.theClasspathResource("picture/img.png")
                        .to(Target.the("pictureField").locatedBy("//input[@id='picture']")),
                Click.on("//button[@id='save-btn']"),

                Switch.toWindowTitled("Summary"),
                Ensure.that(Target.the("NAME ELEMENT")
                        .locatedBy("//strong[contains(text(), 'Nombre')]/parent::p")).text().contains(expectedName),
                Ensure.that(Target.the("LASTNAME ELEMENT")
                        .locatedBy("//strong[contains(text(), 'Apellido:')]/parent::p")).text().contains(expectedLastname),
                Ensure.that(Target.the("AGE ELEMENT")
                        .locatedBy("//strong[contains(text(), 'Edad')]/parent::p")).text().contains(expectedAge)
        );
    }
}
