package p1;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.sql.*;

public class U_Form extends JFrame {

public static void main(String[] args){
U_Form frameTabel = new U_Form();
}

JButton blogin = new JButton("Check Availability");
JPanel panel = new JPanel();
JTextField uname = new JTextField(15);
JLabel l1 = new JLabel("Username : ");
JLabel l2 = new JLabel("Check Availability");


U_Form(){
super("Check Availability for Username");
setSize(400,300);
setLocation(450,200);
panel.setLayout (null); 

l2.setBounds(140,75,100,30);
uname.setBounds(150,110,150,20);
l1.setBounds(80,110,80,20);
blogin.setBounds(150,140,80,20);

panel.add(l2);
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
ResultSet rs=stmt.executeQuery("select username from login_user");
int flag=0;
while(rs.next())
{
	if(puname.equals(rs.getString(1)))
	{
		flag=1;
	}
}


if(flag==0)
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
	RegForm frameTable1 = new RegForm(puname);
	frameTable1.setVisible(true);
	dispose();
}
else
{
	JOptionPane.showMessageDialog(null,"This Username is not available.");
	uname.setText("");
	uname.requestFocus();
}

con.close();
}

catch(Exception e){ System.out.println(e);}
}
});
}
}
