package com.group5.android_project;

import android.app.DatePickerDialog;
import android.app.Dialog;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.util.Log;

import java.util.Calendar;

public class DatePickerFragment extends DialogFragment {
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        // Use the current time as the default values for the picker
        final Calendar c = Calendar.getInstance();
        int year = c.get(Calendar.YEAR);
        int month = c.get(Calendar.MONTH);
        int day = c.get(Calendar.DAY_OF_MONTH);

        // Activity needs to implement this interface
        DatePickerDialog.OnDateSetListener listener = (DatePickerDialog.OnDateSetListener) getActivity();


        //when the date is not been set yet
        VehicleProfileActivity vehicleProfileActivity = (VehicleProfileActivity) getActivity();
        if ((VehicleProfileActivity.flag.equals("startDate") && (vehicleProfileActivity.txtStartDate == null || vehicleProfileActivity.txtStartDate.equals("")))
                || (VehicleProfileActivity.flag.equals("endDate") && (vehicleProfileActivity.txtEndDate == null || vehicleProfileActivity.txtEndDate.equals("")))) {
            // Create a new instance of TimePickerDialog and return with current date
            return new DatePickerDialog(getActivity(), listener, year, month, day);
        }

        //input default date
        String date;
        if (VehicleProfileActivity.flag.equals("startDate")) {
            date = vehicleProfileActivity.txtStartDate.getText().toString();
        } else if (VehicleProfileActivity.flag.equals("endDate")) {
            date = vehicleProfileActivity.txtEndDate.getText().toString();
        } else {
            return new DatePickerDialog(getActivity(), listener, year, month, day);
        }

        if (date.length() < 9) {
            return new DatePickerDialog(getActivity(), listener, year, month, day);
        }

        int nday = Integer.valueOf(date.substring(0, 2));
        int nmonth = Integer.valueOf(date.substring(3, 5)) - 1;
        int nyear = Integer.valueOf(date.substring(6, 10));
        return new DatePickerDialog(getActivity(), listener, nyear, nmonth, nday);
    }
}
