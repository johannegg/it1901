package core;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Scanner;

public class FileHandler {

    public void writeToFile(String title, String filePath) throws IOException {
        try {
            PrintWriter writer = new PrintWriter(new FileWriter(filePath, true));
            writer.println(title);
            writer.close();
            System.out.println("Successfully wrote to the file.");

        } catch (FileNotFoundException e) {
            System.err.println("Error: file " + filePath + " could not be opened for writing.");
        }
    }

    public String readFromFile(String filePath) {
        try {
            Scanner scanner = new Scanner(new File(filePath));
            StringBuilder stringBuilder = new StringBuilder();
            while (scanner.hasNextLine()) {
                stringBuilder.append(scanner.nextLine()).append("\n");
            }
            scanner.close();
            return stringBuilder.toString();
        } catch (FileNotFoundException e) {
            System.err.println("Error: file " + filePath + " not found.");
            return null;
        }
    }

    public static void main(String[] args) throws IOException {
        FileHandler f1 = new FileHandler();
        f1.writeToFile("1984", "bookTracker/core/src/main/java/core/bookTitles.txt");
        System.out.println(f1.readFromFile("bookTracker/core/src/main/java/core/bookTitles.txt"));
    }
}