package com.droids.ffs.smd_project;


import android.util.Log;

import com.droids.ffs.smd_project.SQLite.Class;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.List;


public class TimeTable {


    private static List<Class> classList;
    private static List<Class> selectedClasses;
    private static List<Class> allCourses;

    public static List<Class> getAllCourses() {
        return allCourses;
    }

    public static void setAllCourses(List<Class> allCourses) {
        TimeTable.allCourses = allCourses;
    }

    public static List<Class> getClassList() {
        return classList;
    }

    public static void setClassList(List<Class> classList) {
        TimeTable.classList = classList;
    }

    public static List<Class> getSelectedClasses() {
        return selectedClasses;
    }

    public static void setSelectedClasses(List<Class> selectedClasses) {
        TimeTable.selectedClasses = selectedClasses;
    }


//


    //public static List<Class> getAllClasses(InputStream is) {


    //


    //try {


    //


    //classList = new ArrayList<>();


    //


    //XSSFWorkbook myWorkBook = new XSSFWorkbook(is);


    //// Get the first sheet from workbook


    //XSSFSheet mySheet = myWorkBook.getSheetAt(0);


    //// We now need something to iterate through the cells.


    //Iterator<Row> rowIter = mySheet.rowIterator();


    //


    //Log.v("abc",myWorkBook.toString());


    //


    //String text = "";


    //


    //int rowNumber = 1;


    //while (rowIter.hasNext()) {


    //XSSFRow myRow = (XSSFRow) rowIter.next();


    //


    ////				Starting and ending row limits


    //if (rowNumber > 5 && rowNumber < 125) {


    //


    //Iterator<Cell> cellIter = myRow.cellIterator();


    //


    //int columnNumber = 1;


    ////					Column limit


    //while (cellIter.hasNext() && columnNumber < 13) {


    //


    //XSSFCell myCell = (XSSFCell) cellIter.next();


    //


    ////						Add # after each cell, even if its empty


    //text += myCell.toString() + "#";


    //columnNumber++;


    //}


    //


    ////					Add @ after each row even if its empty


    //text += "@";


    //}


    //


    //rowNumber++;


    //


    //}


    //


    //String[] rows = text.split("@");


    //


    ////		    System.out.println(rows.length);


    //


    //rowNumber = 1;


    //


    //Class[] classes = new Class[20];


    //for (int i = 0; i < classes.length; i++) {


    //classes[i] = new Class();


    //}


    //


    //for (String r : rows) {


    //int columnNumber = 0;


    //


    //if (rowNumber == 1 || rowNumber == 17 || rowNumber == 25 || rowNumber == 41 || rowNumber == 50 || rowNumber == 66 ||  rowNumber == 74 || rowNumber == 90 || rowNumber == 97 || rowNumber == 113) {


    //String[] timeSlots = r.split("#");


    //


    //int j = 0;


    //for (String c : timeSlots) {


    //


    //if (c.matches("Day") == false && c.matches("Venue") == false && c.matches("") == false && c.matches("Labs") == false) {


    //


    //classes[j] = new Class();


    ////		    					System.out.println(c.split("-")[0]);


    //classes[j].setClassStartTime(c.split("-")[0]);


    //


    //classes[j].setClassEndTime(c.split("-")[1]);


    //j++;


    //}


    //


    //}


    //


    //}


    //


    //else {


    //String[] columns = r.split("#");


    //


    //int j = 0;


    //for (String c : columns) {


    //


    ////	    					System.out.println("-"+c);


    //if (j == 0) {


    ////Set Days


    //for (Class c1 : classes) {


    //


    //if (c1.getClassEndTime().isEmpty() == false) {


    //if (rowNumber < 25) {


    //c1.setClassDay("Monday");


    //} else if (rowNumber >= 25 && rowNumber < 50) {


    //c1.setClassDay("Tuesday");


    //} else if (rowNumber >= 50 && rowNumber < 74) {


    //c1.setClassDay("Wednesday");


    //} else if (rowNumber >= 74 && rowNumber < 97) {


    //c1.setClassDay("Thursday");


    //} else if (rowNumber >= 97) {


    //c1.setClassDay("Friday");


    //}


    //}


    //


    //


    ////		    						System.out.println("-"+c);


    //


    //


    //}


    //


    //}


    //else if (j == 1) {//Set Rooms


    //for (Class c1 : classes) {


    ////		    						System.out.println("+"+c);


    //if (c1.getClassDay().isEmpty() == false){


    //if (c != null) {


    //c1.setClassRoom(c);


    //}


    //}


    //


    //


    //}


    //


    //}


    //


    //else if (j >= 2) {


    //if (c.contains("Break") == false) {


    ////	    							System.out.println("#"+c);


    //


    //if (c.compareTo("") != 0) {


    //Log.v("data",c);


    //


    //Matcher m = Pattern.compile("(.+)(\\(.+\\))").matcher(c);


    //if (m.matches())


    //{


    //String sec = m.group(2);


    //sec = sec.substring(1,2);


    //Log.v("data",sec);


    //


    //classes[columnNumber].setCourseSection(sec);


    //// do something


    //}


    //


    //


    //


    //classes[columnNumber].setCourseName(c.split("\\(")[0]);


    //columnNumber++;


    //} else {


    //classes[columnNumber].setCourseName(null);


    //columnNumber++;


    //}


