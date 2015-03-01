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

public class BookDesc
{
	int a=0;
	int uid=0;

	JFrame framet = new JFrame("Book Description");

	public int HORIZSPLIT = JSplitPane.HORIZONTAL_SPLIT;

	public int VERTSPLIT = JSplitPane.VERTICAL_SPLIT;
  
    JLabel label3 = new JLabel("c");
	JButton Add,Myentry;
	JTextArea bname,summary,summaryt,author,pricet,pricep,dt_relt,reldt,publication,rating;
	JPanel panel1,panel2;
	ImageIcon li,stars;
	Float price;
	JLabel coverimage,lstar;
	Font fontbname = new Font("Comic Sans MS", Font.BOLD, 24);
	Font fontsumm = new Font("Arial", Font.PLAIN, 14);
	Font fonttitle = new Font("Times New Roman", Font.BOLD, 16);
	JTextField jt = new JTextField();
	Boolean present = false;
	String addr,Book_Name,synopsis,auth,dt_rel,pub;
    
  
	public BookDesc(String uname,int a)
	{
	framet.setTitle("Welcome : " + uname);
	try
	{
		Class.forName("oracle.jdbc.driver.OracleDriver");
		Connection con=DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe","system","password00");
		System.out.println(uname);
		PreparedStatement stmtid=con.prepareStatement("Select * from login_user where username = ? ");
	stmtid.setString(1,uname);							
	ResultSet rsid = stmtid.executeQuery();
	while(rsid.next())
	{
		uid = rsid.getInt("U_ID");
	}
	
	
	
	PreparedStatement stmt=con.prepareStatement("Select * from books where b_id = ? ");
	stmt.setInt(1,a);							// <<----- To be initialized with constructor value b_id here when needed... 
	int rat=0;
	ResultSet rs = stmt.executeQuery();
	while(rs.next()){
				addr = rs.getString(6);
				Book_Name = rs.getString(2);
				synopsis = rs.getString(3);
				price = rs.getFloat(4);
				dt_rel = rs.getString(5);
				rat = rs.getInt(7);
				}
				
	stmt = con.prepareStatement("Select aname from author where a_id = (select a_id from written_by where b_id = ?)");			
	stmt.setInt(1,a);							//<<--- and here....
	rs = stmt.executeQuery();
	while(rs.next()){
				auth = rs.getString(1);
				}
				
	stmt = con.prepareStatement("Select pname from publication where p_id = (select p_id from books where b_id = ?)");
	stmt.setInt(1,a);							//<<----- aaaaand here.... :P
	rs = stmt.executeQuery();
	while(rs.next()){
				pub = rs.getString(1);
				}				
				
//	int rat = 5;
	li = new ImageIcon(addr);
	String temp1;
		if(rat == 1)
		temp1 = ("E://Reader's Delight/1star.png");
		else if(rat == 2)
		temp1 = ("E://Reader's Delight/2star.png");
		else if(rat == 3)
		temp1 = ("E://Reader's Delight/3star.png");
		else if(rat == 4)
		temp1 = ("E://Reader's Delight/4star.png");
		else
		temp1 = ("E://Reader's Delight/5star.png");
	stars = new ImageIcon(temp1);

}catch(Exception e){System.out.println(e);}
  
 createPanel1(uid,a);
 createPanel2(uid,a);

 coverpg();

  
// framet.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
 framet.setSize(900,650);
 framet.setLocation(250,60); 
 framet.setResizable(false);
 framet.setVisible(true);
 
 framet.add(jt);
 jt.setOpaque(false);
 jt.addKeyListener(new KeyAdapter()
 {
  public void keyPressed(KeyEvent e) {
		 System.out.println("Chirag");
         int c = e.getKeyCode();
		 if(c == (KeyEvent.VK_ESCAPE))
		 
		 framet.dispose();
  }
 });  
 jt.requestFocusInWindow();
    }  
 
 
 
