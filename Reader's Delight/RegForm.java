package p1;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.sql.*;
import java.util.*;


public class RegForm extends JFrame {

public static void main(String[] args){
RegForm frameTabel = new RegForm("Welcome");
}

ImageIcon li = new ImageIcon("e:/Reader's Delight/Reader's Delight official sym - Copy.png");
JLabel lblimage = new JLabel(null, li, JLabel.CENTER);

JButton bsubmit = new JButton("Submit");
JPanel panel = new JPanel();

JTextField Fname = new JTextField(15);
JLabel l1 = new JLabel("Name : ");

JPasswordField pass = new JPasswordField(15);
JLabel l2 = new JLabel("Password : ");

JPasswordField passconf = new JPasswordField(15);
JLabel l3 = new JLabel("Confirm Password : ");

JTextField Hint = new JTextField(15);
JLabel l4 = new JLabel("Hint(for password) : ");

JTextField Bdate = new JTextField(15);
JLabel l5 = new JLabel("Birthdate(DD-MON-YYYY): ");

JTextField Country = new JTextField(15);
JLabel l6 = new JLabel("Country : ");

JTextField Email = new JTextField(15);
JLabel l7 = new JLabel("Email-Id : ");

RegForm(String s){
super("New User Registration");
setSize(800,600);
setLocation(300,80);
panel.setLayout (null);

JLabel l0 = new JLabel("Username :                             "+ s);


l0.setBounds(100,40,330,20);
Fname.setBounds(250,70,200,20);
l1.setBounds(100,70,130,20);
pass.setBounds(250,100,200,20);
l2.setBounds(100,100,130,20);
passconf.setBounds(250,130,200,20);
l3.setBounds(100,130,130,20);
Hint.setBounds(250,160,200,20);
l4.setBounds(100,160,130,20);
Bdate.setBounds(250,190,200,20);
l5.setBounds(100,190,145,20);
Country.setBounds(250,220,200,20);
l6.setBounds(100,220,130,20);
Email.setBounds(250,250,200,20);
l7.setBounds(100,250,130,20);
lblimage.setBounds(5,5,49,58);
getContentPane().add(lblimage);

bsubmit.setBounds(170,340,100,40);

panel.add(bsubmit);
panel.add(l0);
panel.add(Fname);
panel.add(l1);
panel.add(pass);
panel.add(l2);
panel.add(passconf);
panel.add(l3);
panel.add(Hint);
panel.add(l4);
panel.add(Bdate);
panel.add(l5);
panel.add(Country);
panel.add(l6);
panel.add(Email);
panel.add(l7);

getContentPane().add(panel);
setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
setVisible(true);

Register(s);

}

public void Register(final String s1){
bsubmit.addActionListener(new ActionListener() {
public void actionPerformed(ActionEvent ae) {

String name = Fname.getText();
String ppaswd = pass.getText();
String ppaswdconf = passconf.getText();
String hint = Hint.getText();
String bdate = Bdate.getText();
String country = Country.getText();
String email = Email.getText();

try{
Class.forName("oracle.jdbc.driver.OracleDriver");

Connection con=DriverManager.getConnection(
"jdbc:oracle:thin:@localhost:1521:xe","system","password00");

String qry = "Insert into login_user values(login_user_seq.nextval,?,?,?,?,?,sysdate,?,?)";
PreparedStatement stmt=con.prepareStatement(qry);
stmt.setString(1,name);
stmt.setString(2,s1);
stmt.setString(3,ppaswd);

stmt.setString(4,hint);
stmt.setString(5,bdate);
stmt.setString(6,country);
stmt.setString(7,email);
if( ppaswd.equals(ppaswdconf))
{
	stmt.executeUpdate();
	homePage homeframec = new homePage(s1);
	homeframec.setVisible(true);
	dispose();
}
else
{
	JOptionPane.showMessageDialog(null,"Password & Confirm-Password do not match...!!!");
	pass.setText("");
	passconf.setText("");
	pass.requestFocus();
}
	
con.commit();
con.close();
}
catch(Exception e){ System.out.println(e);}
}
});
}
}


 