package com.example.android.boardingpass;

/*
* Copyright (C) 2016 The Android Open Source Project
*
* Licensed under the Apache License, Version 2.0 (the "License");
* you may not use this file except in compliance with the License.
* You may obtain a copy of the License at
*
*      http://www.apache.org/licenses/LICENSE-2.0
*
* Unless required by applicable law or agreed to in writing, software
* distributed under the License is distributed on an "AS IS" BASIS,
* WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
* See the License for the specific language governing permissions and
* limitations under the License.
*/

import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.example.android.boardingpass.databinding.ActivityMainBinding;
import com.example.android.boardingpass.utilities.FakeDataUtils;

import java.text.DateFormat;
import java.text.SimpleDateFormat;

public class MainActivity extends AppCompatActivity {

    private ActivityMainBinding mBinding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mBinding = DataBindingUtil.setContentView(this, R.layout.activity_main);

        BoardingPassInfo boardingPassInfo = FakeDataUtils.generateFakeBoardingPassInfo();
        displayBoardingPassInfo(boardingPassInfo);
    }

    private void displayBoardingPassInfo(BoardingPassInfo info) {
        mBinding.textViewPassengerName.setText(info.passengerName);
        mBinding.textViewFlightCode.setText(info.flightCode);
        mBinding.textViewOriginAirport.setText(info.originCode);
        mBinding.textViewDestinationAirport.setText(info.destCode);

        mBinding.textViewTerminal.setText(info.departureTerminal);
        mBinding.textViewGate.setText(info.departureGate);
        mBinding.textViewSeat.setText(info.seatNumber);

        DateFormat df = SimpleDateFormat.getTimeInstance();

        mBinding.textViewBoardingTime.setText(df.format(info.boardingTime));
        mBinding.textViewDepartureTime.setText(df.format(info.departureTime));
        mBinding.textViewArrivalTime.setText(df.format(info.arrivalTime));

        mBinding.textViewBoardingInCountdown.setText(String.format("%d min", info.getMinutesUntilBoarding()));
    }
}

