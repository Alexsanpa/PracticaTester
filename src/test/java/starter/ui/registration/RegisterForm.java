package starter.ui.registration;

import net.serenitybdd.screenplay.actions.Enter;
import net.serenitybdd.screenplay.targets.Target;

public class RegisterForm {
    public static Target INPUT_NAME = Target.the("Input Name").locatedBy("//input[@id='name']");
    public static Target INPUT_LASTNAME = Target.the("Input Last Name").locatedBy("//input[@id='last-name']");
    public static Target INPUT_AGE = Target.the("Input Age").locatedBy("//input[@id='age']");
    public static Target SELECT_COUNTRY = Target.the("Select Country").locatedBy("//select[@id='country']");
    public static Target INPUT_GENERE = Target.the("Select Gener").locatedBy("//input[@id='sex-m']");
    public static Target INPUT_EMAIL = Target.the("Input Email").locatedBy("//input[@id='email']");
    public static Target INPUT_MONDAY = Target.the("Input Monday").locatedBy("//input[@id='monday']");
    public static Target INPUT_THURSDAY = Target.the("Input Thursday").locatedBy("//input[@id='thursday']");
    public static Target UPLOAD_PICTURE = Target.the("Upload Picture").locatedBy("//input[@id='picture']");
    public static Target BUTTON_SAVE = Target.the("Save Picture").locatedBy("//button[@id='save-btn']");

    public static Target TEXT_ALERT = Target.the("Text Alert").locatedBy("//div[@role='alert']");



}
