package com.example.nehabeautysalon;

import android.app.DatePickerDialog;
import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.DatePicker;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.widget.AppCompatButton;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import com.google.android.material.textfield.TextInputEditText;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Locale;
import java.util.Objects;

public class ProfileFragment extends Fragment {

    ImageView bithdayCalendar;
    Calendar myCalendar;
    DatePickerDialog.OnDateSetListener date;
    AppCompatButton btnSubmit;
    SharedPreferences sharedPreferences;
    TextInputEditText userName, dateOfBirth, phoneNumber;
    Spinner addressSpinner;
    private RadioGroup radioGroup;
    private RadioButton radioButton;
    int spinnerPosition;

    private static final String SHARED_PREF_NAME = "mypref";
    private static final String KEY_NAME = "name";
    private static final String KEY_DOB = "dob";
    private static final String KEY_GENDER = "gender";
    private static final String KEY_MOBILE_NUMBER = "mobileNumber";
    private static final String KEY_ADDRESS = "address";

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_profile, container, false);
        bithdayCalendar = view.findViewById(R.id.birthday_calendar);
        dateOfBirth = view.findViewById(R.id.date_of_birth);
        btnSubmit = view.findViewById(R.id.btnSubmit);
        userName = view.findViewById(R.id.login_phone_number);
        phoneNumber = view.findViewById(R.id.user_phoneNumber);
        addressSpinner = view.findViewById(R.id.spinner1);


        sharedPreferences = Objects.requireNonNull(getActivity()).getSharedPreferences(SHARED_PREF_NAME, Context.MODE_PRIVATE);


        myCalendar = Calendar.getInstance();

        date = new DatePickerDialog.OnDateSetListener() {

            @Override
            public void onDateSet(DatePicker view, int year, int monthOfYear,
                                  int dayOfMonth) {
                // TODO Auto-generated method stub
                myCalendar.set(Calendar.YEAR, year);
                myCalendar.set(Calendar.MONTH, monthOfYear);
                myCalendar.set(Calendar.DAY_OF_MONTH, dayOfMonth);
                updateLabel();
            }

        };


        bithdayCalendar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new DatePickerDialog(getActivity(), date, myCalendar
                        .get(Calendar.YEAR), myCalendar.get(Calendar.MONTH),
                        myCalendar.get(Calendar.DAY_OF_MONTH)).show();
            }
        });

        btnSubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String compareValue = "some value";
                ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(getActivity(), R.array.Areatype_arrays, android.R.layout.simple_spinner_item);
                adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                addressSpinner.setAdapter(adapter);
                if (compareValue != null) {
                    spinnerPosition = adapter.getPosition(compareValue);
                    addressSpinner.setSelection(spinnerPosition);
                }

                SharedPreferences.Editor editor = sharedPreferences.edit();
                editor.putString(KEY_NAME, Objects.requireNonNull(userName.getText()).toString());
                editor.putString(KEY_DOB, Objects.requireNonNull(dateOfBirth.getText()).toString());
                editor.putString(KEY_MOBILE_NUMBER, Objects.requireNonNull(phoneNumber.getText()).toString());
                editor.putString(KEY_ADDRESS, addressSpinner.getSelectedItem().toString());
                editor.apply();

                Fragment selectedFragment;
                Bundle arguments = new Bundle();
                selectedFragment = HomeFragment.newInstance();
                selectedFragment.setArguments(arguments);
                FragmentTransaction transaction = requireActivity().getSupportFragmentManager().beginTransaction();
                transaction.setCustomAnimations(R.anim.enter_from_right, R.anim.exit_to_left);
                transaction.addToBackStack("ProfileFragment");
                transaction.replace(R.id.fragment_container, selectedFragment);
                transaction.commit();

                Toast.makeText(getActivity(), "Info Updated Successfully", Toast.LENGTH_LONG).show();
            }
        });
        return view;
    }

    private void updateLabel() {
        String myFormat = "yyyy-MM-dd"; //In which you need put here
        SimpleDateFormat sdf = new SimpleDateFormat(myFormat, Locale.US);

        dateOfBirth.setText(sdf.format(myCalendar.getTime()));
    }

    @Override
    public void onStart() {
        super.onStart();
        String name = sharedPreferences.getString(KEY_NAME, null);
        String dateOFBirth = sharedPreferences.getString(KEY_DOB, null);
        String phoneNo = sharedPreferences.getString(KEY_MOBILE_NUMBER, null);
        String address = sharedPreferences.getString(KEY_ADDRESS, null);

        userName.setText(name);
        dateOfBirth.setText(dateOFBirth);
        phoneNumber.setText(phoneNo);
        addressSpinner.setSelection(spinnerPosition);

    }
}
