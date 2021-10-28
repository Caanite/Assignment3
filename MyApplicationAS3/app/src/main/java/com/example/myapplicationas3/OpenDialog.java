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
    private TextView repeatOptionText;



    public Dialog onCreateDialog(Bundle SavedInstanceState) {
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());

        Resources res = getResources();

        arrayTimeTexts = res.getStringArray(R.array.Time_array);

        builder.setTitle(R.string.repeat).setMessage(R.string.repeatText).setItems(R.array.Time_array, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                repeatOptionText.setText(arrayTimeTexts[which]);
            }
        });

        return builder.create();
    }
}
