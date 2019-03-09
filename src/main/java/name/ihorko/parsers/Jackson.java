package name.ihorko.parsers;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import name.ihorko.model.Flower;

import java.io.*;
import java.util.ArrayList;

public class Jackson {

    public static class JacksonObjectMapper {
        public static ArrayList<Flower> readJSONAndGiveMeFlowers(File json) {
            try {
                return new ObjectMapper().readValue(json, new TypeReference<ArrayList<Flower>>(){});
            } catch (IOException e) {
                e.printStackTrace();
            }
            return null;
        }
    }
}
