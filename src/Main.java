import com.avangarde.parkinglot.utils.Importer;
import com.avangarde.parkinglot.utils.InputFileImpl;
import com.avangarde.parkinglot.vehicle.models.Vehicle;

import java.util.ArrayList;
import java.util.List;

public class Main {

    public static void main(String[] args) {

        InputFileImpl inputFile = new InputFileImpl();

        String path = "C:\\Users\\Kosa Markos\\IdeaProjects\\parkinglot\\src\\input.txt";

        List<Vehicle> cars = inputFile.readVehicles(path);

        for(var car : cars) {
            System.out.println(car.getInfo());
        }

    }
}
