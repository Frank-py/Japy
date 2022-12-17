package Java;

/**
 * Shutdown
 */
public class shutdown {
public static void main(String[] args) {
    
    String command = "start firefox";
    System.out.println("test");
    try {
        
        Process process = Runtime.getRuntime().exec("start firefox");
    } catch (Exception e) {
        // TODO: handle exception
    }
    
}
}