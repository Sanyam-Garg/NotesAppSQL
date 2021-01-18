package com.example.notessqlite;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class AddNoteActivity extends Activity implements View.OnClickListener {

    //Creating widgets
    private Button addTodoBtn;
    private EditText subjectEditText, descEditText;

    private DBManager dbManger;


    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setTitle("Add Record");
        setContentView(R.layout.activity_add_note);

        //Referring the widgets.
        subjectEditText = findViewById(R.id.subject_edittext);
        descEditText = findViewById(R.id.description_edittext);
        addTodoBtn = findViewById(R.id.add_record);

        dbManger = new DBManager(this);//When clicked on add note, open the database using the method in the DBManager.
        dbManger.open();
        addTodoBtn.setOnClickListener(this);

    }

    @Override
    public void onClick(View v) {
        switch(v.getId()){
            case R.id.add_record:                //If the id of the button matches the add record button, define string name and desc given in the edit texts and then add em to the database.
                final String name = subjectEditText.getText().toString();
                final String desc = descEditText.getText().toString();

                dbManger.insert(name, desc);//Saving it in the database.

                //After a new note is added and then saved in the database, take the user to the list view again.
                Intent main = new Intent(AddNoteActivity.this,
                        MainActivity.class).setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);

                startActivity(main);
                break;
        }
    }
}