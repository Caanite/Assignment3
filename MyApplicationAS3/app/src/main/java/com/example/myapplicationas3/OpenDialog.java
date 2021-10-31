//Programmed by Sam Spark (18040422) and Sarbjot Singh (17190067)

package com.example.myapplicationas3;

import android.app.Dialog;
import android.content.DialogInterface;
import android.content.res.Resources;
import android.os.Bundle;
import android.widget.TextView;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatDialogFragment;

public class OpenDialog extends AppCompatDialogFragment {
    private String[] arrayTimeTexts;



    public Dialog onCreateDialog(Bundle SavedInstanceState) {
        EventAddActivity activity = (EventAddActivity)getActivity(); //gets the activity to interact with functions
        AlertDialog.Builder builder = new AlertDialog.Builder(activity); //builds the dialog

        Resources res = getResources(); //gets system resources to

        arrayTimeTexts = res.getStringArray(R.array.Time_array); //get the time array from the resources

        builder.setTitle(R.string.repeat).setItems(arrayTimeTexts, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                activity.setRepeatText(which, arrayTimeTexts[which]); //sets the options based on repeat options selected
            }
        });

        return builder.create(); //Creates dialog
    }
}
