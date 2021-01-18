package com.example.notessqlite;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.SimpleCursorAdapter;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    private DBManager dbManger;//Instance of DBManager class created by the name of dbManager which will be later used to access it.
    private ListView listView;
    private SimpleCursorAdapter adapter;

    final String[] from = new String[]  {DatabseHelper._ID, DatabseHelper.SUBJECT, DatabseHelper.DESC};

    final int[] to = new int[] {R.id.id, R.id.title, R.id.desc};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        dbManger = new DBManager(this);
        dbManger.open();//Creating the database using the method in DBManager.

        Cursor cursor = dbManger.fetch();//Fetching the cursor containing the data.
        listView = findViewById(R.id.list_view);
        listView.setEmptyView(findViewById(R.id.empty));


        adapter = new SimpleCursorAdapter(this, R.layout.activity_view_record, cursor, from, to, 0);

        adapter.notifyDataSetChanged();
        listView.setAdapter(adapter);

        //Onclick listener for the list view.
        listView.setOnItemClickListener(new  AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long viewid) {
                //Referring the text views
                TextView idTextView = view.findViewById(R.id.id);
                TextView titleTextView = view.findViewById(R.id.title);
                TextView descTextView = view.findViewById(R.id.desc);

                String id = idTextView.getText().toString();
                String title = titleTextView.getText().toString();
                String desc = descTextView.getText().toString();

                Intent modify_intent = new Intent(getApplicationContext(), ModifyNoteActivity.class);//Intent that takes the user to the modify note activity in the following context.
                modify_intent.putExtra("title", title);
                modify_intent.putExtra("desc", desc);
                modify_intent.putExtra("id", id);

                startActivity(modify_intent);


            }
        });
    }

    //Creating a menu here.
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
                getMenuInflater().inflate(R.menu.menu, menu);
                return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        int id = item.getItemId();

        if(id == R.id.add_record){
            Intent add_mem = new Intent(this, AddNoteActivity.class);
            startActivity(add_mem);
        }

        return super.onOptionsItemSelected(item);
    }
}