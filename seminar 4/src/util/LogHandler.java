package util;

import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.time.LocalDateTime;

/**
 * Handles logging of exceptions and error messages to a file.
 */
public class LogHandler {
    private static final String LOG_FILE_NAME = "pos-error-log.txt";
    private final PrintWriter writer;

    public LogHandler() throws IOException {
        writer = new PrintWriter(new FileWriter(LOG_FILE_NAME, true), true);
    }

    /**
     * Logs detailed information about an exception.
     *
     * @param exception The exception to log.
     */
    public void logException(Exception exception) {
        writer.println("----- ERROR LOG ENTRY -----");
        writer.println("Time: " + LocalDateTime.now());
        writer.println("Exception: " + exception.getClass().getName());
        writer.println("Message: " + exception.getMessage());
        for (StackTraceElement element : exception.getStackTrace()) {
            writer.println("\tat " + element);
        }
        writer.println();
    }
}
