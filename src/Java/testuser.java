package Java;

public class testuser {
    public String username;
    private String pw;
    private String a;
    private messInterface main;
    private encry secure;
    private chat[] chats;
    private int NumberOfChats;

    testuser(String[] UP){
    this.NumberOfChats = 0;
    this.username = UP[0];
    this.pw = UP[1];
    this.a = encry.a();
    this.main = new messInterface();
    this.secure = new encry();
    this.chats = new chat[100];



    }

public void newchat(chat newchat){
    chats[NumberOfChats] = newchat;
    this.NumberOfChats ++;
}
    
}
