import javax.microedition.lcdui.*;
import javax.microedition.midlet.*;

public class CreateMenu extends MIDlet implements CommandListener {
	Display display = null;
	List menu = null;
	TextBox input = null;

	// command
	static final Command backCommand = new Command("Back", Command.BACK, 0);
	static final Command mainMenuCommand = new Command("Main", Command.SCREEN, 1);
	static final Command exitCommand = new Command("Exit", Command.STOP, 2);
	String currentMenu = null;

	public CreateMenu() {}

	public void startApp() throws MIDletStateChangeException {
		display = Display.getDisplay(this);

		menu = new List("Menu Items", Choice.IMPLICIT);

		for (int i = 1; i < 7; i++) {
			menu.append("Option " + i, null);
		}

		menu.addCommand(exitCommand);
		menu.setCommandListener(this);

		mainMenu();
	}

	public void pauseApp() {
		display = null;
		menu = null;
		input = null;
	}

	public void destroyApp(boolean unconditional) {
		notifyDestroyed();
	}

	// main menu
	void mainMenu() {
		display.setCurrent(menu);
		currentMenu = "Main";
	}


	public void prepare() {
		input = new TextBox("Enter some text: ", "", 5, TextField.ANY);
		input.addCommand(backCommand);
		input.setCommandListener(this);
		input.setString("");
		display.setCurrent(input);
	}


	public void testItem1() {
		prepare();
		currentMenu = "item1";
	}


	public void testItem2() {
		prepare();
		currentMenu = "item2";
	}


	public void testItem3() {
		prepare();
		currentMenu = "item3";
	}


	public void testItem4() {
		prepare();
		currentMenu = "item4";
	}



	//Handle events.
	public void commandAction(Command c, Displayable d) {
		String label = c.getLabel();
		if (label.equals("Exit")) {
			destroyApp(true);
		} else if (label.equals("Back")) {
			if (currentMenu.equals("item1") || currentMenu.equals("item2") ||
			        currentMenu.equals("item3") || currentMenu.equals("item4"))  {
				// go back to menu
				mainMenu();
			}

		} else {
			List down = (List)display.getCurrent();
			switch (down.getSelectedIndex()) {
			case 0: testItem1(); break;
			case 1: testItem2(); break;
			case 2: testItem3(); break;
			case 3: testItem4(); break;
			}

		}
	}
}

/*
import javax.microedition.midlet.*;
import javax.microedition.lcdui.*;

public class CreateMenu extends MIDlet implements CommandListener {
	public ChoiceGroup choicegroup;
	public Form form;
	public Display display;
	public Command command;
	public String stringitem;

	public CreateMenu() {
		display = Display.getDisplay(this);
		choicegroup = new ChoiceGroup("Edit", Choice.EXCLUSIVE);

		for (int i = 1; i < 7 ; i++) {
			String t = "Option " + i;
			choicegroup.append(t, null);
		}
		//choicegroup.setSelectedIndex(0, true);

		command = new Command("Select list item", Command.OK, 1);
		form = new Form("");
		form.append(choicegroup);
		form.addCommand(command);
		form.setCommandListener(this);
		stringitem = new String("");
	}

	public void startApp() { display.setCurrent(form); }

	public void pauseApp() { }

	public void destroyApp(boolean unconditional) { }

	public void commandAction(Command command, Displayable displayable) {
		if (command == this.command) {
			//stringitem = "Selected option is " + choicegroup.getString(choicegroup.getSelectedIndex());
			//form.append(stringitem);
		}
	}
}

*/