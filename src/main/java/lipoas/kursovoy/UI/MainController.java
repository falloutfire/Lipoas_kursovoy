package lipoas.kursovoy.UI;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import lipoas.kursovoy.Application;
import lipoas.kursovoy.Entity.Refractory;
import lipoas.kursovoy.Entity.RefractoryDto;
import lipoas.kursovoy.Service.RefractoryService;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

public class MainController {

    public TableView<RefractoryDto> refractoryTableView;
    public TableColumn<RefractoryDto, String> nameTableColumn;
    public TableColumn<RefractoryDto, Double> densityTableColumn;
    public TableColumn<RefractoryDto, Double> thermalTableColumn;
    public TableColumn<RefractoryDto, String> useTableColumn;
    public TableColumn<RefractoryDto, String> zoneTableColumn;

    Application app = null;

    private ObservableList<RefractoryDto> listRefractory = FXCollections.observableArrayList();

    @Autowired
    private RefractoryService refractoryService;


    public void initialize(){
        /**
         * тут магия конечно, но если примерно, setCellValueFactory() - метод, который указывает
         * какие данные будут ОТОБРАЖАТЬСЯ в ячейках определенного столбца в таблице
         */
        System.out.println(this);
        nameTableColumn.setCellValueFactory(cellData -> cellData.getValue().nameProperty());
        densityTableColumn.setCellValueFactory(cellData -> cellData.getValue().densityProperty().asObject());
        thermalTableColumn.setCellValueFactory(cellData -> cellData.getValue().thermal_conductivityProperty().asObject());
        useTableColumn.setCellValueFactory(cellData -> cellData.getValue().type_of_useProperty());
        zoneTableColumn.setCellValueFactory(cellData -> cellData.getValue().zone_of_ladleProperty());
        refractoryTableView.setItems(listRefractory);

        /*
        parser(refractoryService.findAll());

        */

        //parser(refractoryService.findAll(), listRefractory);

    }

    public void onClickAdd(ActionEvent actionEvent) {
        app.showLayoutAdd();
        //ArrayList<Refractory> list = (ArrayList<Refractory>) refractoryService.findAll();
        //System.out.println(list.get(0).getName());
    }

    private void parser(List<Refractory> list, ObservableList<RefractoryDto> obsList) {
        obsList.clear();
        for (Refractory refractory : list) {
            RefractoryDto element = new RefractoryDto();
            element.setName(refractory.getName());
            element.setId(refractory.getId());
            element.setDensity(refractory.getDensity());
            element.setThermal_conductivity(refractory.getThermal_conductivity());
            element.setType_of_use(refractory.getType_of_use());
            element.setZone_of_ladle(refractory.getZone_of_ladle());
            obsList.add(element);
        }
    }

    public void onClickShowAll(ActionEvent actionEvent) {
        parser(refractoryService.findAll(), listRefractory);
    }

    public void onClickDelete(ActionEvent actionEvent) {
    }

    public void setApp(Application app) {
        this.app = app;
        System.out.println("test");
        System.out.println(this);
    }
}
