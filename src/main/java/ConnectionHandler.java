import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.Socket;

public class ConnectionHandler extends Thread{

    private final Socket serverSocket;

    public ConnectionHandler(Socket serverSocket){
        this.serverSocket=serverSocket;
    }
    public void run(){

        try {
            BufferedReader bf=new BufferedReader(new InputStreamReader(serverSocket.getInputStream()));
            while (serverSocket.isConnected()){
                String input;
                if((input = bf.readLine())!=null){
                    if(input.equalsIgnoreCase("PING")){
                        serverSocket.getOutputStream().write("+PONG\r\n".getBytes());
                    }
                }
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