  public void createPanel1(final int uid,final int a )
	{
		
		panel1 = new JPanel();
		panel1.setLayout( null );
		
		lstar = new JLabel(null, stars, JLabel.CENTER);
		
		Add = new JButton("Add To My Books");
		Myentry = new JButton("View My Entry For The Book");
		
		rating = new JTextArea("Public Ratings: ");
		rating.setOpaque(false);
		rating.setEditable(false);
		rating.setFont(fonttitle);
		
		author = new JTextArea("Author: " +auth);
		author.setOpaque(false);
		author.setEditable(false);
		author.setFont(fonttitle);
						
		publication = new JTextArea("Publication House:  " +pub);
		publication.setOpaque(false);
		publication.setEditable(false);
		publication.setFont(fonttitle);
		
		bname = new JTextArea(Book_Name);
		bname.setOpaque(false);
		bname.setEditable(false);
		bname.setFont(fontbname);
		
		author.setBounds(25,70,470,30);
		bname.setBounds(25,10,530,50);
		Add.setBounds(25,200,150,30);
		Myentry.setBounds(200,200,250,30);
		lstar.setBounds(130,130,107,18);
		publication.setBounds(25,100,470,30);
		rating.setBounds(25,130,150,30);
		
		panel1.add( lstar );
		panel1.add( rating );
		panel1.add( bname );
		panel1.add( author );
		panel1.add( publication );
		
		try
	{
		Class.forName("oracle.jdbc.driver.OracleDriver");
		Connection con=DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe","system","password00");
		
		PreparedStatement stmtid=con.prepareStatement("Select * from user_books where u_id = ? ");
	stmtid.setInt(1,uid);							
	ResultSet rsid = stmtid.executeQuery();
	while(rsid.next())
	{
		if((rsid.getInt("B_ID")) == a)
		{present = true;
		break;}
		else 
		present = false;
	}
	}catch(Exception e){}
		
		if(present == true)
		{
		panel1.add( Myentry );
		}
		else{
		panel1.add( Add );}
		
		Myentry.addActionListener(new ActionListener() {
		public void actionPerformed(ActionEvent ae) {
		
		MyEntry me1 = new MyEntry(uid,a);
		framet.dispose();
			}
			});
		
	}
	
	
  public void createPanel2(final int uid,final int a)
	{
		panel2 = new JPanel();
		panel2.setLayout( null );
		
//		BCritic = new JButton("View Critic Ratings");
		
		reldt = new JTextArea(dt_rel);
		reldt.setOpaque(false);
		reldt.setEditable(false);
		reldt.setFont(fontsumm);
		
		pricet = new JTextArea("Price : ");
		pricet.setOpaque(false);
		pricet.setEditable(false);
		pricet.setFont(fonttitle);
		
		dt_relt = new JTextArea("Date of Release : ");
		dt_relt.setOpaque(false);
		dt_relt.setEditable(false);
		dt_relt.setFont(fonttitle);
		
		pricep = new JTextArea("Rs. " + price);
		pricep.setOpaque(false);
		pricep.setEditable(false);
		pricep.setFont(fontsumm);
		
		summaryt = new JTextArea("Summary :");
		summaryt.setOpaque(false);
		summaryt.setEditable(false);
		summaryt.setFont(fonttitle);
		
		summary = new JTextArea(synopsis);
		summary.setOpaque(false);
		summary.setLineWrap(true);
		summary.setWrapStyleWord(true);
		summary.setEditable(false);
		summary.setFont(fontsumm);

		panel2.add( summary );
		panel2.add( summaryt );
		panel2.add( pricet );
		panel2.add( pricep );
		panel2.add( dt_relt );
		panel2.add( reldt );
//		panel2.add( BCritic );
				
		summaryt.setBounds(20,35,150,20);	
		summary.setBounds(20,55,800,200);
		dt_relt.setBounds(20,275,140,30);
		reldt.setBounds(150,277,75,30);
		pricet.setBounds(20,305,80,30);
		pricep.setBounds(70,307,80,30);
//		BCritic.setBounds(20,240,145,20);
	
	
		Add.addActionListener(new ActionListener() {
		public void actionPerformed(ActionEvent ae) {
			
			try{
			Class.forName("oracle.jdbc.driver.OracleDriver");

			Connection con=DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe","system","password00");
		/*	int n = JOptionPane.showConfirmDialog(null, "Do you want this Book To Be Added To Your Profile ?", "Confirmation", JOptionPane.OK_CANCEL_OPTION);
			if(n == JOptionPane.OK_OPTION)
			{
			System.out.println("Yes!");
			}
			else
			{
			System.out.println("CANCEL!!");
			}
		*/
		
		String[] options = { "Read", "Currently Reading", "Wishlist"};
  		String sel = (String) JOptionPane.showInputDialog(null, 
        "What is Your status for this Book..!?",
        "Confirm The Addtion",
        JOptionPane.QUESTION_MESSAGE, 
        null, 
        options, 
        null);
		String stat = "";
		if(sel.equals("Read"))
		{System.out.println("R");
		stat = "Read";}
		else if(sel.equals("Wishlist"))
		{System.out.println("W");
		stat = "Wishlist";}
		else if(sel.equals("Currently Reading"))
		{System.out.println("CR");
		stat = "Currently Reading";}
		else
		System.out.println("Hell Yeah!");
		System.out.println(uid + a);
		String qry = "insert into user_books values(?,?,3,null,sysdate,?,null)";
		PreparedStatement stmt=con.prepareStatement(qry);
		stmt.setInt(1,uid);
		stmt.setInt(2,a);
		stmt.setString(3,stat);
		
		stmt.executeUpdate();
		
con.commit();
con.close();
}
catch(Exception e){ System.out.println(e);}
}
});
} 
  
  
  public void coverpg()
  {
  
	coverimage = new JLabel(null, li, JLabel.CENTER);
	
    JSplitPane splitPane1 = new JSplitPane(HORIZSPLIT, true, coverimage, panel1);
    splitPane1.setOneTouchExpandable(true);
    splitPane1.setDividerSize(10);
    splitPane1.setDividerLocation(220);

    JSplitPane splitPane2 = new JSplitPane(VERTSPLIT, true, splitPane1, panel2);
    splitPane2.setOneTouchExpandable(true);
    splitPane2.setDividerLocation(250);
    splitPane2.setDividerSize(10);
    framet.add(splitPane2);
   
  }
  
  
  
  public static void main(String[] a) {
    
   BookDesc falana = new BookDesc("Welcome",0);	 
   
  }
}