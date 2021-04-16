import javax.microedition.midlet.*;
import javax.microedition.lcdui.*;
import javax.microedition.rms.*;
import java.io.*;
public class PlayerList extends MIDlet implements CommandListener {
	public Form form1;
	public Form form2;
	public Form form3;
	public Form form4;
	public Form form5;
	public Form form6;
	public Form form7;
	public ChoiceGroup ch1;
	public ChoiceGroup ch2;
	public ChoiceGroup ch3;
	;
	public ChoiceGroup ch4;
	;
	public ChoiceGroup ch5;
	;
	public Command nextCommand;
	public Command backCommand;
	public Command MenuCommand;
	public Command OkCommand;
	public Command ExitCommand;
	public Command sCommand;
	public Display display;
	public StringItem st;
	public TextField textfield;


	public int count;
	public RecordStore recordstore = null;
	public RecordEnumeration re = null;
	public Alert alert;
	public Compare comp;
	public StringItem st1;
	public PlayerList() {
		count = 0;

		display = Display.getDisplay(this);
		nextCommand = new Command("Next", Command.OK, 1);
		backCommand = new Command("Back", Command.BACK, 1);
		OkCommand = new Command("Save", Command.SCREEN, 1);
		ExitCommand = new Command("Exit", Command.SCREEN, 1);
		sCommand = new Command("TopScores", Command.SCREEN, 1);
		st = new StringItem("TotalPoints", "0");
		st1 = new StringItem("", "");
		textfield = new TextField("EnterName", "", 20, TextField.ANY);
		form1 = new Form("J2ME Stands for");
		form2 = new Form("a+b=");
		form3 = new Form("5*5");
		form4 = new Form("Who is AP CM");
		form5 = new Form("How many Districts in AP");
		form6 = new Form("Score");
		form7 = new Form("Top Scoreers");
		ch1 = new ChoiceGroup("", Choice.EXCLUSIVE);
		ch1.append("Java 2 Mobile Edition", null);
		ch1.append("Java 2 Macro Edition", null);
		ch1.append("Java 2 Micro Edition", null);
		ch1.append("Java 2 Music Edition", null);
		form1.append(ch1);
		form1.addCommand(nextCommand);
		form1.setCommandListener(this);
		ch2 = new ChoiceGroup("", Choice.EXCLUSIVE);
		ch2.append("b+a", null);
		ch2.append("b*a", null);
		ch2.append("b/a", null);
		ch2.append("b-a", null);
		form2.append(ch2);
		form2.addCommand(nextCommand);
		form2.addCommand(backCommand);
		form2.setCommandListener(this);
		ch3 = new ChoiceGroup("", Choice.EXCLUSIVE);
		ch3.append("20", null);
		ch3.append("30", null);
		ch3.append("10", null);
		ch3.append("25", null);


		form3.append(ch3);
		form3.addCommand(nextCommand);
		form3.addCommand(backCommand);
		form3.setCommandListener(this);
		ch4 = new ChoiceGroup("", Choice.EXCLUSIVE);
		ch4.append("Rose", null);
		ch4.append("Jagan", null);
		ch4.append("Chandani", null);
		ch4.append("Kiran", null);
		form4.append(ch4);
		form4.addCommand(nextCommand);
		form4.addCommand(backCommand);
		form4.setCommandListener(this);
		ch5 = new ChoiceGroup("", Choice.EXCLUSIVE);
		ch5.append("8", null);
		ch5.append("4", null);

		ch5.append("11", null);
		ch5.append("23", null);


		form5.append(ch5);
		form5.addCommand(backCommand);
		form5.addCommand(nextCommand);
		form5.setCommandListener(this);


		form6.addCommand(OkCommand);
		form6.addCommand(ExitCommand);
		form6.addCommand(sCommand);
		form6.setCommandListener(this);


		form7.addCommand(backCommand);
		form7.setCommandListener(this);
		try {
			recordstore = RecordStore.openRecordStore("Quiz", true);
		} catch (Exception ex) { }
	}

	public void startApp() {
		display.setCurrent(form1);
	}


	public void pauseApp() { }

	public void destroyApp(boolean unconditional) { }

