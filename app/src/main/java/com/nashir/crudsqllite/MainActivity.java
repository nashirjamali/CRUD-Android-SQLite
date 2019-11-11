package com.nashir.crudsqllite;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.database.Cursor;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    DataHelper db;
    EditText etName, etAddress;
    Button btnCreate, btnRead;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        db = new DataHelper(this);
        etName = findViewById(R.id.etName);
        etAddress = findViewById(R.id.etAlamat);
        btnCreate = findViewById(R.id.btnCreate);
        btnRead = findViewById(R.id.btnRead);

        btnCreate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                addData();
            }
        });

        btnRead.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                readData();
            }
        });

    }

    public void addData(){
        boolean isInserted =db.insertData(etName.getText().toString(), etAddress.getText().toString());
        if (isInserted){
            Toast.makeText(MainActivity.this,"Data Ditambahkan", Toast.LENGTH_LONG).show();
        }else {
            Toast.makeText(MainActivity.this,"Data Gagal Ditambahkan", Toast.LENGTH_LONG).show();
        }
    }

    public void readData(){
        Cursor res = db.getData();
        if (res.getCount() == 0){
            showMessage("Error", "Nothing Found");
        }

        StringBuffer buffer = new StringBuffer();

        while (res.moveToNext()){
            buffer.append("Name :"+ res.getString(1)+"\n");
            buffer.append("Address :"+ res.getString(2)+"\n");
        }

        showMessage("Data",buffer.toString());
    }

    public void showMessage(String title, String Message){

        AlertDialog.Builder builder = new AlertDialog.Builder(this);

        builder.setCancelable(true);

        builder.setTitle(title);

        builder.setMessage(Message);

        builder.show();

    }

}
