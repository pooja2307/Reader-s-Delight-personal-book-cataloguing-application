package p1;
import javax.swing.*;
import javax.swing.JFrame;
import java.awt.*;
import java.awt.event.*;
import java.sql.*;
import java.awt.Frame;

public class homePage implements Runnable
{
	public static void main (String[] args)
	{
		homePage homeframe = new homePage("Welcome");
	}
	
	JFrame framet = new JFrame();
	JButton bhome = new JButton("Home");
	JButton bmyprof = new JButton("My Profile");
	JButton bmybook = new JButton("My Books");
	JButton brecom = new JButton("Recommendation");
	JButton bsearch = new JButton("SEARCH");
	JButton bsigno = new JButton("Sign Out");
	String addr[] = new String[4];
	final JPanel Bookpics = new JPanel();
	GridLayout BookLayout = new GridLayout(1,3);
	Thread t;
	JTextArea quotext = new JTextArea("");
	Font fontbname = new Font("Monotype Corsiva", Font.ITALIC, 21);
	Font warning = new Font("Arial", Font.PLAIN, 18);
	Font qbuk = new Font("Calibri", Font.BOLD, 19);
		
	JButton book1 = new JButton("View My Entry");
	JButton book2 = new JButton("View My Entry");
	JButton book3 = new JButton("View My Entry");

	JPanel panel = new JPanel();

	ImageIcon li = new ImageIcon("e:/Reader's Delight/Reader's Delight official sym - Copy.png");
	JLabel lblimage = new JLabel(null, li, JLabel.CENTER);
	ImageIcon li1 = new ImageIcon("e:/Reader's delight/slider1.png");
	JLabel shelf = new JLabel(null, li1, JLabel.CENTER);
	int count = 0;
	int quoid=0;
	JLabel quobook = new JLabel();

	JPopupMenu menu = new JPopupMenu("Menu");
	
	public homePage(String s)
	{
		framet.setTitle("Welcome "+s+" : Homepage");
		framet.setLocation(210,30);
		Dimension minSize = new Dimension(950,700);
		framet.setMinimumSize(minSize);
		panel.setLayout (null); 

		t = new Thread(this, "Quotes Thread");
		t.start();

		quotext.setFont(fontbname);
		quotext.setBounds(20,100,900,50);
		quotext.setOpaque(false);
		quotext.setLineWrap(true);
		quotext.setEditable(false);
		quotext.setWrapStyleWord(true);
		quobook.setFont(qbuk);
		quobook.setBounds(450,128,500,50);
		quobook.setHorizontalAlignment(JTextField.CENTER);
		
		
		bhome.setBounds(60,20,84,20);
		bmyprof.setBounds(154,20,120,20);
		bmybook.setBounds(284,20,120,20);
		brecom.setBounds(414,20,140,20);
		bsearch.setBounds(640,20,85,20);
		bsigno.setBounds(60,50,84,20);

		panel.add(bhome);
		panel.add(bmybook);
		panel.add(bmyprof);
		panel.add(brecom);
		panel.add(bsearch);
		panel.add(bsigno);
		panel.add(quotext);
		panel.add(quobook);

		lblimage.setBounds(5,5,49,58);
		shelf.setBounds(110,260,720,310);
		
		try
		{
			Class.forName("oracle.jdbc.driver.OracleDriver");
			Connection concheck=DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe","system","password00");
	
			PreparedStatement stmtchk=concheck.prepareStatement("select count(*) from books where b_id in (select b_id from user_books where u_id in (select u_id from login_user where username = ?))");
			stmtchk.setString(1,s);
		
			ResultSet rs=stmtchk.executeQuery();
			while(rs.next())
			{
				count = rs.getInt(1);
				System.out.println(count);
			}
			concheck.close();
		}
		catch(Exception e){System.out.println(e);}
		if (count >= 3)
		{
			try
			{
				Class.forName("oracle.jdbc.driver.OracleDriver");
				Connection con=DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe","system","password00");

				int i = 0;
				System.out.println(s);
				PreparedStatement stmt7=con.prepareStatement("select * from (select * from books where b_id in (select b_id from user_books where u_id in (select u_id from login_user where username = ? ))order by dbms_random.value) where rownum <= 3");
				stmt7.setString(1,s);
				ResultSet rs=stmt7.executeQuery();
				while(rs.next())
				{
					addr[i] = (rs.getString(6));
					System.out.println("Added : " + addr[i]);
					i++;
				}
				con.close();
			}
			catch(Exception e){System.out.println(e);}
		}
		else
		{
			JLabel notice = new JLabel("Welcome "+s+"... To make best use of this software, you must first ");
			JLabel notice1 = new JLabel("add three books to your account...!!");
			notice.setForeground(Color.WHITE);
			notice1.setForeground(Color.WHITE);
			notice.setFont(warning);
			notice1.setFont(warning);
			framet.getContentPane().add(notice);
			framet.getContentPane().add(notice1);
			notice.setBounds(190,370,800,40);
			notice1.setBounds(260,410,600,40); 
			brecom.setEnabled(false);
		}

		framet.getContentPane().add(lblimage);
		if(count >= 3)
		{

			panel.add(book1);
			panel.add(book2);
			panel.add(book3);
			book1.setBounds(186,590,135,25);
			book2.setBounds(395,590,135,25);
			book3.setBounds(610,590,135,25);
			morethanthree(s);
			addComponentsToPane(s,framet.getContentPane());
			framet.getContentPane().add(Bookpics);
			Bookpics.setBounds(165,295,600,240);
		}

		framet.getContentPane().add(shelf);
		framet.getContentPane().add(panel);	
		framet.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		framet.setResizable(false);
	//	framet.setUndecorated(true);
		framet.setVisible(true);
		panel.setOpaque(true);
		actionlogin(s);
	}

