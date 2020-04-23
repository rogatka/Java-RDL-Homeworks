package homework.company.consoleHelper;

import homework.company.exceptions.ExitException;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class ConsoleHelper {
    public static final String CHOOSE_OPTION = "Please choose ";
    private static BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

    public static void writeMessage(String message) {
        System.out.println(message);
    }

    public static void writeMessageWithOption(String message) {
        System.out.println(CHOOSE_OPTION + message + "...");
    }

    public static int readNumber(String option) throws ExitException {
        writeMessageWithOption(option);
        String answer = null;
        while (true) {
            try {
                answer = reader.readLine();
                if (answer != null) {
                    int number = Integer.parseInt(answer);
                    if (number == 0) throw new ExitException("Exiting...");
                    return number;
                }

            } catch (IOException e) {
                e.printStackTrace();
                if (reader != null) {
                    try {
                        reader.close();
                    } catch (IOException ex) {
                        //DO NOTHING
                    }
                }
            } catch (NumberFormatException e) {
                writeMessage("Incorrect data: " + answer + ". Please try again");
            }
        }
    }

    public static void close() {
        if (reader != null) {
            try {
                reader.close();
            } catch (IOException e) {
                e.printStackTrace();
                //DO NOTHING
            }
        }
    }

}
