public class RESPSerializer {
    public static String bulkStringSerializer(String message){
        String res= "";
        res="$"+(message.length())+"\r\n"+message+"\r\n";
        return res;
    }

    public static String nullBulkStringSerializer(){
        return "$-1\r\n";
    }

    public static String okSimpleResponseSerializer(){
        return "+OK\r\n";
    }
}
