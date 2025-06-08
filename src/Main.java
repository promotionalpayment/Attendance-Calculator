import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class Main {
    static String path= "attendance.csv";
    public static void add_subject(String subject,String attended_classes,String total_classes){
        File file=new File(path);
        csvread read=new csvread();
        csvwriting write=new csvwriting();
        List<String[]> record=new ArrayList<>();
        try(BufferedReader bufferedReader=new BufferedReader(new FileReader(path))){
            if(bufferedReader.readLine()==null){
                int attended=Integer.parseInt(attended_classes),total=Integer.parseInt(total_classes);
                if(attended<=total){
                    String[] first_entry={subject,attended_classes,total_classes};
                    record.add(first_entry);
                    write.csvwriter(path,record);
                }
                else{
                    System.out.println("Attended classes cannot be greater than total classes");
                }
            }
            else{
                int attended=Integer.parseInt(attended_classes),total=Integer.parseInt(total_classes);
                if(attended<=total) {
                    record = read.Csvread(path);
                    String[] new_entry = {subject, attended_classes, total_classes};
                    record.add(new_entry);
                    write.csvwriter(path, record);
                }
                else{
                    System.out.println("Number of attended classes cannot be greater than total number of classes");
                }
            }
        } catch (Exception e) {
            System.out.println(e);
        }
    }
    public static void calculate_attendance_percentage(){
        attendance_percentage attendancePercentage=new attendance_percentage();
        HashMap<String,Double> attendance_record=attendancePercentage.attendance_percentage(path);
        csvread read=new csvread();
        List<String[]> record=new ArrayList<>();
        try(BufferedReader bufferedReader=new BufferedReader(new FileReader(path))){
            record=read.Csvread(path);
            System.out.println("|-----------------|----------|-------|------------------------|");
            System.out.printf("| %-15s | %-8s | %-5s | %-22s |\n", "Subject", "Attended", "Total", "Attendance Percentage");
            System.out.println("|-----------------|----------|-------|------------------------|");
            for(String[] subject:record){
                String name = subject[0];
                String attended = subject[1];
                String total = subject[2];
                Double percentage = attendance_record.get(name);

                System.out.printf("| %-15s | %-8s | %-5s | %-21.2f%% |\n", name, attended, total, percentage);
            }
            System.out.println("|-----------------|----------|-------|------------------------|");

        } catch (Exception e) {
            System.out.println(e);
        }



    }
    public static void calculate_attendance_percentage(String subject){
        attendance_percentage attendancePercentage=new attendance_percentage();
        HashMap<String,Double> attendance_record=attendancePercentage.attendance_percentage(path);
        csvread read=new csvread();
        List<String[]> record=new ArrayList<>();
        try(BufferedReader bufferedReader=new BufferedReader(new FileReader(path))){
            record=read.Csvread(path);
            System.out.println("|-----------------|----------|-------|------------------------|");
            System.out.printf("| %-15s | %-8s | %-5s | %-22s |\n", "Subject", "Attended", "Total", "Attendance Percentage");
            System.out.println("|-----------------|----------|-------|------------------------|");
            for(String[] subjects:record) {
                String name = subjects[0];
                String attended = subjects[1];
                String total = subjects[2];
                Double percentage = attendance_record.get(name);
                if (name.equals(subject)) {
                    System.out.printf("| %-15s | %-8s | %-5s | %-21.2f%% |\n", name, attended, total, percentage);
                }
            }
            System.out.println("|-----------------|----------|-------|------------------------|");

        } catch (Exception e) {
            System.out.println(e);
        }


    }

    public static void main(String[] args) {
        File file=new File(path);
        csvread read=new csvread();
        csvwriting write=new csvwriting();
        required_attendance_calculator requiredAttendanceCalculator=new required_attendance_calculator();
        attendance_percentage attendancePercentage=new attendance_percentage();
        List<String[]> record=new ArrayList<>();
        if(file.exists()!=true){
            try{
                file.createNewFile();
            } catch (Exception e) {
                System.out.println(e);
            }
        }
        String command=args[0].toLowerCase();
        if(command.equals("add")&&args.length<4){
            System.out.println("Usage: add <subject_name> <attended_classes> <total_classes>");

        }
        else if(command.equals("sp")&&(args.length>2)||(args.length<0)){
            System.out.println("Usage: sp --> To display all the subjects.");
            System.out.println("                 OR                        ");
            System.out.println("Usage: sp <subject_name> ");
        }

        switch (command){
            case "add":
                String subject=args[1],attended_classes=args[2],total_classes=args[3];
                add_subject(subject,attended_classes,total_classes);
                break;
            case "sp":
                if(args.length==1){
                    calculate_attendance_percentage();
                }
                else {
                    calculate_attendance_percentage(args[1]);
                }
                break;
            default:System.out.println("Invalid command.");
        }
    }
}
