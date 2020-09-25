public class Main {
    public static void main(String[] args) {

        RS232 rs232 = new RS232("COM3", 9600);
        rs232.open();
        String str = rs232.writeAndRead("OLAAAAAAAAAAAAAAAA");
        rs232.close();
        System.out.println(str);

    }


}
