package com.jmartins.modernartui;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.DialogFragment;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.SeekBar;
import android.widget.TextView;

/**
 * Created by jm85martins on 22/10/2014.
 */
public class ModernArtActivity extends Activity {

    private static final String URL_MOMA = "http://www.moma.org";
    private static final String TAG = "ModernArtActivity";
    private static final String BROWSER_CHOOSER_TEXT = "Load " + URL_MOMA + " with:";
    static private final int MORE_INFO_DIALOG = 1;

    private DialogFragment mDialog;
    private SeekBar seekBar;
    private TextView box1, box2, box3, box4, box5;
    private int cbox1, cbox2, cbox3, cbox4, cbox5;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_modernart);

        this.seekBar = (SeekBar) findViewById(R.id.seekBar);
        this.box1 = (TextView) findViewById(R.id.box1);
        this.box2 = (TextView) findViewById(R.id.box2);
        this.box3 = (TextView) findViewById(R.id.box3);
        this.box4 = (TextView) findViewById(R.id.box4);
        this.box5 = (TextView) findViewById(R.id.box5);

        cbox1 = ((ColorDrawable) this.box1.getBackground()).getColor();
        cbox2 = ((ColorDrawable) this.box2.getBackground()).getColor();
        cbox3 = ((ColorDrawable) this.box3.getBackground()).getColor();
        cbox4 = ((ColorDrawable) this.box4.getBackground()).getColor();
        cbox5 = ((ColorDrawable) this.box5.getBackground()).getColor();

        this.seekBar.setOnSeekBarChangeListener(colorSeekBarChangeListener);
    }

    private SeekBar.OnSeekBarChangeListener colorSeekBarChangeListener = new SeekBar.OnSeekBarChangeListener() {

        @Override
        public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
            updateColors(box1, cbox1);
            updateColors(box2, cbox2);
            updateColors(box3, cbox3);
            updateColors(box4, cbox4);
            updateColors(box5, cbox5);
        }

        @Override
        public void onStartTrackingTouch(SeekBar seekBar) {
        }

        @Override
        public void onStopTrackingTouch(SeekBar seekBar) {
        }
    };

    private void updateColors(TextView box, int originalColor) {
        if (originalColor != Color.WHITE && originalColor != Color.GRAY) {
            int seekProgress = seekBar.getProgress();
            box.setBackgroundColor(originalColor + seekProgress);
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.modern_art, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if (id == R.id.action_moreinfo) {
            this.showModal(MORE_INFO_DIALOG);
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    private void showModal(int dialogId) {
        switch (dialogId) {
            case MORE_INFO_DIALOG:
                mDialog = MoreInfoDialog.newInstance();
                mDialog.show(getFragmentManager(), "Alert");
                break;
        }
    }

    public void moreInfoCallback(int option) {
        switch (option) {
            case AlertDialog.BUTTON_POSITIVE:
                this.openUrl(URL_MOMA);
                break;
            case AlertDialog.BUTTON_NEGATIVE:
                mDialog.dismiss();
        }
    }

    private void openUrl(String url) {
        //open url in a browser app
        Intent baseIntent = new Intent(Intent.ACTION_VIEW, Uri.parse(url));
        Intent chooserIntent = Intent.createChooser(baseIntent, BROWSER_CHOOSER_TEXT);
        Log.i(TAG, "Chooser Intent Action:" + chooserIntent.getAction());

        if (chooserIntent.resolveActivity(getPackageManager()) != null) {
            startActivity(chooserIntent);
        }
    }
}
