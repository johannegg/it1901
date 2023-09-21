package core;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;


public class FileHandler {
    private List<String> bookList = new ArrayList<>();

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

    public List<String> readFromFile(String filePath) {
        try {
            Scanner scanner = new Scanner(new File(filePath));
            while (scanner.hasNextLine()) {
                String bookTitle = scanner.nextLine();
                this.bookList.add(bookTitle);
            }
            scanner.close();
            return bookList;
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