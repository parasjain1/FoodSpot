package com.starks.foodspots;

import android.app.Activity;
import android.app.Dialog;
import android.app.DialogFragment;
import android.app.TimePickerDialog;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.icu.util.Calendar;
import android.location.Address;
import android.location.Geocoder;
import android.location.Location;
import android.os.Build;
import android.content.ClipData;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.support.annotation.RequiresApi;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.format.DateFormat;
import android.provider.MediaStore;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TimePicker;
import android.widget.ViewFlipper;

import com.starks.foodspots.adapters.ImageViewsRecyclerViewAdapter;
import com.starks.foodspots.interfaces.OnAddFoodSpotListener;
import com.starks.foodspots.models.FoodSpot;
import com.starks.foodspots.models.User;
import com.starks.foodspots.presenters.AddFoodSpotPresenter;
import com.starks.foodspots.utils.ImageCompressor;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;

/**
 * Created by Shashank on 04-Jan-18.
 */

public class CreateFoodspotActivity  extends BaseActivity implements OnAddFoodSpotListener {
    private ViewFlipper viewFlipper;
    private float lastX;
    EditText editName,editContact,editDiscription,editOpen,editClose;
    RecyclerView recyclerView;
    Spinner spinPref;
    static EditText openEdit,closeEdit;
    Button btnNext,btnPref, previousButton1, nextButton1, nextButton2, previousButton2;
    CheckBox checkNonveg,checkVeg;
    private static final int GET_IMAGES_REQUEST_CODE = 250;
    private static final String TAG = CreateFoodspotActivity.class.getSimpleName();
    Button selectImageButton;
    ArrayList<String> imagePaths = new ArrayList<>();
    ArrayList<Bitmap> imageBitmaps = new ArrayList<>();
    ArrayList<File> imageFiles = new ArrayList<>();
    ImageViewsRecyclerViewAdapter imageViewsRecyclerViewAdapter;
    AddFoodSpotPresenter addFoodSpotPresenter;


    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.create_foodspot_activity);
        viewFlipper = (ViewFlipper) findViewById(R.id.view_flipper);
        iniView();
        initImagesRecyclerView();
        addFoodSpotPresenter = new AddFoodSpotPresenter(this);
        selectImageButton = (Button) findViewById(R.id.selectImagesButton);
        selectImageButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getImages();
            }
        });
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

    }
    void iniView(){
        viewFlipper = (ViewFlipper) findViewById(R.id.view_flipper);
        editDiscription=(EditText) findViewById(R.id.EditTextDescription);
        editName=(EditText) findViewById(R.id.EditTextName);
        editContact=(EditText) findViewById(R.id.EditTextContact);
        editClose=(EditText) findViewById(R.id.closingTime);
        editOpen=(EditText) findViewById(R.id.openTime);
        spinPref=(Spinner) findViewById(R.id.spinnerPreference);
        btnNext=(Button) findViewById(R.id.nextButton);
        btnPref=(Button) findViewById(R.id.previousButton);
        nextButton1 = (Button) findViewById(R.id.nextButton1);
        nextButton2 = (Button) findViewById(R.id.nextButton2);
        previousButton2 = (Button) findViewById(R.id.previousButton2);



        nextButton1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                viewFlipper.setInAnimation(CreateFoodspotActivity.this, R.anim.in_from_right);
                viewFlipper.setOutAnimation(CreateFoodspotActivity.this, R.anim.out_to_left);
                viewFlipper.showNext();
            }
        });

        btnNext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                viewFlipper.setInAnimation(CreateFoodspotActivity.this, R.anim.in_from_right);
                viewFlipper.setOutAnimation(CreateFoodspotActivity.this, R.anim.out_to_left);
                viewFlipper.showNext();
            }
        });

        btnPref.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                viewFlipper.setInAnimation(CreateFoodspotActivity.this, R.anim.in_from_left);
                viewFlipper.setOutAnimation(CreateFoodspotActivity.this, R.anim.out_to_right);
                viewFlipper.showPrevious();
            }
        });

        nextButton2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onSubmit();
            }
        });

        previousButton2.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        viewFlipper.setInAnimation(CreateFoodspotActivity.this, R.anim.in_from_left);
                        viewFlipper.setOutAnimation(CreateFoodspotActivity.this, R.anim.out_to_right);
                        viewFlipper.showPrevious();
                    }
                }
        );




        checkNonveg=(CheckBox) findViewById(R.id.nonvegi);
        checkVeg=(CheckBox) findViewById(R.id.vegi);
    }

    public void TruitonTimePickerDialog(View v) {
        DialogFragment newFragment = new ClosePickerFragment();
        newFragment.show(getFragmentManager(), "timePicker");
    }
    public void showTruitonTimePickerDialog(View v) {
        DialogFragment newFragment = new TimePickerFragment();
        newFragment.show(getFragmentManager(), "timePicker");
    }

    @Override
    public void onFoodSpotAdded(FoodSpot foodSpot) {
        viewFlipper.setInAnimation(CreateFoodspotActivity.this, R.anim.in_from_right);
        viewFlipper.setOutAnimation(CreateFoodspotActivity.this, R.anim.out_to_left);
        viewFlipper.showNext();
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

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if(data == null)
            return;

        switch (requestCode){
            case GET_IMAGES_REQUEST_CODE:
                ClipData clipData = data.getClipData();
                ArrayList<String> imagePaths = new ArrayList<>();
                if(clipData != null){
                    for(int i = 0; i<clipData.getItemCount(); i++)
                        imagePaths.add(getRealPathFromURI(CreateFoodspotActivity.this, clipData.getItemAt(i).getUri()));
                        onAddImages(imagePaths);
                } else {
                    Log.d(TAG, "ClipData is null");
                }
                break;
            default:
                super.onActivityResult(requestCode, resultCode, data);
        }

    }

    public String getRealPathFromURI(Context context, Uri contentUri) {
        Cursor cursor = null;
        try {
            String[] proj = { MediaStore.Images.Media.DATA };
            cursor = context.getContentResolver().query(contentUri, proj, null,
                    null, null);
            int column_index = cursor
                    .getColumnIndexOrThrow(MediaStore.Images.Media.DATA);
            cursor.moveToFirst();
            return cursor.getString(column_index);
        } finally {
            if (cursor != null) {
                cursor.close();
            }
        }
    }

    void initImagesRecyclerView(){
        recyclerView = (RecyclerView) findViewById(R.id.imagesRecyclerView);
        imageViewsRecyclerViewAdapter = new ImageViewsRecyclerViewAdapter(this,imageBitmaps);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setHasFixedSize(true);
        recyclerView.setAdapter(imageViewsRecyclerViewAdapter);
    }

    void onAddImages(ArrayList<String> imagePaths){
        if(imagePaths.size() == 5){
            displayMessage("Maximum 5 images are allowed!");
            return;
        }
        this.imagePaths.addAll(imagePaths);
        for(String path : imagePaths) this.imageBitmaps.add(getBitmapFromImagePath(path));
        Log.d(TAG, "onAddImages called");
        imageViewsRecyclerViewAdapter.notifyDataSetChanged();
        onSubmit();

    }

    Bitmap getBitmapFromImagePath(String path){
        Log.d(TAG, path);
        Bitmap myBitmap = null;
        File imgFile = new File(path);
        if(imgFile.exists()){
            BitmapFactory.Options options = new BitmapFactory.Options();
            options.inJustDecodeBounds = false;
            options.inPreferredConfig = Bitmap.Config.RGB_565;
            options.inDither = true;
            myBitmap = BitmapFactory.decodeFile(imgFile.getAbsolutePath(),options);
        }
        return myBitmap;
    }

    private void getImages() {

        Intent chooseIntent = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
        chooseIntent.putExtra(Intent.EXTRA_ALLOW_MULTIPLE, true);
        startActivityForResult(chooseIntent, GET_IMAGES_REQUEST_CODE);
    }

    MultipartBody.Part[] compressImages(){
        ImageCompressor imageCompressor = new ImageCompressor(this);
        MultipartBody.Part[] fileParts = new MultipartBody.Part[imageBitmaps.size()];
        int counter = 0;
        for(Bitmap bitmap : imageBitmaps){
            Uri uri = imageCompressor.compress(bitmap);
            if(uri == null)
                continue;;
            File file = new File(uri.getPath());
            fileParts[counter] = MultipartBody.Part.createFormData("image" + counter++, file.getName(), RequestBody.create(MediaType.parse("image/*"), file));
        }
        return fileParts;
    }

    void onSubmit(){
        final Map<String, String> map = new HashMap<>();
        final Handler handler = new Handler(Looper.getMainLooper());
        final Runnable runnable = new Runnable() {
            @Override
            public void run() {

                Location location = MainActivity.getInstance().getLocation();
                map.put("name", editName.getText().toString());
                map.put("contact", editContact.getText().toString());
                map.put("description", editDiscription.getText().toString());
                map.put("ownerRating", editDiscription.getText().toString());
                map.put("location.lat", location.getLatitude() + "");
                map.put("location.lng", location.getLongitude() + "");
                if(location == null)
                    handler.postDelayed(this,500);
                else {
                    Log.d(TAG, "lat : " + location.getLatitude());
                    Geocoder geocoder = new Geocoder(CreateFoodspotActivity.this);
                    try {
                        Address address = geocoder.getFromLocation(location.getLatitude(), location.getLongitude(), 1).get(0);
                        map.put("location.city", address.getLocality());
                        map.put("location.state", address.getAdminArea());
                        map.put("location.country", address.getCountryName());
                        map.put("location.pincode", address.getPostalCode());
                        String completeAddress = "";
                        for(int i=0; i<address.getMaxAddressLineIndex(); i++)
                            completeAddress += address.getAddressLine(i) + "\n";
                        map.put("location.streetAddress", completeAddress);
                        Log.d(TAG, address.getLocality());
                    } catch (IOException e) {
                        Log.d(TAG, "Geocoder Exception");
                        e.printStackTrace();
                        map.clear();
                        handler.postDelayed(this,500);
                    }
                    addFoodSpotPresenter.addFoodSpot(compressImages(),map);
                }
            }
        };
        handler.postDelayed(runnable,0);
    }
}
