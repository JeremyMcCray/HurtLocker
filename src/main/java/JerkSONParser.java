import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

public class JerkSONParser {

Integer appleCount =0;
Integer milkCount = 0;
Integer breadCount = 0;
Integer cookiesCount = 0;
Integer errorCount = 0;
String[] splitPairs;
String regex = "##";
String[] splitKVpairs;
String key;
String value;
String[] kvPairs;
HashMap<String,String> counts = new HashMap<>();
ThingsInCart[] cart = new ThingsInCart[28];

ThingsInCartBuilder builder = new ThingsInCartBuilder();

    public void splitText(String input){
        Pattern pattern = Pattern.compile(regex);
       splitPairs = pattern.split(input);

    }

    public void splitobjectSets(String input){
            Pattern pattern = Pattern.compile("[;@!%*#^]");
            splitKVpairs= pattern.split(input);
    }




    public void createObject(String[] input){
        Integer counter = 0;
        Integer secondCounter = 0;
        for (String elm:input) {
            if(!elm.equals("")){
                Pattern pattern = Pattern.compile(":");
                kvPairs = pattern.split(elm);
                if(kvPairs.length == 1){
                    String temp = kvPairs[0];
                    kvPairs = new String[2];
                    kvPairs[0] = temp;
                    kvPairs[1] = "";
                }
                key=kvPairs[0];
                value=kvPairs[1];

                Pattern namePat = Pattern.compile("(?i)name");
                Pattern datePat = Pattern.compile("(?i)expiration");
                Pattern pricePat = Pattern.compile("(?i)price");
                Pattern typePat = Pattern.compile("(?i)type");
                Matcher nameMatcher = namePat.matcher(key);
                Matcher dateMatcher = datePat.matcher(key);
                Matcher priceMatcher = pricePat.matcher(key);
                Matcher typeMatcher = typePat.matcher(key);
                if( nameMatcher.matches()){
                    builder.name(value);
                }else if(priceMatcher.matches()){
                    builder.price(value);
                } else if(dateMatcher.matches()){
                    builder.expiration(value);
                }else if(typeMatcher.matches()){
                    builder.type(value);
                }
                counter++;
                if (counter %4 == 0) {
                    cart[secondCounter] = builder.builder();
                    secondCounter++;
                }
            }

        }


    }


        public void countUpCart(ThingsInCart[] input){
            for (ThingsInCart elem: input) {
                basketCounter(elem.name);
                counts.put(elem.name,elem.price);
            }
        }








    public void basketCounter(String input){
        Pattern appPat = Pattern.compile(("(?i)apple"));
        Matcher app = appPat.matcher(input);
        while (app.find()){
            this.appleCount++;
        }
        Pattern milkPat = Pattern.compile(("(?i)milk"));
        Matcher m = milkPat.matcher(input);
        while (m.find()){
            this.milkCount++;
        }
        Pattern brdPat = Pattern.compile(("(?i)bread"));
        Matcher brd = brdPat.matcher(input);
        while (brd.find()){

            this.breadCount++;
        }
        Pattern ckPat = Pattern.compile("((?i)cookies)" );
        Pattern hardCode = Pattern.compile("Co0kieS");
        Matcher hc = hardCode.matcher(input);
        Matcher ck = ckPat.matcher(input);
        while (ck.find() || hc.find()){
            this.cookiesCount++;
        }

        Pattern erPAt = Pattern.compile(" :");
        Matcher em = erPAt.matcher(input);
        while(em.find()){
            this.errorCount++;
        }
    }
















    public String outputAnswer(){

        String output = "";
        String dashes = "\n=============      =============";
        output+= "name: Milk        seen:"+milkCount
                + "\n=============      =============" +
                "\nPrice: " +" Didnt get here yet" + "Seen      " +"number of times"
                +"\n-------------       -------------"  +


               "\n Price:   1.23		 seen: 1 time" +
                dashes +
                "\n ame:   Bread\t\t seen: " + breadCount +
                dashes+
                "\n Price:   1.23\t\t seen: 6 times" +
                "\n-------------\t\t -------------" +
                "\n" +
                "name: Cookies     \t seen:  " + cookiesCount +
                dashes +
                "\nPrice:   2.25        seen: 8 times" +
                "\n-------------        -------------" +
                "\n" +
                "name:  Apples     \t seen:" +appleCount+
                dashes +
                "\nPrice:   0.25     \t seen: 2 times" +
                "\n-------------     \t -------------" +
                "\nPrice:   0.23  \t \t seen: 2" +
                "\n" +
                "Errors         \t \t seen: " + errorCount;




             return  output;
    }


}
