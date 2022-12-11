package Java.examplecodes;

    import java.awt.FlowLayout;  

import javax.swing.*;

      
    public class ninetytwo {  
        
      
        private static void createAndShowGUI() {  
      
            // Create and set up the window.  
            final JFrame frame = new JFrame("Scroll Pane Example");  
      
            // Display the window.  
            frame.setSize(500, 500);  
            frame.setVisible(true);  
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);  
      
            // set flow layout for the frame  
            frame.getContentPane().setLayout(new FlowLayout());  
            JPanel panel = new JPanel();
            panel.setSize(200,200);
            JTextArea textArea = new JTextArea(20,20);  
            
            textArea.setEnabled(false);
            textArea.setText("nugsgsdgegll\n\n\n\n\ngsddgsdgsgd");

            JScrollPane scrollableTextArea = new JScrollPane(textArea);
      
            scrollableTextArea.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);  
            scrollableTextArea.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);  
      
            frame.getContentPane().add(scrollableTextArea);  
        }  
        public static void main(String[] args) {  
      
      
            javax.swing.SwingUtilities.invokeLater(new Runnable() {  
      
                public void run() {  
                    createAndShowGUI();  
                }  
            });  
        }  
    }  
