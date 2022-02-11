package Japy.src.Ctest.examplecodes;
//package Ctest.examplecodes;
//package Java.examplecodes;
import java.awt.Color;
import java.awt.Font;
import javax.swing.*;
import javax.swing.border.Border;

public class label0 extends JLabel {

	label0(String lol) {
		// JLabel = a GUI display area for a string of text, an image, or both as dimension
		ImageIcon image = new ImageIcon("src\\Java\\prof.png");
		Border border = BorderFactory.createLineBorder(Color.green, 3);
		this.setText(lol);
		this.setIcon(image);
		this.setHorizontalTextPosition(JLabel.CENTER); // set text LEFT,CENTER, RIGHT of imageicon
		this.setVerticalTextPosition(JLabel.TOP); // set text TOP,CENTER, BOTTOM of imageicon
		this.setForeground(new Color(0x00FF00)); // set font color of text
		this.setFont(new Font("MV Boli", Font.PLAIN, 10));
		this.setIconTextGap(100); // set gap of text to image
		this.setBackground(Color.black);
		this.setOpaque(true); // display background color
		this.setBorder(border);
		this.setVerticalAlignment(JLabel.CENTER); // set vertical position of icon+text within label
		this.setHorizontalAlignment(JLabel.CENTER); // set horizontal position of icon+text within label
		// this.setBounds(100, 100, 1000, 1000); //set x,y position within frame as well
		// frame.setLayout(null);
	}
}