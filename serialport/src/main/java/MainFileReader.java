import jssc.SerialPort;
import jssc.SerialPortEvent;
import jssc.SerialPortEventListener;
import jssc.SerialPortException;

/**
 * [sync] [len] [type] [payload] [crc8] len = 1 (type) + length(payload) + 1 (crc8)
 * максимальная длина всего пакета 64 байта
 *
 * при чтении встретили 0xC8 - маркер начала пакета
 * если не закончили чтение текущего пакета (известна длина, и она не достигнута) - игнорируем, продолжаем чтение;
 * если закончили читать пакет, то начинаем чтение нового (следующий байт - длина), проверяем, что значение <= 64, иначе
 * ждем следующей метки начала пакета
 *
 */
public class MainFileReader {

    static SerialPort serialPort = new SerialPort("/dev/ttyUSB0");

    public static void main(String[] args) {
        //new PortReader().workPackage(new byte[] {(byte)0xC8, 118,12,-3,44});


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

        static byte[] pac = new byte[64];
        static int buf_position = 0;
        static boolean packageJustReaded = true; // индикатор завершенности пакета
        static byte pac_length = 0;
        static int pac_readed_bytes = 0;
        static int[] channels = new int[16];

        public void serialEvent(SerialPortEvent event) {
            if(event.isRXCHAR() && event.getEventValue() > 0) {
                try {
                    //Получаем ответ от устройства, обрабатываем данные и т.д.
                    byte[] buf = serialPort.readBytes();


                    System.out.println("buf:"+buf.length);

                    for (byte b : buf) {
                        int i = b & 0xFF;
                        System.out.printf("0x%02X, ", i);
                    }
                    System.out.println();


                    buf_position = 0;
                    if (buf != null) {
                        int i = 0;
                        while (buf_position < buf.length) {
                            //System.out.println(++i);
                            // пакет еще не сформирован полностью, но часть данных уже получена
                            if (!packageJustReaded && pac_length > 0) {
                                //System.out.println("B");
                                // данных в буфере порта хватает, чтобы сформировать пакет
                                //
                                if (buf.length - buf_position >= pac_length + 2 - pac_readed_bytes) {
                                    System.arraycopy(buf, buf_position, pac, pac_readed_bytes, pac_length + 2 - pac_readed_bytes);

                                    workPackage(pac);

                                    pac_length = 0;
                                    packageJustReaded = true;
                                    pac_readed_bytes = 0;
                                    buf_position += pac_length + 2 - pac_readed_bytes;
                                    continue;
                                    //переходим к анализу последующих байт
                                } else {
                                    System.arraycopy(buf, buf_position, pac, pac_readed_bytes, buf.length - buf_position);
                                    pac_readed_bytes = pac_readed_bytes + buf.length - buf_position;
                                    //больше ничего нет, уходим из цикла ожидать следующую порцию данных
                                    break;
                                }
                            } else if (!packageJustReaded && pac_readed_bytes == 1) {// прочитали 1 байт не знаем длину
                                pac_length = buf[buf_position];
                                //System.out.println("A buf[" + buf_position  +"]=" + pac_length);

                                if (pac_length <= 62) {
                                    pac[1] = pac_length;
                                    pac_readed_bytes = 2;
                                } else { // Это не длина, будем ждать следующей метки
                                    packageJustReaded = true;
                                }
                                buf_position++;
                                continue;
                            }

                            // ищем метку начала пакета
                            for ( ;buf_position < buf.length; buf_position++) {
                                int b = buf[buf_position] & 0xFF;
                                if ((b == 0xc8 || b == 0xee || b == 0xea || b == 0xec) && packageJustReaded) {
                                    //System.out.println("start pac " + buf_position);
                                    pac[0] = buf[buf_position];
                                    pac_readed_bytes = 1;
                                    packageJustReaded = false;
                                    buf_position++; //поскольку выходим из цикла принудительно
                                    break;
                                }
                            }

                        }
                        //System.out.println("buf readed");
                    }

                } catch (SerialPortException e) {
                    e.printStackTrace();
                } catch (Exception ex) {
                    System.out.println(ex);
                }
            }
        }

        public void workPackage(byte[] pac) {

/*
            if (pac[2] == 0x16) {
                unpackChannels(pac, channels);
                for (int c : channels) {
                    System.out.printf("%d, ", c);
                }
                System.out.println();
            } else if (pac[2] == 0x14) {
                System.out.println("0x14");
            }
*/
            System.out.println("pac:");
            for (byte b : pac) {
                int i = b & 0xFF;
                System.out.printf("0x%02X, ", i);
            }
            System.out.println();
        }

        /*
         * Выделение сигналов каналов (по 11 бит на каждый) из массива, полученного через UART от приемника
         */
        void unpackChannels (byte[] pac, int[] channels) {
            int inputChannelMask = 0x7FF;
            channels[0] = (((pac[3] & 0xFF ) << 8) & inputChannelMask) | (pac[2] & 0xFF);
            channels[1] = (((pac[4] & 0xFF ) << 5) & inputChannelMask) | ((pac[3] & 0xFF) >> 3);
            channels[2] = (((pac[6] & 0xFF) << 10) & inputChannelMask) | ((pac[5] & 0xFF) << 2) | ((pac[4] & 0xFF) >> 6);
            channels[3] = (((pac[7] & 0xFF) << 7) & inputChannelMask) | ((pac[6] & 0xFF) >> 1);
            channels[4] = (((pac[8] & 0xFF) << 4) & inputChannelMask) | ((pac[7] & 0xFF) >> 4);
            channels[5] = (((pac[10] & 0xFF) << 9) & inputChannelMask) | ((pac[9] & 0xFF) << 1) | ((pac[8] & 0xFF) >> 7);
            channels[6] = (((pac[11] & 0xFF) << 6) & inputChannelMask) | ((pac[10] & 0xFF) >> 2);
            channels[7] = (((pac[12] & 0xFF) << 3) & inputChannelMask) | ((pac[11] & 0xFF) >> 5);

            channels[8] = (((pac[14] & 0xFF) << 8) & inputChannelMask) | (pac[13] & 0xFF);
            channels[9] = (((pac[15] & 0xFF) << 5) & inputChannelMask) | ((pac[14] & 0xFF) >> 3);
            channels[10] = (((pac[17] & 0xFF) << 10) & inputChannelMask) | ((pac[16] & 0xFF) << 2) | ((pac[15] & 0xFF) >> 6);
            channels[11] = (((pac[18] & 0xFF) << 7) & inputChannelMask) | ((pac[17] & 0xFF) >> 1);
            channels[12] = (((pac[19] & 0xFF) << 4) & inputChannelMask) | ((pac[18] & 0xFF) >> 4);
            channels[13] = (((pac[21] & 0xFF) << 9) & inputChannelMask) | ((pac[20] & 0xFF) << 1) | ((pac[19] & 0xFF) >> 7);
            channels[14] = (((pac[22] & 0xFF) << 6) & inputChannelMask) | ((pac[21] & 0xFF) >> 2);
            channels[15] = (((pac[23] & 0xFF) << 3) & inputChannelMask) | ((pac[22] & 0xFF) >> 5);
        }
    }
}
