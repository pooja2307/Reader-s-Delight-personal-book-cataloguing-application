package p1;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.sql.*;
import javax.swing.table.*;
import java.util.*;


public class searchTab extends JFrame {
		public static void main (String []args){
			searchTab tab = new searchTab("Welcome",0,"","","","");
		}
		 
		JButton bsearch1 = new JButton("Search Book");
		JButton bsearch2 = new JButton("Search Author");
		JButton bsearch3 = new JButton("Search Publication");

		ImageIcon li = new ImageIcon("e:/Reader's Delight/Reader's Delight official sym - Copy.png");
		JLabel lblimage1 = new JLabel(null, li, JLabel.CENTER);
		JLabel lblimage2 = new JLabel(null, li, JLabel.CENTER);
		JLabel lblimage3 = new JLabel(null, li, JLabel.CENTER);
		JButton bhome1 = new JButton("Back to Home");
	    JButton bhome2 = new JButton("Back to Home");
	    JButton bhome3 = new JButton("Back to Home");
	    
		JTextField Bookname1 = new JTextField(15);
		JTextField Bookname2 = new JTextField(15);
		JTextField Bookname3 = new JTextField(15);
		JTextField Auname1 = new JTextField(15);
		JTextField Auname2 = new JTextField(15);
		JTextField Auname3 = new JTextField(15);
		JTextField Pubname1 = new JTextField(15);
		JTextField Pubname2 = new JTextField(15);
		JTextField Pubname3 = new JTextField(15);
		JTextField Gename1 = new JTextField(15);
		JTextField Gename2 = new JTextField(15);
		JTextField Gename3 = new JTextField(15);
		
