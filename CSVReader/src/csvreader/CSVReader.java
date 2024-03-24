/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package csvreader;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author lucas
 */
public class CSVReader {
    
    //this code reads a CSV file line by line, 
    //converts each line to an integer and stores these numbers in a list, which is then returned
    public static List<Integer> readCSV(String filePath) throws IOException {
    List<Integer> data = new ArrayList<>();
    try (BufferedReader br = new BufferedReader(new FileReader(filePath))) {
        String line;
        while ((line = br.readLine()) != null) {
            String[] numbers = line.split(",");
            for (String number : numbers) {
                int num = Integer.parseInt(number);
                data.add(num);
            }
        }
    }
    return data;
}
    
    

    
    public static void main(String[] args) throws IOException {
        List<Integer> data = readCSV("C:\\Users\\lucas\\Downloads\\data.csv");
        
        //Create a new Merge Sort class to sort a list 
        //of integers in descending order, using 4 threads to improve sort performance
        int numThreads = 4;
        MergeSort ms = new MergeSort(data, numThreads);
        List<Integer> sortedData = ms.sortDescending();

        System.out.println("Sorted Data:");
        System.out.println(sortedData);
    
    
    }
    
}
