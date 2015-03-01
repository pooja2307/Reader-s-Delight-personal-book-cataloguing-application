package p1;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.sql.*;

public class Log{

public static void main(String[] args){
Log frameTabel = new Log();
}
public JFrame framelog = new JFrame();
JButton blogin = new JButton("Login");
JPanel panel = new JPanel();
JButton bnewuser = new JButton("New User");
JTextField txuser = new JTextField(15);
JPasswordField pass = new JPasswordField(15);
JLabel l1 = new JLabel("Username : ");
JLabel l2 = new JLabel("Password : ");
ImageIcon li = new ImageIcon("e:/Reader's Delight/Reader's Delight official sym - Copy1.png");
JLabel lblimage = new JLabel(null, li, JLabel.CENTER);
ImageIcon li1 = new ImageIcon("e:/Reader's Delight/Reader's Delight official logo1.png");
JLabel lblimage1 = new JLabel(null, li1, JLabel.CENTER);

JButton bfp = new JButton("Forgot Password ?");

Log(){
	framelog.setTitle("Reader's Delight : Login");
	framelog.setLocation(300,80);
	Dimension minSize = new Dimension(800,600);
	framelog.setResizable(false);
	framelog.setMinimumSize(minSize);
panel.setLayout (null); 
panel.setBackground(new Color(192,202,223));

bnewuser.setBounds(345,360,100,20);
txuser.setBounds(355,243,150,20);
l1.setBounds(285,240,80,20);

pass.setBounds(355,275,150,20);
l2.setBounds(285,275,80,20);
blogin.setBounds(280,310,80,20);
bfp.setBounds(365,310,145,20);

lblimage.setBounds(0,0,800,600);
lblimage1.setBounds(150,90,532,97);
panel.add(blogin);
panel.add(bnewuser);
panel.add(txuser);
panel.add(l1);
panel.add(pass);
panel.add(l2);
panel.add(bfp);

framelog.getContentPane().add(lblimage);
framelog.getContentPane().add(lblimage1);
framelog.getContentPane().add(panel);

 
framelog.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
framelog.setVisible(true);
actionlogin();

}

public void actionlogin(){
blogin.addActionListener(new ActionListener() {
public void actionPerformed(ActionEvent ae) {
String puname = txuser.getText();
String ppaswd = pass.getText();
try{
Class.forName("oracle.jdbc.driver.OracleDriver");

Connection con=DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe","system","password00");

Statement stmt=con.createStatement();
ResultSet rs=stmt.executeQuery("select * from login_user");
int flag=0;
while(rs.next()){
if(puname.equals(rs.getString(3)) && ( ppaswd.equals(rs.getString(4))))
{
		flag = 1;
}	
}
if (flag == 1){
			homePage homeframec1 = new homePage(puname);
			//homeframec1.setVisible(true);
			framelog.dispose();
	}
	else {
	JOptionPane.showMessageDialog(null,"Wrong Password / Username");
	txuser.setText("");
	pass.setText("");
	txuser.requestFocus();
	}

con.close();
}
catch(Exception e){ System.out.println(e);}
}
});




bnewuser.addActionListener(new ActionListener() {
public void actionPerformed(ActionEvent ae) {
	U_Form RegForm1 =new U_Form();
	RegForm1.setVisible(true);
	
}
});

bfp.addActionListener(new ActionListener() {
public void actionPerformed(ActionEvent ae) {
	forgot fp =new forgot();
	fp.setVisible(true);
	framelog.dispose();
}
});

}
}


