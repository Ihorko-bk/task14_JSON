package name.ihorko.model;

import com.fasterxml.jackson.annotation.JsonProperty;

public class VisualParameters {
    @JsonProperty("stem_color")
    private String stemColor;
    @JsonProperty("leaf_color")
    private String leafColor;
    @JsonProperty("plant_average_size")
    private double plantAverageSize;

    public String getStemColor() {
        return stemColor;
    }

    public void setStemColor(String stemColor) {
        this.stemColor = stemColor;
    }

    public String getLeafColor() {
        return leafColor;
    }

    public void setLeafColor(String leafColor) {
        this.leafColor = leafColor;
    }

    public double getPlantAverageSize() {
        return plantAverageSize;
    }

    public void setPlantAverageSize(double plantAverageSize) {
        this.plantAverageSize = plantAverageSize;
    }

    public VisualParameters() {
    }

    public VisualParameters(String stemColor, String leafColor, double plantAverageSize) {
        this.stemColor = stemColor;
        this.leafColor = leafColor;
        this.plantAverageSize = plantAverageSize;
    }

    @Override
    public String toString() {
        return "stemColor='" + stemColor + '\'' +
                ", leafColor='" + leafColor + '\'' +
                ", plantAverageSize=" + plantAverageSize;
    }
}
