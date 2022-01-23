/**
 * label0
 */
import java.awt.Color;
import java.awt.Font;
import javax.swing.JLabel;
import javax.swing.ImageIcon;
import javax.swing.BorderFactory;
import javax.swing.border.Border;

public class label0 extends JLabel {
	label0() {
		// JLabel = a GUI display area for a string of text, an image, or both
		ImageIcon image = new ImageIcon("prof.png");
		this.setIcon(image);
		Border border = BorderFactory.createLineBorder(Color.yellow,3);
		this.setText("Messenger is loading"); //set text of label
		this.setHorizontalTextPosition(JLabel.CENTER); //set text LEFT,CENTER, RIGHT of imageicon
		this.setVerticalTextPosition(JLabel.TOP); //set text TOP,CENTER, BOTTOM of imageicon
		this.setForeground(new Color(0x00FF00)); //set font color of text
		this.setFont(new Font("MV Boli",Font.PLAIN,10)); //set font of text
		this.setIconTextGap(100); //set gap of text to image
		this.setBackground(Color.green); //set background color
		this.setOpaque(true); //display background color
		this.setBorder(border); //sets border of label (not image+text)
		this.setVerticalAlignment(JLabel.CENTER); //set vertical position of icon+text within label
		this.setHorizontalAlignment(JLabel.CENTER); //set horizontal position of icon+text within label
		//this.setBounds(100, 100, 1000, 1000); //set x,y position within frame as well as dimensions
		//frame.setLayout(null);
		
	}

    
    
}
