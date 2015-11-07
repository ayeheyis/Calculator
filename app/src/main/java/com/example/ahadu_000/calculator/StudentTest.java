package com.example.ahadu_000.calculator;

import android.app.ActionBar;
import android.content.Intent;
import android.graphics.Point;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.text.method.ScrollingMovementMethod;
import android.util.Log;
import android.view.Display;
import android.view.Gravity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.GridLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import net.sourceforge.jeval.EvaluationException;
import net.sourceforge.jeval.Evaluator;
import net.sourceforge.jeval.function.Function;
import net.sourceforge.jeval.function.FunctionConstants;
import net.sourceforge.jeval.function.FunctionException;
import net.sourceforge.jeval.function.FunctionResult;
import net.sourceforge.jeval.function.string.Eval;

import com.parse.ParseObject;

import java.util.Arrays;


public class StudentTest extends ActionBarActivity {

    private ParseUtil parseUtil;
    private static final String NAME = "Name";
    private static final String TEACHER = "Teacher";
    private static final String CALCULATOR = "Calculator";
    private static final int MAXROWS = 6;
    private static final int MAXCOLS = 5;
    private static final int MAXBUTTONS = 30;
    private int[] filledRows = new int[MAXROWS];
    private int[] filledCols = new int[MAXCOLS];
    private int[] trueRow = new int[MAXROWS];
    private int[] trueCol = new int[MAXCOLS];
    private Evaluator eval = new Evaluator();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        Intent lastIntent = getIntent();
        Bundle extras = lastIntent.getExtras();
        assert(extras != null);
        String teacher = extras.getString(TEACHER);
        String calcName = extras.getString("CalcName");
        String name = extras.getString(NAME);
        parseUtil = new ParseUtil();
        ParseObject parseObject = parseUtil.getParseObject(CALCULATOR, TEACHER, teacher, calcName);
        Calculator calc = parseUtil.convertToCalculator(parseObject);
        Log.d(calc.getFunctions().get(0), "woohoo");
        String[] calcButtonTexts = (calc.getFunctions()).toArray(new String[0]);
        findEmptyRows(calcButtonTexts, MAXROWS, MAXCOLS);
        findEmptyCols(calcButtonTexts, MAXROWS, MAXCOLS);
        final int totRows = sum(filledRows, MAXROWS);
        final int totCols = sum(filledCols, MAXCOLS);
        Button[] myButtons = new Button[totRows*totCols];

        Display display = getWindowManager().getDefaultDisplay();
        Point size = new Point();
        display.getSize(size);
        int screenWidth = size.x;

        super.onCreate(savedInstanceState);

        final RelativeLayout mainLayout = new RelativeLayout(StudentTest.this);
        mainLayout.setId(mainLayout.generateViewId());

        GridLayout gl = new GridLayout(mainLayout.getContext());
        RelativeLayout.LayoutParams gllp = new RelativeLayout.LayoutParams(RelativeLayout.LayoutParams.WRAP_CONTENT,
                RelativeLayout.LayoutParams.WRAP_CONTENT);
        gllp.addRule(RelativeLayout.ALIGN_PARENT_BOTTOM); //, changeButton.getId());
        gl.setLayoutParams(gllp);
        gl.setId(gl.generateViewId());
        gl.setColumnCount(totCols);
        gl.setRowCount(totRows);

        TextView calcScreen = new TextView(this);
        RelativeLayout.LayoutParams screenParams = new RelativeLayout.LayoutParams(RelativeLayout.LayoutParams.WRAP_CONTENT,
                RelativeLayout.LayoutParams.WRAP_CONTENT);
        screenParams.addRule(RelativeLayout.ALIGN_PARENT_TOP);
        screenParams.addRule(RelativeLayout.ABOVE, gl.getId());
        calcScreen.setId(calcScreen.generateViewId());
        final int calcScreenId = calcScreen.getId();
        calcScreen.setText("");
        calcScreen.setVerticalScrollBarEnabled(true);
        calcScreen.setMovementMethod(new ScrollingMovementMethod());
        mainLayout.addView(calcScreen);
        setTrueCol();
        setTrueRow();

