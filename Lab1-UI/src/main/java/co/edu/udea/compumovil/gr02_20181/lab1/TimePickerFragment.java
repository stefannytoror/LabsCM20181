package co.edu.udea.compumovil.gr02_20181.lab1;
import android.app.Dialog;
import android.app.DialogFragment;
import android.app.TimePickerDialog;
import android.os.Bundle;
import android.text.format.DateFormat;
import android.widget.TimePicker;

import java.util.Calendar;

/**
 * Created by santiago.molinae on 23/02/18.
 */

public class TimePickerFragment extends DialogFragment implements
        TimePickerDialog.OnTimeSetListener {

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {

        // Set the current date in the DatePickerFragment
        final Calendar c = Calendar.getInstance();
        int hour = c.get(Calendar.HOUR_OF_DAY);
        int minute = c.get(Calendar.MINUTE);

        // Create a new instance of TimePickerDialog and return it
        //return new TimePickerDialog(getActivity(), this, hour, minute,
           //     DateFormat.is24HourFormat(getActivity()));

        return new TimePickerDialog(getActivity(), this, hour, minute,true
                );


    }

    // Callback to TimePickerActivity.onDateSet() to update the UI
    @Override
    public void onTimeSet(TimePicker view, int hourOfDay, int minute) {

        ((TimePickerDialog.OnTimeSetListener) getActivity()).onTimeSet(view, hourOfDay, minute);
    }
}
