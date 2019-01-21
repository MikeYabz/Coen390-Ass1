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

    protected boolean showGradesAsLetter = false; //true means letter grade view so false means number grade view
    ListView gradeList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ActionBar ab = getSupportActionBar();   //get the Action Bar object
        ab.setTitle("My Grades");   //set the title
        ab.setDisplayHomeAsUpEnabled(true); //enable UP button, parent is declared in the manifest
        setContentView(R.layout.activity_grade);

        //generate random number of courses;
        Random rand = new Random(); //
        int numberOfCourses = rand.nextInt(5);

        //generate and populate the course list
        ArrayList<Course> courses = new ArrayList<>();  //initialize course array list
        for(int j=0; j<numberOfCourses; j++) {
            Course generatedCourse = Course.generateRandomCourse();
            courses.add(generatedCourse);
        }

        for(int i=0;i<courses.size();i++)
        {
            Course tempCourse = courses.get(i);
            ArrayList<Assignment> tempCourseAssignments = tempCourse.getAssignments();
            String title = tempCourse.getCourseTitle();
            int average = getAssignmentAverage(tempCourseAssignments);
        }




        gradeList = (ListView)findViewById(R.id.gradeList);
        ArrayList<String> arrayList=new ArrayList<>();

        arrayList.add("android");
        arrayList.add("michael");

        ArrayAdapter arrayAdapter=new ArrayAdapter(this,android.R.layout.simple_list_item_1,arrayList);

        gradeList.setAdapter(arrayAdapter);
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


}
