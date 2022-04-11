package ro.pub.cs.systems.eim.practicaltest01var06;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class PracticalTest01Var06SecondaryActivity extends AppCompatActivity {
    private ButtonClickListener buttonClickListener = new ButtonClickListener();
    int wonScore = 0;

    private class ButtonClickListener implements Button.OnClickListener {
        @Override
        public void onClick(View view) {
            if (view.getId() == R.id.ok_button) {
                
                setResult(wonScore, new Intent());
                finish();
            }
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_practical_test01_var06_secondary);

        Button okButton = (Button) findViewById(R.id.ok_button);
        okButton.setOnClickListener(buttonClickListener);

        Intent intent = getIntent();
        if (intent != null) {
            EditText scoreEditText = (EditText) findViewById(R.id.score_edit_text);

            String number1 = intent.getStringExtra(Constants.NUMBER1_EDIT_TEXT_KEY);
            String number2 = intent.getStringExtra(Constants.NUMBER2_EDIT_TEXT_KEY);
            String number3 = intent.getStringExtra(Constants.NUMBER3_EDIT_TEXT_KEY);
            int total = intent.getIntExtra(Constants.CHECKBOX_COUNT_KEY, -1);

            boolean won = true;
            if (!number1.equals("*")) {
                if (!number2.equals("*")) {
                    if (!number3.equals("*")) {
                        if (!number1.equals(number2)) {
                            won = false;
                        }
                        else if (!number1.equals(number3)) {
                            won = false;
                        }
                        else if (!number2.equals(number3)) {
                            won = false;
                        }
                    }
                }
                if (!number3.equals("*")) {
                    if (!number1.equals(number3)) {
                        won = false;
                    }
                }
            }

            if (won) {
                if (total == 0) {
                    wonScore = 100;
                } else if (total == 1) {
                    wonScore = 50;
                } else if (total == 2) {
                    wonScore = 10;
                }

                scoreEditText.setText("Gained " + wonScore);
            }
        }
    }
}