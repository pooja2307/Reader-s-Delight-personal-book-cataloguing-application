package p1;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.sql.*;
import javax.swing.table.*;
import java.util.*;

public class myProfile1{

public static void main(String[] args){
myProfile1 proframe = new myProfile1("Welcome");
}
JFrame framet = new JFrame();

JButton bhome = new JButton("Home");
JButton bmyprof = new JButton("My Profile");
JButton brecom = new JButton("Recommendation");
JButton bsearch = new JButton("Search");

JPanel panel = new JPanel();
JPanel p = new JPanel();

ImageIcon li = new ImageIcon("e:/Reader's Delight/Reader's Delight official sym - Copy.png");
JLabel lblimage = new JLabel(null, li, JLabel.CENTER);


myProfile1(String s){
framet.setTitle("Welcome" +s+": My Profile");

framet.setLocation(300,80);
Dimension minSize = new Dimension(800,600);
framet.setMinimumSize(minSize);

panel.setLayout (null); 
p.setLayout (new BorderLayout());

bhome.setBounds(60,20,80,20);
bmyprof.setBounds(160,20,120,20);
brecom.setBounds(300,20,140,20);
bsearch.setBounds(690,20,80,20);
lblimage.setBounds(5,5,49,58);

panel.add(bhome);
panel.add(bmyprof);
panel.add(brecom);
panel.add(bsearch);

Vector columnNames = new Vector();
Vector data = new Vector();
try 
{
	Class.forName("oracle.jdbc.driver.OracleDriver");
	Connection con=DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe","system","password00");
	Statement stmt=con.createStatement();

	String sql = "Select * from login_user";
	ResultSet rs = stmt.executeQuery( sql );
	ResultSetMetaData md = rs.getMetaData();
	int columns = md.getColumnCount();

	for(int i = 1; i <= columns; i++)
	{
		columnNames.addElement( md.getColumnName(i) );
	}
	while (rs.next())
	{
		Vector row = new Vector(columns);
		for(int i = 1; i <= columns; i++)
		{
			row.addElement( rs.getObject(i) );
		}
		data.addElement( row );
	}
	rs.close();
	stmt.close();
}
catch(Exception e)
{
	System.out.println(e);
}
JTable table = new JTable(data, columnNames);
TableColumn col;
for(int i = 0; i < table.getColumnCount(); i++)
{
	col = table.getColumnModel().getColumn(i);
	col.setMaxWidth(350);
}
JScrollPane scrollPane = new JScrollPane( table );
p.add( scrollPane);
//framet.setLayout(new BorderLayout() ); 

framet.getContentPane().add(lblimage);
framet.getContentPane().add(panel);
framet.getContentPane().add(p,BorderLayout.SOUTH);
/*
framet.getContentPane().add(lblimage,BorderLayout.WEST);
framet.getContentPane().add(panel,BorderLayout.CENTER);
framet.getContentPane().add(p,BorderLayout.SOUTH);
*/
framet.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

framet.pack();
framet.setVisible(true);
actionlogin(s);
}

public void actionlogin(final String s1){
bhome.addActionListener(new ActionListener() 
{
	public void actionPerformed(ActionEvent ae) 
	{
		homePage homeframe1 = new homePage(s1);
	//	homeframe1.setVisible(true);
		framet.dispose();
	}
});	



bmyprof.addActionListener(new ActionListener() 
{
	public void actionPerformed(ActionEvent ae) 
	{
		myProfile nf1 = new myProfile(s1);
	//	nf1.setVisible(true);
		framet.dispose();
	}
});

brecom.addActionListener(new ActionListener() 
{
	public void actionPerformed(ActionEvent ae) 
	{
		newframe nf2 = new newframe("working");
	//	nf2.setVisible(true);
		framet.dispose();
	}
});

bsearch.addActionListener(new ActionListener() 
{
	public void actionPerformed(ActionEvent ae) 
	{
		newframe nf3 = new newframe("working");
	//	nf3.setVisible(true);
		framet.dispose();
	}
});
}
}