		JLabel c11=new JLabel("Book Title : ");
		JLabel c12=new JLabel("Book Title : ");
		JLabel c13=new JLabel("Book Title : ");
		JLabel c21=new JLabel("Author Name : ");
		JLabel c22=new JLabel("Author Name : ");
		JLabel c23=new JLabel("Author Name : ");
		JLabel c31=new JLabel("Publication Name : ");
		JLabel c32=new JLabel("Publication Name : ");
		JLabel c33=new JLabel("Publication Name : ");
		JLabel c41=new JLabel("Genre : ");
		JLabel c42=new JLabel("Genre : ");
		JLabel c43=new JLabel("Genre : ");
		JButton gotobb = new JButton("Go To Book");
		JTextField gotobf = new JTextField(15);
		JLabel gotobl = new JLabel("Book No. : ");
		JLabel dis1 = new JLabel("Select From Table :- ");
		JButton gotoab = new JButton("Go To Author");
		JTextField gotoaf = new JTextField(15);
		JLabel gotoal = new JLabel("Author No. : ");
		JLabel dis2 = new JLabel("Select From Table :- ");
		JButton gotopb = new JButton("Go To Publication");
		JTextField gotopf = new JTextField(15);
		JLabel gotopl = new JLabel("Publication No. : ");
		JLabel dis3 = new JLabel("Select From Table :- ");
		
		
		
		
		public searchTab(String uname, int z, String bkname, String akname, String pkname, String gkname) 
		{
       
        setTitle(" "+uname+" : Search");  
        setSize(800,600);
		setLocation(300,80);
		
		bhome1.setBounds(100,30,144,20);
		bhome2.setBounds(100,30,144,20);
		bhome3.setBounds(100,30,144,20);
		lblimage1.setBounds(5,5,49,58);
		lblimage2.setBounds(5,5,49,58);
		lblimage3.setBounds(5,5,49,58);
		
        Bookname1.setBounds(200,60,300,25);
		Bookname2.setBounds(200,60,300,25);
		Bookname3.setBounds(200,60,300,25);
		c11.setBounds(50,60,130,20);
		c12.setBounds(50,60,130,20);
		c13.setBounds(50,60,130,20);
		Auname1.setBounds(200,100,300,25);
		Auname2.setBounds(200,100,300,25);
		Auname3.setBounds(200,100,300,25);
		c21.setBounds(50,100,130,20);
		c22.setBounds(50,100,130,20);
		c23.setBounds(50,100,130,20);
		Pubname1.setBounds(200,140,300,25);
		Pubname2.setBounds(200,140,300,25);
		Pubname3.setBounds(200,140,300,25);
		c31.setBounds(50,140,150,20);
		c32.setBounds(50,140,150,20);
		c33.setBounds(50,140,150,20);
		Gename1.setBounds(200,180,300,25);
		Gename2.setBounds(200,180,300,25);
		Gename3.setBounds(200,180,300,25);
		c41.setBounds(50,180,130,20);
		c42.setBounds(50,180,130,20);
		c43.setBounds(50,180,130,20);
		
		gotobl.setBounds(100,245,145,20);
		gotoal.setBounds(100,245,145,20);
		gotopl.setBounds(100,245,150,20);
		gotobf.setBounds(175,245,150,20);
		gotoaf.setBounds(175,245,150,20);
		gotopf.setBounds(195,245,150,20);
		gotobb.setBounds(175,270,100,20);
		gotoab.setBounds(175,270,120,20);
		gotopb.setBounds(195,270,140,20);
		dis1.setBounds(125,220,170,30);
		dis2.setBounds(125,220,170,30);
		dis3.setBounds(125,220,170,30);
   
		JTabbedPane jtp = new JTabbedPane();
        getContentPane().add(jtp);
        
        JPanel jpbook = new JPanel();
		jpbook.setLayout (null); 
		
		jpbook.add(lblimage1);	
		jpbook.add(bhome1);	
        jpbook.add(Bookname1);	
        jpbook.add(c11);	
        jpbook.add(Auname1);	
        jpbook.add(c21);	
        jpbook.add(Pubname1);	
        jpbook.add(c31);	
        jpbook.add(Gename1);	
        jpbook.add(c41);	
		jpbook.add(gotobb);	
		jpbook.add(gotobf);	
		jpbook.add(gotobl);	
		jpbook.add(dis1);
		jpbook.add(bsearch1);
		
		
		Vector columnNames1 = new Vector();
		Vector columnNames2 = new Vector();
		Vector columnNames3 = new Vector();
		Vector data1 = new Vector();
		Vector data2 = new Vector();
		Vector data3 = new Vector();
		JPanel p1 = new JPanel();
		p1.setLayout (new BorderLayout());
		JPanel p2 = new JPanel();
		p2.setLayout (new BorderLayout());
		JPanel p3 = new JPanel();
		p3.setLayout (new BorderLayout());
		
		try 
		{
			Class.forName("oracle.jdbc.driver.OracleDriver");
			Connection con=DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe","system","password00");
			//Statement stmt=con.createStatement();
			System.out.println(""+bkname);
			String qry1 = "select * from books where lower(bname) like ? and b_id in (select b_id from written_by where a_id in (select a_id from author where lower(aname) like ?)) and p_id in (select p_id from publication where lower(pname) like ?)";
			PreparedStatement stmt1=con.prepareStatement(qry1);
			stmt1.setString(1,"%" + bkname + "%");
			stmt1.setString(2,"%" + akname + "%" );
			stmt1.setString(3,"%" + pkname + "%");
			
			
			ResultSet rs1 = stmt1.executeQuery();
			ResultSetMetaData md1 = rs1.getMetaData();
			int columns1 = md1.getColumnCount();

			for(int i = 1; i <= columns1; i++)
			{
				columnNames1.addElement( md1.getColumnName(i) );
			}	
			while (rs1.next())
			{
				Vector row1 = new Vector(columns1);
				for(int i = 1; i <= columns1; i++)
				{
					row1.addElement( rs1.getObject(i) );
				}
				data1.addElement( row1 );
			}
			rs1.close();
			stmt1.close();
		}
		catch(Exception e)
		{
			System.out.println(e);
		}
		
		JTable table1 = new JTable(data1, columnNames1);
		TableColumn col1;
		for(int i = 0; i < table1.getColumnCount(); i++)
		{
			col1 = table1.getColumnModel().getColumn(i);
			col1.setMaxWidth(1550);
		}
		JScrollPane scrollPane1 = new JScrollPane( table1, JScrollPane.VERTICAL_SCROLLBAR_ALWAYS, JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS );

		p1.add(scrollPane1);
		p1.setBorder(BorderFactory.createLineBorder(Color.blue));
		p1.setPreferredSize(new Dimension(750, 190));

		bsearch1.setBounds(600,138,150,30);
        
		JSplitPane splitPane1 = new JSplitPane(JSplitPane.VERTICAL_SPLIT,true, jpbook,p1);
		splitPane1.setContinuousLayout(false);
		splitPane1.setOneTouchExpandable(false);
		splitPane1.setDividerSize(10);
		splitPane1.setDividerLocation(300);
		
		JPanel jpauthor = new JPanel();
		jpauthor.setLayout (null);
		
		jpauthor.add(lblimage2);	
		jpauthor.add(bhome2);	
        jpauthor.add(Bookname2);	
        jpauthor.add(c12);	
        jpauthor.add(Auname2);	
        jpauthor.add(c22);	
        jpauthor.add(Pubname2);	
        jpauthor.add(c32);	
        jpauthor.add(Gename2);	
        jpauthor.add(c42);
		jpauthor.add(gotoab);	
		jpauthor.add(gotoaf);	
		jpauthor.add(gotoal);	
		jpauthor.add(dis2);
		
		bsearch2.setBounds(600,138,150,30);
        jpauthor.add(bsearch2);
		
		try 
		{
			Class.forName("oracle.jdbc.driver.OracleDriver");
			Connection con=DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe","system","password00");
			//Statement stmt=con.createStatement();

			String qry2 = "select a_id,aname,a_country from author";
			PreparedStatement stmt2=con.prepareStatement(qry2);
		
			ResultSet rs2 = stmt2.executeQuery();
			ResultSetMetaData md2 = rs2.getMetaData();
			int columns2 = md2.getColumnCount();

			for(int i = 1; i <= columns2; i++)
			{
				columnNames2.addElement( md2.getColumnName(i) );
			}	
			while (rs2.next())
			{
				Vector row2 = new Vector(columns2);
				for(int i = 1; i <= columns2; i++)
				{
					row2.addElement( rs2.getObject(i) );
				}
				data2.addElement( row2 );
			}
			rs2.close();
			stmt2.close();
		}
		catch(Exception e)
		{
			System.out.println(e);
		}
		
		JTable table2 = new JTable(data2, columnNames2);
		TableColumn col2;
		for(int i = 0; i < table2.getColumnCount(); i++)
		{
			col2 = table2.getColumnModel().getColumn(i);
			col2.setMaxWidth(550);
		}
		JScrollPane scrollPane2 = new JScrollPane( table2, JScrollPane.VERTICAL_SCROLLBAR_ALWAYS, JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS );
		//scrollPane.setLocation(10, 30);
		p2.add(scrollPane2);
		p2.setBorder(BorderFactory.createLineBorder(Color.blue));
		p2.setPreferredSize(new Dimension(750, 190));
		JSplitPane splitPane2 = new JSplitPane(JSplitPane.VERTICAL_SPLIT,true, jpauthor,p2);
		splitPane2.setContinuousLayout(false);
		splitPane2.setOneTouchExpandable(false);
		splitPane2.setDividerSize(10);
		splitPane2.setDividerLocation(300);
		
		JPanel jppubli = new JPanel();
		jppubli.setLayout (null);
		
		jppubli.add(lblimage3);	
		jppubli.add(bhome3);	
        jppubli.add(Bookname3);	
        jppubli.add(c13);	
        jppubli.add(Auname3);	
        jppubli.add(c23);	
        jppubli.add(Pubname3);	
        jppubli.add(c33);	
        jppubli.add(Gename3);	
        jppubli.add(c43);
		jppubli.add(gotopb);	
		jppubli.add(gotopf);	
		jppubli.add(gotopl);	
		jppubli.add(dis3);
		
		bsearch3.setBounds(600,138,150,30);
        jppubli.add(bsearch3);
		
		try 
		{
			Class.forName("oracle.jdbc.driver.OracleDriver");
			Connection con=DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe","system","password00");
			//Statement stmt=con.createStatement();

			String qry3 = "select p_id,pname,books_published from publication";
			PreparedStatement stmt3=con.prepareStatement(qry3);
		
			ResultSet rs3 = stmt3.executeQuery();
			ResultSetMetaData md3 = rs3.getMetaData();
			int columns3 = md3.getColumnCount();

			for(int i = 1; i <= columns3; i++)
			{
				columnNames3.addElement( md3.getColumnName(i) );
			}	
			while (rs3.next())
			{
				Vector row3 = new Vector(columns3);
				for(int i = 1; i <= columns3; i++)
				{
					row3.addElement( rs3.getObject(i) );
				}
				data3.addElement( row3 );
			}
			rs3.close();
			stmt3.close();
		}
		catch(Exception e)
		{
			System.out.println(e);
		}
		
		JTable table3 = new JTable(data3, columnNames3);
		TableColumn col3;
		for(int i = 0; i < table3.getColumnCount(); i++)
		{
			col3 = table3.getColumnModel().getColumn(i);
			col3.setMaxWidth(550);
		}
		JScrollPane scrollPane3 = new JScrollPane( table3, JScrollPane.VERTICAL_SCROLLBAR_ALWAYS, JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS );
		//scrollPane.setLocation(10, 30);
		p3.add(scrollPane3);
		p3.setBorder(BorderFactory.createLineBorder(Color.blue));
		p3.setPreferredSize(new Dimension(750, 190));
		JSplitPane splitPane3 = new JSplitPane(JSplitPane.VERTICAL_SPLIT,true, jppubli,p3);
		splitPane3.setContinuousLayout(false);
		splitPane3.setOneTouchExpandable(false);
		splitPane3.setDividerSize(10);
		splitPane3.setDividerLocation(300);
		
		jtp.addTab("Search Book", splitPane1);
        jtp.addTab("Search Author", splitPane2);
		jtp.addTab("Search Publication", splitPane3);
		jtp.setSelectedIndex(z);
		setVisible(true);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		actionlogin(uname);    
	}
    


public void actionlogin(final String uname1)
{
bhome1.addActionListener(new ActionListener() 
{
		public void actionPerformed(ActionEvent ae) 
		{
			homePage homeframe1 = new homePage(uname1);
		//	homeframe1.setVisible(true);
			dispose();
		}
});

bhome2.addActionListener(new ActionListener() 
{
		public void actionPerformed(ActionEvent ae) 
		{
			homePage homeframe1 = new homePage(uname1);
			//homeframe1.setVisible(true);
			dispose();
		}
});

bhome3.addActionListener(new ActionListener() 
{
		public void actionPerformed(ActionEvent ae) 
		{
			homePage homeframe1 = new homePage(uname1);
			//homeframe1.setVisible(true);
			dispose();
		}
});

bsearch1.addActionListener(new ActionListener()
{
		public void actionPerformed(ActionEvent ae)
		{
			String bkname1 = Bookname1.getText();
			String akname1 = Auname1.getText();
			String pkname1 = Pubname1.getText();
			String gkname1 = Gename1.getText();
			
			searchTab st1 = new searchTab(uname1,0,bkname1,akname1,pkname1,gkname1);
			st1.setVisible(true);
			dispose();
		}
});

}
}