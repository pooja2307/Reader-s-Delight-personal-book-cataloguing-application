package p1;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;


public class newframe extends JFrame {

public static void main(String[] args) {
newframe frameTabel = new newframe("abc");
}

JLabel welcome = new JLabel("Welcome to a New Frame");
JPanel panel = new JPanel();

newframe(String s){

super("Welcome " + s);
setSize(800,500);
setLocation(500,280);
panel.setLayout (null); 

welcome.setBounds(370,50,150,60);

panel.add(welcome);

getContentPane().add(panel);
setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
setVisible(true);
}

}