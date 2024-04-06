package ru.itis.inf301.lab2_5.transport;

import com.fasterxml.jackson.databind.ObjectMapper;
import ru.itis.inf301.lab2_5.transport.model.TrasportDataBase;

import java.io.File;
import java.io.IOException;

public class MainTransport {

    public static void main(String[] args) throws IOException {
        ObjectMapper mapper = new ObjectMapper();
        TrasportDataBase dataBase =
                mapper.readValue(new File("transport.json"), TrasportDataBase.class);




    }
}
