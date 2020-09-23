
import gnu.io.NRSerialPort;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {

        RS232 rs232 = new RS232("COM3",9600);
        rs232.open();
        rs232.write("Ala");
        rs232.close();

    }

}
