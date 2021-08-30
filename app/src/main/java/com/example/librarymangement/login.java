package com.example.librarymangement;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class login extends AppCompatActivity {
    Button btn_reg,btn_login;
    EditText edt_username,edt_password;
    DatabaseHelping databaseHelping;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        btn_reg=findViewById(R.id.btn_reg);
        btn_login=findViewById(R.id.btn_login);
        edt_username=findViewById(R.id.edt_username);
        edt_password=findViewById(R.id.edt_password);
        databaseHelping = new DatabaseHelping(this);
        btn_reg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i=new Intent(login.this,registation.class);
                startActivity(i);
                finish();


            }
        });
        btn_login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(validation()) {
                    boolean login_Verify = databaseHelping.loginUser(edt_username.getText().toString().trim(), edt_password.getText().toString().trim());
                    if (login_Verify) {
                        Toast.makeText(login.this, "Login Successful", Toast.LENGTH_LONG).show();
                        Intent intent = new Intent(login.this, management.class);
                        startActivity(intent);
                        finish();
                    } else {
                        Toast.makeText(login.this, "Login failed", Toast.LENGTH_LONG).show();
                        Intent intent = new Intent(login.this, registation.class);
                        startActivity(intent);
                        finish();
                    }
                }




            }
            boolean validation(){
                if(edt_username.getText().toString().trim().isEmpty() && edt_password.getText().toString().trim().isEmpty()){
                    Toast.makeText(login.this,"Enter valid input",Toast.LENGTH_LONG).show();
                    return false;
                }
                else
                    return true;


            }

        });




    }
}
