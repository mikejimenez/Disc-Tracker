package android.post.testing;

import android.app.Activity;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.SimpleCursorAdapter;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Map;


public class make_course extends Activity {
    ListView listView;
    int counter = 0;
    int counter_ = 0;
    private int i;
    final String getCourses = "savedCourses";
    String TAG = "MAKE_COURSE";
    Map TestMap;
    View view;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_make_course);
        CreateListView();

    }

    private class MyData {
        int id;
        String name;
        String holes;
    }

    public void CreateListView() {

        listView = (ListView) findViewById(R.id.listView2);
        SQLite db = new SQLite(this);
        Cursor cursor = db.getCoursesRaw();
        ArrayList<MyData> listData = new ArrayList<MyData>();

        while (cursor.moveToNext()) {
            MyData temp = new MyData();
            temp.id = cursor.getInt(0);
            Log.d(TAG, "ID: " + temp.id);
            temp.name = cursor.getString(1);
            Log.d(TAG, "Name: " + temp.name);
            temp.holes = cursor.getString(2);
            Log.d(TAG, "Holes: " + temp.holes);
            listData.add(temp);
            Log.d(TAG, "String: " + temp.toString());
            temp = null;
        }
        SimpleCursorAdapter adapter = new SimpleCursorAdapter(this,
                android.R.layout.two_line_list_item,
                cursor,
                new String[]{"name", "holes"},
                new int[]{android.R.id.text1, android.R.id.text2},
                0);

        listView.setAdapter(adapter);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                int itemPosition = position;

                String itemValue = listView.getItemAtPosition(position).toString();

                if (itemPosition == 0) {
                    // TODO Add Stuff
                }
                // Show Alert *Debug*
                Toast.makeText(getApplicationContext(),
                        "Position :" + itemPosition + "  ListItem : " + itemValue, Toast.LENGTH_LONG)
                        .show();

            }

        });
    }

    @Override
    protected void onResume() {
        super.onResume();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.make_course, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if (id == R.id.action_plus) {
            Intent make_course = new Intent(getApplicationContext(), custom_course.class);
            startActivity(make_course);
        }
        return super.onOptionsItemSelected(item);
    }
}

//OLD CODE
//OLD CODE
//package android.post.testing;
//
//        import android.app.Activity;
//        import android.content.Intent;
//        import android.content.SharedPreferences;
//        import android.os.Bundle;
//        import android.preference.PreferenceManager;
//        import android.view.Menu;
//        import android.view.MenuItem;
//        import android.view.View;
//        import android.widget.AdapterView;
//        import android.widget.ArrayAdapter;
//        import android.widget.ListView;
//        import android.widget.Toast;
//
//
//public class make_course extends Activity {
//
//    ListView listView ;
//    SharedPreferences mPrefs;
//    int counter = 0;
//    private int array_courses[];
//    private int i;
//    final String getCourses = "savedCourses";
//
//    @Override
//    protected void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_make_course);
//    }
//    public void CreateListView() {
//
//        listView = (ListView) findViewById(R.id.listView);
//        mPrefs = PreferenceManager.getDefaultSharedPreferences(this);
//        String savedCourses = mPrefs.getString(getCourses, "Empty");
//        //.getBoolean(welcomeScreenShownPref, false);
//
//        String[] array_courses = new String[10];
//        for (i = 1;i < 11; i++) {
//            array_courses[counter++] = savedCourses[i];
//        }
//
//        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,
//                android.R.layout.simple_list_item_1, android.R.id.text1, array_courses);
//        listView.setAdapter(adapter);
//        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
//
//            @Override
//            public void onItemClick(AdapterView<?> parent, View view,
//                                    int position, long id) {
//
//                int itemPosition     = position;
//
//                String  itemValue    = (String) listView.getItemAtPosition(position);
//                if (itemPosition == 0) {
//                    Intent make_course = new Intent(getApplicationContext(),make_course.class);
//                    startActivity(make_course);
//                }
//                // Show Alert *Debug*
//                Toast.makeText(getApplicationContext(),
//                        "Position :" + itemPosition + "  ListItem : " + itemValue, Toast.LENGTH_LONG)
//                        .show();
//
//            }
//
//        });
//    }
//    @Override
//    protected void onResume() {
//        super.onResume();
//    }
//    @Override
//    public boolean onCreateOptionsMenu(Menu menu) {
//        getMenuInflater().inflate(R.menu.make_course, menu);
//
//        return true;
//    }
//    @Override
//    public boolean onOptionsItemSelected(MenuItem item) {
//        int id = item.getItemId();
//        if (id == R.id.action_plus) {
//            Intent make_course = new Intent(getApplicationContext(),custom_course.class);
//            startActivity(make_course);
//        }
//        return super.onOptionsItemSelected(item);
//    }
//}
