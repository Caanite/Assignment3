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
        EventAddActivity activity = (EventAddActivity)getActivity();
        AlertDialog.Builder builder = new AlertDialog.Builder(activity);

        Resources res = getResources();

        arrayTimeTexts = res.getStringArray(R.array.Time_array);

        builder.setTitle(R.string.repeat).setItems(arrayTimeTexts, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                activity.setRepeatText(arrayTimeTexts[which]);
            }
        });

        return builder.create();
    }
}
