package com.example.nehabeautysalon;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.google.android.material.textfield.TextInputLayout;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class SignUp extends AppCompatActivity {

    //Variables
    TextInputLayout regFirstName, regLastName, regContactNo, regEmail, regPassword;
    Button regAccount, regLoginBtn;

    FirebaseDatabase rootNode;
    DatabaseReference reference;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);

        Log.d("Reference",String.valueOf(reference));


        //Hooks to all xml elements in activity_sign_up.xml

        regFirstName = findViewById(R.id.firstname);
        regLastName = findViewById(R.id.lastname);
        regContactNo = findViewById(R.id.contactno);
        regEmail = findViewById(R.id.email);
        regPassword = findViewById(R.id.password);
        regAccount = findViewById(R.id.createaccount);
        regLoginBtn = findViewById(R.id.login);



        //Save data in Firebase on button click

        regAccount.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                rootNode = FirebaseDatabase.getInstance();
                //    reference = rootNode.getReference("courses");
                reference = rootNode.getReference("users");

                //Get all the values
                String firstname =regFirstName.getEditText().getText().toString();
                String lastname =regLastName.getEditText().getText().toString();
                String contactno =regContactNo.getEditText().getText().toString();
                String email=regEmail.getEditText().getText().toString();
                String password=regPassword.getEditText().getText().toString();


                //Map<String, Object> updates = new HashMap<String, Object>();

                //updates.put("FirstName", firstname);
                //updates.put("LastName", lastname);
                //updates.put("ContactNumber", contactno);
                //updates.put("Email", email);
                //updates.put("Password", password);

                // reference.updateChildren(updates);

                UserHelperClass helperClass = new UserHelperClass(firstname, lastname, contactno, email,password);

                //Contact number will be user id...primary key...this is unique for every user
                //Use contact number to fetch details of any particular user
                reference.child(contactno).setValue(helperClass);

                Toast.makeText(SignUp.this,"Successfully Signed In",Toast.LENGTH_LONG).show();

            }
        });//Registration Button Method End

        //Login Activity
        regLoginBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(SignUp.this,Login.class);
                startActivity(intent);
            }
        });
    }//OnCreate Method End
}