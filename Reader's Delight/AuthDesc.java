package p1;
import javax.swing.JApplet;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JSplitPane;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import javax.swing.text.DefaultCaret;
import java.sql.*;

public class AuthDesc{


JFrame framet = new JFrame("Author Description");

  public int HORIZSPLIT = JSplitPane.HORIZONTAL_SPLIT;

  public int VERTSPLIT = JSplitPane.VERTICAL_SPLIT;
  
	JButton Add,BCritic;
	JTextArea aname,acountry,auth_desc,desct,bwrittent,bwritten,auth_desct;
	JPanel panel1,panel2;
	ImageIcon li;
//	int books_written;
    JLabel authimage;
	Font fontaname = new Font("Comic Sans MS", Font.BOLD, 24);
	Font fontsumm = new Font("Arial", Font.PLAIN, 14);
	Font fonttitle = new Font("Times New Roman", Font.BOLD, 16);
	
	
	String addr,Auth_Name,country,desc;
    
  
  public AuthDesc(String uname1,int b){

  try{

	Class.forName("oracle.jdbc.driver.OracleDriver");

	Connection con=DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe","system","password00");


	PreparedStatement stmt=con.prepareStatement("Select * from Author where a_id = ? ");
	stmt.setInt(1,b);							// <<----- To be initialized with constructor value a_id here when needed... 

	ResultSet rs = stmt.executeQuery();
	while(rs.next()){
				Auth_Name = rs.getString(2);
				country = rs.getString(3);
				desc = rs.getString(4);
				addr = rs.getString(5);
				}
				

	li = new ImageIcon(addr);
	

}catch(Exception e){System.out.println(e);}
  
 createPanel1();
 createPanel2();

 coverpg();
 
  
 //framet.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
 framet.setSize(900,650);
 framet.setLocation(250,60); 
 framet.setResizable(false);
 framet.setVisible(true);
 }
  
  public void createPanel1()
	{
		panel1 = new JPanel();
		panel1.setLayout( null );
		
		acountry = new JTextArea("Country: " +country);
		acountry.setOpaque(false);
		acountry.setEditable(false);
		acountry.setFont(fonttitle);

		aname = new JTextArea(Auth_Name);
		aname.setOpaque(false);
		aname.setEditable(false);
		aname.setFont(fontaname);
		
		acountry.setBounds(25,70,470,30);
		aname.setBounds(25,10,530,50);
	
		panel1.add( aname );
		panel1.add( acountry );

	}
	
	
  public void createPanel2()
	{
		panel2 = new JPanel();
		panel2.setLayout( null );
		
		auth_desct = new JTextArea("About This Author :");
		auth_desct.setOpaque(false);
		auth_desct.setEditable(false);
		auth_desct.setFont(fonttitle);
		
		auth_desc = new JTextArea(desc);
		auth_desc.setOpaque(false);
		auth_desc.setLineWrap(true);
		auth_desc.setWrapStyleWord(true);
		auth_desc.setEditable(false);
		auth_desc.setFont(fontsumm);

		panel2.add( auth_desc );
		panel2.add( auth_desct );
				
		auth_desct.setBounds(20,35,150,20);	
		auth_desc.setBounds(20,55,800,200);
	}
	
  
	public void coverpg()
	{
  
	authimage = new JLabel(null, li, JLabel.CENTER);
	
    JSplitPane splitPane1 = new JSplitPane(HORIZSPLIT, true, authimage, panel1);
    splitPane1.setOneTouchExpandable(true);
    splitPane1.setDividerSize(10);
    splitPane1.setDividerLocation(220);

    JSplitPane splitPane2 = new JSplitPane(VERTSPLIT, true, splitPane1, panel2);
    splitPane2.setOneTouchExpandable(true);
    splitPane2.setDividerLocation(250);
    splitPane2.setDividerSize(10);
    framet.add(splitPane2);
   
	}
  
  
  
	public static void main(String[] a) 
	{
		AuthDesc falana = new AuthDesc("Welcome",2);	 
	}
}