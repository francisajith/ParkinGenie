package com.example.parkinGenie;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class UserRegistration extends AppCompatActivity {




    String role;
    EditText email, firstName, lastName, password, confirm, telephone;
    Button continuebtn;
    DatabaseHelper user;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.user_registration1);

        user = new DatabaseHelper(this);

        email = (EditText) findViewById(R.id.txtEmail);
        firstName = (EditText) findViewById(R.id.txtFName);
        lastName = (EditText) findViewById(R.id.txtLName);
        password = (EditText) findViewById(R.id.txtCreatePass);
        confirm = (EditText) findViewById(R.id.txtConfPass);
        telephone = (EditText) findViewById(R.id.txtPhone);
        role = "Customer";

        //continue button
        continuebtn = (Button) findViewById(R.id.btnContReg);
        continuebtn.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                String email_S = email.getText().toString();
                String firstName_S = firstName.getText().toString();
                String lastName_S = lastName.getText().toString();
                String password_S = password.getText().toString();
                String confirm_S = confirm.getText().toString();
                String telephone_S = telephone.getText().toString();
                String role_S = role;
                if (email_S.equals("") || password_S.equals("") || confirm_S.equals(""))
                    Toast.makeText(getApplicationContext(), "Fields are empty!", Toast.LENGTH_SHORT).show();
                else{
                    if(password_S.equals(confirm_S)){
                        Boolean checkEmail = user.checkEmail(email_S);
                        if(checkEmail){
                            Boolean insert = user.insert(firstName_S,lastName_S,email_S,password_S, telephone_S,role_S);
                            if(insert){
                                Toast.makeText(getApplicationContext(), "Account Registered!", Toast.LENGTH_SHORT).show();
                            }
                        }
                        else{
                            Toast.makeText(getApplicationContext(), "Account already Exists!", Toast.LENGTH_SHORT).show();
                        }
                    }
                }
            }
        });
    }
}



