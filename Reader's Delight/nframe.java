package p1;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.sql.*;
import java.util.*;
public class nframe extends JFrame
{
public static void main(String[] st)
{
nframe n2= new nframe("Welcome");
}
JButton bok = new JButton("OK");


JTextField CurrentPassword = new JTextField(15);
JLabel lcurpass = new JLabel("Current Password :");

JPasswordField NewPassword = new JPasswordField(15);
JLabel lnewpass = new JLabel("New Password : ");

JPasswordField confirmnewpass = new JPasswordField(15);
JLabel lconfirm = new JLabel("Confirm new password : ");
JPanel panel2= new JPanel();

nframe(String s)
{
super("Edit Password : " + s);
setSize(800,600);
setLocation(300,80);

CurrentPassword.setBounds(200,40,200,20);
lcurpass.setBounds(50,40,140,20);
NewPassword.setBounds(200,70,200,20);
lnewpass.setBounds(50,70,130,20);
confirmnewpass.setBounds(200,100,200,20);
lconfirm.setBounds(50,100,150,20);
bok.setBounds(100,140,150,20);


panel2.add(bok);
panel2.add(CurrentPassword);
panel2.add(lcurpass);
panel2.add(NewPassword);
panel2.add(lnewpass);
panel2.add(confirmnewpass);
panel2.add(lconfirm);
panel2.setLayout (null);
getContentPane().add(panel2);
//setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
setVisible(true);
changepsw2(s);
}

public void changepsw2(final String us1)
{

bok.addActionListener(new ActionListener() {
public void actionPerformed(ActionEvent ae) {
int flag=0;
String s1 = CurrentPassword.getText();
String s2 = NewPassword.getText();
String s3 = confirmnewpass.getText();

try
{
Class.forName("oracle.jdbc.driver.OracleDriver");

Connection con=DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe","system","password00");
String qry = "select * from login_user where username = ?";

PreparedStatement stmt=con.prepareStatement(qry);
stmt.setString(1,us1);
ResultSet rs = stmt.executeQuery();
while(rs.next())
{
String curpass = rs.getString(4);

if(( curpass.equals(s1)) && (s2.equals(s3)))
{
String qry1 = "update login_user set PASSWORD=?  where username=?";
PreparedStatement stmt1=con.prepareStatement(qry1);
	stmt1.setString(2,us1);
stmt1.setString(1,s2);
       stmt1.executeUpdate();
        JOptionPane.showMessageDialog(null,"Password Changed..!!!");
        dispose();
        flag=1;
	
}

else if(!s2.equals(s3))
{
JOptionPane.showMessageDialog(null,"Password & Confirm-Password do not match...!!!");
	CurrentPassword.setText("");
	NewPassword.setText("");
	confirmnewpass.setText("");
    CurrentPassword.requestFocus();
}
else if(!curpass.equals(s1))
{
JOptionPane.showMessageDialog(null,"Existing password is not correct...!!");
	CurrentPassword.setText("");
	NewPassword.setText("");
	confirmnewpass.setText("");
    CurrentPassword.requestFocus();
}


if(flag==1)
{

	
	final Frame[] frames = Frame.getFrames();  
        System.out.println("all frames:");  
        if (frames != null){  
            for (final Frame f : frames)  
            {  
                System.out.println("\t\t" + f.getTitle());  
                f.dispose();  
            }  
	}
	homePage hm = new homePage(us1);
	dispose();
}
con.commit();
con.close();
}
 
}catch(Exception e){ System.out.println(e);}
}
});
}

}
