package name.ihorko;

import name.ihorko.model.Flower;
import name.ihorko.parsers.GSON;
import name.ihorko.parsers.Jackson;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class ParsersRunner {
    public static void main(String[] args) {
        File json = new File("src\\main\\resources\\flowers.json");
        List<Flower> flowers = new ArrayList<>();

//        flowers = JSON_Simple.readJSONAndGiveMeFlowers(json);
//        flowers = GSON.readObjectFromJsonFile(flowers, json);
        flowers = Jackson.JacksonObjectMapper.readJSONAndGiveMeFlowers(json);



        System.out.println(flowers);
    }
}
