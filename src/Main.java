import com.avangarde.parkinglot.utils.Importer;

public class Main {

    public static void main(String[] args) {

        Importer importFromFile = new Importer();

        String path = "C:\\Users\\Kosa Markos\\IdeaProjects\\parkinglot\\src\\input.txt";

        importFromFile.getObjectsFromFile(path);

    }
}
