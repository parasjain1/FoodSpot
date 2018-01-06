package com.starks.foodspots;

import android.app.Activity;
import android.app.Dialog;
import android.app.DialogFragment;
import android.app.Fragment;
import android.app.TimePickerDialog;
import android.icu.util.Calendar;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.RequiresApi;
import android.support.v4.app.FragmentActivity;
import android.text.format.DateFormat;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.ViewFlipper;

import static android.R.attr.format;

/**
 * Created by Shashank on 04-Jan-18.
 */

public class CreateFoodspotActivity  extends Activity{
    private ViewFlipper viewFlipper;
    private float lastX;
    EditText editName,editContact,editDiscription,editOpen,editClose;
    Spinner spinPref;
    static EditText openEdit,closeEdit;
    Button btnNext,btnPref;
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.create_foodspot_activity);
        viewFlipper = (ViewFlipper) findViewById(R.id.view_flipper);
        openEdit = (EditText) findViewById(R.id.openTime);
        closeEdit = (EditText) findViewById(R.id.closingTime);
        openEdit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showTruitonTimePickerDialog(v);
            }
        });
        closeEdit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                TruitonTimePickerDialog(v);
            }
        });
        iniView();
    }
    void iniView(){
        viewFlipper = (ViewFlipper) findViewById(R.id.view_flipper);
        editDiscription=(EditText) findViewById(R.id.EditTextDiscription);
        editName=(EditText) findViewById(R.id.EditTextName);
        editContact=(EditText) findViewById(R.id.EditTextContact);
        editClose=(EditText) findViewById(R.id.closingTime);
        editOpen=(EditText) findViewById(R.id.openTime);
        spinPref=(Spinner) findViewById(R.id.spinnerPreference);
        btnNext=(Button) findViewById(R.id.nextButton);
        btnPref=(Button) findViewById(R.id.previousButton);
    }
    public void TruitonTimePickerDialog(View v) {
        DialogFragment newFragment = new ClosePickerFragment();
        newFragment.show(getFragmentManager(), "timePicker");
    }
    public void showTruitonTimePickerDialog(View v) {
        DialogFragment newFragment = new TimePickerFragment();
        newFragment.show(getFragmentManager(), "timePicker");
    }
    public static class ClosePickerFragment extends DialogFragment implements
            TimePickerDialog.OnTimeSetListener {

        @RequiresApi(api = Build.VERSION_CODES.N)
        @Override
        public Dialog onCreateDialog(Bundle savedInstanceState) {
            // Use the current time as the default values for the picker
            final Calendar c = Calendar.getInstance();
            int hour = c.get(Calendar.HOUR_OF_DAY);
            int minute = c.get(Calendar.MINUTE);

            // Create a new instance of TimePickerDialog and return it
            return new TimePickerDialog(getActivity(), this, hour, minute,
                    DateFormat.is24HourFormat(getActivity()));
        }

        public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
            // Do something with the time chosen by the user
            closeEdit.setText(hourOfDay + ":" + minute);
        }
    }

    public static class TimePickerFragment extends DialogFragment implements
            TimePickerDialog.OnTimeSetListener {

        @RequiresApi(api = Build.VERSION_CODES.N)
        @Override
        public Dialog onCreateDialog(Bundle savedInstanceState) {
            // Use the current time as the default values for the picker
            final Calendar c = Calendar.getInstance();
            int hour = c.get(Calendar.HOUR_OF_DAY);
            int minute = c.get(Calendar.MINUTE);

            // Create a new instance of TimePickerDialog and return it
            return new TimePickerDialog(getActivity(), this, hour, minute,
                    DateFormat.is24HourFormat(getActivity()));
        }

        public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
            // Do something with the time chosen by the user
            openEdit.setText(hourOfDay + ":" + minute);
        }
    }



    // Method to handle touch event like left to right swap and right to left swap
    public boolean onTouchEvent(MotionEvent touchevent)
    {
        switch (touchevent.getAction())
        {
            // when user first touches the screen to swap
            case MotionEvent.ACTION_DOWN:
            {
                lastX = touchevent.getX();
                break;
            }
            case MotionEvent.ACTION_UP:
            {
                float currentX = touchevent.getX();

                // if left to right swipe on screen
                if (lastX < currentX)
                {
                    // If no more View/Child to flip
                    if (viewFlipper.getDisplayedChild() == 0)
                        break;

                    // set the required Animation type to ViewFlipper
                    // The Next screen will come in form Left and current Screen will go OUT from Right
                    viewFlipper.setInAnimation(this, R.anim.in_from_left);
                    viewFlipper.setOutAnimation(this, R.anim.out_to_right);
                    // Show the next Screen
                    viewFlipper.showNext();
                }

                // if right to left swipe on screen
                if (lastX > currentX)
                {
                    if (viewFlipper.getDisplayedChild() == 1)
                        break;
                    // set the required Animation type to ViewFlipper
                    // The Next screen will come in form Right and current Screen will go OUT from Left
                    viewFlipper.setInAnimation(this, R.anim.in_from_right);
                    viewFlipper.setOutAnimation(this, R.anim.out_to_left);
                    // Show The Previous Screen
                    viewFlipper.showPrevious();
                }
                break;
            }
        }
        return false;
    }
}
