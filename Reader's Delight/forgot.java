
package p1;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.sql.*;

public class forgot extends JFrame {

public static void main(String[] args){
forgot frameTabel = new forgot();
}

JButton blogin = new JButton("Get Hint");
JPanel panel = new JPanel();
JTextField uname = new JTextField(15);

JLabel l1 = new JLabel("Username : ");


forgot(){
super("Forgot Password");
setSize(800,600);
setLocation(300,80);
panel.setLayout (null); 


uname.setBounds(355,303,150,20);
l1.setBounds(285,300,80,20);
blogin.setBounds(385,450,80,20);

panel.add(blogin);
panel.add(uname);
panel.add(l1);
getContentPane().add(panel);
setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
setVisible(true);
check();
}

public void check(){
blogin.addActionListener(new ActionListener() {
public void actionPerformed(ActionEvent ae) {
String puname = new String();
puname = uname.getText();

try{
Class.forName("oracle.jdbc.driver.OracleDriver");

Connection con=DriverManager.getConnection(
"jdbc:oracle:thin:@localhost:1521:xe","system","password00");

Statement stmt=con.createStatement();
ResultSet rs=stmt.executeQuery("select * from login_user");
int flag=0;
while(rs.next())
{
	if(puname.equals(rs.getString(3)))
	{
		flag=1;
	}
}


if(flag==0)
{
	JOptionPane.showMessageDialog(null,"This Username does not exist..! ");
	uname.setText("");
        uname.requestFocus();
	    

}
else
{

Class.forName("oracle.jdbc.driver.OracleDriver");

con=DriverManager.getConnection(
"jdbc:oracle:thin:@localhost:1521:xe","system","password00");

PreparedStatement stmt1 =con.prepareStatement("Select * from login_user where username=?");
stmt1.setString(1,puname);
 rs=stmt1.executeQuery();
while(rs.next())
JOptionPane.showMessageDialog(null,"Hint for your password is:" +rs.getString(5));
Log log1=new Log();
dispose();   
}


con.close();
}

catch(Exception e){ System.out.println(e);}
}
});
}
}
