package com.example.bmarshall.calculator;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    private TextView screen;

    String numberAsText;

    private int savedNumber;

    private boolean modeWasLast;

    private Mode currentMode;

    public enum Mode {
        ADD, SUBTRACT, DIVIDE, MULTIPLY
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        screen = (TextView) findViewById(R.id.screenTextView);
        numberAsText = "0";
        screen.setText(numberAsText);
        modeWasLast = false;
        savedNumber = 0;
    }

    public void onClickNumberButton(View view) {
        Button numberButton = (Button) findViewById(view.getId());
        if (modeWasLast) {
            numberAsText = "";
        }
        if (numberAsText.equals("0")) {
            numberAsText = "";
        }
        numberAsText = numberAsText + numberButton.getText();
        screen.setText(numberAsText);
        modeWasLast = false;
    }

    public void onClickModeButton(View view) {
        Button modeButton = (Button) findViewById(view.getId());
        String modeText = (String) modeButton.getText();

        switch (modeText) {
            case "+":
                currentMode = Mode.ADD;
                break;
            case "-":
                currentMode = Mode.SUBTRACT;
                break;
            case "/":
                currentMode = Mode.DIVIDE;
                break;
            case "X":
                currentMode = Mode.MULTIPLY;
                break;
            default:
                screen.setText("fail");
        }
        modeWasLast = true;
        savedNumber = Integer.parseInt(numberAsText);
    }

    public void onClickEquals(View view) {
        switch (currentMode) {
            case ADD:
                savedNumber = savedNumber + Integer.parseInt(numberAsText);
                break;
            case SUBTRACT:
                savedNumber = savedNumber - Integer.parseInt(numberAsText);
                break;
            case DIVIDE:
                if (numberAsText.equals("0")) {
                    Toast toast = Toast.makeText(this, "Can't divide by Zero!", Toast.LENGTH_SHORT);
                    toast.show();
                } else {
                savedNumber = savedNumber / Integer.parseInt(numberAsText);
                }
                break;
            case MULTIPLY:
                savedNumber = savedNumber * Integer.parseInt(numberAsText);
                break;
            default:
                //do nothing
        }
        numberAsText = Integer.toString(savedNumber);
        screen.setText(numberAsText);

    }

    public void onClickClear(View view){
        savedNumber = 0;
        numberAsText = "0";
        screen.setText(numberAsText);
    }
}
