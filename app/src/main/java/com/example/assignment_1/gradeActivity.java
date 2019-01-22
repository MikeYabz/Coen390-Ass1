package com.example.assignment_1;

import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.Random;

public class gradeActivity extends AppCompatActivity {

    protected static boolean showGradesAsLetter = false; //true means letter grade view so false means number grade view
    ArrayList<Course> courses = new ArrayList<>();  //initialize course array list
    ListView gradeList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ActionBar ab = getSupportActionBar();   //get the Action Bar object
        ab.setTitle("My Grades");   //set the title
        ab.setDisplayHomeAsUpEnabled(true); //enable UP button, parent is declared in the manifest
        setContentView(R.layout.activity_grade);
        gradeList = (ListView)findViewById(R.id.gradeList);

        //Generate Random Number of Courses;
        Random rand = new Random(); //
        int numberOfCourses = rand.nextInt(5);

        //Generate and Populate The Course List
        for(int j=0; j<numberOfCourses; j++) {
            Course generatedCourse = Course.generateRandomCourse();
            courses.add(generatedCourse);
        }

        //Print Grades and Populate the grade list
        printGrades();
    }

    //Creates ActionBar Menu
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_grades,menu);
        return super.onCreateOptionsMenu(menu);
    }

    //Handles action button
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.action_switchGrade:
                showGradesAsLetter = !showGradesAsLetter;   //flip the variable, if true becomes false and if false becomes true
                printGrades();
                /*
                if (showGradesAsLetter == true){
                    printGradesAsLetter();
                }
                else
                {
                    printGradesAsNumber();
                }
                */
                return true;

            default:
                // If we got here, the user's action was not recognized.
                // Invoke the superclass to handle it.
                return super.onOptionsItemSelected(item);
        }
    }

    private int getAssignmentAverage(ArrayList<Assignment> inputAssignmentList)
    {
        //Calculate grade average by summing all the grades the diving by the number of grades to ge the mean
        int average = 0;
        int numberOfAssignments = inputAssignmentList.size();
        for (int j=0;j<numberOfAssignments;j++)
        {
            average += inputAssignmentList.get(j).getAssignmentGrade();   //sum all the assignment grades
        }
        if (average != 0)   //prevents dividing by 0 if theres no assignments
        {
            average = average / numberOfAssignments;
        }
        return average;
    }

    private String convertGradeToLetter(int grade){
        if(grade < 50){
            return "F"; }
        else if(grade < 53){
            return "D-"; }
        else if(grade < 56){
            return "D"; }
        else if(grade < 60){
            return "D+"; }
        else if(grade < 63){
            return "C-"; }
        else if(grade < 66){
            return "C"; }
        else if(grade < 70){
            return "C+"; }
        else if(grade < 75){
            return "B-"; }
        else if(grade < 78){
            return "B"; }
        else if(grade < 81){
            return "B+"; }
        else if(grade < 85){
            return "A-"; }
        else if(grade < 90){
            return "A"; }
        else if(grade <= 100){
            return "A+"; }
        else return "Grade Out Of Range, Something's Wrong, Very Wrong";
    }

    private void printGrades(){
        ArrayList<String> arrayList=new ArrayList<>();
        if (courses.size() == 0) {  //check if no courses were generated
            arrayList.add("No Courses Present");
        }
        else {
            for (int i = 0; i < courses.size(); i++) {  //iterate through the courses to process one at a time
                Course tempCourse = courses.get(i); //get a Course object to work with
                ArrayList<Assignment> tempCourseAssignments = tempCourse.getAssignments();  //get Assignment list from tempCourse Object
                String title = tempCourse.getCourseTitle(); //get course title
                arrayList.add(tempCourse.getCourseTitle());
                if (tempCourseAssignments.size() == 0) {
                    arrayList.add("No Assignments Yet");
                    continue;
                }
                int average = getAssignmentAverage(tempCourseAssignments);  //get course average
                arrayList.add("Average: " + Integer.toString(getAssignmentAverage(tempCourse.getAssignments())));
                for (int j = 0; j < tempCourseAssignments.size(); j++) {
                    arrayList.add(tempCourseAssignments.get(j).getAssignmentTitle());
                    if (showGradesAsLetter == true){
                        arrayList.add("Assignment Grade : " + convertGradeToLetter(tempCourseAssignments.get(j).getAssignmentGrade()));
                    }
                    else{
                        arrayList.add("Assignment Grade : " + Integer.toString(tempCourseAssignments.get(j).getAssignmentGrade()));
                    }
                }
            }
        }
        ArrayAdapter arrayAdapter=new ArrayAdapter(this,android.R.layout.simple_list_item_1,arrayList);
        gradeList.setAdapter(arrayAdapter);
    }
}
