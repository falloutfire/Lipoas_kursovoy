package lipoas.kursovoy.Entity;

import javafx.beans.property.*;

public class RefractoryDto {

    private LongProperty id;
    private StringProperty name;
    private DoubleProperty density;
    private DoubleProperty thermal_conductivity;
    private StringProperty type_of_use;
    private StringProperty zone_of_ladle;

    public RefractoryDto() {
        this.id = new SimpleLongProperty();
        this.name = new SimpleStringProperty();
        this.density = new SimpleDoubleProperty();
        this.thermal_conductivity = new SimpleDoubleProperty();
        this.type_of_use = new SimpleStringProperty();
        this.zone_of_ladle = new SimpleStringProperty();
    }

    public long getId() {
        return id.get();
    }

    public LongProperty idProperty() {
        return id;
    }

    public void setId(long id) {
        this.id.set(id);
    }

    public String getName() {
        return name.get();
    }

    public StringProperty nameProperty() {
        return name;
    }

    public void setName(String name) {
        this.name.set(name);
    }

    public double getDensity() {
        return density.get();
    }

    public DoubleProperty densityProperty() {
        return density;
    }

    public void setDensity(double density) {
        this.density.set(density);
    }

    public double getThermal_conductivity() {
        return thermal_conductivity.get();
    }

    public DoubleProperty thermal_conductivityProperty() {
        return thermal_conductivity;
    }

    public void setThermal_conductivity(double thermal_conductivity) {
        this.thermal_conductivity.set(thermal_conductivity);
    }

    public String getType_of_use() {
        return type_of_use.get();
    }

    public StringProperty type_of_useProperty() {
        return type_of_use;
    }

    public void setType_of_use(String type_of_use) {
        this.type_of_use.set(type_of_use);
    }

    public String getZone_of_ladle() {
        return zone_of_ladle.get();
    }

    public StringProperty zone_of_ladleProperty() {
        return zone_of_ladle;
    }

    public void setZone_of_ladle(String zone_of_ladle) {
        this.zone_of_ladle.set(zone_of_ladle);
    }
}
