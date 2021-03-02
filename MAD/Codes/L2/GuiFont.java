import javax.microedition.lcdui.*;
import javax.microedition.midlet.*;

public class GuiFont extends MIDlet
	implements CommandListener {
	// display manager
	Display display;

	// main menu : a menu with items
	List menu;

	// list of choices
	List choose;

	// textbox
	TextBox input;

	// ticker
	Ticker ticker = new Ticker("3.1_GUI_Components");

	// alerts
	final Alert soundAlert =  new Alert("sound Alert");

	// date
	DateField date = new DateField("Today's date: ", DateField.DATE);

	// form
	Form form = new Form("Form for Stuff");

	// today's form
	Form today = new Form("Today's date");

	// gauge
	Gauge gauge = new Gauge("Progress Bar", false, 20, 9);

	// text field
	TextField textfield = new TextField(
	    "TextField Label", "Dummy Text", 50, 0);

	//canvas
	private DecodeCanvas decodeCanvas = null;
	private boolean painting = false;
	public static final boolean COLOR = false;
	public static final boolean DEBUG = false;

	//colors
	public static final int WHITE = 0xFFFFFF;
	public static final int BLACK = 0x000000;
	public static final int LIGHT_GRAY = 0xAAAAAA;
	public static final int DARK_GRAY = 0x555555;
	public static final int RED = 0xFF0000;
	public static final int GREEN = 0x00FF00;
	public static final int BLUE = 0x0000FF;

	// command
	static final Command backCommand = new Command("Back", Command.BACK, 0);
	static final Command mainMenuCommand =  new Command("Main", Command.SCREEN, 1);
	static final Command exitCommand =  new Command("Exit", Command.STOP, 2);
	String currentMenu;

	// constructor.
	public GuiFont() {
	}

	/**
	 * Start the MIDlet by creating a list of
	 * items and associating the
	 * exit command with it.
	 */
	public void startApp() throws
		MIDletStateChangeException {
		display = Display.getDisplay(this);
		menu = new List("Test Components", Choice.IMPLICIT);
		menu.append("Test TextBox", null);
		menu.append("Test List", null);
		menu.append("Test Alert", null);
		menu.append("Test Date", null);
		menu.append("Test Form", null);
		menu.append("Font and color", null);
		menu.addCommand(exitCommand);
		menu.setCommandListener(this);
		menu.setTicker(ticker);
		mainMenu();
		// form
		form.append(gauge);
		form.append(textfield);
		// today
		today.append(date);
	}

	public void pauseApp() {
		display = null;
		choose = null;
		menu = null;
		ticker = null;
		form = null;
		today = null;
		input = null;
		gauge = null;
		textfield = null;
	}

	public void destroyApp(boolean unconditional) {
		notifyDestroyed();
	}

	// main menu
	void mainMenu() {
		display.setCurrent(menu);
		currentMenu = "Main";
	}

	//	 * Test the TextBox component.
	public void testTextBox() {
		input = new TextBox
		("Enter Some Text:", "", 10, TextField.ANY);
		input.setTicker(new Ticker(
		                    "Testing TextBox"));
		input.addCommand(backCommand);
		input.setCommandListener(this);
		input.setString("");
		display.setCurrent(input);
		currentMenu = "input";
	}

	/**
	 * Test the List component.
	 */
	public void testList() {
		choose = new List("Choose Items",
		                  Choice.MULTIPLE);
		choose.setTicker(new Ticker(
		                     "Testing List"));
		choose.addCommand(backCommand);
		choose.setCommandListener(this);
		choose.append("Item 1", null);
		choose.append("Item 2", null);
		choose.append("Item 3", null);
		display.setCurrent(choose);
		currentMenu = "list";
	}

	/**
	 * Test the Alert component.
	 */
	public void testAlert() {
		soundAlert.setType(AlertType.ERROR);
		//soundAlert.setTimeout(20);
		soundAlert.setString("** Alert Demo ** ");
		display.setCurrent(soundAlert);
	}

	/**
	 * Test the DateField component.
	 */
	public void testDate() {
		java.util.Date now = new java.util.Date();
		date.setDate(now);
		today.addCommand(backCommand);
		today.setCommandListener(this);
		display.setCurrent(today);
		currentMenu = "date";
	}

	/**
	 * Test the Form component.
	 */
	public void testForm() {
		form.addCommand(backCommand);
		form.setCommandListener(this);
		display.setCurrent(form);
		currentMenu = "form";
	}


	/**
	 * Font
	 */
	public void gui() {
		//display = Display.getDisplay(this);
		decodeCanvas = new DecodeCanvas(this);
		decodeCanvas.addCommand(backCommand);
		decodeCanvas.setCommandListener(this);
		Display.getDisplay(this).setCurrent(decodeCanvas);
		decodeCanvas.repaint();
		currentMenu = "gui";
	}


	/**
	 * Handle events.
	 */
	public void commandAction(Command c,
	                          Displayable d) {
		String label = c.getLabel();
		if (label.equals("Exit")) {
			destroyApp(true);
		} else if (label.equals("Back")) {
			if (currentMenu.equals("list")
			        || currentMenu.equals("input")
			        || currentMenu.equals("date")
			        || currentMenu.equals("form")
			        || currentMenu.equals("gui")) {
				// go back to menu
				mainMenu();
			}

		} else {
			List down = (List)display.getCurrent();
			switch (down.getSelectedIndex()) {
			case 0: testTextBox(); break;
			case 1: testList(); break;
			case 2: testAlert(); break;
			case 3: testDate(); break;
			case 4: testForm(); break;
			case 5: gui(); break;
			}

		}
	}

	class DecodeCanvas extends Canvas {
		private GuiFont parent = null;

		private int width = getWidth();
		private int height = getHeight();


		public DecodeCanvas(GuiFont parent) {
			this.parent = parent;

		}

		public void paint(Graphics g) {


			g.setColor(WHITE);
			g.fillRect(0, 0, width, height);


			Font f1 = Font.getFont(Font.FACE_SYSTEM, Font.STYLE_PLAIN, Font.SIZE_LARGE);
			Font f2 = Font.getFont(Font.FACE_SYSTEM, Font.STYLE_BOLD, Font.SIZE_MEDIUM);
			Font f3 = Font.getFont(Font.FACE_SYSTEM, Font.STYLE_ITALIC, Font.SIZE_SMALL);
			int yPos = 0;
			if (COLOR)
				g.setColor(BLUE);
			else
				g.setColor(LIGHT_GRAY);

			g.fillRect(0, yPos, width, f1.getHeight());

			if (COLOR)
				g.setColor(WHITE);
			else
				g.setColor(BLACK);
			g.setFont(f1);

			g.setColor(RED);
			g.drawString("BIG FONT : Plain", 0, yPos, Graphics.LEFT | Graphics.TOP);
			yPos = yPos + f1.getHeight() + 10;
			g.setFont(f2);
			//	 g.drawLine(0, f1.getHeight() + yPos - 1, width, f1.getHeight() + yPos - 1);

			g.setColor(GREEN);
			g.drawString("MEDIUM FONT : Bold", 0, yPos, Graphics.LEFT | Graphics.TOP);
			//g.drawLine(0, f2.getHeight() + yPos - 1, width, f2.getHeight() + yPos - 1);
			yPos = yPos + f1.getHeight() + 10;
			g.setFont(f3);

			g.setColor(BLUE);
			g.drawString("SMALL FONT : Italic", 0, yPos, Graphics.LEFT | Graphics.TOP);
			yPos = yPos + f1.getHeight() + 10;
			g.drawLine(0, f3.getHeight() + yPos - 1, width, f3.getHeight() + yPos - 1);

			painting = false;
		}

	}
}