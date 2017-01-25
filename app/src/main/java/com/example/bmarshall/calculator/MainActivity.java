package com.example.bmarshall.calculator;

import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.GridLayout;
import android.widget.TextView;
import android.widget.Toast;

import java.text.DecimalFormat;
import java.text.NumberFormat;

public class MainActivity extends AppCompatActivity {

    //need to handle only decimal entered exception

    private NumberFormat nf;

    GridLayout background;

    private TextView screen;

    private String numberAsText;

    private int color;

    private double savedNumber;

    private boolean modeWasLast;
    private boolean numberHasDecimal;

    private Mode currentMode;

    public enum Mode {
        ADD, SUBTRACT, DIVIDE, MULTIPLY
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        nf = new DecimalFormat("##.###");
        screen = (TextView) findViewById(R.id.screenTextView);
        background = (GridLayout) findViewById(R.id.activity_main);
        numberAsText = getString(R.string.zero);
        screen.setText(numberAsText);
        modeWasLast = false;
        savedNumber = 0;
    }

    public void onClickNumberButton(View view) {
        Button numberButton = (Button) findViewById(view.getId());
        if (modeWasLast) {
            numberAsText = "";
        }
        if (numberAsText.equals(getString(R.string.zero))) {
            numberAsText = "";
        }
        if (numberHasDecimal && numberButton.getText().equals(".")) {
            //do nothing
        } else {
            numberAsText = numberAsText + numberButton.getText();
            screen.setText(numberAsText);
            modeWasLast = false;
        }
        if (numberButton.getText().equals(getString(R.string.decimal))) {
            numberHasDecimal = true;
        }
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
                screen.setText(R.string.fail);
        }
        modeWasLast = true;
        savedNumber = Double.parseDouble(numberAsText);
        numberHasDecimal = false;
    }

    public void onClickEquals(View view) {
        switch (currentMode) {
            case ADD:
                savedNumber = savedNumber + Double.parseDouble(numberAsText);
                break;
            case SUBTRACT:
                savedNumber = savedNumber - Double.parseDouble(numberAsText);
                break;
            case DIVIDE:
                if (numberAsText.equals(getString(R.string.zero))) {
                    Toast toast = Toast.makeText(this, R.string.cantDivideByZero, Toast.LENGTH_SHORT);
                    toast.show();
                } else {
                    savedNumber = savedNumber / Double.parseDouble(numberAsText);
                }
                break;
            case MULTIPLY:
                savedNumber = savedNumber * Double.parseDouble(numberAsText);
                break;
            default:
                //do nothing
        }
        numberAsText = nf.format(savedNumber);
        screen.setText(numberAsText);
        numberHasDecimal = false;
    }

    public void onClickClear(View view) {
        savedNumber = 0;
        numberAsText = getString(R.string.zero);
        screen.setText(numberAsText);
        numberHasDecimal = false;
    }

    public void onClickChangeBackground(View view) {

        color = color + 1;

        switch (color % 5) {
            case 0:
                background.setBackgroundColor(Color.WHITE);
                break;
            case 1:
                background.setBackgroundColor(Color.BLUE);
                screen.setTextColor(Color.RED);
                break;
            case 2:
                background.setBackgroundColor(Color.RED);
                screen.setTextColor(Color.BLACK);
                break;
            case 3:
                background.setBackgroundColor(Color.YELLOW);
                break;
            case 4:
                background.setBackgroundColor(Color.GREEN);
                break;
        }
    }
}
