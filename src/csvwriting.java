import com.opencsv.CSVWriter;

import java.io.File;
import java.io.FileWriter;
import java.util.List;

public class csvwriting {
    public static void csvwriter(String path, List<String[]> record){
        File file=new File(path);

        try(CSVWriter write=new CSVWriter(new FileWriter(path))){
            write.writeAll(record);



        } catch (Exception e) {
            System.out.println(e);
        }
    }
}
