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
                    Log.d(Constants.TAG, "Number 1: " + tmpText1);
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
                    Log.d(Constants.TAG, "Number 2: " + tmpText2);
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
                    Log.d(Constants.TAG, "Number 3: " + tmpText3);
                }

                Intent intent = new Intent(getApplicationContext(), PracticalTest01Var06SecondaryActivity.class);
                intent.putExtra(Constants.NUMBER1_EDIT_TEXT_KEY, tmpText1);
                intent.putExtra(Constants.NUMBER2_EDIT_TEXT_KEY, tmpText2);
                intent.putExtra(Constants.NUMBER3_EDIT_TEXT_KEY, tmpText3);
                intent.putExtra(Constants.CHECKBOX_COUNT_KEY, checkedCheckBoxCount);
                startActivityForResult(intent, Constants.SECONDARY_ACTIVITY_REQUEST_CODE);

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
    public void onActivityResult(int requestCode, int resultCode, Intent intent) {
        super.onActivityResult(requestCode, resultCode, intent);
        switch (requestCode) {
            case Constants.SECONDARY_ACTIVITY_REQUEST_CODE:
                Toast.makeText(this, "Activity returned with result " + resultCode, Toast.LENGTH_LONG).show();
                totalScore += resultCode;
                break;
        }
    }
}