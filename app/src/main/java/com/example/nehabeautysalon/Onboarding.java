package com.example.nehabeautysalon;

import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.ViewPager;

import android.content.Intent;
import android.os.Bundle;
import android.text.Html;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

public class Onboarding extends AppCompatActivity {


    private ViewPager mSlideViewPager;
    private LinearLayout mdotLayout;

    private TextView[] mDots;

    private SliderAdapter sliderAdapter;

    private Button mNextBtn;
    private Button mBackBtn;

    private int mCurrentPage;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_onboarding);


        mSlideViewPager=(ViewPager) findViewById(R.id.SlideViewPager);
        mdotLayout=(LinearLayout) findViewById(R.id.dotLayout);


        mNextBtn = (Button) findViewById(R.id.nextbtn);
        mBackBtn = (Button) findViewById(R.id.prebtn);

        sliderAdapter = new SliderAdapter(this);
        mSlideViewPager.setAdapter(sliderAdapter);

        addDotsIndicator(0);
        mSlideViewPager.addOnPageChangeListener(viewListener);

        //OnClickListeners
        mNextBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Onboarding.this, SignUp.class);
                startActivity(intent);
            }
        });

        mBackBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                mSlideViewPager.setCurrentItem(mCurrentPage - 1);


            }
        });

    }

    public void addDotsIndicator(int position){
        mDots = new TextView[3];
        mdotLayout.removeAllViews();
        for (int i = 0; i < mDots.length; i++){

            mDots[i] = new TextView(this);
            mDots[i].setText(Html.fromHtml("&#8226;"));
            mDots[i].setTextSize(35);
            mDots[i].setTextColor(getResources().getColor(R.color.TransparentWhite));

            mdotLayout.addView(mDots[i]);

        }

        if (mDots.length > 0){
            mDots[position].setTextColor(getResources().getColor(R.color.black));
        }

    }

    ViewPager.OnPageChangeListener viewListener = new ViewPager.OnPageChangeListener() {
        @Override
        public void onPageScrolled(int i, float v, int i1) {

        }

        @Override
        public void onPageSelected(int i) {

            addDotsIndicator(i);

            mCurrentPage = i;

            if (i == 0){

                mNextBtn.setEnabled(true);
                mBackBtn.setEnabled(false);
                mBackBtn.setVisibility(View.INVISIBLE);

                mNextBtn.setText("NEXT");
                mBackBtn.setText("");

            }else if (i == mDots.length -1 ){

                mNextBtn.setEnabled(true);
                mBackBtn.setEnabled(true);
                mBackBtn.setVisibility(View.VISIBLE);

                mNextBtn.setText("SIGN UP");
                mBackBtn.setText("BACK");

            } else {

                mNextBtn.setEnabled(true);
                mBackBtn.setEnabled(true);
                mBackBtn.setVisibility(View.VISIBLE);

                mNextBtn.setText("NEXT");
                mBackBtn.setText("BACK");

            }


        }

        @Override
        public void onPageScrollStateChanged(int i) {

        }
    };

}