import javax.microedition.lcdui.*;
import javax.microedition.midlet.MIDlet;

public final class Calculator extends MIDlet implements CommandListener, ItemCommandListener {

	private static final int NUM_SIZE = 20;

	private final Command exitCmd = new Command("Exit", Command.EXIT, 2);

	private final Command add = new Command("Add", Command.ITEM, 1);
	private final Command sub = new Command("Sub", Command.ITEM, 1);
	private final Command mul = new Command("Mul", Command.ITEM, 1);
	private final Command div = new Command("Div", Command.ITEM, 1);

	private final TextField textFiled_1 = new TextField("Num 1 ", "", NUM_SIZE, TextField.DECIMAL);
	private final TextField textField_2 = new TextField("Num 2 ", "", NUM_SIZE, TextField.DECIMAL);
	private final TextField tr = new TextField("Result", "", NUM_SIZE, TextField.UNEDITABLE);
	private final Alert alert = new Alert("Error", "", null, AlertType.ERROR);

	private boolean isInitialized = false;

	protected void startApp() {
		if (isInitialized) {
			return;
		}

		Form form = new Form("Calculator");
		form.append(textFiled_1);
		form.append(textField_2);

		StringItem itema = new StringItem("  Add   ", "", Item.BUTTON);
		itema.setDefaultCommand(add);
		itema.setItemCommandListener(this);
		form.append(itema);
		StringItem items = new StringItem("Subtract", "", Item.BUTTON);
		items.setDefaultCommand(sub);
		items.setItemCommandListener(this);
		form.append(items);
		StringItem itemm = new StringItem("Multiply", "", Item.BUTTON);
		itemm.setDefaultCommand(mul);
		itemm.setItemCommandListener(this);
		form.append(itemm);
		StringItem itemd = new StringItem(" Divide ", "", Item.BUTTON);
		itemd.setDefaultCommand(div);
		itemd.setItemCommandListener(this);
		form.append(itemd);
		form.append(tr);
		form.addCommand(exitCmd);
		form.setCommandListener(this);
		Display.getDisplay(this).setCurrent(form);
		isInitialized = true;
	}

	protected void destroyApp(boolean unconditional) {}

	protected void pauseApp() {}

	public void commandAction(Command c, Item item) {

//do not declare variables inside try…it will give you an error
		double res = 0.0;
		double n1 = getNumber(textFiled_1, "First");
		double n2 = getNumber(textField_2, "Second");
		try {
			if (c == add)
				res = n1 + n2;
			if (c == sub)
				res = n1 - n2;
			if (c == mul)
				res = n1 * n2;
			if (c == div)
				res = n1 / n2;
		} catch (NumberFormatException e) {
			return;
		} catch (ArithmeticException e) {
			alert.setString("Divide by zero.");
			Display.getDisplay(this).setCurrent(alert);
			return;
		}

		String res_str = Double.toString(res);


		if (res_str.length() > tr.getMaxSize()) {
			tr.setMaxSize(res_str.length());
		}

		tr.setString(res_str);
	}

	public void commandAction(Command c, Displayable d) {
		//this method must be written otherwise it will give an error "Calculator is not abstract and does not override abstract method commandAction(javax.microedition.lcdui.Command,javax.microedition.lcdui.Displayable) in javax.microedition.lcdui.CommandListener"
		if (c == exitCmd) {
			destroyApp(false);
			notifyDestroyed();
			return;
		}
	}

	private double getNumber(TextField t, String type) throws NumberFormatException {
		String s = t.getString();

		if (s.length() == 0) {
			alert.setString("No " + type + " Argument");
			Display.getDisplay(this).setCurrent(alert);
			throw new NumberFormatException();
		}

		double n;

		try {
			n = Double.parseDouble(s);
		} catch (NumberFormatException e) {
			alert.setString(type + " argument is out of range.");
			Display.getDisplay(this).setCurrent(alert);
			throw e;
		}

		return n;
	}
}