package com.starks.foodspots;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.starks.foodspots.fragments.HomeFragment1;
import com.starks.foodspots.interfaces.GetUsersListener;
import com.starks.foodspots.models.User;
import com.starks.foodspots.presenters.GetUsersPresenter;

import java.util.ArrayList;

/**
 * Created by sharda on 07/01/18.
 */

public class ProfileFragment extends Fragment {

    TextView creditText,nameText,emailText,contactText;
    Button btnprofile;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.home_fragment_3, container, false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        creditText=(TextView) view.findViewById(R.id.credit);
        contactText=(TextView) view.findViewById(R.id.contact);
        emailText=(TextView) view.findViewById(R.id.email);
        nameText=(TextView) view.findViewById(R.id.name);
        btnprofile=(Button) view.findViewById(R.id.editProfile);
        btnprofile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i =new Intent(getActivity(),MyProfileActiviy.class);
                startActivity(i);
            }

        });



        new GetUsersPresenter(new GetUsersListener() {
            @Override
            public void onReceiveUsers(ArrayList<User> users) {

            }

            @Override
            public void onReceiveUser(User user) {
                    nameText.setText(user.getFullName());
                    emailText.setText(user.getEmail());

            }

            @Override
            public void displayMessage(String message) {

            }

            @Override
            public void showLoader() {

            }

            @Override
            public void hideLoader() {

            }

            @Override
            public void showNetworkTimeoutError() {

            }

            @Override
            public void showNoNetworkException() {

            }
        }).getCurrentUser();
    }

    public static ProfileFragment newInstance() {
        return new ProfileFragment();
    }
}
