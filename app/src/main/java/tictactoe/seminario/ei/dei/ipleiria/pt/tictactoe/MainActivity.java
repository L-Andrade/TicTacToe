package tictactoe.seminario.ei.dei.ipleiria.pt.tictactoe;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    private List<Button> buttons = new ArrayList<>();
    private Button restartButton;

    String playerOne = "X";
    String playerTwo = "O";

    int playerTurn = 1;

    boolean gameOver = false;

    private TextView winnerTextView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

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

        winnerTextView = findViewById(R.id.winnerTextView);

        for (Button button :
                buttons) {
            button.setText(" ");
            button.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if (canClick(view)) {
                        if (playerTurn == 1) {
                            ((Button) view).setText(playerOne);
                            playerTurn = 2;
                        } else {
                            ((Button) view).setText(playerTwo);
                            playerTurn = 1;
                        }
                        isGameOver();
                    }
                }


            });
        }

    }

    private boolean canClick(View view) {
        return ((Button) view).getText().equals(" ") && !gameOver;
    }

    private void setWinner(String winner){
        winnerTextView.setText("Player "+winner+" wins ");
        gameOver = true;
    }

    private void isGameOver(){
        String board[] = new String[9];
        for (int i = 0; i < 9; i++) {
            board[i] = ((String) buttons.get(i).getText());
        }
        for (int i = 0; i < 3; i++) {
            // Check horizontal
            if(board[i*3].equals(board[1+i*3]) && board[i*3].equals(board[2+i*3])){
                if (!board[i*3].equals(" ")){
                    setWinner(board[i*3]);
                    return;
                }
            }
            //if(board[i+0*3].equals(board[i+1*3]) && board[i*3].equals(board[i+2*3])){
            // Check vertical
            if(board[i].equals(board[i+3]) && board[i].equals(board[i+2*3])){

                if (!board[i].equals(" ")) {
                    setWinner(board[i]);
                    return;
                }
            }
        }

        // Check diagonal
        if((board[0].equals(board[4]) && board[0].equals(board[8]))) {
            if (!board[0].equals(" ")) {
                setWinner(board[0]);
                return;
            }
        }
        if ((board[2].equals(board[4]) && board[2].equals(board[6]))){
            if (!board[2].equals(" ")) {
                setWinner(board[2]);
            }
        }
    }

    private void restartGame(){
        for (Button button :
                buttons) {
            button.setText(" ");
        }
        playerTurn = 1;
        gameOver = false;
        winnerTextView.setText("");
    }
}
