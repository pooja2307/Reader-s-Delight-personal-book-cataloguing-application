package p1;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.sql.*;
import javax.swing.table.*;
import java.util.*;

public class reCom{

public static void main(String[] args){
reCom reframe = new reCom("Welcome");
}

JFrame recframe = new JFrame();
JButton bhome = new JButton("Home");
JButton bmyprof = new JButton("My Profile");
JButton bmybook = new JButton("My Books");
JButton brecom = new JButton("Recommendation");
JButton bsearch = new JButton("SEARCH");
JPanel panel = new JPanel();
JPanel p = new JPanel();
JLayeredPane lpane = new JLayeredPane();

Font bname = new Font("Monotype Corsiva", Font.ITALIC, 25);

//JButton gotob = new JButton("Go To Book");
//JTextField gotof = new JTextField(15);
//JLabel gotol = new JLabel("Book No. : ");
JLabel dis1 = new JLabel("Recommendations : Based on Author and Genre");

ImageIcon li = new ImageIcon("e:/Reader's Delight/Reader's Delight official sym - Copy.png");
JLabel lblimage = new JLabel(null, li, JLabel.CENTER);

JPopupMenu menu = new JPopupMenu("Menu");
	
reCom(String s){
recframe.setTitle(" " +s+" : Recommendations");
recframe.setLocation(300,80);
Dimension minSize = new Dimension(800,600);
recframe.setMinimumSize(minSize);

panel.setLayout (null); 

bhome.setBounds(60,20,84,20);
bmyprof.setBounds(154,20,120,20);
bmybook.setBounds(284,20,120,20);
brecom.setBounds(414,20,140,20);
bsearch.setBounds(640,20,85,20);

//gotol.setBounds(100,100,145,20);
//gotof.setBounds(175,100,150,20);
//gotob.setBounds(175,130,100,20);
dis1.setBounds(135,125,2000,30);
dis1.setFont(bname);

panel.add(bhome);
panel.add(bmybook);
panel.add(bmyprof);
panel.add(brecom);
panel.add(bsearch);
//panel.add(gotob);
//panel.add(gotof);
//panel.add(gotol);
panel.add(dis1);

lblimage.setBounds(5,5,49,58);


    p.setLayout (new BorderLayout());	
	Vector columnNames = new Vector();
	Vector data = new Vector();
	try 
	{
		Class.forName("oracle.jdbc.driver.OracleDriver");
		Connection con=DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe","system","password00");

		String qry = "select bname,synopsis,b_price,b_pubrating from (select * from books order by dbms_random.value) where rownum<=3 and b_id not in (select b_id from user_books where u_id in (select u_id from login_user where username = ?)) and b_id in (select b_id from written_by where a_id in (select a_id from written_by where b_id in (select b_id from user_books where u_id in (select u_id from login_user where username = ?)))) and b_id in (select b_id from book_category where g_id in (select g_id from book_category where b_id in (select b_id from user_books where u_id  in (select u_id from login_user where username = ?))))";
		PreparedStatement stmt=con.prepareStatement(qry);	
		stmt.setString(1,s);
		stmt.setString(2,s);
		stmt.setString(3,s);
		
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
	JScrollPane scrollPane = new JScrollPane( table, JScrollPane.VERTICAL_SCROLLBAR_ALWAYS, JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS ){          
      public Dimension getMaximumSize () {
        double height = 0;

        double width = super.getMaximumSize().getWidth();
        height += columnHeader.getViewSize().getHeight();           
        height += viewport.getView().getPreferredSize().getHeight();
        height += (getViewportBorderBounds().getHeight() * -1);
        Dimension returnValue = new Dimension ((int) width, (int) height);
        return returnValue;
      }
    };

	p.add( scrollPane);
	p.setBounds(10,30,800,40);
	p.setOpaque(true);
	p.setBorder(BorderFactory.createLineBorder(Color.blue));
    p.setPreferredSize(new Dimension(500, 390));
	
    lpane.setBounds(0, 0, 600, 400);
	panel.setBounds(0, 0, 600, 400);
    panel.setOpaque(true);
    p.setBounds(0, 200, 800, 200);
    p.setOpaque(true);
    lpane.add(panel , new Integer(0), 0);
    lpane.add(p , new Integer(1), 0);
	
recframe.getContentPane().add(lblimage);
//recframe.getContentPane().add(panel);	
//recframe.getContentPane().add(p);		, BorderLayout.SOUTH
recframe.add(lpane, BorderLayout.CENTER);

recframe.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
recframe.setVisible(true);
actionlogin(s);

}

public void actionlogin(final String s1){
bhome.addActionListener(new ActionListener() 
{
	public void actionPerformed(ActionEvent ae) 
	{
		homePage homeframe1 = new homePage(s1);
		recframe.dispose();
	}
});	



bmyprof.addActionListener(new ActionListener() 
{
	public void actionPerformed(ActionEvent ae) 
	{
		editprofile proframe1 = new editprofile(s1);
	}
});

brecom.addActionListener(new ActionListener() 
{
	public void actionPerformed(ActionEvent ae) 
	{
		reCom nf2 = new reCom(s1);
		recframe.dispose();
	}
});

final JMenuItem item1 = new JMenuItem("BOOK");
item1.setComponentOrientation(ComponentOrientation.LEFT_TO_RIGHT);
final JMenuItem item2 = new JMenuItem("AUTHOR");
item2.setComponentOrientation(ComponentOrientation.LEFT_TO_RIGHT);
final JMenuItem item3 = new JMenuItem("PUBLICAITON");
item3.setComponentOrientation(ComponentOrientation.LEFT_TO_RIGHT);
	
menu.add(item1);
menu.add(item2);
menu.add(item3);

	item1.addActionListener(
		new ActionListener(){
			public void actionPerformed(ActionEvent e)
			{
				searchTab nf21 = new searchTab(s1,0,"","","","");
				recframe.dispose();
			}
		}
	);
	item2.addActionListener(
		new ActionListener(){
			public void actionPerformed(ActionEvent e)
			{
				searchTab nf22 = new searchTab(s1,1,"","","","");
				recframe.dispose();
			}
		}
	);
	item3.addActionListener(
		new ActionListener(){
			public void actionPerformed(ActionEvent e)
			{
				searchTab nf23 = new searchTab(s1,2,"","","","");
				recframe.dispose();
			}
		}
	);

			bmybook.addActionListener(new ActionListener() 
		{
			public void actionPerformed(ActionEvent ae) 
			{
				MyBooks nf1 = new MyBooks(s1);
			//	framet.dispose();
				recframe.dispose();
			}
		});
		
bsearch.addActionListener(new ActionListener() 
{
	public void actionPerformed(ActionEvent ae) 
	{
		menu.show(bsearch, bsearch.getWidth()/2, bsearch.getHeight());
	}
});

}
}