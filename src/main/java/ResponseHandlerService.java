import java.io.IOException;
import java.net.Socket;
import java.util.List;
import java.util.Map;

public class ResponseHandlerService {

    public static void pingResponse(Socket serverSocket) throws IOException {
        serverSocket.getOutputStream().write(RESPSerializer.bulkStringSerializer("PONG").getBytes());
    }
     public static void echoResponse(List<String> req, Socket serverSocket) throws IOException {
         serverSocket.getOutputStream().write(RESPSerializer.bulkStringSerializer(req.get(1)).getBytes());
     }
     public static void setResponse(List<String> req, Socket serverSocket) throws IOException {
         serverSocket.getOutputStream().write(RESPSerializer.okSimpleResponseSerializer().getBytes());
     }
     public static void getResponse(Map<String,String> keyValueStore,List<String> req, Socket serverSocket) throws IOException {
         serverSocket.getOutputStream().write(RESPSerializer.bulkStringSerializer(keyValueStore.get(req.get(1))).getBytes());

     }
     public static void nullStringResponse(Socket serverSocket) throws IOException {
         serverSocket.getOutputStream().write(RESPSerializer.nullBulkStringSerializer().getBytes());
     }
}
