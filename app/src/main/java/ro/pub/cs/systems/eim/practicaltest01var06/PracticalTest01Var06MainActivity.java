package ro.pub.cs.systems.eim.practicaltest01var06;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Toast;

import java.util.Random;

public class PracticalTest01Var06MainActivity extends AppCompatActivity {
    final private Random random = new Random();
    private ButtonClickListener buttonClickListener = new ButtonClickListener();
    private int totalScore = 0;
    private boolean serviceStarted = false;

    private class ButtonClickListener implements Button.OnClickListener {

        @Override
        public void onClick(View view) {
            EditText firstEditText = (EditText) findViewById(R.id.first_edit_text);
            EditText secondEditText = (EditText) findViewById(R.id.second_edit_text);
            EditText thirdEditText = (EditText) findViewById(R.id.third_edit_text);

            CheckBox firstCheckbox = (CheckBox) findViewById(R.id.hold1_checkbox);
            CheckBox secondCheckbox = (CheckBox) findViewById(R.id.hold2_checkbox);
            CheckBox thirdCheckbox = (CheckBox) findViewById(R.id.hold3_checkbox);

            String tmpText1 = null;
            String tmpText2 = null;
            String tmpText3 = null;
            int checkedCheckBoxCount = 0;

            if (view.getId() == R.id.play_button) {
                {
                    int randomNum = random.nextInt(4);
                    switch (randomNum) {
                        case 0:  // star
                            tmpText1 = "*";
                            break;
                        case 1:
                            tmpText1 = "1";
                            break;
                        case 2:
                            tmpText1 = "2";
                            break;
                        case 3:
                            tmpText1 = "3";
                            break;
                    }
                    if (!firstCheckbox.isChecked()) {
                        firstEditText.setText(tmpText1);
                    } else {
                        checkedCheckBoxCount += 1;
                    }
//                    Log.d(Constants.TAG, "Number 1: " + tmpText1);
                }
                {
                    int randomNum = random.nextInt(4);
                    switch (randomNum) {
                        case 0:  // star
                            tmpText2 = "*";
                            break;
                        case 1:
                            tmpText2 = "1";
                            break;
                        case 2:
                            tmpText2 = "2";
                            break;
                        case 3:
                            tmpText2 = "3";
                            break;
                    }
                    if (!secondCheckbox.isChecked()) {
                        secondEditText.setText(tmpText2);
                    } else {
                        checkedCheckBoxCount += 1;
                    }
//                    Log.d(Constants.TAG, "Number 2: " + tmpText2);
                }
                {
                    int randomNum = random.nextInt(4);
                    switch (randomNum) {
                        case 0:  // star
                            tmpText3 = "*";
                            break;
                        case 1:
                            tmpText3 = "1";
                            break;
                        case 2:
                            tmpText3 = "2";
                            break;
                        case 3:
                            tmpText3 = "3";
                            break;
                    }
                    if (!thirdCheckbox.isChecked()) {
                        thirdEditText.setText(tmpText3);
                        checkedCheckBoxCount += 1;
                    }
//                    Log.d(Constants.TAG, "Number 3: " + tmpText3);
                }

                boolean won = true;
                if (!tmpText1.equals("*")) {
                    if (!tmpText2.equals("*")) {
                        if (!tmpText3.equals("*")) {
                            if (!tmpText1.equals(tmpText2)) {
                                won = false;
                            }
                            else if (!tmpText1.equals(tmpText3)) {
                                won = false;
                            }
                            else if (!tmpText2.equals(tmpText3)) {
                                won = false;
                            }
                        }
                    }
                    if (!tmpText3.equals("*")) {
                        if (!tmpText1.equals(tmpText3)) {
                            won = false;
                        }
                    }
                }

                if (won) {
//                    if (totalScore > 300) {
                    if (totalScore >= 50) {
                        Intent intent1 = new Intent(getApplicationContext(), PracticalTest01Var06Service.class);
                        stopService(intent1);
                        intent1 = new Intent(getApplicationContext(), PracticalTest01Var06Service.class);
                        intent1.putExtra(Constants.TOTAL_SCORE_KEY, totalScore);
                        intent1.putExtra(Constants.SERVICE_VICOTRY_DATA_KEY, 1);
                        startService(intent1);
                        serviceStarted = true;
                    }
                    else if (totalScore > 0 && !serviceStarted) {
                        Intent intent1 = new Intent(getApplicationContext(), PracticalTest01Var06Service.class);
                        intent1.putExtra(Constants.TOTAL_SCORE_KEY, totalScore);
                        intent1.putExtra(Constants.SERVICE_VICOTRY_DATA_KEY, 0);
                        startService(intent1);
                        serviceStarted = true;
                    }

                    Intent intent = new Intent(getApplicationContext(), PracticalTest01Var06SecondaryActivity.class);
                    intent.putExtra(Constants.NUMBER1_EDIT_TEXT_KEY, tmpText1);
                    intent.putExtra(Constants.NUMBER2_EDIT_TEXT_KEY, tmpText2);
                    intent.putExtra(Constants.NUMBER3_EDIT_TEXT_KEY, tmpText3);
                    intent.putExtra(Constants.CHECKBOX_COUNT_KEY, checkedCheckBoxCount);
                    startActivityForResult(intent, Constants.SECONDARY_ACTIVITY_REQUEST_CODE);

                } else {
                    Toast.makeText(getApplicationContext(), "Total score: " + totalScore, Toast.LENGTH_LONG).show();
                }
            }
        }
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_practical_test01_var06_main);

        Button playButton = (Button) findViewById(R.id.play_button);
        playButton.setOnClickListener(buttonClickListener);
    }


    @Override
    protected void onDestroy() {
        Intent intent = new Intent(this, PracticalTest01Var06Service.class);
        stopService(intent);
        serviceStarted = false;
        super.onDestroy();
        Log.d(Constants.TAG, "[mainActivity]onDestroy() method was invoked");
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putInt(Constants.TOTAL_SCORE_KEY, totalScore);
        Log.d(Constants.TAG, "[mainActivity]onSaveInstanceState() method was invoked");
    }

    @Override
    protected void onRestoreInstanceState(Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);
        if (savedInstanceState.containsKey(Constants.TOTAL_SCORE_KEY)) {
            totalScore = savedInstanceState.getInt(Constants.TOTAL_SCORE_KEY);
        }
        Log.d(Constants.TAG, "[mainActivity]onRestoreInstanceState() method was invoked");
        Toast.makeText(this, "Total Score: " + totalScore, Toast.LENGTH_LONG).show();
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent intent) {
        super.onActivityResult(requestCode, resultCode, intent);
        switch (requestCode) {
            case Constants.SECONDARY_ACTIVITY_REQUEST_CODE:
                totalScore += resultCode;
                Toast.makeText(this, "Total Score: " + totalScore, Toast.LENGTH_LONG).show();
                break;
        }
    }
}