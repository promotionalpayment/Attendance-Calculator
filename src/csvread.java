import com.opencsv.CSVReader;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.List;

public class csvread {
    public static List<String[]> Csvread(String path){
        List<String[]> record=new ArrayList<>();

        try(BufferedReader read=new BufferedReader(new FileReader(path))){
            CSVReader csvreader=new CSVReader(read);
            record = csvreader.readAll();
            return record;



        } catch (Exception e) {
            System.out.println("Error reading CSV: " + e);
            return record;

        }

    }
}
