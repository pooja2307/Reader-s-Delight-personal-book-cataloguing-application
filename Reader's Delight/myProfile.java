package p1;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.sql.*;
import javax.swing.table.*;
import java.util.*;

public class myProfile{

public static void main(String[] args)
{
	myProfile proframe = new myProfile("Welcome");
}
JFrame framet = new JFrame();

JButton bhome = new JButton("Home");
JButton bmyprof = new JButton("My Profile");
JButton bmybook = new JButton("My Books");
JButton bsigno = new JButton("Sign Out");
JButton bedit = new JButton("Edit Profile");

JPanel panel = new JPanel();
JPanel p = new JPanel();
//JPanel pedit = new JPanel();

ImageIcon li = new ImageIcon("e:/Reader's Delight/Reader's Delight official sym - Copy.png");
JLabel lblimage = new JLabel(null, li, JLabel.CENTER);


myProfile(String s)
{
	framet.setTitle(" " +s+" : My Profile");
	framet.setLocation(300,80);
	Dimension minSize = new Dimension(800,600);
	framet.setMinimumSize(minSize);

	panel.setLayout (null);
//	pedit.setLayout (new BorderLayout());
	p.setLayout (new BorderLayout());
	
	bhome.setBounds(60,20,84,20);
	bmyprof.setBounds(154,20,120,20);
	bmybook.setBounds(284,20,120,20);
	bsigno.setBounds(60,50,84,20);
	bedit.setBounds(30,100,140,20);
	lblimage.setBounds(5,5,49,58);

	panel.add(bhome);
	panel.add(bmybook);
	panel.add(bmyprof);
	panel.add(bsigno);
	panel.add(bedit);
//	pedit.add( bedit);
	
	Vector columnNames = new Vector();
	Vector data = new Vector();
	try 
	{
		Class.forName("oracle.jdbc.driver.OracleDriver");
		Connection con=DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe","system","password00");
		//Statement stmt=con.createStatement();

		String qry = "select * from login_user where username = ?";
		PreparedStatement stmt=con.prepareStatement(qry);	
		stmt.setString(1,s);
		
//		String sql = "Select * from login_user";
		ResultSet rs = stmt.executeQuery();
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
	JScrollPane scrollPane = new JScrollPane( table, JScrollPane.VERTICAL_SCROLLBAR_ALWAYS, JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS );
	
    scrollPane.setLocation(10, 30);
	p.add( scrollPane);
	p.setBorder(BorderFactory.createLineBorder(Color.blue));
    p.setPreferredSize(new Dimension(350, 190));
	 
	framet.getContentPane().add(lblimage);
	framet.getContentPane().add(panel);
	framet.getContentPane().add(p,BorderLayout.SOUTH);
	
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
		homeframe1.setVisible(true);
		framet.dispose();
	}
});	



bmyprof.addActionListener(new ActionListener() 
{
	public void actionPerformed(ActionEvent ae) 
	{
		myProfile nf11 = new myProfile(s1);
		framet.dispose();
	}
});


bsigno.addActionListener(new ActionListener() 
{
	public void actionPerformed(ActionEvent ae) 
	{
		Log nf3 = new Log();
		nf3.setVisible(true);
		framet.dispose();
	}
});
}
}