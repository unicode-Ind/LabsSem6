// Objective: Make application to login in HTTP server.
// Code:
// J2ME Program
import javax.microedition.midlet.*;
import javax.microedition.lcdui.*;
import javax.microedition.io.*;
import java.io.*;
import java.lang.*;
/**
 *
 */
public class login extends MIDlet implements CommandListener {
	public Form form1;
	public Form form2;
	public Command okCommand;
	public Display display;
	public HttpConnection ht = null;
	public InputStream ist = null;
	public StringItem st;
	public TextField t1;
	public TextField t2;

	public StringBuffer buffer = new StringBuffer();
	public TextBox access;

	public login() {
		display = Display.getDisplay(this);
		st = new StringItem(" ", " Welcome");

		t1 = new TextField("UserName", " ", 30, TextField.ANY);
		t2 = new TextField("Password", " ", 30, TextField.PASSWORD);
		form1 = new Form("Login Here");
		form2 = new Form("Welcome");
		okCommand = new Command("Login", Command.OK, 1);
		form1.addCommand(okCommand);
		form1.setCommandListener(this);

		form1.append(t1);
		form1.append(t2);
	}

	public void startApp() {
		display.setCurrent(form1);
	}

	public void pauseApp() {
	}

	public void destroyApp(boolean unconditional) {
		notifyDestroyed();
	}


	public void commandAction(Command cmd, Displayable d) {
		if (cmd == okCommand) {
			try {

				//           String  url="http://192.168.5.19:8080/WebApplication7/index.jsp?t1=101&t2=aaa";
				String url = "http://127.0.0.1:12357/login/su1?pass=11&user=sudhanshu";
				//ht=(HttpConnection)Connector.open("http://192.168.5.19:8080/WebApplication7/index.jsp");
				ht = (HttpConnection)Connector.open(url);
				ist = ht.openInputStream();
				int chars;
				while ((chars = ist.read()) != -1) {
					buffer.append((char) chars);
				}
				System.out.println(buffer.toString());
				access = new TextBox("Access Text", buffer.toString(), 1024, 0);
				//form2.append(access);
				display.setCurrent(access);
			} catch (Exception e) {
				form1.append(e.getMessage());
			}

			//finally{
			//if(ist != null){
			//ist.close();
			//}}

		}
	}
}

// JSP code
// <%@ page language="java" contentType="text/html; charset=ISO-8859-1"
//     pageEncoding="ISO-8859-1"%>
//  <%@ page import="java.sql.*" %>
// <!DOCTYPE html>
// <html>
// <head>
// <meta charset="ISO-8859-1">
// <title>Details</title>
// </head>
// <body>
// 	<div style="padding:5px 400px; border:4px solid orange; border-radius:4px;">
// 	<%
// 	String url ="jdbc:mysql://localhost:3306/studentdetails";
// 	String name ="root";
// 	String pass ="1219";
// 	String query="select * from student";
// 	int pass= Integer.parseInt(request.getParameter("pass"));
// 	session.setAttribute("pass", pass);
// 	Connection con = DriverManager.getConnection(url,name,pass);
// 	Statement st=con.createStatement();
// 	ResultSet rs = st.executeQuery(query);
// 	int flag=0;
// 	while(rs.next())
// 	{
// 		if(rs.getInt(1)==pass)
// 		{
// 			out.println("<b>Roll No:</b>"+rs.getInt(1)+"<br><b>Name is:  </b>"+rs.getString(2)+"<br><b>Phone No: </b>"+rs.getInt(3)+"<br><b>Your father name is </b>"+rs.getString(4));
// 			flag=1;
// 		}
// 	}
// 	if(flag==0)
// 		out.println("<b>Invalid roll Number please type correct Roll Number</b>");

// 	st.close();
// 	con.close();
// 	%>
// 	</div>
// 	<fieldset style="display:flex; margin:auto">
// 		<form action="su2">
// 			New Phone no: <input type="number" name="phone" />
// 			<input type="submit" value="update now"/>
// 		</form>
// 	</fieldset>

// 	<br>
// 	<br>
// 	<br>
// 	<fieldset>
// 	<h3><b><i>for logout <a href="login.html"> click me</a></i></b></h3>
// 	</fieldset>

// </body>
// </html>
// Note: This app serves details of student after verifying username and passworsd. The problem which came is that it is serving html code instead parsing it.