    //


    //}


    //


    //}


    //j++;


    //


    //}


    //


    //}


    //


    ////		    		System.out.println(rowNumber);


    ////			    	System.out.println(r);


    //


    //if (rowNumber >= 2) {


    //for (int i = 0; i < columnNumber; i++) {


    //


    //


    //


    ////                            Log.v("Test", String.valueOf(i));


    //


    //if (classes[i] != null){


    //if (classes[i].getCourseName() != null){


    //classList.add(classes[i]);


    //Log.v("Test",


    //classes[i].getCourseName() + "##" + classes[i].getClassStartTime()+"-"+classes[i].getClassEndTime() + "##" + classes[i].getClassDay() + "##" + classes[i].getClassRoom());


    //


    //}


    //


    //}


    //


    //


    //}


    //


    //System.out.println("\n\n");


    ////		    		if (rowNumber == 9) {


    ////		    			break;


    ////		    		}


    //}


    //


    //rowNumber++;


    //


    //}


    //


    ////		    System.out.println(text);


    //


    //} catch (FileNotFoundException e) {


    //// TODO Auto-generated catch block


    //e.printStackTrace();


    //} catch (IOException e) {


    //// TODO Auto-generated catch block


    //e.printStackTrace();


    //}


    //


    //return classList;


    //


    //}

    public static List<Class> getAllClasses(InputStream is) {

        if (classList == null) {
            classList = new ArrayList<>();
        } else {
            return classList;
        }


        String line = "";
//        String csvFile = "classes.csv";
        String cvsSplitBy = ",";
        String timeSplitBy = "-";
        String roomSplitBy = "\\$";

        BufferedReader reader = new BufferedReader(
                new InputStreamReader(is, Charset.forName("UTF-8")));

        try {

            while ((line = reader.readLine()) != null) {
                String[] days = {"Monday", "Tuesday", "Wednesday", "Thursday", "Friday"};
                String[] splits = line.split(cvsSplitBy);

                String courseName = splits[0];
                String shortName = splits[1];
                String section = splits[2];

                for (int i = 3; i < splits.length; i++) {
                    if (splits[i].isEmpty() == false) {
                        String day = days[i - 3];

                        String time = splits[i].split(roomSplitBy)[0];
                        String room = splits[i].split(roomSplitBy)[1];

                        String startTime = time.split(timeSplitBy)[0];
                        String endTime = time.split(timeSplitBy)[1];

                        //Day of week set according to 1.SUNDAY, 2.MONDAY, 3.TUESDAY, 4.WEDNESDAY, 5.THURSDAY, 6.FRIDAY, 7.SATURDAY
                        //Calendar logic
                        switch (day) {
                            case "Monday":
                                classList.add(new Class(courseName, shortName, section, day, 2, startTime, endTime, "10", room));
                                break;

                            case "Tuesday":
                                classList.add(new Class(courseName, shortName, section, day, 3, startTime, endTime, "10", room));
                                break;
                            case "Wednesday":
                                classList.add(new Class(courseName, shortName, section, day, 4, startTime, endTime, "10", room));
                                break;

                            case "Thursday":
                                classList.add(new Class(courseName, shortName, section, day, 5, startTime, endTime, "10", room));
                                break;

                            case "Friday":
                                classList.add(new Class(courseName, shortName, section, day, 6, startTime, endTime, "10", room));
                                break;
                        }


                    }
                }
            }
            return classList;
        } catch (IOException e) {
            e.printStackTrace();
        }

        return classList;
    }


    public static List<Class> getClassObjects(String courseName, String section, String shortname) {

        if (selectedClasses == null) {
            selectedClasses = new ArrayList<>();
        }


        List<Class> _classes = new ArrayList<>();

        for (int i = 0; i < classList.size(); i++) {


            if (classList.get(i).getCourseName() != null) {

                if (classList.get(i).getCourseSection().contains(section)) {

                    Log.v("SectionFound", classList.get(i).toString());

//AI: F first condition false
                    if ((classList.get(i).getCourseName().compareToIgnoreCase(courseName) >= 0) || classList.get(i).getCourseShortname().contains(shortname)) {
                        Log.v("CourseFound", "Class found");
                        _classes.add(classList.get(i));
                        selectedClasses.add(classList.get(i));
                    }


                }
            }


        }
        return _classes;
    }


    public static List<Class> getAllCourses(InputStream is) {

        if (allCourses == null) {
            allCourses = new ArrayList<>();
        } else {
            return allCourses;
        }

//        String csvFile = "courselist.csv";
        String line = "";
        String cvsSplitBy = ",";

        BufferedReader reader = new BufferedReader(
                new InputStreamReader(is, Charset.forName("UTF-8")));

        try {

            while ((line = reader.readLine()) != null) {

                // use comma as separator
                String[] splits = line.split(cvsSplitBy);
                String courseName = splits[0];
                String courseShortname = splits[1];

                for (int i = 2; i < splits.length; i++) {
                    String section = splits[i];

                    if (section.isEmpty() == false) {
                        Log.v("TimeTable", courseName + ":" + section + ":" + courseShortname);
                        allCourses.add(new Class(courseName, section, "", "", "", courseShortname));
                    } else {
                        break;
                    }

                }


            }

            return allCourses;
        } catch (IOException e) {
            e.printStackTrace();
        }

        return allCourses;
    }


}
