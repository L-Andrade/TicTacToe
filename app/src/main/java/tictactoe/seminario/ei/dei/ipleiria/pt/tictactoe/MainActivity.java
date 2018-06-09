package tictactoe.seminario.ei.dei.ipleiria.pt.tictactoe;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    public static final String PLAYER_1_NAME_TAG = "PLAYER_1_NAME";
    public static final String PLAYER_2_NAME_TAG = "PLAYER_2_NAME";
    private static final String X_SYMBOL = "X";
    private static final String O_SYMBOL = "O";


    private List<Button> buttons = new ArrayList<>();
    private Button restartButton;

    String playerOne = "Player 1";
    String playerTwo = "Player 2";

    int playerTurn = 1;

    boolean gameOver = false;

    private TextView infoTextView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        playerOne = this.getIntent().getStringExtra(PLAYER_1_NAME_TAG);
        playerTwo = this.getIntent().getStringExtra(PLAYER_2_NAME_TAG);

        buttons.add((Button) findViewById(R.id.button));
        buttons.add((Button) findViewById(R.id.button2));
        buttons.add((Button) findViewById(R.id.button3));
        buttons.add((Button) findViewById(R.id.button4));
        buttons.add((Button) findViewById(R.id.button5));
        buttons.add((Button) findViewById(R.id.button6));
        buttons.add((Button) findViewById(R.id.button7));
        buttons.add((Button) findViewById(R.id.button8));
        buttons.add((Button) findViewById(R.id.button9));

        restartButton = findViewById(R.id.restartButton);

        restartButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                restartGame();
            }
        });

        infoTextView = findViewById(R.id.infoTextView);

        setCurrentPlayer();

        for (Button button :
                buttons) {
            button.setText("");
            button.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if (canClick(view)) {

                        if (playerTurn == 1) {
                            ((Button) view).setText(X_SYMBOL);
                        } else {
                            ((Button) view).setText(O_SYMBOL);
                        }

                        isGameOver();

                        if (!gameOver) {
                            switchPlayerTurn();
                        }

                    }
                }


            });
        }

    }

    private boolean canClick(View view) {
        return ((Button) view).getText().equals("") && !gameOver;
    }

    private void setWinner(int winner) {

        String winnerName = winner == 1 ? playerOne : playerTwo;
        infoTextView.setText(winnerName.concat(" won!"));
        gameOver = true;
    }

    private void setCurrentPlayer() {
        String playerName = this.playerTurn == 1 ? playerOne : playerTwo;
        infoTextView.setText(playerName.concat("'s turn!"));

    }

    private void switchPlayerTurn() {
        playerTurn = (playerTurn == 1) ? 2 : 1;
        setCurrentPlayer();
    }

    private void isGameOver() {
        String board[] = new String[9];
        for (int i = 0; i < 9; i++) {
            board[i] = ((String) buttons.get(i).getText());
        }
        for (int i = 0; i < 3; i++) {
            // Check horizontal
            if (board[i * 3].equals(board[1 + i * 3]) && board[i * 3].equals(board[2 + i * 3])) {
                if (!board[i * 3].equals("")) {
                    setWinner(playerTurn);
                    return;
                }
            }
            //if(board[i+0*3].equals(board[i+1*3]) && board[i*3].equals(board[i+2*3])){
            // Check vertical
            if (board[i].equals(board[i + 3]) && board[i].equals(board[i + 2 * 3])) {

                if (!board[i].equals("")) {
                    setWinner(playerTurn);
                    return;
                }
            }
        }

        // Check diagonal
        if ((board[0].equals(board[4]) && board[0].equals(board[8]))) {
            if (!board[0].equals("")) {
                setWinner(playerTurn);
                return;
            }
        }
        if ((board[2].equals(board[4]) && board[2].equals(board[6]))) {
            if (!board[2].equals("")) {
                setWinner(playerTurn);
            }
        }
    }

    private void restartGame() {
        for (Button button :
                buttons) {
            button.setText("");
        }
        gameOver = false;
        setCurrentPlayer();
        playerTurn = 1;
    }

    public static Intent getIntent(Context context, String playerOneName, String playerTwoName) {

        Intent intent = new Intent(context, MainActivity.class);
        intent.putExtra(PLAYER_1_NAME_TAG, playerOneName);
        intent.putExtra(PLAYER_2_NAME_TAG, playerTwoName);

        return intent;

    }
}
