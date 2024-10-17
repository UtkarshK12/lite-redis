import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.Socket;
import java.util.HashMap;
import java.util.List;
import java.util.ArrayList;
import java.util.Map;

public class ConnectionHandler extends Thread{

    private final Socket serverSocket;

    public ConnectionHandler(Socket serverSocket){
        this.serverSocket=serverSocket;
    }
    public void run(){

        try {
            BufferedReader bf=new BufferedReader(new InputStreamReader(serverSocket.getInputStream()));
            RequestHandlerService requestHandlerService = new RequestHandlerService();
            requestHandlerService.handleRequest(serverSocket,bf);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
