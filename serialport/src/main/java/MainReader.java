import jssc.SerialPort;
import jssc.SerialPortEvent;
import jssc.SerialPortEventListener;
import jssc.SerialPortException;

import java.util.Arrays;

public class MainReader {

    static SerialPort serialPort = new SerialPort("/dev/ttyUSB0");
    static byte[] pac = new byte[64];
    static byte[] buf = new byte[64];
    static int buf_size = 0;
    static int buf_position = 0;
    static boolean packageJustReaded = true;
    static int pac_length = 0;
    static int pac_readed_bytes = 0;

    public static void main(String[] args) {

        try {
            //Открываем порт
            serialPort.openPort();
            //Выставляем параметры
            serialPort.setParams(SerialPort.BAUDRATE_115200,
                    SerialPort.DATABITS_8,
                    SerialPort.STOPBITS_1,
                    SerialPort.PARITY_NONE);
            //Включаем аппаратное управление потоком
            serialPort.setFlowControlMode(SerialPort.FLOWCONTROL_RTSCTS_IN |
                    SerialPort.FLOWCONTROL_RTSCTS_OUT);

            //Устанавливаем ивент листенер и маску
            serialPort.addEventListener(new PortReader(), SerialPort.MASK_RXCHAR);
            //Отправляем запрос устройству
            //serialPort.writeString("Get data");
        } catch (SerialPortException e) {
            throw new RuntimeException(e);
        }
    }

    private static class PortReader implements SerialPortEventListener {

        public void serialEvent(SerialPortEvent event) {
            if(event.isRXCHAR() && event.getEventValue() > 0){
                try {
                    //Получаем ответ от устройства, обрабатываем данные и т.д.
                    byte[] data = serialPort.readBytes();
                    if (data != null) {
                        if (!packageJustReaded) { // пакет еще не сформирован полностью, но часть данных уже получена
                            if (pac_readed_bytes == 1) { // прочитали 1 байт не знаем длину
                                pac_length = data[0];

                                if (pac_length > 62) {
                                }
                            }

                            if (data.length >= pac_length + 2 - pac_readed_bytes) { // данных в буфере порта хватает, чтобы сформировать пакет
                                System.arraycopy(data, 0, pac, pac_readed_bytes, pac_length + 2);
                                if (data.length > pac_length + 2 - pac_readed_bytes) { // данных в буфере больше, чем надо для формирования пакета, этим буфером воспользуемся при начале сборки нового пакета
                                    System.arraycopy(data, pac_length + 2 - pac_readed_bytes, buf, 0, data.length - (pac_length + 2 - pac_readed_bytes));
                                    buf_size =data.length - (pac_length + 2 - pac_readed_bytes);
                                    buf_position = 0;
                                }
                                packageJustReaded = true;
                            } else {
                                System.arraycopy(data, 0, pac, 1, pac_length + 1);
                                pac_readed_bytes = pac_readed_bytes + data.length;
                            }
                        }
                        for (byte b : data) {
                            if ((b == 0xc8 || b == 0xee || b == 0xea || b == 0xec) && packageJustReaded) {
                                pac_length = 0;

                            }
                            System.out.printf("0x%02X ", 0xFF & b);
                        }
                    }

                }
                catch (SerialPortException ex) {
                    System.out.println(ex);
                }
            }
        }
    }
}
