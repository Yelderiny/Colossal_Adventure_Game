import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class FileLogger implements Logger
{
    private static final String FILE_LOGGER_NAME =  "StudentFileOutput.txt";

    static
    {
        try
        {
            File logs = new File(FILE_LOGGER_NAME); //new file object
            if (logs.exists()) { logs.delete(); } //if the file exists, delete it
        }
        catch (Exception e) { e.printStackTrace(); }
    }

    @Override
    public void log (String message)
    {
        try (FileWriter writer = new FileWriter(FILE_LOGGER_NAME, true)) { writer.append(message).append("\n"); }
        catch (IOException e) { e.printStackTrace(); }
    }
}
