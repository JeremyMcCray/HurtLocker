import org.apache.commons.io.IOUtils;
import sun.nio.cs.ext.SJIS;

import java.io.IOException;
import java.lang.reflect.Array;
import java.util.Arrays;

public class Main {

    static String rawdata;




    public String readRawDataToString() throws Exception{
        ClassLoader classLoader = getClass().getClassLoader();
        String result = IOUtils.toString(classLoader.getResourceAsStream("RawData.txt"));
        rawdata=result;
        return result;
    }

    public static void main(String[] args) throws Exception{
        JerkSONParser jp = new JerkSONParser();
        Main Main = new Main();
        Main.readRawDataToString();

        jp.splitText(rawdata);
        jp.splitobjectSets(rawdata);


        jp.createObject(jp.splitKVpairs);

        jp.countUpCart(jp.cart);

        System.out.println(jp.outputAnswer());
        System.out.println(jp.counts);
    }
}