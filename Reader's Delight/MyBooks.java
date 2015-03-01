package p1;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.sql.*;
import javax.swing.table.*;
import java.util.*;
import java.applet.*;

public class MyBooks extends Applet{

public static void main(String[] args){
MyBooks reframe = new MyBooks("Welcome");
}

JFrame recframe = new JFrame();
JButton bhome = new JButton("Home");
JButton banalysis = new JButton("Analysis");
JButton bmyprof = new JButton("My Profile");
JButton bmybook = new JButton("My Books");
JButton brecom = new JButton("Recommendation");
JButton bsearch = new JButton("SEARCH");
JPanel panel = new JPanel();
JPanel p = new JPanel();
JLayeredPane lpane = new JLayeredPane();
Font bname = new Font("Monotype Corsiva", Font.ITALIC, 29);
JLabel notice = new JLabel("Click On The Book Name Cell To Edit Your Book Entry...");

JLabel dis1 = new JLabel("My Books");

ImageIcon li = new ImageIcon("e:/Reader's Delight/Reader's Delight official sym - Copy.png");
JLabel lblimage = new JLabel(null, li, JLabel.CENTER);

JPopupMenu menu = new JPopupMenu("Menu");
//final JTable table; 

int uid =0;
	
MyBooks(String s)
{
recframe.setTitle(" " +s+" : My Books");
recframe.setLocation(300,80);
Dimension minSize = new Dimension(800,600);
recframe.setMinimumSize(minSize);

panel.setLayout (null); 
   
bhome.setBounds(60,20,84,20);
banalysis.setBounds(284,75,120,30);
bmyprof.setBounds(154,20,120,20);
bmybook.setBounds(284,20,120,20);
brecom.setBounds(414,20,140,20);
bsearch.setBounds(640,20,85,20);
dis1.setBounds(320,120,250,70);
dis1.setFont(bname);
notice.setBounds(220,170,600,30);

panel.add(bhome);
panel.add(bmybook);
panel.add(bmyprof);
panel.add(brecom);
panel.add(bsearch);
recframe.add(dis1);
panel.add(banalysis);
lblimage.setBounds(5,5,49,58);

displaytable(s);
    
	
recframe.getContentPane().add(lblimage);
recframe.getContentPane().add(notice);
//recframe.getContentPane().add(panel);	
//recframe.getContentPane().add(p);		, BorderLayout.SOUTH
recframe.add(lpane, BorderLayout.CENTER);

	
	p.setBounds(10,30,800,40);
	p.setOpaque(true);
	p.setBorder(BorderFactory.createLineBorder(Color.blue));
    p.setPreferredSize(new Dimension(500, 390));
	
    lpane.setBounds(0, 0, 600, 400);
	panel.setBounds(0, 0, 800, 600);
    panel.setOpaque(true);
	
    p.setBounds(0, 200, 800, 200);
    p.setOpaque(true);
    lpane.add(panel , new Integer(0), 0);
    lpane.add(p , new Integer(1), 0);

//	recframe.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	recframe.setVisible(true);
	actionlogin(s);
}

public void displaytable(final String uname)
{
	p.setLayout (new BorderLayout());	
	Vector columnNames = new Vector();
	Vector data = new Vector();
	try 
	{
		Class.forName("oracle.jdbc.driver.OracleDriver");
		Connection con=DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe","system","password00");
		
		PreparedStatement stmtid=con.prepareStatement("Select * from login_user where username = ? ");
		stmtid.setString(1,uname);							
		ResultSet rsid = stmtid.executeQuery();
		while(rsid.next())
		{
			uid = rsid.getInt("U_ID");
		}
	
		String qry = "select b.bname, au.aname, pb.pname, ub.status from user_books ub, author au, publication pb, books b, written_by wb where ub.u_id = ? and b.b_id = ub.b_id and wb.b_id = ub.b_id and pb.p_id = b.p_id and au.a_id = wb.a_id";
	
		PreparedStatement stmt=con.prepareStatement(qry);	
		stmt.setInt(1,uid);
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
	catch(Exception e1)
	{
		System.out.println(e1);
	}
	final JTable table = new JTable(data, columnNames);
	DefaultTableModel model;
	
	TableColumn col;
	for(int i = 0; i < table.getColumnCount(); i++)
	{
		col = table.getColumnModel().getColumn(i);
		col.setMaxWidth(350);
	}
	table.setAutoCreateRowSorter(true);

	JScrollPane scrollPane = new JScrollPane( table, JScrollPane.VERTICAL_SCROLLBAR_ALWAYS, JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS )
	{ 
		public Dimension getMaximumSize () 
		{
			double height = 0;
	
			double width = super.getMaximumSize().getWidth();
			height += columnHeader.getViewSize().getHeight();           
			height += viewport.getView().getPreferredSize().getHeight();
			height += (getViewportBorderBounds().getHeight() * -1);
			Dimension returnValue = new Dimension ((int) width, (int) height);
			return returnValue;
		}
	};
	scrollPane.getViewport().setOpaque(true);
	p.remove(scrollPane);
	p.add(scrollPane);
	//p.add(new scrollPane);
	/*model =(DefaultTableModel)table.getModel();  
    model.fireTableDataChanged();*/
	table.revalidate();
	repaint();
	p.revalidate(); 
	
	table.addMouseListener(new MouseAdapter()
	{
		public void mouseClicked(MouseEvent e)
		{
			int row=table.rowAtPoint(e.getPoint());
			int col= table.columnAtPoint(e.getPoint());
			int book_id=0;
			int u_id= 0;
			String booknm = table.getValueAt(row,col).toString();
	//		System.out.println(booknm);
			if (col == 0)
			{
				try
				{
					Class.forName("oracle.jdbc.driver.OracleDriver");
					Connection con1=DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe","system","password00");

					PreparedStatement stmt1=con1.prepareStatement("Select b_id from books where bname = ?");
					stmt1.setString(1,booknm);
				
					ResultSet rs=stmt1.executeQuery();
					while(rs.next())
					{
						book_id = (rs.getInt(1));
//						System.out.println(book_id);
					}
					
					stmt1=con1.prepareStatement("Select u_id from login_user where username = ?");
					stmt1.setString(1,uname);
				
					 rs=stmt1.executeQuery();
					while(rs.next())
					{
						u_id = (rs.getInt("U_ID"));
//						System.out.println(book_id);
					}
					con1.close();
				}
				catch(Exception ex){System.out.println(e);}
				MyEntry bd1 = new MyEntry(u_id,book_id);
			}
		}
	});
}


public void actionlogin(final String s1){
bhome.addActionListener(new ActionListener() 
{
	public void actionPerformed(ActionEvent ae) 
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
		homePage homeframe1 = new homePage(s1);
//		homeframe1.setVisible(true);
		recframe.dispose();
	}
});	

		bmybook.addActionListener(new ActionListener() 
		{
			public void actionPerformed(ActionEvent ae) 
			{
				MyBooks nf1 = new MyBooks(s1);
			//	framet.dispose();
				recframe.dispose();
			}
		});

bmyprof.addActionListener(new ActionListener() 
{
	public void actionPerformed(ActionEvent ae) 
	{
		
		editprofile proframe1 = new editprofile(s1);
	//	nf1.setVisible(true);
	}
});

brecom.addActionListener(new ActionListener() 
{
	public void actionPerformed(ActionEvent ae) 
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
		reCom nf2 = new reCom(s1);
		recframe.dispose();
	}
});

banalysis.addActionListener(new ActionListener() 
{
	public void actionPerformed(ActionEvent ae) 
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
		
		Analysis af2 = new Analysis(s1);
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

		item1.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent e)
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
				
				searchTab nf21 = new searchTab(s1,0,"","","","");
				recframe.dispose();
			}
		});
		
		item2.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent e)
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
				
				searchTab nf22 = new searchTab(s1,1,"","","","");
				recframe.dispose();
			}
		});
	
		item3.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent e)
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
				
				searchTab nf23 = new searchTab(s1,2,"","","","");
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