import java.io.IOException;
import java.util.concurrent.TimeoutException;

public class Main {
    public static void main(String[] args) {

        RS232 rs232 = new RS232("COM3", 9600);
        rs232.open();
        String str="";
        char terminator=Character.LINE_SEPARATOR;
               // "\n".charAt(0); //"\n".charAt(0)'';
        try {
            str = rs232.writeAndRead("OLAAAAAAAAAAAAAAAA",terminator,5000);
        } catch (IOException | TimeoutException e) {
            e.printStackTrace();
        }
        rs232.close();
        System.out.println(str);

    }


}
