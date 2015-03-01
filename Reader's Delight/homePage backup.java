package p1;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.sql.*;

public class homePage{

public static void main(String[] args){
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
final JPanel compsToExperiment = new JPanel();
GridLayout BookLayout = new GridLayout(1,3);

JPanel panel = new JPanel();

ImageIcon li = new ImageIcon("e:/Reader's Delight/Reader's Delight official sym - Copy.png");
JLabel lblimage = new JLabel(null, li, JLabel.CENTER);
ImageIcon li1 = new ImageIcon("e:/Reader's delight/slider.png");
JLabel lblimage1 = new JLabel(null, li1, JLabel.CENTER);

JPopupMenu menu = new JPopupMenu("Menu");
	
homePage(String s){
framet.setTitle("Welcome "+s+" : Homepage");
framet.setLocation(300,80);
Dimension minSize = new Dimension(800,600);
framet.setMinimumSize(minSize);
panel.setLayout (null); 

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
lblimage.setBounds(5,5,49,58);
lblimage1.setBounds(80,225,623,178);

try{
		Class.forName("oracle.jdbc.driver.OracleDriver");

		Connection con=DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe","system","password00");

		
		int i = 0;
		
		Statement stmt=con.createStatement();
		
		ResultSet rs=stmt.executeQuery("select * from (select * from images order by dbms_random.value) where rownum<=3");
		while(rs.next())
		{
		addr[i] = (rs.getString(1));
		System.out.println("Added : " + addr[i]);
		i++;
		}
}
catch(Exception e){System.out.println(e);}

framet.getContentPane().add(lblimage);
framet.getContentPane().add(compsToExperiment);
addComponentsToPane(framet.getContentPane());
framet.getContentPane().add(lblimage1);
framet.getContentPane().add(panel);	

compsToExperiment.setBounds(125,225,500,155);
framet.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
framet.setVisible(true);
panel.setOpaque(true);
actionlogin(s);
}

public void addComponentsToPane(final Container pane) 
{
        compsToExperiment.setLayout(BookLayout);
   		compsToExperiment.setSize(600,225);
        
	ImageIcon ii=new ImageIcon(addr[0]);
	ImageIcon ii2=new ImageIcon(addr[1]);
	ImageIcon ii3=new ImageIcon(addr[2]);
	
	JLabel label=new JLabel(ii);  
	JLabel label2=new JLabel(ii2);  
    JLabel label3=new JLabel(ii3);  
	
	BookLayout.setHgap(30);
	    
		compsToExperiment.add(label);
		compsToExperiment.add(label2);
        compsToExperiment.add(label3);
		compsToExperiment.setOpaque(false);
}





public void actionlogin(final String s1){
bhome.addActionListener(new ActionListener() 
{
	public void actionPerformed(ActionEvent ae) 
	{
		homePage homeframe1 = new homePage(s1);
		framet.dispose();
	}
});	



bmyprof.addActionListener(new ActionListener() 
{
	public void actionPerformed(ActionEvent ae) 
	{
		myProfile nf1 = new myProfile(s1);
		framet.dispose();
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

	item1.addActionListener(
		new ActionListener(){
			public void actionPerformed(ActionEvent e)
			{
				searchTab nf21 = new searchTab(s1,"",0);
				framet.dispose();
			}
		}
	);
	item2.addActionListener(
		new ActionListener(){
			public void actionPerformed(ActionEvent e)
			{
				searchTab nf22 = new searchTab(s1,"",1);
				framet.dispose();
			}
		}
	);
	item3.addActionListener(
		new ActionListener(){
			public void actionPerformed(ActionEvent e)
			{
				searchTab nf23 = new searchTab(s1,"",2);
				framet.dispose();
			}
		}
	);

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