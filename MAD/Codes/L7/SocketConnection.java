// Make a SOCKET connection from a J2ME Program
// This J2ME sample program shows how to how to make a SOCKET Connection from a J2ME Phone.

// Many a times there is a need to connect theto a backend HTTP server from the J2ME application. This free J2ME sample program for example shows how to make a SOCKET connection from the phone to port 80 of  http : //www.java-samples.com website and retrieve the contents of index.htm file.

/*
 *
 * This is a free J2ME sample program
 * SOCKET CONNECTION from J2ME phone to a HTTP Server
 *
 * @author William Alexander
 * free for use as long as this comment is included
 *  in the program as it is
 *
 * More Free Java programs available for download
 * at http://www.java-samples.com
 *
 */

import javax.microedition.midlet.*;
import javax.microedition.io.*;
import javax.microedition.lcdui.*;
import java.io.*;
public class SocketConnection extends MIDlet {
// StreamConnection allows bidirectional communication
  private StreamConnection streamConnection = null;

// use OutputStream to send requests
  private OutputStream outputStream = null;
  private DataOutputStream dataOutputStream = null;

// use InputStream to receive responses from Web server
  private InputStream inputStream = null;
  private DataInputStream dataInputStream = null;

// specify the connect string
  private String connectString = "socket://www.java-samples.com:80";

// use a StrignBuffer to store the retrieved page contents
  private StringBuffer results;

// define GUI components
  private Display myDisplay = null;
  private Form resultScreen;
  private StringItem resultField;

  public SocketConnection() {
    // initializing GUI display
    results = new StringBuffer();
    myDisplay = Display.getDisplay(this);
    resultScreen = new Form("Page Content:");
  }

  public void startApp() {
    try {
      // establish a socket connection with remote server
      streamConnection =
        (StreamConnection) Connector.open(connectString);

      // create DataOuputStream on top of the socket connection
      outputStream = streamConnection.openOutputStream();
      dataOutputStream = new DataOutputStream(outputStream);

      // send the HTTP request
      dataOutputStream.writeChars("GET /index.htm HTTP/1.0 \n");
      dataOutputStream.flush();

      // create DataInputStream on top of the socket connection
      inputStream = streamConnection.openInputStream();
      dataInputStream = new DataInputStream(inputStream);

      // retrieve the contents of the requested page from Web server
      int inputChar;
      while ( (inputChar = dataInputStream.read()) != -1) {
        results.append((char) inputChar);
      }

      // display the page contents on the phone screen
      resultField = new StringItem(null, results.toString());
      resultScreen.append(resultField);
      myDisplay.setCurrent(resultScreen);

    } catch (IOException e) {
      System.err.println("Exception caught:" + e);
    } finally {
      // free up I/O streams and close the socket connection
      try {
        if (dataInputStream != null)
          dataInputStream.close();
      } catch (Exception ignored) {}
      try {
        if (dataOutputStream != null)
          dataOutputStream.close();
      } catch (Exception ignored) {}
      try {
        if (outputStream != null)
          outputStream.close();
      } catch (Exception ignored) {}
      try {
        if (inputStream != null)
          inputStream.close();
      } catch (Exception ignored) {}
      try {
        if (streamConnection != null)
          streamConnection.close();
      } catch (Exception ignored) {}
    }
  }

  public void pauseApp() {
  }

  public void destroyApp(boolean unconditional) {
  }
}