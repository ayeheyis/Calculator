package com.example.ahadu_000.calculator;

import android.content.Intent;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.text.Html;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.GridView;
import android.widget.TextView;
import android.widget.Toast;


public class EditButtonPage extends ActionBarActivity {

    static String plusMinus = (Html.fromHtml("&#177").toString());
    static String sqrt = (Html.fromHtml("&#8730").toString());
    static String pi = (Html.fromHtml("&#928").toString());
    static final String[] numbers = new String[] {
            "*", "+", "/", "-", "x^2", plusMinus, sqrt, pi, "log", "ln", "sin", "cos", "tan", "|x|",
            "arcsin", "arccos", "arctan", "!"};

    @Override
    public void onCreate(Bundle savedInstanceState) {
        Intent intent = getIntent();
        Bundle extras = intent.getExtras();
        final int row = extras.getInt("row");
        final int col = extras.getInt("col");
        //Log.d("Row: ", Integer.toString(row));
        //Log.d("Col: ", Integer.toString(col));

        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_edit_button_page);

        GridView gridView = (GridView) findViewById(R.id.gridView);

        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,
                android.R.layout.simple_list_item_1, numbers);

        gridView.setAdapter(adapter);

        gridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            public void onItemClick(AdapterView<?> parent, View v,
                                    int position, long id) {
                String text = numbers[position];

                Intent intent = new Intent(EditButtonPage.this, HorizontalCalculator.class);
                Bundle bund = new Bundle();
                bund.putInt("row", row);
                bund.putInt("col", col);
                bund.putString("text", text);
                intent.putExtras(bund);
                startActivity(intent);
                //Toast.makeText(getApplicationContext(),
                //      ((TextView) v).getText(), Toast.LENGTH_SHORT).show();
            }
        });

        Button create = (Button) findViewById(R.id.button12);
        create.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                EditText fnNameEditText = (EditText) findViewById(R.id.editText10);
                EditText fnDeclEditText = (EditText) findViewById(R.id.editText9);
                String fnName = fnNameEditText.getText().toString();
                String fnDecl = fnDeclEditText.getText().toString();
                if (fnName.equals("") || fnDecl.equals("")) {
                    Toast.makeText(getApplicationContext(), "You must enter something into both fields",
                            Toast.LENGTH_LONG).show();
                }
                else {
                    Intent intent = new Intent(EditButtonPage.this, HorizontalCalculator.class);
                    Bundle bund = new Bundle();
                    bund.putInt("row", row);
                    bund.putInt("col", col);
                    bund.putString("text", fnName);
                    bund.putString("fnDecl", fnDecl);
                    intent.putExtras(bund);
                    startActivity(intent);
                }

            }
        });

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_edit_button_page, menu);
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
