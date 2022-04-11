package ro.pub.cs.systems.eim.practicaltest01var06;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;

import java.util.Random;

public class PracticalTest01Var06MainActivity extends AppCompatActivity {
    final private Random random = new Random();
    private ButtonClickListener buttonClickListener = new ButtonClickListener();

    private class ButtonClickListener implements Button.OnClickListener {

        @Override
        public void onClick(View view) {
            EditText firstEditText = (EditText) findViewById(R.id.first_edit_text);
            EditText secondEditText = (EditText) findViewById(R.id.second_edit_text);
            EditText thirdEditText = (EditText) findViewById(R.id.third_edit_text);

            CheckBox firstCheckbox = (CheckBox) findViewById(R.id.hold1_checkbox);
            CheckBox secondCheckbox = (CheckBox) findViewById(R.id.hold2_checkbox);
            CheckBox thirdCheckbox = (CheckBox) findViewById(R.id.hold3_checkbox);

            if (view.getId() == R.id.play_button) {
                {
                    int randomNum = random.nextInt(4);
                    String tmpText = null;
                    switch (randomNum) {
                        case 0:  // star
                            tmpText = "*";
                            break;
                        case 1:
                            tmpText = "1";
                            break;
                        case 2:
                            tmpText = "2";
                            break;
                        case 3:
                            tmpText = "3";
                            break;
                    }
                    if (!firstCheckbox.isChecked()) {
                        firstEditText.setText(tmpText);
                    }
                    Log.d(Constants.TAG, "Number 1: " + tmpText);
                }
                {
                    int randomNum = random.nextInt(4);
                    String tmpText = null;
                    switch (randomNum) {
                        case 0:  // star
                            tmpText = "*";
                            break;
                        case 1:
                            tmpText = "1";
                            break;
                        case 2:
                            tmpText = "2";
                            break;
                        case 3:
                            tmpText = "3";
                            break;
                    }
                    if (!secondCheckbox.isChecked()) {
                        secondEditText.setText(tmpText);
                    }
                    Log.d(Constants.TAG, "Number 2: " + tmpText);
                }
                {
                    int randomNum = random.nextInt(4);
                    String tmpText = null;
                    switch (randomNum) {
                        case 0:  // star
                            tmpText = "*";
                            break;
                        case 1:
                            tmpText = "1";
                            break;
                        case 2:
                            tmpText = "2";
                            break;
                        case 3:
                            tmpText = "3";
                            break;
                    }
                    if (!thirdCheckbox.isChecked()) {
                        thirdEditText.setText(tmpText);
                    }
                    Log.d(Constants.TAG, "Number 3: " + tmpText);
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
}