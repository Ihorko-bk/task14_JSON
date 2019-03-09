package name.ihorko.model;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;
import com.sun.deploy.util.StringUtils;

import java.util.HashMap;
import java.util.Map;

public enum Soil {
    PODZOL("підзолиста"), GROUNG("ґрунтова"), DARN_PODZOLIC("дерново-підзолиста");

    private String soil;
    Soil(String soil) {
        this.soil = soil;
    }

    public static Soil getSoil(String soil) {
        if (soil.equals(PODZOL.soil)) return PODZOL;
        if (soil.equals(GROUNG.soil)) return GROUNG;
        if (soil.equals(DARN_PODZOLIC.soil)) return DARN_PODZOLIC;
        return null;
    }

    @Override
    public String toString() {
        return soil;
    }



    // для сереалізації та десереалізації використовуючи Jackson ↓↓↓↓↓
    private static Map<String, Soil> namesMap = new HashMap<>(3);
    static {
        namesMap.put("підзолиста", PODZOL);
        namesMap.put("ґрунтова", GROUNG);
        namesMap.put("дерново-підзолиста", DARN_PODZOLIC);
    }
    @JsonCreator
    public static Soil forValue(String value) {
        return namesMap.get(value);
    }
    @JsonValue
    public String toValue() {
        for (Map.Entry<String, Soil> entry : namesMap.entrySet()) {
            if (entry.getValue() == this)
                return entry.getKey();
        }
        return null; // or fail
    }
    // для сереалізації та десереалізації використовуючи Jackson ↑↑↑↑↑
}
