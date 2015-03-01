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

public class MyEntry{


JFrame framet = new JFrame("Book Description");

  public int HORIZSPLIT = JSplitPane.HORIZONTAL_SPLIT;

  public int VERTSPLIT = JSplitPane.VERTICAL_SPLIT;
  
    JLabel label3 = new JLabel("c");
	JButton updentry;
	JTextArea bname,myremark,remarkt,author,bkmrktitle,bkmrktext,myrating,publication,rating,ratingtitle;
	JPanel panel1,panel2;
	ImageIcon li,stars;
	Float price;
	JLabel coverimage,lstar;
	Font fontbname = new Font("Comic Sans MS", Font.BOLD, 24);
	Font fontsumm = new Font("Arial", Font.PLAIN, 14);
	Font fonttitle = new Font("Times New Roman", Font.BOLD, 16);
	int rat;
	
	String addr,Book_Name,mydesc,auth,bkmark,pub;
    
  
  public MyEntry(final int uid,final int a){

  try{

	Class.forName("oracle.jdbc.driver.OracleDriver");

	Connection con=DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe","system","password00");


	PreparedStatement stmt=con.prepareStatement("Select * from user_books where u_id = ? and b_id = ?");
	stmt.setInt(1,uid);	// <<----- To be initialized with constructor value b_id here when needed... 
	stmt.setInt(2,a);
	ResultSet rs = stmt.executeQuery();
	while(rs.next()){
				
			
				rat = rs.getInt(3);
				mydesc = rs.getString(4);
				bkmark = rs.getString(7);
				}
				
	stmt = con.prepareStatement("Select * from books where b_id = ?");			
	stmt.setInt(1,a);							//<<--- and here....
	rs = stmt.executeQuery();
	while(rs.next()){
				addr = rs.getString(6);
				Book_Name = rs.getString(2);
				}
				
	stmt = con.prepareStatement("Select aname from author where a_id = (select a_id from written_by where b_id = ?)");
	stmt.setInt(1,a);							//<<----- aaaaand here.... :P
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
  
 createPanel1();
 createPanel2(uid,a);

 coverpg();
   
 framet.setSize(900,650);
 framet.setLocation(300,80); 
 framet.setVisible(true);
 }
  
  public void createPanel1()
	{
		panel1 = new JPanel();
		panel1.setLayout( null );
		
		lstar = new JLabel(null, stars, JLabel.CENTER);
		
		rating = new JTextArea("My Ratings: ");
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
		lstar.setBounds(130,130,107,18);
		publication.setBounds(25,100,470,30);
		rating.setBounds(25,130,150,30);
		
		panel1.add( lstar );
		panel1.add( rating );
		panel1.add( bname );
		panel1.add( author );
		panel1.add( publication );
		
	}
	
	
  public void createPanel2(final int uid,final int a)
	{
		panel2 = new JPanel();
		panel2.setLayout( null );
		
		updentry = new JButton("Update Entry In My Books");
				
		remarkt = new JTextArea("Remarks:");
		remarkt.setOpaque(false);
		remarkt.setEditable(false);
		remarkt.setFont(fonttitle);
		
		myremark = new JTextArea(mydesc);
		myremark.setOpaque(true);
		myremark.setLineWrap(true);
		myremark.setWrapStyleWord(true);
		myremark.setEditable(true);
		myremark.setFont(fontsumm);
		
		bkmrktitle = new JTextArea("Bookmark : ");
		bkmrktitle.setOpaque(false);
		bkmrktitle.setEditable(false);
		bkmrktitle.setFont(fonttitle);
		
		bkmrktext = new JTextArea(bkmark);
		bkmrktext.setOpaque(true);
		bkmrktext.setEditable(true);
		bkmrktext.setFont(fontsumm);
		
		myrating = new JTextArea(Integer.toString(rat));
		myrating.setOpaque(true);
		myrating.setEditable(true);
		myrating.setFont(fontsumm);
		
		ratingtitle = new JTextArea("Change Rating : ");
		ratingtitle.setOpaque(false);
		ratingtitle.setEditable(false);
		ratingtitle.setFont(fonttitle);

		panel2.add( myremark );
		panel2.add( remarkt );
		panel2.add( bkmrktitle );
		panel2.add( bkmrktext );
		panel2.add( ratingtitle );
		panel2.add( myrating );
		panel2.add( updentry );
		
		remarkt.setBounds(20,35,150,20);	
		myremark.setBounds(20,55,800,100);
		bkmrktitle.setBounds(20,195,80,30);
		bkmrktext.setBounds(140,197,680,40);
		updentry.setBounds(250,250,200,20);
		ratingtitle.setBounds(20,165,140,30);
		myrating.setBounds(150,167,75,20);
	
		updentry.addActionListener(new ActionListener() {
		public void actionPerformed(ActionEvent ae) {
			
			try{
			Class.forName("oracle.jdbc.driver.OracleDriver");

			Connection con=DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe","system","password00");
			String qry1 = "update user_books set u_rating=?,u_remarks=?,bookmark=? where u_id=? and b_id=?";
			
			PreparedStatement stmt1=con.prepareStatement(qry1);
			String temp1 = myremark.getText();
			String temp2 = bkmrktext.getText();
			int temp3 = Integer.parseInt(myrating.getText());
			stmt1.setInt(1,temp3);
			stmt1.setString(2,temp1);
			stmt1.setString(3,temp2);
			stmt1.setInt(4,uid);
			stmt1.setInt(5,a);
			stmt1.executeUpdate();
			
			con.commit();
			con.close();
			framet.dispose();
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
    
   MyEntry falana = new MyEntry(1,1);	 
   
  }
}