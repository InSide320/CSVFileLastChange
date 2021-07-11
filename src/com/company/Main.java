package com.company;


import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVPrinter;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new FileReader("acme_worksheet1.csv"));
        CSVPrinter printer = new CSVPrinter(new FileWriter("acme_test.csv"), CSVFormat.EXCEL);
        String line = null;
        int index = 0;
        List<Employee> emList = new ArrayList<>();
        while ((line = reader.readLine()) != null) {
            Employee employee = new Employee(" ", " ", " ");
            Scanner scanner = new Scanner(line);
            scanner.useDelimiter(",");
            while (scanner.hasNext()) {
                String data = scanner.next();
                if (index == 0) {
                    employee.setName(data);
                } else if (index == 1) {
                    employee.setDate(data);
                } else if (index == 2) {
                    employee.setWork_hours(data);
                } else
                    System.out.println("Error" + data);
                index++;
            }
//            Stream<Employee> employeeStream = Stream.of(new Employee(employee.getName(),employee.getDate(),employee.getWork_hours()));
//            Map<String, List<Employee>> empl= employeeStream.collect(Collectors.groupingBy(Employee :: getName));
//            for(Map.Entry<String, List<Employee>> item : empl.entrySet()){
//                System.out.println("Name: " + item.getKey());
//                printer.printRecord(item.getKey());
//                for(Employee e : item.getValue()){
//                    System.out.printf("Date: %s, Hours Work: %s %n",e.getDate(), e.getWork_hours());
//                    printer.printRecord(e.getDate() , e.getWork_hours());
//                }
//            }
            index = 0;
            emList.add(employee);
            printer.printRecord(employee.getName(), employee.getDate(), employee.getWork_hours());
        }
        reader.close();
        System.out.println(emList);
        printer.close();
    }
}
