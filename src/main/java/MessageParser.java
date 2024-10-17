import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.lang.String;

public class MessageParser {
    public static ArrayList<String> parseMessage(String message) {
        if (message != null && !message.isEmpty()) {
            char ch1 = message.charAt(0);

            if (ch1 == '*') {
                return resolveArrayOfStrings(message);
            }

        }
        return new ArrayList<String>();
    }

    private static ArrayList<String> resolveArrayOfStrings(String message) {
        ArrayList<String> res = new ArrayList<>();
        String inp = message.substring(1);
        Queue<Character> messageQueue = new LinkedList<Character>();
        for (int i = 0; i < inp.length(); i++) {
            messageQueue.add(inp.charAt(i));
        }
        int sizeOfArray = 0;
        while (!messageQueue.isEmpty()) {
            char peeked = messageQueue.peek();

            //handling for size
            StringBuilder num = new StringBuilder();
            if (Character.isDigit(peeked) && sizeOfArray == 0) {
                while (!messageQueue.isEmpty() && Character.isDigit(messageQueue.peek())) {
                    num.append(messageQueue.poll());
                }
                if (sizeOfArray == 0) {
                    sizeOfArray = Integer.valueOf(String.valueOf(num));
                }
            }

            //handling for escaped characters
            else if (!messageQueue.isEmpty() && peeked == '\r') {
                messageQueue.poll();
                messageQueue.poll();
            }

            //handling for int data types
            else if (!messageQueue.isEmpty() && peeked == ':') {
                StringBuilder integerValue = new StringBuilder();
                messageQueue.poll();
                while (!messageQueue.isEmpty() && Character.isDigit(messageQueue.peek())) {
                    integerValue.append(messageQueue.poll());
                }
                res.add(String.valueOf(integerValue));
            }

            else if(!messageQueue.isEmpty() && peeked == '$'){
                while(!messageQueue.isEmpty() && messageQueue.peek()!='\r'){
                    messageQueue.poll();
                }
            }
            else{
                StringBuilder stringVal = new StringBuilder();
                while (!messageQueue.isEmpty() && messageQueue.peek()!='\r') {
                    stringVal.append(messageQueue.poll());
                }
                res.add(String.valueOf(stringVal));
            }

        }
        return res;
    }
}
