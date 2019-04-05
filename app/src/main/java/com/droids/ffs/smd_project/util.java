package com.droids.ffs.smd_project;

import com.droids.ffs.smd_project.SQLite.Class;

import java.util.ArrayList;
import java.util.List;

public class util {

    /**
     *
     * @param classes
     * @param _class
     * @return List of classes with names removed
     */
    public static List<Class> removeSameCoursesDifferentSections(List<Class> classes, Class _class) {

        List<Class> updatedClasses = new ArrayList<>();

        for (int i = 0; i < classes.size(); i++) {
            if ((classes.get(i).getCourseName().matches(_class.getCourseName()) || classes.get(i).getCourseShortname().matches(_class.getCourseShortname())) == false) {
                updatedClasses.add(classes.get(i));
            }
        }

        return updatedClasses;
    }


//    public static List<Class> sortSelectedClassesOnTime(List<Class> classes){
//
//    }


}
