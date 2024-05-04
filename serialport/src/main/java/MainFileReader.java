import java.io.*;
import java.util.Base64;

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

    public static void main(String[] args) {
        PortReader portReader = new PortReader();
        try {
            BufferedReader reader = new BufferedReader(new FileReader("data.bin"));
            while (reader.ready()) {
                byte[] buf = Base64.getDecoder().decode(reader.readLine());
                portReader.serialEvent(buf);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static class PortReader {

        static byte[] pac = new byte[64];
        static boolean start_package = false; // индикатор  процесса формирования пакета
        static byte pac_length = 0;
        static int pac_readed_bytes = 0;
        static int[] channels = new int[16];

        public void serialEvent(byte[] buf) {
            try {
                //Получаем ответ от устройства, обрабатываем данные и т.д.

                for (byte b : buf) {
                    // пакет еще не сформирован полностью, но часть данных уже получена
                    if (start_package && pac_length > 0)
                    {
                        pac[pac_readed_bytes++] = b;

                        if (pac_readed_bytes == pac_length + 2)
                        {
                            work_package(pac);

                            pac_length = 0;
                            start_package = false;
                            pac_readed_bytes = 0;
                        }
                        continue;
                    }
                    else if (start_package && pac_readed_bytes == 1) // прочитали 1 байт не знаем длину
                    {
                        pac_length = b;
                        if ((pac_length & 0xFF) <= 62 ) {
                            pac[pac_readed_bytes++] = pac_length; //pac[1] = pac_length
                        } else { // Это не длина, будем ждать следующей метки
                            start_package = false;
                        }
                        continue;
                    }

                    // пакет еще не начал формироваться
                    // проверяем полученный байт на метку начала пакета
                    int start_byte = b & 0xFF;

                    if ((start_byte == 0xc8 || start_byte == 0xee || start_byte == 0xea || start_byte == 0xec) && !start_package) {
                        pac[0] = b;
                        pac_readed_bytes = 1;
                        start_package = true;
                        continue;
                    }

                }
            } catch (Exception ex) {
                System.out.println(ex);
            }
        }

        public void work_package(byte[] pac) {
            if (pac[2] == 0x16) {
                System.out.print("chanels: ");
                unpackChannels(pac, channels);
                for (int c : channels) {
                    System.out.printf("%5d ", c);
                }
                System.out.println();
            } else if (pac[2] == 0x14) {
                System.out.println("LINK_STATISTICS");
            }
        }

        /*
         * Выделение сигналов каналов (по 11 бит на каждый) из массива, полученного через UART от приемника
         */
        void unpackChannels (byte[] pac, int[] channels) {
            int inputChannelMask = 0x7FF;
            channels[0] = (((pac[4] & 0xFF ) << 8) & inputChannelMask) | (pac[3] & 0xFF);
            channels[1] = (((pac[5] & 0xFF ) << 5) & inputChannelMask) | ((pac[4] & 0xFF) >> 3);
            channels[2] = (((pac[7] & 0xFF) << 10) & inputChannelMask) | ((pac[6] & 0xFF) << 2) | ((pac[5] & 0xFF) >> 6);
            channels[3] = (((pac[8] & 0xFF) << 7) & inputChannelMask) | ((pac[7] & 0xFF) >> 1);
            channels[4] = (((pac[9] & 0xFF) << 4) & inputChannelMask) | ((pac[8] & 0xFF) >> 4);
            channels[5] = (((pac[11] & 0xFF) << 9) & inputChannelMask) | ((pac[10] & 0xFF) << 1) | ((pac[9] & 0xFF) >> 7);
            channels[6] = (((pac[12] & 0xFF) << 6) & inputChannelMask) | ((pac[11] & 0xFF) >> 2);
            channels[7] = (((pac[13] & 0xFF) << 3) & inputChannelMask) | ((pac[12] & 0xFF) >> 5);

            channels[8] = (((pac[15] & 0xFF ) << 8) & inputChannelMask) | (pac[14] & 0xFF);
            channels[9] = (((pac[16] & 0xFF ) << 5) & inputChannelMask) | ((pac[15] & 0xFF) >> 3);
            channels[10] = (((pac[18] & 0xFF) << 10) & inputChannelMask) | ((pac[17] & 0xFF) << 2) | ((pac[16] & 0xFF) >> 6);
            channels[11] = (((pac[19] & 0xFF) << 7) & inputChannelMask) | ((pac[18] & 0xFF) >> 1);
            channels[12] = (((pac[20] & 0xFF) << 4) & inputChannelMask) | ((pac[19] & 0xFF) >> 4);
            channels[13] = (((pac[22] & 0xFF) << 9) & inputChannelMask) | ((pac[21] & 0xFF) << 1) | ((pac[20] & 0xFF) >> 7);
            channels[14] = (((pac[23] & 0xFF) << 6) & inputChannelMask) | ((pac[22] & 0xFF) >> 2);
            channels[15] = (((pac[24] & 0xFF) << 3) & inputChannelMask) | ((pac[23] & 0xFF) >> 5);
        }

        void unpackChannels1 (byte[] pac, int[] channels) {
            //int inputChannelMask = 0x7FF;
            int numOfChannels = 16;
            int srcBits = 11;
            int dstBits = 32;
            int inputChannelMask = (1 << srcBits) - 1;

            // code from BetaFlight rx/crsf.cpp / bitpacker_unpack
            byte bitsMerged = 0;
            int readValue = 0;
            int readByteIndex = 3;
            for (int n = 0; n < numOfChannels; n++)
            {
                while (bitsMerged < srcBits)
                {
                    byte readByte = pac[readByteIndex++];
                    readValue |= ( readByte & 0xFF) << bitsMerged;
                    bitsMerged += 8;
                }
                //printf("rv=%x(%x) bm=%u\n", readValue, (readValue & inputChannelMask), bitsMerged);
                channels[n] = (readValue & inputChannelMask);
                readValue >>= srcBits;
                bitsMerged -= srcBits;
            }
        }
    }
}
//chanels:   992  1124   978   992   191   191   191   191   191   191   191   191     0     0  1811  1811
//chanels:   992  1124   978   992   191   191   191   191   191   191   191   191     0     0  1811  1811