        for (int i = 0; i < MAXROWS; i++) {
            for (int j = 0; j < MAXCOLS; j++) {
                if (filledRows[i] == 1 && filledCols[j] == 1) {
                    int ind = trueRow[i] * totCols + trueCol[j];
                    String text = calcButtonTexts[i * MAXCOLS + j];
                    myButtons[ind] = new Button(this);
                    if (calcButtonTexts[i * MAXCOLS + j].indexOf(":") != -1) {
                        //removes function declaration from text if it's a custom function
                        final String text1 = text.substring(0, calcButtonTexts[i*MAXCOLS + j].indexOf(":"));
                        String fn = text.substring(calcButtonTexts[i*MAXCOLS + j].indexOf(":"));
                        final String fnDecl = fn.replace(":", "");
                        Log.d("fnDecl", fnDecl);
                        Function function = new Function() {
                            @Override
                            public String getName() {
                                return text1;
                            }

                            @Override
                            public FunctionResult execute(Evaluator evaluator, String arguments) throws FunctionException {
                                try {return new FunctionResult(evaluator.evaluate(fnDecl.replace("x", arguments)), FunctionConstants.FUNCTION_RESULT_TYPE_NUMERIC);}
                                catch (EvaluationException ee) {
                                    return new FunctionResult("Invalid", FunctionConstants.FUNCTION_RESULT_TYPE_STRING);
                                }
                            }
                        };
                        eval.putFunction(function);
                        text = text1;

                    }
                    final String buttonText = text;
                    myButtons[ind].setText(text);
                    myButtons[ind].setGravity(Gravity.CENTER);
                    myButtons[ind].generateViewId();
                    myButtons[ind].setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            TextView calcScreen = (TextView) findViewById(calcScreenId);
                            if (((TextView) v).getText().toString().equals("=")) {
                                //evaluate the mathematical expression
                                calcScreen.setText(evaluate(calcScreen.getText().toString()));
                            } else if (((TextView) v).getText().toString().equals("AC")) {
                                calcScreen.setText("");
                            } else {
                                calcScreen.append(buttonText);
                            }
                        }
                    });
                    gl.addView(myButtons[ind], screenWidth / totCols, myButtons[ind].getMinimumHeight());
                }
            }
        }
        mainLayout.addView(gl);
        setContentView(mainLayout);
        //super.onCreate(savedInstanceState);
        //setContentView(R.layout.activity_student_test)
    }

    public void setTrueRow() {
        int index = 0;
        for (int i = 0; i < MAXROWS; i++) {
            if (filledRows[i] == 0)
                trueRow[i] = -1;
            else {
                trueRow[i] = index;
                index++;
            }
        }
    }

    public void setTrueCol() {
        int index = 0;
        for (int i = 0; i < MAXCOLS; i++) {
            if (filledCols[i] == 0)
                trueCol[i] = -1;
            else {
                trueCol[i] = index;
                index++;
            }
        }
    }

    public String evaluate(String exp) {
        String result;
        try {
            result = eval.evaluate(exp)
            ;}
        catch (EvaluationException ee) {
            result = "";
        }
        return result;
    }

    public int sum(int[] vals, int len) {
        int s = 0;
        for (int i = 0; i < len; i++) {
            s += vals[i];
        }
        return s;
    }

    public void findEmptyCols(String[] texts, int rows, int cols) {
        boolean colEmpty = true;
        for (int i = 0; i < cols; i++) {
            for (int j = 0; j < rows; j++) {
                if (!texts[j * cols + i].equals("")) {
                    colEmpty = false;
                    break;
                }
            }
            if (colEmpty) {
                filledCols[i] = 0;
            }
            else {
                filledCols[i] = 1;
            }
            colEmpty = true;
        }
    }

    public void findEmptyRows(String[] texts, int rows, int cols) {
        boolean rowEmpty = true;
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                if (!texts[i * cols + j].equals("")) {
                    rowEmpty = false;
                    break;
                }
            }
            if (rowEmpty) {
                filledRows[i] = 0;
            }
            else {
                filledRows[i] = 1;
            }
            rowEmpty = true;
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_student_test, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
