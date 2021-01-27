package com.example.nehabeautysalon;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatButton;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.ScrollView;

import com.google.android.gms.safetynet.SafetyNet;
import com.google.android.gms.safetynet.SafetyNetApi;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;
import com.hbb20.CountryCodePicker;

public class Login extends AppCompatActivity {


    //Variables

    ScrollView scrollView;
    TextInputEditText phoneNumber;
    CountryCodePicker countryCodePicker;
    Button btnLogin;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_login);

        //Hooks
        scrollView=findViewById(R.id.login_screen);
        countryCodePicker = findViewById(R.id.country_code_picker);
        phoneNumber=findViewById(R.id.login_phone_number);
        btnLogin= findViewById(R.id.login_next_button);

        SafetyNet.getClient(this)
                .isVerifyAppsEnabled()
                .addOnCompleteListener(new OnCompleteListener<SafetyNetApi.VerifyAppsUserResponse>() {
                    @Override
                    public void onComplete(@NonNull Task<SafetyNetApi.VerifyAppsUserResponse> task) {
                        if (task.isSuccessful()) {
                            SafetyNetApi.VerifyAppsUserResponse result = task.getResult();
                            if (result.isVerifyAppsEnabled()){
                                Log.d("MY_APP_TAG", "The Verify Apps feature is enabled.");

                            }else {
                                Log.d("MY_APP_TAG","The Verify Apps feature is disabled.");
                            }
                        } else {
                            Log.e("MY_APP_TAG", "A general error occurred.");
                        }
                    }
                });


        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
      //     callOTPScreen();
                Intent intent= new Intent(Login.this, HomeActivity.class);
                startActivity(intent);
            }
        });


    }

    public void callOTPScreen (){

        //Validate fields

//        if (!validatePhoneNumber()){
//            return;
//        }else{
            //Validation succeeded and now move to next screen to verify phone number and save data

            //Get all values passed from previous screen using Intent
            String _getUserEnteredPhoneNumber = phoneNumber.getText().toString().trim(); //Get Phone Number
            String _phoneNo = "+"+countryCodePicker.getFullNumber()+_getUserEnteredPhoneNumber;

            Intent intent = new Intent(Login.this,Verification.class);

            //Pass all fields to the next activity
            intent.putExtra("phoneNo", _phoneNo);
            startActivity(intent);
      //  }



    }


    private boolean validatePhoneNumber() {
        return true;
    }
}