import com.avangarde.parkinglot.utils.Import;

public class Main {

    public static void main(String[] args) {

        Import importFromFile = new Import();

        String path = "C:\\Users\\Kosa Markos\\IdeaProjects\\parkinglot\\src\\input.txt";

        importFromFile.getFromFile(path);
        for(String string : importFromFile.aaa) {
           System.out.println(string);
        }
    }
}
