import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class required_attendance_calculator {
    public static void required_attendance_calculator(String path){
        csvread read=new csvread();
        attendance_percentage attendancePercentage=new attendance_percentage();
        List<String[]> record=new ArrayList<>();
        HashMap<String,Double> subject_attendance_record=new HashMap<>();
        record=read.Csvread(path);
        subject_attendance_record=attendancePercentage.attendance_percentage(path);
        int req_attended,attended,total,req_total;;
        double percentage;
        for(String[] subjects:record){
            attended=Integer.parseInt(subjects[1]);
            total=Integer.parseInt(subjects[2]);
            req_attended=attended;
            req_total=total;
            percentage=((double)attended/total);
            while(((double)req_attended/req_total)<0.75){
                percentage=((double)req_attended/req_total);
                req_attended++;
                req_total++;
            }

            System.out.println("You need to attend "+(req_attended-attended)+" more classes to have 75% attendance in "+subjects[0]+".");


        }
    }
}
