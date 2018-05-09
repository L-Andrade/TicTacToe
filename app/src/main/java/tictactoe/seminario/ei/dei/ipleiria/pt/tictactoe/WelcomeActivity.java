package tictactoe.seminario.ei.dei.ipleiria.pt.tictactoe;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AutoCompleteTextView;
import android.widget.Toast;

public class WelcomeActivity extends AppCompatActivity {

    AutoCompleteTextView mAutoCompleteTextViewPlayerOne;
    AutoCompleteTextView mAutoCompleteTextViewPlayerTwo;
    FloatingActionButton mFloatingActionButtonPlay;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_welcome);

        findViews();

        mFloatingActionButtonPlay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if (mAutoCompleteTextViewPlayerOne.getText().toString().trim().isEmpty() || mAutoCompleteTextViewPlayerTwo.getText().toString().trim().isEmpty()) {
                    Toast.makeText(WelcomeActivity.this, "You need both players to play!", Toast.LENGTH_SHORT).show();
                    return;
                }

                if (mAutoCompleteTextViewPlayerOne.getText().toString().compareToIgnoreCase(mAutoCompleteTextViewPlayerTwo.getText().toString()) == 0){
                    Toast.makeText(WelcomeActivity.this, "Don't choose equal names!", Toast.LENGTH_SHORT).show();
                    return;

                }


                Intent intent = MainActivity.getIntent(WelcomeActivity.this, mAutoCompleteTextViewPlayerOne.getText().toString(), mAutoCompleteTextViewPlayerTwo.getText().toString());
                startActivity(intent);

            }
        });

    }

    private void findViews() {
        mAutoCompleteTextViewPlayerOne = findViewById(R.id.tv_player_one);
        mAutoCompleteTextViewPlayerTwo = findViewById(R.id.tv_player_two);
        mFloatingActionButtonPlay = findViewById(R.id.floatingActionButton_play);
    }
}
