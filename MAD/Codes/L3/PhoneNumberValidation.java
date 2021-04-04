//PhoneNumberValidation

import javax.microedition.midlet.*;
import javax.microedition.lcdui.*;

public class PhoneNumberValidation extends MIDlet implements CommandListener {

	public Form form;
	public TextField textfield;
	public Command exitCommand;
	public Command okCommand;
	public StringItem st;
	public String instruction;
	public Display display;

	public PhoneNumberValidation() {

		display = Display.getDisplay(this);
		form = new Form("Insert the Phone number");

		exitCommand = new Command("Exit", Command.EXIT, 1);
		okCommand = new Command("Ok", Command.OK, 1);
		st = new StringItem("Phone Number is ", "");
		instruction = "Format : <areaCode>XXXXXX\nArea code must be 040|050|041|0400|044\n";

		textfield = new TextField("Phone Number:", "", 30, TextField.ANY);


		form.append(textfield);
		form.append(instruction);
		form.addCommand(okCommand);
		form.addCommand(exitCommand);
		form.setCommandListener(this);

	}

	public void startApp() { display.setCurrent(form); }

	public void pauseApp() { }

	public void destroyApp(boolean unconditional) { }

	public void commandAction(Command cmd, Displayable displayable)     {
		if (cmd == exitCommand) {
			notifyDestroyed();
		} else if (cmd == okCommand) {
			String s = textfield.getString();
			boolean correct = false;
			int len = s.length();

			if (len == 9 || len == 10) {
				String number = s.substring(len - 6);
				String areaCode = s.substring(0, len - 6);
				boolean numberIsNumeric = true;

				try {
					int num = Integer.parseInt(number);
				} catch (NumberFormatException e) {
					numberIsNumeric = false;
				}

				if (areaCode.equals("040") || areaCode.equals("041") || areaCode.equals("050") || areaCode.equals("0400") || areaCode.equals("044")) {
					if (number.length() == 6 && numberIsNumeric ) {
						correct = true;
					}
				}
			}


			if (correct)  {
				st.setText("OK");
			} else {
				st.setText("wrong\n");
			}

			form.append(st);

		}
	}
}