package com.jmartins.modernartui;

import android.app.AlertDialog;
import android.app.Dialog;
import android.app.DialogFragment;
import android.content.DialogInterface;
import android.os.Bundle;

/**
 * Created by jm85martins on 22/10/2014.
 */
public class MoreInfoDialog extends DialogFragment {

    public static MoreInfoDialog newInstance() {
        return new MoreInfoDialog();
    }

    // Build AlertDialog using AlertDialog.Builder
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        return new AlertDialog.Builder(getActivity())
                .setMessage(R.string.momadialogtext)

                        // User cannot dismiss dialog by hitting back button
                .setCancelable(false)

                        // Set up No Button
                .setNegativeButton(R.string.notnow,
                        new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int id) {
                                ((ModernArtActivity) getActivity()).moreInfoCallback(AlertDialog.BUTTON_NEGATIVE);
                            }
                        })

                        // Set up Yes Button
                .setPositiveButton(R.string.visitmoma,
                        new DialogInterface.OnClickListener() {
                            public void onClick(
                                    final DialogInterface dialog, int id) {
                                ((ModernArtActivity) getActivity()).moreInfoCallback(AlertDialog.BUTTON_POSITIVE);
                            }
                        }).create();
    }
}