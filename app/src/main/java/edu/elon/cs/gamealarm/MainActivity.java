package edu.elon.cs.gamealarm;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Switch;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends Activity {

    private ListView listView;
    protected static List<Alarm> alarmArrayList;
    protected static int relevantPosition;
    protected static ArrayAdapter<Alarm> arrayAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        listView = (ListView) findViewById(R.id.alarmList);

        alarmArrayList = new ArrayList<Alarm>();

        //TODO: Remove - just for testing



        //TODO: code something that populates the array list with a file


        arrayAdapter = new ArrayAdapter<Alarm>(this,
                R.layout.program_list,R.id.listtextview,
                alarmArrayList);


        listView.setAdapter(arrayAdapter);

        listView.setOnItemClickListener(itemClickListener);
        listView.setOnItemLongClickListener(itemLongClickListener);

                    //add logic for re-ordering the list


    }

    AdapterView.OnItemClickListener itemClickListener = new AdapterView.OnItemClickListener() {
        @Override
        public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
            Alarm current = alarmArrayList.get(position);
            if (current.isOn()) {
                current.setOn(false);
            }
            else
                current.setOn(true);
        }
    };

    AdapterView.OnItemLongClickListener itemLongClickListener = new AdapterView.OnItemLongClickListener() {
        @Override
        public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {

            System.out.println("long click");
            relevantPosition = position;
            //Rachel: added null
            startEditOrDelete(null);
            return true;
        }
    };

    //Rachel changed this method to private and it now takes a view
    public void startEditOrDelete(View view){
        Intent intent = new Intent (this, EditOrDeleteActivity.class);
        startActivity(intent);
        arrayAdapter.notifyDataSetChanged();
    }

    public void onAddClick(View view){
        Intent intent = new Intent (this, AddAlarmActivity.class);
        intent.putExtra("action", "add");
        startActivity(intent);
    }

    // RACHEL DID THIS ADD IT TO THE NEW CODE CHANGES
    //NOTE: The switch only works if you CLICK on the widget specifically
    //YOU CANNOT SWIPE IT
    public void onSwitchClicked(View view) {
        boolean on = ((Switch) view).isChecked();

        if(on) {
            //code for if the switch is on to activate the alarms at the alotted time
            System.out.println("the switch is working");
        } else {
            //code that turns off the switch & the alarm
        }
    }
}


