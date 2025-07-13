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
            for(String[] subjects:record) {
                String name = subjects[0];
                String attended = subjects[1];
                String total = subjects[2];
                Double percentage = attendance_record.get(name);
                String colour = "";
                String reset = Colors.RESET;
                if (percentage >= 75.0) {
                    colour = Colors.GREEN;
                } else {
                    colour = Colors.RED;
                }
                int barfilled = (int) (percentage / 10);
                int barempty = (int) (10 - barfilled);
                String bar = "█".repeat(barfilled) + "-".repeat(barempty);
                System.out.printf("%-15s %s[%s]%s %5.1f%% (%s/%s)\n",name, colour, bar, reset, percentage, attended, total);


            }

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

            for(String[] subjects:record) {
                String name = subjects[0];
                String attended = subjects[1];
                String total = subjects[2];
                Double percentage = attendance_record.get(name);
                String colour="";
                String reset=Colors.RESET;
                if(percentage>=75.0){
                    colour=Colors.GREEN;
                }
                else{
                    colour=Colors.RED;
                }
                int barfilled=(int)(percentage/10);
                int barempty=(int)(10-barfilled);
                String bar="█".repeat(barfilled)+"-".repeat(barempty);

                if (name.equals(subject)) {
                    System.out.printf("%.15s "+colour+"["+bar+"]"+reset+" %.1f%%",name,percentage);
                    System.out.println("\nYou've attended "+colour+attended+"/"+total+reset+".");
                }

            }


        } catch (Exception e) {
            System.out.println(e);
        }


    }
    public static void update_subject(String target_subject,String new_attended_classes,String new_total_classes){
        File file=new File(path);
        csvread read=new csvread();
        csvwriting write=new csvwriting();
        List<String[]> record=new ArrayList<>();
        try(BufferedReader bufferedReader=new BufferedReader(new FileReader(path))){
            if(bufferedReader.readLine()==null){
                System.out.println("There are no subjects here to be updated. (Add subjects using the 'Add' command).");
            }
            else{
                List<String[]> new_record=new ArrayList<>();
                record = read.Csvread(path);
                boolean found=false;
                if(Integer.parseInt(new_attended_classes)<=Integer.parseInt(new_total_classes)){
                    for(String[] subjects:record){
                        if(subjects[0].equals(target_subject)){
                        String[] new_subject_entry=new String[3];
                        new_subject_entry[0]=subjects[0];
                        new_subject_entry[1]=new_attended_classes;
                        new_subject_entry[2]=new_total_classes;
                        new_record.add(new_subject_entry);
                        found=true;
                        }
                        else{
                            new_record.add(subjects);
                        }
                    }
                    write.csvwriter(path,new_record);
                    if(found){
                        System.out.println(Colors.GREEN+"Updated "+target_subject+Colors.RESET);
                    }
                    else{
                        System.out.println(Colors.RED+"Subject not found."+Colors.RESET);
                    }

                }
                else{
                    System.out.println("Attended classes cannot be more than Total class.");
                }
            }
        } catch (Exception e) {
            System.out.println(e);
        }
    }
    public static void remove_subject(String target_subject){
        File file=new File(path);
        csvread read=new csvread();
        csvwriting write=new csvwriting();
        List<String[]> record=new ArrayList<>();
        try(BufferedReader bufferedReader=new BufferedReader(new FileReader(path))){
            if(bufferedReader.readLine()==null){
                System.out.println("There are no subjects here to be updated. (Add subjects using the 'Add' command).");
            }
            else{
                List<String[]> new_record=new ArrayList<>();
                record = read.Csvread(path);
                boolean found=false;
                for(String[] subjects:record){
                    if(subjects[0].equals(target_subject)){
                        found=true;
                        continue;

                    }
                    else{
                        new_record.add(subjects);
                    }
                }
                write.csvwriter(path,new_record);
                if(found){
                    System.out.println(Colors.GREEN+"Removed "+target_subject+Colors.RESET);
                }
                else{
                    System.out.println(Colors.RED+"Subject not found."+Colors.RESET);
                }
            }
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
        if(command.equals("add")&&args.length>4){
            System.out.println("Usage: add <subject_name> <attended_classes> <total_classes>");

        }
        else if(command.equals("sp")&&((args.length>2)||(args.length<0))){
            System.out.println("Usage: sp --> To display all the subjects.");
            System.out.println("                 OR                        ");
            System.out.println("Usage: sp <subject_name> ");
        }
        else if(command.equals("update")&&args.length<4){
            System.out.println("Usage: update <subject_name> <attended_classes> <total_classes>");

        }
        else if(command.equals("remove")&&args.length>2){
            System.out.println("Usage: remove <subject_name>");
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
            case "rq":
                requiredAttendanceCalculator.required_attendance_calculator(path);
                break;

            case "update":
                update_subject(args[1],args[2],args[3]);
                break;
            case "remove":
                remove_subject(args[1]);
                break;
            default:System.out.println("Invalid command.");
        }
    }
    public class Colors {
        public static final String RESET = "\u001B[0m";
        public static final String RED = "\u001B[31m";
        public static final String GREEN = "\u001B[32m";
        public static final String YELLOW = "\u001B[33m";
        public static final String BLUE = "\u001B[34m";
    }
}
