import javax.microedition.lcdui.*;
import javax.microedition.midlet.*;

public class InputValidation extends MIDlet implements CommandListener {
    private Display display;
    private Form registrationFrm;

    private String uname = "18124004";
    private String pass = "12345";

    private Alert messageAlert;

    private TextField userName;
    private TextField passwordTxt;

    private Command okCmd;
    private Command exitCmd;

    public InputValidation() {
        // userName = new TextField("Email:", "", 100, TextField.EMAILADDR);
        userName = new TextField("Roll No:     ", "", 16, TextField.ANY);
        passwordTxt = new TextField("Password:", "", 16, TextField.PASSWORD);


        registrationFrm = new Form("User Registration",
                                   new Item[] {userName, passwordTxt});

        messageAlert = new Alert("Registration Complete");
        messageAlert.setTimeout(3000);
        messageAlert.setType(AlertType.CONFIRMATION);

        okCmd = new Command("OK", Command.OK, 0);
        exitCmd = new Command("Exit", Command.EXIT, 1);

        registrationFrm.addCommand(okCmd);
        registrationFrm.addCommand(exitCmd);

        registrationFrm.setCommandListener(this);
    }

    protected void startApp() {
        display = Display.getDisplay(this);
        display.setCurrent(registrationFrm);
    }

    public void commandAction(Command c, Displayable d) {
        if (d == registrationFrm) {
            if (c == okCmd) {
                String messageContent;

                if (userName.getString().length() == 0 || passwordTxt.getString().length() == 0) {
                    messageContent = "Please Enter All fields";
                } else {
                    boolean c1 = uname.equals(userName.getString());
                    boolean c2 = pass.equals(passwordTxt.getString());

                    if (c1 && c2)
                        messageContent = "Login Successfull";
                    else
                        messageContent = "Wrong Credentials";
                }


                messageAlert.setString(messageContent);

                display.setCurrent(messageAlert, registrationFrm);
            } else if (c == exitCmd) {
                notifyDestroyed();
            }
        }
    }

    protected void pauseApp() {
    }

    protected void destroyApp(boolean unconditional) {
    }
}