import gnu.io.NRSerialPort;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;


public class RS232 {
    private final NRSerialPort serial;

    public RS232(String port, int baudRate) {
        serial = new NRSerialPort(port, baudRate);
    }

    public void open() {
        serial.connect();
    }

    public void close() {
        serial.disconnect();
    }

    public void write(String data) {
        DataOutputStream outs = new DataOutputStream(serial.getOutputStream());
        try {
            for (int i = 0; i < data.length(); i++) {
                char c = data.charAt(i);
                outs.write(c);
            }
            Thread.sleep(5);
        } catch (IOException | InterruptedException e) {
            e.printStackTrace();
        }
    }

    public String read() {
        DataInputStream ins = new DataInputStream(serial.getInputStream());
        String str = "";
        try {
            while (!Thread.interrupted()) {// read all bytes
                if (ins.available() > 0) {
                    char b = (char) ins.read();
                    System.out.print(b);
                    Thread.sleep(5);
                }
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return str;
    }


    public String writeAndRead(String dataToWrite){

        String responseStr = "";
        this.write(dataToWrite);
     //   System.out.println(addCharEnd("12345",'a'));
        try {
            Thread.sleep(50);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        try(DataInputStream ins = new DataInputStream(serial.getInputStream())) {
            while(ins.available()>0) {
                char b = (char) ins.read();
                responseStr=addCharEnd(responseStr,b);
                Thread.sleep(5);
            }
        } catch (IOException | InterruptedException e) {
            e.printStackTrace();
        }
        return responseStr;
    }

  /*  public String addChar(String str, char ch, int position) {
        int len = str.length();
        char[] updatedArr = new char[len + 1];
        str.getChars(0, position, updatedArr, 0);
        updatedArr[position] = ch;
        str.getChars(position, len, updatedArr, position + 1);
        return new String(updatedArr);
    }*/

    public String addCharEnd(String str, char ch) {
        int len = str.length();
        char[] updatedArr = new char[len + 1];
        str.getChars(0,len, updatedArr, 0);
        updatedArr[updatedArr.length-1] = ch;
       return new String(updatedArr);
    }

   /* public String addChar(String str, char ch, int position) {
        return str.substring(0, position) + ch + str.substring(position);
    }*/
/*
    public String addCharOnEnd(String str, char ch) {
        return str.substring(0, str.length()) + ch ;
    }*/


   /* public String addChar(String str, char ch, int position) {
        StringBuilder sb = new StringBuilder(str);
        sb.insert(position, ch);
        return sb.toString();
    }*/


}
