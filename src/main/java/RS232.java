import gnu.io.NRSerialPort;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;


public class RS232 {
    private String port;
    private int baudRate;
    NRSerialPort serial;

    public RS232(String port, int baudRate) {
        this.port = port;
        this.baudRate = baudRate;
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
        } catch (IOException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
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
                }
                Thread.sleep(5);
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return str;
    }
}
