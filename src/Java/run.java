package Java;
//package Japy.src.Java;

public class run {
  public static void main(String[] args) {
    sendrecv.socket();
    login Login =new login(); 
    while (true){
    if (Login.recv.equals("0") |Login.recv.equals("1")  ) {
      new messInterface(0); 
      System.out.println("er");
    }}
    
    
    //new messInterface(0);
    /* int log = login.createGUI();
    if (log == 4) {
      return;
    }
     messInterface.createGUI(0); 
    */
  }
}
