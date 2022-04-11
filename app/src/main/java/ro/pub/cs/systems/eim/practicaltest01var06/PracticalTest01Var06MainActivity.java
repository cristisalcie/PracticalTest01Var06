package ro.pub.cs.systems.eim.practicaltest01var06;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import java.util.Random;

public class PracticalTest01Var06MainActivity extends AppCompatActivity {
    final private Random random = new Random();
    private ButtonClickListener buttonClickListener = new ButtonClickListener();

    private class ButtonClickListener implements Button.OnClickListener {

        @Override
        public void onClick(View view) {
            if (view.getId() == R.id.play_button) {
                int randomNum = random.nextInt(4);
                switch (randomNum) {
                    case 0:  // star

                        break;
                    case 1:
                        break;
                    case 2:
                        break;
                    case 3:
                        break;

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