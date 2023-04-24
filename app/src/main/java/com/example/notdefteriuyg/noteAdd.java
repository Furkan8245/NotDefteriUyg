package com.example.notdefteriuyg;

import android.app.AlertDialog;
import android.app.TimePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.CalendarView;
import android.widget.EditText;
import android.widget.TimePicker;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AlertDialogLayout;

import org.w3c.dom.Text;

import java.util.Locale;

public class noteAdd extends AppCompatActivity {
    CalendarView calendar;
    EditText edt_activity;
    Button btn_clock, btn_save;

    int hour,minute;
    int y, m, d,selecteDateid;
    String selecetedhour,selectedate;





    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_note_add);

        calendar= findViewById(R.id.calendar);
        edt_activity=findViewById(R.id.edit_plan);
        btn_clock=findViewById(R.id.clock_btn);
        btn_save=findViewById(R.id.btn_save);

        calendar.setOnDateChangeListener(new CalendarView.OnDateChangeListener() {
            @Override
            public void onSelectedDayChange(@NonNull CalendarView calendarView, int year, int month, int day) {

                y=year;
                d=day;
                m=month+1;
                String unified = String.valueOf(d)+String.valueOf(m)+String.valueOf(y);
                selecteDateid = Integer.valueOf(unified);
                selectedate = d+"/"+m+"/"+y;
            }
        });
    }
    public void popTimePicker(View view){
        TimePickerDialog.OnTimeSetListener onTimeSetListener = new TimePickerDialog.OnTimeSetListener() {
            @Override
            public void onTimeSet(TimePicker timePicker, int selecthour, int selectminute) {
                hour=selecthour;
                minute=selectminute;
                btn_clock.setText(String.format(Locale.getDefault(),  "%02d:%02d", hour,minute));
                selecetedhour = btn_clock.getText().toString();
            }
        };
        int style = AlertDialog.THEME_HOLO_LIGHT;
        TimePickerDialog timePickerDialog= new TimePickerDialog(this,style,onTimeSetListener,hour,minute,true);
        timePickerDialog.setTitle("You can set your clock.");
        timePickerDialog.show();

    }
    public void Save(View view){
        int noteid= Integer.valueOf(selecteDateid);
        String noteclock=selecetedhour;
        String notedate=selectedate;
        String activity= edt_activity.getText().toString();

        if (TextUtils.isEmpty(activity)){
            Toast.makeText(noteAdd.this, "Please enter activity", Toast.LENGTH_SHORT).show();
        }
        else{
            Note not=new Note();
            not.setId(noteid);
            not.setNotedate(notedate);
            not.setNoteclock(noteclock);

            not.setNote(activity);

            MainActivity.mydatabase.dao().noteAdd(not);
            Toast.makeText(noteAdd.this, "Your note showing the activities you will do daily has been created.", Toast.LENGTH_SHORT).show();

            edt_activity.setText("");
            btn_clock.setText("");


            Intent intent = new Intent(noteAdd.this, MainActivity.class);
            startActivity(intent);
        }
    }

}
