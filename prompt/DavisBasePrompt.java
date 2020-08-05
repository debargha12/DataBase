package chakri.prompt;
import java.util.Scanner;
import chakri.configuration.CatalogDatabaseHelper;
import chakri.model.DatabaseConstants;

/**
 * Created by Chakriramoj on Apr 21, 2019
 *
 */



public class DavisBasePrompt {

    private static Scanner scanner = new Scanner(System.in).useDelimiter(";");

    public static void main(String[] args) {

        CatalogDatabaseHelper.InitializeDatabase();
        splashScreen();

        while(!QueryParser.isExit) {
            System.out.print(DatabaseConstants.PROMPT);
            String command = scanner.next().replace("\n", "").replace("\r", " ").trim().toLowerCase();
            QueryParser.parseCommand(command);
        }
    }

    private static void splashScreen() {
        System.out.println(QueryHandler.line("-",80));
        System.out.println("Welcome to DavisBaseLite"); // Display the string.
        QueryHandler.ShowVersionQueryHandler();
        System.out.println("\nType 'help;' to display supported commands.");
        System.out.println(QueryHandler.line("-",80));
    }
}