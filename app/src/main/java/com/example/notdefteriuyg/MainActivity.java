package com.example.notdefteriuyg;

import android.arch.persistence.room.Room;
import android.content.ContentResolver;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Adapter;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    public static database mydatabase;
    ListView list_view;

    int images[] = {R.drawable.ic_dr};



    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        list_view=findViewById(R.id.list_view_a);

        mydatabase= Room.databaseBuilder(getApplicationContext(),database.class, "notedb").allowMainThreadQueries().build();

        Button btn_noteAdd = findViewById(R.id.btnoteAdd);

        btn_noteAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent =new Intent(getApplicationContext(), noteAdd.class);
                startActivity(intent);
            }
        });

        List<Note> notes = MainActivity.mydatabase.dao().getNote();
        ArrayList arrayList = new ArrayList();

        ArrayList n = new ArrayList();
        ArrayList c = new ArrayList();
        ArrayList d = new ArrayList();



        String data = "";

        for(Note nt : notes){
            int id = nt.getId();
            String ntclock = nt.getNoteclock();
            String ntdate = nt.getNotedate();
            String notte = nt.getNote();

            data = data + notte + ntclock + ntdate;
            arrayList.add(data);
            data="";

            n.add(notte);
            c.add(ntclock);
            d.add(ntdate);



        }
        MyAdapter adapter = new MyAdapter(this, n, c, d, images );
        list_view.setAdapter(adapter);

        ArrayAdapter arrayAdapter= new ArrayAdapter(this, android.R.layout.simple_list_item_1, arrayList);

        list_view.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long l) {
                AlertDialog.Builder dialogBuilder = new AlertDialog.Builder(MainActivity.this);


                long dataId = arrayAdapter.getItemId(position);
                String listId = String.valueOf(dataId);



                dialogBuilder.setMessage("Are you sure you want to delete the note?").setCancelable(false)
                        .setPositiveButton("Evet", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {
                                dletedata(listId);
                            }
                        }).setNegativeButton("Hayır", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {
                                dialogInterface.dismiss();
                            }
                        });
                dialogBuilder.create().show();
            }
        });

    }
    private void dletedata(String listId){
        List<Note> notesdelete = MainActivity.mydatabase.dao().getNote();
        ArrayList arrayListDelete = new ArrayList();

        int datas;

        for (Note deleteList :notesdelete){
            int id = deleteList.getId();

            datas = id;
            arrayListDelete.add(datas);
            datas = 0;
        }
        String Sid = listId;
        int id = Integer.valueOf(Sid);

        int element = (int) arrayListDelete.get(id);
        Note note = new Note();

        note.setId(element);

        MainActivity.mydatabase.dao().noteDelete(note);
        Toast.makeText(getApplicationContext(), "Your grade has been deleted", Toast.LENGTH_SHORT).show();

        finish();
        startActivity(getIntent());

    }
    class MyAdapter extends ArrayAdapter<String>{
        Context context;
        ArrayList rNote;
        ArrayList rClock;
        ArrayList rDate;
        int rImgs[];

        MyAdapter (Context c, ArrayList note, ArrayList clock, ArrayList date, int imgs[]) {
            super(c, R.layout.custom_view, R.id.txt_note, note);
            this.context = c;
            this.rNote = note;
            this.rClock = clock;
            this.rDate = date;
            this.rImgs = images;
        }

        @NonNull
        @Override
        public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
            LayoutInflater layoutInflater =(LayoutInflater)  getApplicationContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            View row = layoutInflater.inflate(R.layout.custom_view, parent, false);
            ImageView images = row.findViewById(R.id.Imge);
            TextView myNote = row.findViewById(R.id.txt_note);
            TextView myClock = row.findViewById(R.id.saat_txt);
            TextView myDate = row.findViewById(R.id.tarih_txt);

            images.setImageResource(rImgs[0]);
            myNote.setText((CharSequence) rNote.get(position));
            myClock.setText((CharSequence) rClock.get(position));
            myDate.setText((CharSequence) rDate.get(position));



            return row;
        }
    }

}