	public void commandAction(Command cmd, Displayable displayable) {
		if (displayable == form1) {

			if (cmd == nextCommand) display.setCurrent(form2);
		} else if (displayable == form2) {
			if (cmd == nextCommand) display.setCurrent(form3);
			else if (cmd == backCommand) display.setCurrent(form1);
		} else if (displayable == form3) {
			if (cmd == nextCommand) display.setCurrent(form4);
			else if (cmd == backCommand) display.setCurrent(form2);
		} else if (displayable == form4) {
			if (cmd == nextCommand)

				display.setCurrent(form5);
			else if (cmd == backCommand) display.setCurrent(form3);
		} else if (displayable == form5) {
			if (cmd == nextCommand) {
				if (ch1.getSelectedIndex() == 2) count++;



				if (ch2.getSelectedIndex() == 0) count++;
				if (ch3.getSelectedIndex() == 3) count++;
				if (ch4.getSelectedIndex() == 3) count++;
				if (ch5.getSelectedIndex() == 3) count++;


				st.setText(String.valueOf(count));
				form6.append(st);
				form6.append(textfield);
				display.setCurrent(form6);
			}

			else if (cmd == backCommand) display.setCurrent(form4);
		} else if (displayable == form6) {
			if (cmd == OkCommand) {

				try {
					String Pname = textfield.getString();
					int Points = Integer.parseInt(st.getText());
					byte[] bytes;
					ByteArrayOutputStream ostream = new ByteArrayOutputStream();
					DataOutputStream dstream = new DataOutputStream(ostream);
					dstream.writeUTF(Pname);
					dstream.writeInt(Points);
					dstream.flush();
					bytes = ostream.toByteArray();
					recordstore.addRecord(bytes, 0, bytes.length );
					ostream.reset();
					ostream.close();
					dstream.close();
					alert = new Alert("Message", "Saved", null, AlertType.INFO);
					alert.setTimeout(Alert.FOREVER);
					display.setCurrent(alert);
				} catch (Exception ex) {
					alert = new Alert("Message", ex.toString(), null, AlertType.INFO);
					alert.setTimeout(Alert.FOREVER);
					display.setCurrent(alert);
				}
			} else if (cmd == ExitCommand) {
				try {
					recordstore.closeRecordStore();
					notifyDestroyed();
				} catch (Exception ex) { }
			} else if (cmd == sCommand) {
				try {
					byte[] bytes = new byte[300];
					ByteArrayInputStream bstream = new ByteArrayInputStream(bytes);


					DataInputStream dstream = new DataInputStream(bstream);
					StringBuffer sb = new StringBuffer();
					comp = new Compare();
					re = recordstore.enumerateRecords(null, comp, false);
					st1.setText("");


					while (re.hasNextElement()) {
						recordstore.getRecord(re.nextRecordId(), bytes, 0);
						sb.append(dstream.readUTF() + "|" + dstream.readInt());
						sb.append("\n");
						dstream.reset();
					} bstream.close();
					dstream.close();
					st1.setText(sb.toString());
					form7.append(st1);
				}

				catch (Exception ex) {
					alert = new Alert("Msg", ex.toString(), null, AlertType.INFO);
					alert.setTimeout(Alert.FOREVER);
					display.setCurrent(alert);
				} display.setCurrent(form7);
			}
		} else if (displayable == form7) {
			if (cmd == backCommand) display.setCurrent(form6);
		}
	}
}

class Compare implements RecordComparator {
	public byte[] bytedata = new byte[300];
	public ByteArrayInputStream bstream = null;
	public DataInputStream dstream = null;
	public int compare(byte[] r1, byte[] r2) {
		int r1int, r2int;
		int or = 0;
		try {
			int maxlen = Math.max(r1.length, r2.length);


			if (maxlen > bytedata.length) {
				bytedata = new byte[maxlen];
			} bstream = new ByteArrayInputStream(r1);
			dstream = new DataInputStream(bstream);
			dstream.readUTF();
			r1int = dstream.readInt();
			bstream = new ByteArrayInputStream(r2);
			dstream = new DataInputStream(bstream);
			dstream.readUTF();
			r2int = dstream.readInt();
			if (r1int == r2int) {
				or = RecordComparator.EQUIVALENT;
			} else if (r1int > r2int) {
				or = RecordComparator.PRECEDES;
			} else if (r1int < r2int) {
				or = RecordComparator.FOLLOWS;
			} return or;
		} catch (Exception ex) {
			return RecordComparator.EQUIVALENT;
		}
	} public void compareClose() {
		try {
			if (bstream != null) {
				bstream.close();
			} if (dstream != null) {
				dstream.close();
			}
		} catch (Exception ex) { }
	}
}

