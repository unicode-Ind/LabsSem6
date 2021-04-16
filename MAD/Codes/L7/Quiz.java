import javax.microedition.midlet.*;
import javax.microedition.lcdui.*;
public class Quiz extends MIDlet implements
	CommandListener {
	private Display display;
	private Form form0, form1, form2, form3, form4, form5;
	private ChoiceGroup ch0, ch1, ch2, ch3, ch4;
	private Command next;
	private Command back;
	private Command ok;
	private Command exit;
	private StringItem st;
	int count = 0;
	public Quiz() {
		display = Display.getDisplay(this);
		next = new Command("Next", Command.OK, 1);
		back = new Command("Back", Command.BACK, 1);

		st = new StringItem("Total correct answers :", "0");

		form0 = new Form("1. 3*5  is?");
		ch0 = new ChoiceGroup("", Choice.EXCLUSIVE);
		ch0.append("9", null);
		ch0.append("12", null);
		ch0.append("15", null);
		ch0.append("18", null);
		form0.append(ch0);
		form0.addCommand(next);
		form0.setCommandListener(this);

		form1 = new Form("2.Android is based on?");
		ch1 = new ChoiceGroup("", Choice.EXCLUSIVE);
		ch1.append("Linux", null);
		ch1.append("Unix", null);
		ch1.append("Kai", null);
		ch1.append("None of Above", null);
		form1.append(ch1);
		form1.addCommand(next);
		form1.setCommandListener(this);

		form2 = new Form("3. Is JAVA purely object oriented Language?");
		ch2 = new ChoiceGroup("", Choice.EXCLUSIVE);
		ch2.append("True", null);
		ch2.append("False", null);
		form2.append(ch2);
		form2.addCommand(next);
		form2.addCommand(back);
		form2.setCommandListener(this);

		form3 = new Form("4.Root of 625?");
		ch3 = new ChoiceGroup("", Choice.EXCLUSIVE);
		ch3.append("15", null);
		ch3.append("35", null);
		ch3.append("45", null);
		ch3.append("25", null);
		form3.append(ch3);
		form3.addCommand(next);
		form3.addCommand(back);
		form3.setCommandListener(this);

		form4 = new Form("5.Root of 144?");
		ch4 = new ChoiceGroup("", Choice.EXCLUSIVE);
		ch4.append("14", null);
		ch4.append("12 ", null);
		ch4.append("11", null);
		ch4.append("It's not an Integer", null);
		form4.append(ch4);
		form4.addCommand(next);
		form4.addCommand(back);
		form4.setCommandListener(this);

		form5 = new Form("Score");
		exit = new Command("Exit", Command.SCREEN, 1);
		ok = new Command("Submit", Command.OK, 2);
		form5.addCommand(ok);
		form5.addCommand(exit);
		form5.setCommandListener(this);
	} public void startApp() {
		display.setCurrent(form0);
	}

	public void pauseApp() {}
	public void destroyApp(boolean unconditional) {}
	public void commandAction(Command cmd, Displayable displayable) {
		if (displayable == form0) {
			if (cmd == next)
				display.setCurrent(form1);
		} else if (displayable == form1) {
			if (cmd == next)
				display.setCurrent(form2);
			else if (cmd == back)
				display.setCurrent(form0);
		} else if (displayable == form2) {
			if (cmd == next)
				display.setCurrent(form3);
			else if (cmd == back)
				display.setCurrent(form1);
		} else if (displayable == form3) {
			if (cmd == next)
				display.setCurrent(form4);
			else if (cmd == back)
				display.setCurrent(form2);
		} else if (displayable == form4) {
			if (cmd == next) {
				if (ch0.getSelectedIndex() == 2)
					count++;
				if (ch1.getSelectedIndex() == 0)
					count++;
				if (ch2.getSelectedIndex() == 1)
					count++;
				if (ch3.getSelectedIndex() == 3)
					count++;
				if (ch4.getSelectedIndex() == 1)
					count++;
				st.setText(String.valueOf(count));
				form5.append(st);
				display.setCurrent(form5);
			}
		} else if (displayable == form5) {
			if (cmd == ok)
			{display.setCurrent(form5);}
			else if (cmd == exit) {
				destroyApp(false);
				notifyDestroyed();
			}
		}
	}
}


// import javax.microedition.midlet.*;
// import javax.microedition.lcdui.*;
// import javax.microedition.rms.*;
// import java.io.*;
// public class Quiz extends MIDlet implements CommandListener {
// 	public Form form1;
// 	public Form form2;
// 	public Form form3;
// 	public Form form4;
// 	public Form form5;
// 	public Form form6;
// 	public Form form7;
// 	public ChoiceGroup ch1;
// 	public ChoiceGroup ch2;
// 	public ChoiceGroup ch3;;

