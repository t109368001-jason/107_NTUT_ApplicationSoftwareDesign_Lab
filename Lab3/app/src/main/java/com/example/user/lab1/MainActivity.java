package com.example.user.lab1;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    EditText gamer;
    TextView status;
    RadioButton radioButton1;
    RadioButton radioButton2;
    RadioButton radioButton3;
    RadioGroup radioGroup;
    Button play;
    TextView name;
    TextView winner;
    TextView myMora;
    TextView computerMora;

    int mora_gamer = -1;
    String[] MoraString = {"剪刀", "石頭", "布"};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        gamer = (EditText) findViewById(R.id.gamer);
        status = (TextView) findViewById(R.id.status);
        radioButton1 = (RadioButton) findViewById(R.id.radioButton1);
        radioButton2 = (RadioButton) findViewById(R.id.radioButton2);
        radioButton3 = (RadioButton) findViewById(R.id.radioButton3);
        radioGroup = (RadioGroup) findViewById(R.id.radioGroup);
        play = (Button) findViewById(R.id.play);
        name = (TextView) findViewById(R.id.name);
        winner = (TextView) findViewById(R.id.winner);
        myMora = (TextView) findViewById(R.id.myMora);
        computerMora = (TextView) findViewById(R.id.computerMora);

        final RadioGroup radioGroup = (RadioGroup) findViewById(R.id.radioGroup);

        play.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(gamer.getText().toString().equals(""))
                    status.setText("請選擇玩家名稱");
                else if(mora_gamer == -1)
                    status.setText("請選擇出拳種類");
                else
                {
                    name.setText(gamer.getText());
                    myMora.setText(MoraString[mora_gamer]);
                    int mora_computer = (int)(Math.random() * 3);
                    computerMora.setText(MoraString[mora_computer]);
                    if((mora_gamer == 0 && mora_computer == 1)||(mora_gamer == 1 && mora_computer == 2)||(mora_gamer == 2 && mora_computer == 0))
                    {
                        winner.setText("電腦");
                        status.setText("可惜，電腦獲勝了");
                    }
                    else if(mora_gamer == mora_computer)
                    {
                        winner.setText("平局");
                        status.setText("平局!再試一場看看");
                    }
                    else
                    {
                        winner.setText(gamer.getText());
                        status.setText("恭喜你獲勝了");
                    }
                }
            }
        });
        radioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                switch (radioGroup.getCheckedRadioButtonId()) {
                    case R.id.radioButton1:
                        mora_gamer = 0;
                        break;
                    case R.id.radioButton2:
                        mora_gamer = 1;
                        break;
                    case R.id.radioButton3:
                        mora_gamer = 2;
                        break;
                }
            }
        });
    }
}
