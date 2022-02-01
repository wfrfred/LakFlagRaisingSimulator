package com.wfrfred.flagraisingceremonysimulator.activity;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.widget.Button;
import android.widget.Spinner;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.wfrfred.flagraisingceremonysimulator.R;
import com.wfrfred.flagraisingceremonysimulator.core.Lak.Lak;

import java.io.FileNotFoundException;

public class PrefActivity extends AppCompatActivity {
    private static final String TAG = "!!!!!!";
    private Lak demo;
    private final Spinner spinner = null;
    //private ArrayAdapter<CharSequence> adapter = null;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        /*
        setContentView(R.layout.activity_pref);
        updateSpinner(
                new String[]{"a", "b", "c"}
        );

         */
        setContentView(R.layout.activity_pref);
        demo = findViewById(R.id.demo);
        Button selectBtn = findViewById(R.id.select_btn);
        Button startBtn = findViewById(R.id.start_btn);
        selectBtn.setOnClickListener(v -> {
            Intent intent = new Intent(Intent.ACTION_GET_CONTENT);
            intent.setType("image/*");
            intent.addCategory(Intent.CATEGORY_OPENABLE);
            startActivityForResult(intent, 1);
        });
        startBtn.setOnClickListener(v -> {
            Intent intent = new Intent(PrefActivity.this, CoreActivity.class);
            startActivity(intent);
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        Bitmap lakBody = null;
        if (resultCode == Activity.RESULT_OK) {
            Uri uri = data.getData();
            try {
                lakBody = BitmapFactory.decodeStream(this.getContentResolver().openInputStream(uri));
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            }
            if (lakBody != null) demo.changeBody(lakBody);
            demo.invalidate();
            CoreActivity.setLakPicture(lakBody);
        }
    }

    /*
    private void updateSpinner(String[] options){
        this.spinner = (Spinner) this.findViewById(R.id.selectedOption);
        this.adapter = new ArrayAdapter<CharSequence>(this,
                android.R.layout.simple_spinner_dropdown_item,options);
        this.spinner.setAdapter(adapter);
    }

     */

}
