import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class attendance_percentage {
    public static HashMap<String,Double> attendance_percentage(String path){
        csvread read=new csvread();
        List<String[]> record=new ArrayList<>();
        HashMap<String,Double> subject_attendance_record=new HashMap<>();
        record=read.Csvread(path);
        double attended;
        double total;
        double percentage;
        for(String[] subjects:record){
            /*System.out.println("Subject is "+subjects[0]+" and you have attended "+subjects[1]+"/"+subjects[2]);*/
            attended=Integer.parseInt(subjects[1]);
            total=Integer.parseInt(subjects[2]);
            percentage=(attended/total)*100;
            subject_attendance_record.put(subjects[0],percentage);

        }
        return subject_attendance_record;

    }
}