	public void run() 
	{
		try 
		{
			Class.forName("oracle.jdbc.driver.OracleDriver");
			Connection conn=DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe","system","password00");
			
			PreparedStatement quote = conn.prepareStatement("select q.*,b.bname from Quotes q,books b where q.b_id = b.b_id	order by dbms_random.value");
		
			ResultSet rs=quote.executeQuery();
			while(rs.next())
			{
				quobook.setText("- " + rs.getString(4));
	//			quoid = rs.getInt("b_id");
				quotext.setText(rs.getString(2));
				Thread.sleep(6000);
			//		System.out.println(quotext);
			}
			conn.close();
			
		} 
		catch (Exception e) {System.out.println(e);}
		//     System.out.println("Exiting Quote thread.");
	}

	public void addComponentsToPane(final String s1,final Container pane) 
	{
		Bookpics.setLayout(BookLayout);
   		//Bookpics.setSize(600,240);
		//BookLayout.setVgap(40);
	
		        
		ImageIcon ii=new ImageIcon(addr[0]);
		ImageIcon ii2=new ImageIcon(addr[1]);
		ImageIcon ii3=new ImageIcon(addr[2]);
	
		JLabel label=new JLabel(ii);  
		JLabel label2=new JLabel(ii2);  
		JLabel label3=new JLabel(ii3);  
	
		BookLayout.setHgap(30);
	    
		Bookpics.add(label);
		Bookpics.add(label2);
        Bookpics.add(label3);
		
		Bookpics.setOpaque(false);
		
		label.addMouseListener(new MouseAdapter() 
		{
			public void mouseClicked(MouseEvent e)
			{
				int bid1 = 0;
				try
				{
					Class.forName("oracle.jdbc.driver.OracleDriver");
					Connection con1=DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe","system","password00");
	
					PreparedStatement stmt1=con1.prepareStatement("Select b_id from books where cover_pg = ?");
					stmt1.setString(1,addr[0]);
				
					ResultSet rs=stmt1.executeQuery();
					while(rs.next())
					{
						bid1 = (rs.getInt(1));
						System.out.println(bid1);
					}
					con1.close();
				}
				catch(Exception ex){System.out.println(e);}
				BookDesc bd1 = new BookDesc(s1,bid1);
			}
		});
		
		label2.addMouseListener(new MouseAdapter() 
		{
			public void mouseClicked(MouseEvent e)
			{
				int bid1 = 0;
				try
				{
					Class.forName("oracle.jdbc.driver.OracleDriver");
					Connection con1=DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe","system","password00");
	
					PreparedStatement stmt1=con1.prepareStatement("Select b_id from books where cover_pg = ?");
					stmt1.setString(1,addr[1]);
				
					ResultSet rs=stmt1.executeQuery();
					while(rs.next())
					{
						bid1 = (rs.getInt(1));
						System.out.println(bid1);
					}
					con1.close();
				}
				catch(Exception ex){System.out.println(e);}
				BookDesc bd1 = new BookDesc(s1,bid1);
			}
		});
		
		label3.addMouseListener(new MouseAdapter() 
		{
			public void mouseClicked(MouseEvent e)
			{
				int bid1 = 0;
				try
				{
					Class.forName("oracle.jdbc.driver.OracleDriver");
					Connection con1=DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe","system","password00");
	
					PreparedStatement stmt1=con1.prepareStatement("Select b_id from books where cover_pg = ?");
					stmt1.setString(1,addr[2]);
				
					ResultSet rs=stmt1.executeQuery();
					while(rs.next())
					{
						bid1 = (rs.getInt(1));
						System.out.println(bid1);
					}
					con1.close();
				}
				catch(Exception ex){System.out.println(e);}
				BookDesc bd1 = new BookDesc(s1,bid1);
			}
		});
	}

