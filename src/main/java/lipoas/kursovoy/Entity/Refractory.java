package lipoas.kursovoy.Entity;


import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "Refractory")
public class Refractory implements Serializable {


    @Id
    @GeneratedValue
    private Long id;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    private double density;

    @Column(nullable = false)
    private double thermal_conductivity;

    @Column
    private String type_of_use;

    @Column
    private String zone_of_ladle;

    public Refractory() {
    }

    public Refractory(String name, double density, double thermal_conductivity, String type_of_use, String zone_of_ladle) {
        this.name = name;
        this.density = density;
        this.thermal_conductivity = thermal_conductivity;
        this.type_of_use = type_of_use;
        this.zone_of_ladle = zone_of_ladle;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getDensity() {
        return density;
    }

    public void setDensity(double density) {
        this.density = density;
    }

    public double getThermal_conductivity() {
        return thermal_conductivity;
    }

    public void setThermal_conductivity(double thermal_conductivity) {
        this.thermal_conductivity = thermal_conductivity;
    }

    public String getType_of_use() {
        return type_of_use;
    }

    public void setType_of_use(String type_of_use) {
        this.type_of_use = type_of_use;
    }

    public String getZone_of_ladle() {
        return zone_of_ladle;
    }

    public void setZone_of_ladle(String zone_of_ladle) {
        this.zone_of_ladle = zone_of_ladle;
    }
}
