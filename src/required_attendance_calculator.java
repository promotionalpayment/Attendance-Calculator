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
            req_attended=(int)((0.75*total-attended)/0.25);
            //Green Tick: \u2713
            //Red Cross: \u2717
            if (req_attended <= 0) {
                System.out.printf(Main.Colors.GREEN+"[OK] %-6s: Already at or above 75%% attendance!\n"+ Main.Colors.RESET,subjects[0]);
            } else {
                System.out.printf(Main.Colors.RED+"[X] %-6s: Attend %d more classes to reach 75%%\n"+ Main.Colors.RESET, subjects[0],req_attended);
            }



        }
    }
}
