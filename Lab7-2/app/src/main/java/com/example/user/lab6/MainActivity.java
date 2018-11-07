package com.example.user.lab6;

import android.annotation.SuppressLint;
import android.app.ProgressDialog;
import android.os.AsyncTask;
import android.os.Handler;
import android.os.Message;
import android.provider.ContactsContract;
import android.provider.MediaStore;
import android.support.v4.widget.CircularProgressDrawable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.SeekBar;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    protected EditText tall;
    protected EditText weight;
    protected RadioButton boy;
    protected RadioButton girl;
    protected RadioGroup radioGroup;
    protected Button button;
    protected TextView standardWeight;
    protected TextView bodyFat;

    int gender = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        tall = (EditText) findViewById(R.id.tall);
        weight = (EditText) findViewById(R.id.weight);
        boy = (RadioButton) findViewById(R.id.boy);
        girl = (RadioButton) findViewById(R.id.girl);
        radioGroup = (RadioGroup) findViewById(R.id.radioGroup);
        button = (Button) findViewById(R.id.button);
        standardWeight = (TextView) findViewById(R.id.standardWeight);
        bodyFat = (TextView) findViewById(R.id.bodyFat);

        radioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                switch (radioGroup.getCheckedRadioButtonId()) {
                    case R.id.boy:
                        gender = 1;
                        break;
                    case R.id.girl:
                        gender = 2;
                        break;
                }
            }
        });
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                runAsycnTask();
            }
        });
    }

    private void runAsycnTask() {
        new AsyncTask<Void, Integer, Boolean>() {
            private ProgressDialog dialog = new ProgressDialog(MainActivity.this);

            @Override
            protected void onPreExecute() {
                super.onPreExecute();
                dialog.setMessage("計算中");
                dialog.setCancelable(false);
                dialog.setProgressStyle(ProgressDialog.STYLE_HORIZONTAL);
                dialog.show();
            }

            @Override
            protected Boolean doInBackground(Void... voids) {
                int progress = 0;
                while (progress <= 100) {
                    try {
                        Thread.sleep(50);
                        publishProgress(Integer.valueOf(progress));
                        progress++;
                    }catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
                return true;
            }

            @Override
            protected void onProgressUpdate(Integer... values) {
                super.onProgressUpdate(values);
                dialog.setProgress(values[0]);
            }

            @Override
            protected void onPostExecute(Boolean aBoolean) {
                dialog.dismiss();
                double cal_Tall = Double.parseDouble(tall.getText().toString());
                double cal_Weight = Double.parseDouble(weight.getText().toString());
                double cal_StandWeight = 0;
                double cal_BodyFat = 0;
                if(gender == 1) {
                    cal_StandWeight = 22 * cal_Tall/100 * cal_Tall/100;
                    cal_BodyFat = (cal_Weight - (0.88 * cal_StandWeight)) / cal_Weight * 100;
                }
                if(gender == 2) {
                    cal_StandWeight = 22 * cal_Tall/100 * cal_Tall/100;
                    cal_BodyFat = (cal_Weight - (0.82 * cal_StandWeight)) / cal_Weight * 100;
                }
                standardWeight.setText(String.format("%.2f", cal_StandWeight));
                bodyFat.setText(String.format("%.2f", cal_BodyFat));
            }
        }.execute();
    }
}
