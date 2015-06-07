package android.post.testing;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import java.util.List;

public class custom_course extends Activity implements AdapterView.OnItemSelectedListener {

    private int i;
    int counter = 0;
    private Spinner spinner;
    SharedPreferences mPrefs;
    //                  OLD CODE
//    final String savedCourses = "savedCourses";
//    final String savedHoles = "savedHoles";
    EditText mEdit;
    Spinner mS;
    String TAG = "CUSTOM_MAKE";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_custom_course);

        String[] array_spinner = new String[100];
        for (i = 1; i < 101; i++) {
            array_spinner[counter++] = String.valueOf(i);
        }

        this.spinner = (Spinner) findViewById(R.id.spinner_holes);
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, array_spinner);
        spinner.setAdapter(adapter);
        spinner.setOnItemSelectedListener(this);
        spinner.setSelection(17);
    }

    @Override
    protected void onResume() {
        super.onResume();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

        getMenuInflater().inflate(R.menu.custom_course, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        mEdit = (EditText) findViewById(R.id.course_name);
        mS = (Spinner) findViewById(R.id.spinner_holes);
        mPrefs = PreferenceManager.getDefaultSharedPreferences(this);

        Integer SH = mS.getSelectedItemPosition()+1;
        String SHH = String.valueOf(SH);
        String CN = mEdit.getText().toString();
        Log.d(TAG, "Saved Holes Value: " + SH + "");
        Log.d(TAG, "Saved Courses Value: " + CN + "");

        int id = item.getItemId();
        Log.d(TAG, "ID Value: " + id + "");
        if (id == R.id.save_course) {
            Log.d(TAG, "Made it here..");
            SQLite db = new SQLite(this);
            db.addCourses(new Courses(CN, SHH));
           // TODO REPLACE WITH NEXT SCREEN WHICH WOULD BE SCORE CARD. NYI!
//            MyActivity go = new MyActivity();
//            go.hideKeyboard();
            Intent back = new Intent(this, make_course.class);
            startActivity(back);
//            Debug_Write();
//
//                      OLD CODE
//            SharedPreferences.Editor editor = mPrefs.edit();
//            editor.putString(savedCourses, CN);
//            editor.putInt(savedHoles, SH);
//            editor.commit();
            Log.d(TAG, "Settings WROTE");
            finish();
        }
        return super.onOptionsItemSelected(item);
    }

    public void onItemSelected(AdapterView<?> parent, View view,
                               int pos, long id) {
        Spinner s = (Spinner) findViewById(R.id.spinner_holes);
        // An item was selected. You can retrieve the selected item using
        Toast.makeText(parent.getContext(),
                "OnItemSelectedListener : " + parent.getItemAtPosition(pos).toString(),
                Toast.LENGTH_SHORT).show();
    }

    public void Debug_Write() {
        // Reading all courses
        Log.d(TAG, "Reading all courses..");
        SQLite db = new SQLite(this);
        List<Courses> courses = db.getAllCourses();

        for (Courses cn : courses) {
            String log = "Id: " + cn.getID() + " ,Name: " + cn.getName() + " ,Holes: " + cn.getHoles();
            // Writing Courses to log
            Log.d(TAG, log);
        }
    }

    public void onNothingSelected(AdapterView<?> parent) {
        // Another interface callback
    }
}
