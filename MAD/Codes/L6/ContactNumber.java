import javax.microedition.rms.*;
import javax.microedition.midlet.*;
import javax.microedition.lcdui.*;
import java.io.*;
public class ContactNumber extends MIDlet implements CommandListener {
	private Display display;
	private Alert alert;
	private Form form;
	private Command exit;
	private Command start;
	private RecordStore recordstore = null;
	private RecordEnumeration recordEnumeration = null;
	public ContactNumber() {
		display = Display.getDisplay(this);
		exit = new Command("Exit", Command.SCREEN, 1);
		start = new Command("Start", Command.SCREEN, 1);
		form = new Form("Mixed RecordEnumeration");
		form.addCommand(exit);
		form.addCommand(start);
		form.setCommandListener(this);
	}
	public void startApp() {
		display.setCurrent(form);
	}
	public void pauseApp() {
	}
	public void destroyApp( boolean unconditional ) {
	}
	public void commandAction(Command command, Displayable displayable) {
		if (command == exit) {
			destroyApp(true);
			notifyDestroyed();
		} else if (command == start) {
			try {

				recordstore = RecordStore.openRecordStore(
				                  "myRecordStore", true );

			} catch (Exception error) {
				alert = new Alert("Error Creating",

				                  error.toString(), null, AlertType.WARNING);

				alert.setTimeout(Alert.FOREVER);
				display.setCurrent(alert);
			}
			try {
				byte[] outputRecord;
				String outputString[] = {"Arvin",
				                         "Arti", "Vanshika"
				                        };
				int outputInteger[] = {983923103, 999989897, 987897897};
				ByteArrayOutputStream outputStream =
				    new ByteArrayOutputStream();
				DataOutputStream outputDataStream =

				    new DataOutputStream(outputStream);

				for (int x = 0; x < 3; x++) {
					outputDataStream.writeUTF(outputString[x]);
					outputDataStream.writeInt(outputInteger[x]);
					outputDataStream.flush();
					outputRecord = outputStream.toByteArray();
					recordstore.addRecord(outputRecord, 0,
					                      outputRecord.length);

				}
				outputStream.reset();
				outputStream.close();
				outputDataStream.close();
			} catch ( Exception error) {
				alert = new Alert("Error Writing",
				                  error.toString(), null, AlertType.WARNING);
				alert.setTimeout(Alert.FOREVER);
				display.setCurrent(alert);
			}
			try {
				StringBuffer buffer = new StringBuffer();
				byte[] byteInputData = new byte[300];
				ByteArrayInputStream inputStream = new ByteArrayInputStream(byteInputData);
				DataInputStream inputDataStream =

				    new DataInputStream(inputStream);
				recordEnumeration = recordstore.enumerateRecords(

				                        null, null, false);
				while (recordEnumeration.hasNextElement()) {
					recordstore.getRecord(recordEnumeration.nextRecordId(),
					                      byteInputData, 0);
					buffer.append(inputDataStream.readUTF());
					buffer.append("\n");
					buffer.append(inputDataStream.readInt());
					buffer.append("\n");
					alert = new Alert("Reading", buffer.toString(),
					                  null, AlertType.WARNING);
					alert.setTimeout(Alert.FOREVER);
					display.setCurrent(alert);
				}
				inputStream.close();
			} catch (Exception error) {
				alert = new Alert("Error Reading",
				                  error.toString(), null, AlertType.WARNING);
				alert.setTimeout(Alert.FOREVER);
				display.setCurrent(alert);
			}
			try {
				recordstore.closeRecordStore();

			} catch (Exception error) {
				alert = new Alert("Error Closing",
				                  error.toString(), null, AlertType.WARNING);
				alert.setTimeout(Alert.FOREVER);
				display.setCurrent(alert);
			}
			if (RecordStore.listRecordStores() != null) {
				try {
					RecordStore.deleteRecordStore("myRecordStore");
					recordEnumeration.destroy();
				} catch (Exception error) {
					alert = new Alert("Error Removing",
					                  error.toString(), null, AlertType.WARNING);
					alert.setTimeout(Alert.FOREVER);
					display.setCurrent(alert);
				}
			}
		}
	}
}
