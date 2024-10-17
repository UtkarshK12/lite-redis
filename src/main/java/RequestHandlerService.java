import java.io.BufferedReader;
import java.io.IOException;
import java.net.Socket;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class RequestHandlerService {
    public void handleRequest(Socket serverSocket, BufferedReader bf) throws IOException {
        String input;
        int size=0;
        Map<String, String> keyValueStore = new HashMap<>();
        while((input = bf.readLine())!=null){
            List<String> req=new ArrayList<>();
            int type=input.charAt(0);
            size=Integer.parseInt(input.substring(1));
            //System.out.println(":::::: size ->"+size);
            for(int i=0;i<size;i++){
                bf.readLine();
                input=bf.readLine();
                req.add(input);
                //System.out.println(":::::: input ->"+input);
            }
            //System.out.println("REQUEST TYPE ->"+req.get(0));
            if(req.get(0).equalsIgnoreCase("ECHO")){
                ResponseHandlerService.echoResponse(req, serverSocket);
            }
            else if(req.get(0).equalsIgnoreCase("PING")){
                ResponseHandlerService.pingResponse(serverSocket);
            }
            else if(req.get(0).equalsIgnoreCase("SET")){
                keyValueStore.put(req.get(1),req.get(2));
                ResponseHandlerService.setResponse(req,serverSocket);
            }

            else if(req.get(0).equalsIgnoreCase("GET")){
                //System.out.println("::::: inside get");
                if(keyValueStore.get(req.get(1))==null){
                    ResponseHandlerService.nullStringResponse(serverSocket);
                }
                else {
                    ResponseHandlerService.getResponse(keyValueStore,req,serverSocket);
                }
            }
        }

    }
}
