import javax.microedition.midlet.*;
import javax.microedition.lcdui.*;
public class BarGraph extends MIDlet implements CommandListener {
	public Form form;
	public Command exitCommand;
	public Command OkCommand;
	public Command backCommand;
	public Displayable d;
	public Display display;
	public TextField []textfield;
	public static int []color = {0x00CED1, 0xff0033, 0x0a75ad, 0xffb6c1,	0xee8899};
	public static String []labels = {"DBMS :", "OS   :", "CN 	 :", "OOPS :", "JAVA :"};


	public BarGraph() {
		display = Display.getDisplay(this);
		form = new Form("BarGraph : Enter marks (out of 100):");

		textfield = new TextField[5];
		for (int i = 0; i < 5 ; ++i ) {
			textfield[i] = new TextField(labels[i], "", 30, TextField.ANY);
			form.append(textfield[i]);

		}

		OkCommand = new Command("Ok", Command.OK, 1);
		exitCommand = new Command("Exit", Command.EXIT, 1);
		backCommand = new Command("Back", Command.BACK, 1);
		form.addCommand(OkCommand);
		form.addCommand(exitCommand);
		form.setCommandListener(this);
	}

	public void startApp() { display.setCurrent(form); }

	public void pauseApp() { }

	public void destroyApp(boolean unconditional) { }

	public void commandAction(Command command, Displayable displayable) {
		if (displayable == form) {
			if (command == OkCommand) {
				int[] data = new int[5];

				for (int i = 0; i < 5 ; ++i ) {
					data[i] = Integer.parseInt(textfield[i].getString());
				}

				d = new BarCanvas(data);
				d.addCommand(backCommand);
				d.setCommandListener(this);
				display.setCurrent(d);
			} else if (command == exitCommand) {
				notifyDestroyed();
			}
		} else if (displayable == d) {
			if (command == backCommand) {
				display.setCurrent(form);
			}
		}
	}

	class BarCanvas extends Canvas {
		int[] data;
		int height = 20;
		int width = 0;
		int ox = 50, oy = 50;
		int px = 5,  py = 55;
		int inc = 25;
		int maxWidth = 150;
		int maxMarks = 100;

		public BarCanvas() {}
		public BarCanvas(int[] data) {
			this.data = data;
		}

		public void paint(Graphics g) {


			g.setColor(255, 255, 255);
			g.fillRect(0, 0, this.getWidth(), this.getHeight());


			int i = 0;
			while (i < data.length) {

				//find horizontal lenght
				width = (int)data[i] * maxWidth / maxMarks;

				//draw bar
				g.setColor(color[i]);
				g.fillRect(ox, oy, width, height);

				//print label
				g.setColor(0, 0, 0);
				g.drawString(labels[i], px, py, g.TOP | g.LEFT);

				//next element
				oy += inc;
				py += inc;
				i++;

			}

			//draw lines
			g.setColor(0, 0, 0);
			g.drawLine(ox, 30, ox, oy);
			g.drawLine(ox, oy, ox + 160, oy);

			//plot markings
			int cur = 0;
			while (cur <= 100) {
				int newX = (int)(cur * maxWidth / maxMarks) + ox;
				g.drawLine(newX, oy, newX, oy + 5);
				g.drawString("" + cur, newX, oy + 10, g.TOP | g.LEFT);
				cur += 25;
			}

			g.drawString("Marks", ox + 60, oy + 25, g.TOP | g.LEFT);



		}
	}

}

/*
import javax.microedition.midlet.*;
import javax.microedition.lcdui.*;
public class BarGraph extends MIDlet implements CommandListener {
	public Form form;
	public Command exitCommand;
	public Command OkCommand;
	public Command backCommand;
	public Displayable d;
	public Display display;
	public TextField textfield1;
	public TextField textfield2;
	public TextField textfield3;
	public TextField textfield4;
	public TextField textfield5;

	public BarGraph() {
		display = Display.getDisplay(this);
		form = new Form("BarGraph");
		textfield1 = new TextField("Value1:-", "", 30, TextField.ANY);
		textfield2 = new TextField("Value2:-", "", 30, TextField.ANY);
		textfield3 = new TextField("Value3:-", "", 30, TextField.ANY);
		textfield4 = new TextField("Value4:-", "", 30, TextField.ANY);
		textfield5 = new TextField("Value5:-", "", 30, TextField.ANY);
		form.append(textfield1);
		form.append(textfield2);
		form.append(textfield3);
		form.append(textfield4);
		form.append(textfield5);

		OkCommand = new Command("Ok", Command.OK, 1);
		exitCommand = new Command("Exit", Command.EXIT, 1);
		backCommand = new Command("Back", Command.BACK, 1);
		form.addCommand(OkCommand);
		form.addCommand(exitCommand);
		form.setCommandListener(this);
	}

	public void startApp() { display.setCurrent(form); }

	public void pauseApp() { }

	public void destroyApp(boolean unconditional) { }

	public void commandAction(Command command, Displayable displayable) {
		if (displayable == form) {
			if (command == OkCommand) {
				int[] data = new int[5];
				data[0] = Integer.parseInt(textfield1.getString());
				data[1] = Integer.parseInt(textfield2.getString());
				data[2] = Integer.parseInt(textfield3.getString());
				data[3] = Integer.parseInt(textfield4.getString());
				data[4] = Integer.parseInt(textfield5.getString());
				d = new BarCanvas(data);
				d.addCommand(backCommand);
				d.setCommandListener(this);
				display.setCurrent(d);
			} else if (command == exitCommand) {
				notifyDestroyed();
			}
		} else if (displayable == d) {
			if (command == backCommand) {
				display.setCurrent(form);
			}
		}
	}
	class BarCanvas extends Canvas {
		int[] data;
		public int x;
		public int y;
		public int y1;
		public int h;

		public BarCanvas(int[] data) {
			this.data = data; x = 10;
		}

		public void paint(Graphics g) {
			g.setColor(255, 255, 255);
			g.fillRect(0, 0, this.getWidth(), this.getHeight());
			g.setColor(255, 125, 100);
			int i = 0;
			y1 = data[0];
			h = 200;
			while (i < data.length) {
				y = data[i];
				h = 200 + y1 - y;
				g.fillRect(x, y, 25 , h);
				x += 30;
				i++;
			}
		}
	}
}
*/