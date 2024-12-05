import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;

public class TestLogger {

    private static final String LOG_FILE_PATH = "test_log.txt";

    public static void log(String message) {
        try (FileWriter fileWriter = new FileWriter(LOG_FILE_PATH, true);
             PrintWriter printWriter = new PrintWriter(fileWriter)) {
            printWriter.println(message);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
