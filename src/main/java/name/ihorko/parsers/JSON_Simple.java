package name.ihorko.parsers;

import name.ihorko.model.Flower;
import name.ihorko.model.GrowingTips;
import name.ihorko.model.Soil;
import name.ihorko.model.VisualParameters;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class JSON_Simple {

    public static void createJsonAndWriteToFile(final Flower flower, File json) {
        createJsonAndWriteToFile(new ArrayList<Flower>(){{add(flower);}}, json);
    }
    public static void createJsonAndWriteToFile(List<Flower> flowers, File json) {

        JSONArray jsonArray = new JSONArray();
        JSONObject jsonObject;
        Flower f;
        for (int i = 0; i < flowers.size(); i++) {
            jsonObject = new JSONObject();
            f = flowers.get(i);

            jsonObject.put("name", f.getName());
            jsonObject.put("soil", f.getSoil().toString());
            jsonObject.put("origin", f.getOrigin());
            jsonObject.put("visual_parameters",
                    createJsonObjectWithVisualParameters(f.getVisualParameters()));
            jsonObject.put("growing_tips", createJsonObjectWithGrowingTips(f.getGrowingTips()));
            jsonObject.put("multiplying", f.getMultiplying());

            jsonArray.add(jsonObject);
        }

        writeJsonToFile(jsonArray, json);
    }
    private static JSONObject createJsonObjectWithVisualParameters(VisualParameters vp) {
        JSONObject jsonVP = new JSONObject();
        jsonVP.put("stem_color", vp.getStemColor());
        jsonVP.put("leaf_color", vp.getLeafColor());
        jsonVP.put("plant_average_size", vp.getPlantAverageSize());
        return jsonVP;
    }
    private static JSONObject createJsonObjectWithGrowingTips(GrowingTips gt) {
        JSONObject jsonGT = new JSONObject();
        jsonGT.put("temperature", gt.getTemperature());
        jsonGT.put("light", gt.isLight());
        jsonGT.put("watering", gt.getWatering());
        return jsonGT;
    }
    private static void writeJsonToFile(JSONArray jsonArray, File json) {
        try (FileWriter file = new FileWriter(json.getPath())) {
            file.write(jsonArray.toJSONString());
            file.flush();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    public static List<Flower> readJSONAndGiveMeFlowers(File json) {
        ArrayList<Flower> flowers = new ArrayList<Flower>();
        JSONParser parser = new JSONParser();

        try {
            Object obj = parser.parse(new FileReader(json.getPath()));
            JSONArray jsonArray = (JSONArray) obj;
            for (int i = 0; i < jsonArray.size(); i++) {
                flowers.add(getFlowerFromJsonObject((JSONObject) jsonArray.get(i)));
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ParseException e) {
            e.printStackTrace();
        }

        return flowers;
    }
    private static Flower getFlowerFromJsonObject(JSONObject jsonObject) {
        Flower flower = new Flower();

        String name = (String) jsonObject.get("name");
//        System.out.println(name);
        flower.setName(name);

        Soil soil = Soil.getSoil((String) jsonObject.get("soil"));
//        System.out.println(soil);
        flower.setSoil(soil);

        String origin = (String) jsonObject.get("origin");
//        System.out.println(origin);
        flower.setOrigin(origin);

        JSONObject jsonObjectVisualParameters = (JSONObject) jsonObject.get("visual_parameters");
        flower.setVisualParameters(getVisualParametersFromJsonObject(jsonObjectVisualParameters));

        JSONObject jsonObjectGrowingTips = (JSONObject) jsonObject.get("growing_tips");
        flower.setGrowingTips(getGrowingTipsFromJsonObject(jsonObjectGrowingTips));

        String multiplying = (String) jsonObject.get("multiplying");
//        System.out.println(multiplying);
        flower.setMultiplying(multiplying);

        return flower;
    }
    private static VisualParameters getVisualParametersFromJsonObject(JSONObject jsonObject) {
        VisualParameters visualParameters = new VisualParameters();

        String stem_color = (String) jsonObject.get("stem_color");
//        System.out.println(stem_color);
        visualParameters.setStemColor(stem_color);

        String leaf_color = (String) jsonObject.get("leaf_color");
//        System.out.println(leaf_color);
        visualParameters.setLeafColor(leaf_color);

        double plant_average_size = (Double) jsonObject.get("plant_average_size");
//        System.out.println(plant_average_size);
        visualParameters.setPlantAverageSize(plant_average_size);

        return visualParameters;
    }
    private static GrowingTips getGrowingTipsFromJsonObject(JSONObject jsonObject) {
        GrowingTips growingTips = new GrowingTips();

        double temperature = (Double) jsonObject.get("temperature");
//        System.out.println(temperature);
        growingTips.setTemperature(temperature);

        boolean light = (Boolean) jsonObject.get("light");
//        System.out.println(light);
        growingTips.setLight(light);

        int watering = ((Long) jsonObject.get("watering")).intValue();
//        System.out.println(watering);
        growingTips.setWatering(watering);

        return growingTips;
    }

}
