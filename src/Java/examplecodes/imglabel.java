package Java.examplecodes;



import javax.swing.JPanel;

import javax.swing.*;
import java.awt.*;

public class imglabel extends JPanel {

        private final ImageIcon ba = new ImageIcon("src\\Java\\Background.png");
        private final Image backgroundImage = ba.getImage();
        
        
        public void paintComponent(Graphics g) {
          super.paintComponent(g);
          // Draw the background image.
          g.drawImage(backgroundImage, 0, 0, this);
        }
      }

