package p1;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.sql.*;
import java.util.*;

public class editprofile{

public static void main(String[] args){
editprofile proframe1 = new editprofile("Welcome");
//editprofile changepass= new editprofile();
}
JFrame nfr = new JFrame("Your Profile");

JLabel lname = new JLabel();
JLabel luname= new JLabel();
//JLabel lpass= new JLabel();
JButton bcpass= new JButton();
JLabel lpasshint= new JLabel();
JLabel ldate= new JLabel();
JLabel lcountry= new JLabel();
JLabel lemail= new JLabel();
JPanel panel= new JPanel();
//JPanel panel2= new JPanel();
String username,name,password,hint,bdate,country,email;

ImageIcon li = new ImageIcon("e:/Reader's Delight/Reader's Delight official sym - Copy.png");
JLabel lblimage = new JLabel(null, li, JLabel.CENTER);


public editprofile(String s)
{
//super("Your Profile Information");
nfr.setSize(800,600);
nfr.setLocation(300,80);
nfr.setVisible(true);
//nfr.add(lblimage);
getdata(s);
}



public void getdata(final String s1)
{
 try
 {
 Class.forName("oracle.jdbc.driver.OracleDriver");

Connection con=DriverManager.getConnection(
"jdbc:oracle:thin:@localhost:1521:xe","system","password00");

 String qry = "select * from login_user where username = ?";
PreparedStatement stmt=con.prepareStatement(qry);
stmt.setString(1,s1);
ResultSet rs = stmt.executeQuery();
while(rs.next())
{
username = rs.getString(3);
name = rs.getString(2);
//password= rs.getString(4);
hint = rs.getString(5);
bdate = rs.getString(6);

country = rs.getString(8);
email = rs.getString(9);
}
con.commit();
con.close();
}catch(Exception e){}

JLabel lname = new JLabel("Name: " +name);

JLabel luname = new JLabel("Username:"+username);
//JLabel lpass = new JLabel("Password:"+password);
JButton bcpass = new JButton("Change Password");
JLabel lpasshint = new JLabel("Hint for Password:"+hint);
JLabel ldate = new JLabel("Birthdate:"+bdate);
JLabel lcountry = new JLabel("Country:"+country);
JLabel lemail = new JLabel("E-mail id:"+email);

panel.setLayout (null);


//Username.setBounds(200,40,200,20);
lname.setBounds(50,40,200,20);
//Fname.setBounds(200,70,200,20);
luname.setBounds(50,70,200,20);
//pass.setBounds(200,100,200,20);
//lpass.setBounds(50,100,200,20);
//passconf.setBounds(200,130,200,20);
bcpass.setBounds(400,100,200,20);
//Hint.setBounds(200,160,200,20);
lpasshint.setBounds(50,130,200,20);
//Bdate.setBounds(200,190,200,20);
ldate.setBounds(50,160,200,20);
//Country.setBounds(200,220,200,20);
lcountry.setBounds(50,190,200,20);
//Email.setBounds(200,250,200,20);
lemail.setBounds(50,220,400,20);

panel.add(lblimage);
panel.add(bcpass);
//panel.add(Username);
panel.add(lname);
//panel.add(Fname);
panel.add(luname);
//panel.add(pass);
//panel.add(lpass);
//panel.add(passconf);
//panel.add(l3);
//panel.add(Hint);
panel.add(lpasshint);
//panel.add(Bdate);
panel.add(ldate);
//panel.add(Country);
panel.add(lcountry);
//panel.add(Email);
panel.add(lemail);



nfr.getContentPane().add(panel);
lblimage.setBounds(0,0,49,58);
//nfr.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
nfr.setVisible(true);


bcpass.addActionListener(new ActionListener() 
{
	public void actionPerformed(ActionEvent ae) 
	{
		nframe nfr2 = new nframe(s1);
		nfr2.setVisible(true);
	}
});

}



}




