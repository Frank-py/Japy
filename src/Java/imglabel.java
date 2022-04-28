package Java;

import javax.swing.JPanel;

import javax.swing.*;
import java.awt.*;

public class imglabel extends JPanel {

        private ImageIcon ba = new ImageIcon("src\\Java\\Background.png");
        private Image backgroundImage = ba.getImage();
        
        
        public void paintComponent(Graphics g) {
          super.paintComponent(g);
          // Draw the background image.
          g.drawImage(backgroundImage, 0, 0, this);
        }
      }

