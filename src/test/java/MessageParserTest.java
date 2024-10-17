import org.junit.Assert;
import org.junit.Test;

import java.util.ArrayList;

public class MessageParserTest {
    @Test
    public void stringParserTest(){
        String test="*2\r\n$4\r\nECHO\r\n$3\r\nhey\r\n";
        ArrayList<String> res=MessageParser.parseMessage(test);
        Assert.assertEquals(res.get(0),"ECHO");
        Assert.assertEquals(res.get(1),"hey");
    }

    @Test
    public void stringParserTest1(){
        String test="*2\r\n$4\r\nECHO\r\n$6\r\norange\r\n";
        ArrayList<String> res=MessageParser.parseMessage(test);
        Assert.assertEquals(res.get(0),"ECHO");
        Assert.assertEquals(res.get(1),"orange");
        Assert.assertEquals(res.size(),2);
    }

    @Test
    public void intListParserTest(){
        String test="*3\r\n:1\r\n:2\r\n:3\r\n";
        ArrayList<String> res=MessageParser.parseMessage(test);
        Assert.assertEquals(res.get(0),"1");
        Assert.assertEquals(res.get(1),"2");
        Assert.assertEquals(res.get(2),"3");
    }
}