// 	public ChoiceGroup ch4;;

// 	public ChoiceGroup ch5;;

// 	public Command nextCommand;


// 	public Command backCommand;
// 	public Command MenuCommand;
// 	public Command OkCommand;
// 	public Command ExitCommand;
// 	public Command sCommand;
// 	public Display display;
// 	public StringItem st;
// 	public TextField textfield;
// 	public int count;
// 	public RecordStore recordstore = null;
// 	public RecordEnumeration re = null;
// 	public Alert alert;
// 	public StringItem st1;


// 	public Quiz()

// 	{
// 		count = 0;
// 		display = Display.getDisplay(this);
// 		nextCommand = new Command("Next", Command.OK, 1);
// 		backCommand = new Command("Back", Command.BACK, 1);
// 		st1 = new StringItem("", "");
// 		textfield = new TextField("EnterName", "", 20, TextField.ANY);


// 		form1 = new Form("J2ME Stands for");
// 		form2 = new Form("a+b=");
// 		form3 = new Form("5*5");
// 		form4 = new Form("Who is AP CM");
// 		form5 = new Form("How many Districts in AP");
// 		form6 = new Form("Score");
// 		ch1 = new ChoiceGroup("", Choice.EXCLUSIVE);
// 		ch1.append("Java 2 Mobile Edition", null);
// 		ch1.append("Java 2 Macro Edition", null);
// 		ch1.append("Java 2 Micro Edition", null);
// 		ch1.append("Java 2 Music Edition", null);


// 		form1.append(ch1);
// 		form1.addCommand(nextCommand);
// 		form1.setCommandListener(this);
// 		ch2 = new ChoiceGroup("", Choice.EXCLUSIVE);
// 		ch2.append("b+a", null);
// 		ch2.append("b*a", null);
// 		ch2.append("b/a", null);
// 		ch2.append("b-a", null);


// 		form2.append(ch2);
// 		form2.addCommand(nextCommand);
// 		form2.addCommand(backCommand);
// 		form2.setCommandListener(this);
// 		ch3 = new ChoiceGroup("", Choice.EXCLUSIVE);
// 		ch3.append("20", null);
// 		ch3.append("30", null);
// 		ch3.append("10", null);
// 		ch3.append("25", null);
// 		form3.append(ch3);


// 		form3.addCommand(nextCommand);
// 		form3.addCommand(backCommand);
// 		form3.setCommandListener(this);
// 		ch4 = new ChoiceGroup("", Choice.EXCLUSIVE);
// 		ch4.append("Rosiah", null);
// 		ch4.append("Jagan", null);
// 		ch4.append("ChandaBabu", null);
// 		ch4.append("Kiran", null);


// 		form4.append(ch4);
// 		form4.addCommand(nextCommand);
// 		form4.addCommand(backCommand);
// 		form4.setCommandListener(this);
// 		ch5 = new ChoiceGroup("", Choice.EXCLUSIVE);
// 		ch5.append("8", null);
// 		ch5.append("4", null);


// 		ch5.append("11", null);
// 		ch5.append("23", null);


// 		form5.append(ch5);
// 		form5.addCommand(backCommand);
// 		form5.addCommand(nextCommand);
// 		form5.setCommandListener(this);
// 		form6.addCommand(ExitCommand);
// 	} public void startApp() {
// 		display.setCurrent(form1);
// 	}

// 	public void pauseApp() { }
//	public void destroyApp(boolean unconditional) { }
//	public void commandAction(Command cmd, Displayable displayable) {
// 		if (displayable == form1) {
// 			if (cmd == nextCommand) display.setCurrent(form2);
// 		} else if (displayable == form2) {
// 			if (cmd == nextCommand) display.setCurrent(form3);
// 			else if (cmd == backCommand) display.setCurrent(form1);
// 		} else if (displayable == form3) {
// 			if (cmd == nextCommand) display.setCurrent(form4);
// 			else if (cmd == backCommand) display.setCurrent(form2);


// 		} else if (displayable == form4) {
// 			if (cmd == nextCommand) display.setCurrent(form5);
// 			else if (cmd == backCommand) display.setCurrent(form3);
// 		} else if (displayable == form5) {
// 			if (cmd == nextCommand) {

// 				if (ch1.getSelectedIndex() == 2) count++;


// 				if (ch2.getSelectedIndex() == 0) count++;


// 				if (ch3.getSelectedIndex() == 3) count++;


// 				if (ch4.getSelectedIndex() == 3) count++;


// 				if (ch5.getSelectedIndex() == 3) count++;


// 				st.setText(String.valueOf(count));


// 				form6.append(st);
// 				form6.append(textfield);
// 				display.setCurrent(form6);


// 			}
// 		}
// 	}
// }
