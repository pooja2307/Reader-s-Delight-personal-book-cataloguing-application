package p1;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.sql.*;

public class Analysis{

JPanel panel = new JPanel();
JFrame framet = new JFrame("Welcome");
JButton bhome = new JButton("Home");
JButton bmyprof = new JButton("My Profile");
JButton brecom = new JButton("Recommendation");
JButton bsearch = new JButton("Search");

JLabel l1 = new JLabel();
JLabel l2= new JLabel();
JLabel l3= new JLabel();
JLabel l4= new JLabel();
JLabel l5= new JLabel();
JLabel l6= new JLabel();

JPopupMenu menu = new JPopupMenu("Menu");
ImageIcon li = new ImageIcon("e:/Reader's Delight/Reader's Delight official sym - Copy.png");
JLabel lblimage = new JLabel(null, li, JLabel.CENTER);

public Analysis(String s){
framet.setTitle("Welcome : " + s);
framet.setSize(800,600);
framet.setLocation(300,80);
panel.setLayout (null); 

falana(s);

bhome.setBounds(60,20,80,20);
bmyprof.setBounds(160,20,120,20);
brecom.setBounds(300,20,140,20);
bsearch.setBounds(690,20,80,20);

l1.setBounds(30,90,400,25);
panel.add(l1);
l2.setBounds(30,160,300,25);
panel.add(l2);
l3.setBounds(30,230,300,25);
panel.add(l3);
l4.setBounds(30,300,300,25);
panel.add(l4);

panel.add(lblimage);

panel.add(bhome);
panel.add(bmyprof);
panel.add(brecom);
panel.add(bsearch);

lblimage.setBounds(5,5,49,58);
framet.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
framet.setVisible(true);

action(s);

}

public void falana(final String s1)
{
try{
Class.forName("oracle.jdbc.driver.OracleDriver");

Connection con=DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe","system","password00");
			
			int uid=0;
			PreparedStatement getuid = con.prepareStatement("Select u_id from login_user where username = ?");
			getuid.setString(1,s1);
			ResultSet rs1 = getuid.executeQuery();
			while(rs1.next())
			{
			uid = rs1.getInt(1);
			}
			System.out.println(uid);

PreparedStatement stmt1 = con.prepareStatement("Select count(*) from User_Books where u_id=?");
stmt1.setInt(1,uid);
ResultSet rs=stmt1.executeQuery();
while(rs.next())
{
l1 = new JLabel("Total No. Of Books added to my Account   :   " +rs.getInt(1));  
}

System.out.println(uid);


PreparedStatement stmt2 =con.prepareStatement("Select count(*) from User_Books where upper(status)='READ' and to_char(dt_added,'mon')=to_char(sysdate,'mon') and u_id=?");
stmt2.setInt(1,uid);
rs=stmt2.executeQuery();
while(rs.next())
{
l2 = new JLabel("No. Of Books 'READ' this month   :   "+rs.getInt(1));  
}

System.out.println(uid);


PreparedStatement stmt3 =con.prepareStatement("Select count(*) from User_Books where upper(status)='CURRENTLY READING' and to_char(dt_added,'mon')=to_char(sysdate,'mon') and u_id=?");
stmt3.setInt(1,uid);
rs=stmt3.executeQuery();
while(rs.next())
{
l3 = new JLabel("No. Of Books which are being 'Read Currently'   :   "+rs.getInt(1));  
}

System.out.println(uid);

PreparedStatement stmt4 =con.prepareStatement("Select count(*) from User_Books where upper(status)='WISHLIST' and to_char(dt_added,'mon')=to_char(sysdate,'mon') and u_id=?");
stmt4.setInt(1,uid);
 rs=stmt4.executeQuery();
while(rs.next())
{
l4 = new JLabel("No. Of Books added to 'Wishlist' this month   :   "+rs.getInt(1));  
}


System.out.println(uid);

PreparedStatement stmt5 =con.prepareStatement("Select count(g_id) from Book_Category where b_id in (select b_id from User_Books where uid=?) ");
stmt5.setInt(1,uid);
 rs=stmt5.executeQuery();
while(rs.next())
{
l5 = new JLabel("No. Of Genres added to MyBooks this month   :   "+rs);  
}



PreparedStatement stmt6 =con.prepareStatement("Select aname from Author where a_id in ( select a_id from Written_by where b_id in (select b_id from User_Books where uid=?)))");
stmt4.setInt(1,uid);
 rs=stmt6.executeQuery();
while(rs.next())
{
l6 = new JLabel("Authors of books read till now   :   "+rs.getInt(1));  
}



}
catch(Exception e){}

framet.getContentPane().add(panel);

}


public void action(final String s2)
{
bhome.addActionListener(new ActionListener() 
{
	public void actionPerformed(ActionEvent ae) 
	{
		homePage homeframe1 = new homePage(s2);
//		homeframe1.setVisible(true);
		framet.dispose();
	}
});	



bmyprof.addActionListener(new ActionListener() 
{
	public void actionPerformed(ActionEvent ae) 
	{
		editprofile proframe1 = new editprofile(s2);
	}
});

brecom.addActionListener(new ActionListener() 
{
	public void actionPerformed(ActionEvent ae) 
	{
		reCom nf2 = new reCom(s2);
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
				searchTab nf21 = new searchTab(s2,0,"","","","");
				framet.dispose();
			}
		});
		
		item2.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent e)
			{
				searchTab nf22 = new searchTab(s2,1,"","","","");
				framet.dispose();
			}
		});
	
		item3.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent e)
			{
				searchTab nf23 = new searchTab(s2,2,"","","","");
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
}

public static void main(String[] args)
{
Analysis afr = new Analysis("Welcome");
}

}
