import javax.microedition.midlet.*;
import javax.microedition.lcdui.*;

public class EventHandling extends MIDlet implements CommandListener, ItemStateListener {
	public ChoiceGroup choicegroup;
	public ChoiceGroup choicegroup1;
	public Form form;
	public Form form1;
	public Display display;
	public Command View;
	public Command Exit;
	public Command Back;
	public StringItem options;
	public Item item;

	public EventHandling() {
		display = Display.getDisplay(this);
		form = new Form("");
		form1 = new Form("Selcted Options are");
		choicegroup = new ChoiceGroup("Preferences", Choice.MULTIPLE);


		for (int i = 1; i < 7 ; i++) {
			String t = "Option " + i;
			choicegroup.append(t, null);
		}

		choicegroup.setSelectedIndex(0, true);
		form.append(choicegroup);
		choicegroup1 = new ChoiceGroup("", Choice.EXCLUSIVE);
		choicegroup1.append("select all", null);
		choicegroup1.append("unselect all", null);
		choicegroup1.setSelectedIndex(1, true);
		form.append(choicegroup1);

		View = new Command("View", Command.OK, 1);
		Exit = new Command("Exit", Command.EXIT, 1);
		Back = new Command("Back", Command.BACK, 1);

		form.addCommand(View);
		form.addCommand(Exit);
		form1.addCommand(Back);
		form.setCommandListener(this);
		form1.setCommandListener(this);
		form.setItemStateListener(this);
	}

	public void startApp() { display.setCurrent(form); }

	public void pauseApp() { }

	public void destroyApp(boolean unconditional) { }

	public void commandAction(Command command, Displayable displayable) {
		if (displayable == form) {
			if (command == View) {
				boolean opt[] = new boolean[choicegroup.size()];
				options = new StringItem("", "");
				String values = "";
				choicegroup.getSelectedFlags(opt);
				options.setText("");
				for (int i = 0; i < opt.length; i++) {
					if (opt[i]) {
						values += choicegroup.getString(i) + "\n";
					}
				}

				options.setText(values);
				form1.append(options);
				display.setCurrent(form1);
			} else if (command == Exit) {
				destroyApp(true);
				notifyDestroyed();
			}
		} else if (displayable == form1) {
			if (command == Back) {
				display.setCurrent(form);
				options.setText("");
			}
		}
	}

	public void itemStateChanged(Item item) {
		if (item == choicegroup1) {
			int size = choicegroup.size();

			for (int i = 0; i < size ; i++ ) {
				if (choicegroup1.getSelectedIndex() == 0) {
					choicegroup.setSelectedIndex(i, true);
				} else {
					choicegroup.setSelectedIndex(i, false);
				}
			}
		}
	}
}