	public void morethanthree(final String s1)
	{
		book1.addActionListener(new ActionListener() 
		{	
			public void actionPerformed(ActionEvent ae) 
			{
				int bid1 = 0;
				int uid1 = 0;
				try
				{
					Class.forName("oracle.jdbc.driver.OracleDriver");
					Connection con1=DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe","system","password00");
		
					PreparedStatement stmt1=con1.prepareStatement("Select b_id from books where cover_pg = ?");
					stmt1.setString(1,addr[0]);
		
					ResultSet rs=stmt1.executeQuery();
					while(rs.next())
					{
						bid1 = (rs.getInt(1));
					}
					
					PreparedStatement stmt2=con1.prepareStatement("Select * from login_user where username = ?");
					stmt2.setString(1,s1);
		
					ResultSet rs0=stmt2.executeQuery();
					while(rs0.next())
					{
						uid1 = (rs0.getInt("U_ID"));
					}
					con1.close();
				}
				catch(Exception e){System.out.println(e);}
		
				MyEntry me1 = new MyEntry(uid1,bid1);
				//framet.dispose();
			}
		});	

		book2.addActionListener(new ActionListener() 
		{
			public void actionPerformed(ActionEvent ae) 
			{
				int bid2 = 0;
				int uid1 = 0;
				try
				{
					Class.forName("oracle.jdbc.driver.OracleDriver");
					Connection con2=DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe","system","password00");

					PreparedStatement stmt2=con2.prepareStatement("Select b_id from books where cover_pg = ?");
					stmt2.setString(1,addr[1]);
		
					ResultSet rs=stmt2.executeQuery();
					while(rs.next())
					{
						bid2 = (rs.getInt(1));
						System.out.println(bid2);
					}
					
					PreparedStatement stmt3=con2.prepareStatement("Select * from login_user where username = ?");
					stmt3.setString(1,s1);
		
					ResultSet rs0=stmt3.executeQuery();
					while(rs0.next())
					{
						uid1 = (rs0.getInt("U_ID"));
					}
					con2.close();
				}
				catch(Exception e){System.out.println(e);}
		
				MyEntry me2 = new MyEntry(uid1,bid2);
				//framet.dispose();
			}
		});

		book3.addActionListener(new ActionListener() 
		{
			public void actionPerformed(ActionEvent ae) 
			{
				int bid3 = 0;
				int uid1 = 0;
				try
				{
					Class.forName("oracle.jdbc.driver.OracleDriver");
					Connection con3=DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe","system","password00");

					PreparedStatement stmt3=con3.prepareStatement("Select b_id from books where cover_pg = ?");
					stmt3.setString(1,addr[2]);
			
					ResultSet rs=stmt3.executeQuery();
					while(rs.next())
					{
						bid3 = (rs.getInt(1));
						System.out.println(bid3);
					}
					
					PreparedStatement stmt2=con3.prepareStatement("Select * from login_user where username = ?");
					stmt2.setString(1,s1);
		
					ResultSet rs0=stmt2.executeQuery();
					while(rs0.next())
					{
						uid1 = (rs0.getInt("U_ID"));
					}
					con3.close();
				}
				catch(Exception e){System.out.println(e);}	
		
				MyEntry me3 = new MyEntry(uid1,bid3);
				//framet.dispose();
			}
		});

	}
	
	public void actionlogin(final String s1)
	{
		bhome.addActionListener(new ActionListener() 
		{
			public void actionPerformed(ActionEvent ae) 
			{
				homePage homeframe1 = new homePage(s1);
				framet.dispose();
			}
		});	

		bmybook.addActionListener(new ActionListener() 
		{
			public void actionPerformed(ActionEvent ae) 
			{
				MyBooks nf1 = new MyBooks(s1);
			//	framet.dispose();
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
				framet.dispose();;
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
				searchTab nf21 = new searchTab(s1,0,"","","","");
				framet.dispose();
			}
		});
		
		item2.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent e)
			{
				searchTab nf22 = new searchTab(s1,1,"","","","");
				framet.dispose();
			}
		});
	
		item3.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent e)
			{
				searchTab nf23 = new searchTab(s1,2,"","","","");
				framet.dispose();
			}
		});

		bsearch.addActionListener(new ActionListener() 
		{
			public void actionPerformed(ActionEvent ae) 
			{
				menu.show(bsearch, bsearch.getWidth()/2, bsearch.getHeight());
			}
		});


		bsigno.addActionListener(new ActionListener() 
		{
			public void actionPerformed(ActionEvent ae) 
			{
				Log nf3 = new Log();
				framet.dispose();
			}
		});
	}
}