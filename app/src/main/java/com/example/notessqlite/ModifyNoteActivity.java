package com.example.notessqlite;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class ModifyNoteActivity extends Activity implements View.OnClickListener {

    private EditText titleText, descText;
    private Button updateBtn, deleteBtn;


    private long _id;
    private DBManager dbManager;
    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setTitle("Modify Record");
        setContentView(R.layout.activity_modify_note);

        dbManager = new DBManager(this);
        dbManager.open();

        titleText = findViewById(R.id.subject_edittext);
        descText = findViewById(R.id.description_edittext);

        updateBtn = findViewById(R.id.btn_update);
        deleteBtn = findViewById(R.id.btn_delete);

        Intent intent = getIntent();//Getting all the data passed from the main activity.
        String id = intent.getStringExtra("id");
        String name = intent.getStringExtra("title");
        String desc = intent.getStringExtra("desc");    //When the user clicks the button, the texts will get modified using the intent.

        _id = Long.parseLong(id);

        titleText.setText(name);
        descText.setText(desc);
        updateBtn.setOnClickListener(this);
        deleteBtn.setOnClickListener(this);


    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.btn_update://If the user clicks the update button, update the title and desc and save it in the database.
                String title = titleText.getText().toString();
                String desc = descText.getText().toString();

                dbManager.update(_id, title, desc);//Updates the title and desc for the corresponding id.
                this.returnHome();
                break;

            case R.id.btn_delete:
                dbManager.delete(_id);//Since title and desc are according to the id.
                this.returnHome();
                break;
        }
    }

    public void returnHome(){
        Intent home_intent = new Intent(getApplicationContext(), MainActivity.class).setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);//Go back to the main activity.
        startActivity(home_intent);
    }
}