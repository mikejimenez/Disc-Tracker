package android.post.testing;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.provider.Settings;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;
import android.location.*;

public class MyActivity extends Activity {

    EditText mEdit;
    ListView mainListView;
    ListView listView;
    Switch mSwitch;
    SharedPreferences mPrefs;
    TextView mTextView;

    final String welcomeScreenShownPref = "welcomeScreenShown";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my);
        SQLite db = new SQLite(this);
    //db.onDrop();
        CreateListView();
        checkGPS();

//        mButton = (Button) findViewById(R.id.btnSubmit);
//        mEdit = (EditText) findViewById(R.id.txtPassword);
        mainListView = (ListView) findViewById(R.id.listView1);
        mSwitch = (Switch) findViewById(R.id.record);
        mTextView = (TextView) findViewById(R.id.textView3);

//        mButton.setOnClickListener(
//                new View.OnClickListener() {
//                    public void onClick(View view) {
//                        submitResponse();
//                    }
//                }
//        );

        mSwitch.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    //mTextView.setText("Switch is currently ON");
                    Intent MapsActivity = new Intent(getApplicationContext(), MapsActivity.class);
                    startActivity(MapsActivity);
                } else {
                    // mTextView.setText("Switch is currently OFF");
                }

            }
        });
        if (mSwitch.isChecked()) {
            // mTextView.setText("Switch is currently ON");
        } else {
            //mTextView.setText("Switch is currently OFF");
        }
    }


    public void CreateListView() {

        listView = (ListView) findViewById(R.id.listView1);

        String[] values = new String[]{
                "Create Course",
                "Recent Course"
        };

        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,
                android.R.layout.simple_list_item_1, android.R.id.text1, values);
        listView.setAdapter(adapter);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> parent, View view,
                                    int position, long id) {

                int itemPosition = position;

                String itemValue = (String) listView.getItemAtPosition(position);
                if (itemPosition == 0) {
                    Intent make_course = new Intent(getApplicationContext(), make_course.class);
                    startActivity(make_course);
                }
                if (itemPosition == 1) {
                    Intent make_course = new Intent(getApplicationContext(), make_course.class);
                    startActivity(make_course);
                }
                // Show Alert *Debug*
                Toast.makeText(getApplicationContext(),
                        "Position :" + itemPosition + "  ListItem : " + itemValue, Toast.LENGTH_LONG)
                        .show();

            }

        });
    }

    public void checkGPS() {
        LocationManager service = (LocationManager) getSystemService(Context.LOCATION_SERVICE);
        boolean enabled = service.isProviderEnabled(LocationManager.GPS_PROVIDER);

        if (!enabled) {
            alertGPS();
        } else {
            Integer GPS = 1;
        }
    }

    public void alertGPS() {
        AlertDialog.Builder alertDialog = new AlertDialog.Builder(this);
        alertDialog.setIcon(R.drawable.ic_launcher);
        alertDialog.setTitle("This application requires: GPS");
        alertDialog.setMessage("Do you want to go to Location settings?");
        alertDialog.setPositiveButton("yes", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int which) {
                startActivity(new Intent(Settings.ACTION_LOCATION_SOURCE_SETTINGS));
            }
        });
        alertDialog.setNegativeButton("no", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int which) {
                close();
            }
        });
        alertDialog.show();
    }

    public void close() {
        AlertDialog.Builder alertDialog = new AlertDialog.Builder(this);
        alertDialog.setIcon(R.drawable.ic_launcher);
        alertDialog.setTitle("Please Turn on GPS");
        alertDialog.setMessage("Thank you");
        alertDialog.setPositiveButton("Ok", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int which) {
                startActivity(new Intent(Settings.ACTION_LOCATION_SOURCE_SETTINGS));
            }
        });
        alertDialog.setNegativeButton("no", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int which) {
                close();
                dialog.cancel();
                android.os.Process.killProcess(android.os.Process.myPid());
            }
        });
        alertDialog.show();
    }

    public void hideKeyboard() {
        InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
        imm.hideSoftInputFromWindow(mEdit.getWindowToken(), 0);
    }

    @Override
    protected void onResume() {
        super.onResume();
        mSwitch.setChecked(false);
    }

    @Override
    protected void onStop() {
        super.onStop();
        mPrefs = PreferenceManager.getDefaultSharedPreferences(this);

        SharedPreferences.Editor editor = mPrefs.edit();
        editor.putBoolean(welcomeScreenShownPref, false);
        editor.commit(); // Very important to save the preference

    }
//    @Override
//    public boolean onCreateOptionsMenu(Menu menu) {
//        // Inflate the menu; this adds items to the action bar if it is present.
//        getMenuInflater().inflate(R.menu.my, menu);
//        return true;
//    }
//
//    @Override
//    public boolean onOptionsItemSelected(MenuItem item) {
//        // Handle action bar item clicks here. The action bar will
//        // automatically handle clicks on the Home/Up button, so long
//        // as you specify a parent activity in AndroidManifest.xml.
//        int id = item.getItemId();
//        if (id == R.id.action_settings) {
//            return true;
//        }
//        return super.onOptionsItemSelected(item);
//    }
    //
//    public void submitResponse() {
//        if (mEdit.getText().toString().trim().length() > 0) {
//            mNameList.add(mEdit.getText().toString());
//            mArrayAdapter.notifyDataSetChanged();
//            Toast.makeText(this, "" + mEdit.getText().toString() + "", Toast.LENGTH_LONG).show();
//            mEdit.setText("");
//            hideKeyboard();
//        } else {
//            Toast.makeText(this, "Please enter a name", Toast.LENGTH_LONG).show();
//        }
//    }
}


