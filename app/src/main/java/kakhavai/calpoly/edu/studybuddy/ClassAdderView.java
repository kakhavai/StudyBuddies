package kakhavai.calpoly.edu.studybuddy;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.test.LoaderTestCase;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

/**
 * Screen with the class addition specs. User may enter class name and
 * add class to the list.
 * @Author: Ani
 */

public class ClassAdderView extends AppCompatActivity implements View.OnClickListener {
    private EditText inputClassText;
    private Button confirmB;

    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.add_class);

        inputClassText = (EditText)findViewById(R.id.inputClass);
        confirmB = (Button)findViewById(R.id.confirm);

        Bundle extras = getIntent().getExtras();

        confirmB.setOnClickListener(this);
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
    }

    /**
     * On click method for confirm button, creates an intent and sends it back to the Main Activity
     * where the class information can be added to the list.
     */
    public void onClick(View v) {
        if (v == confirmB) {
            String classString = inputClassText.getText().toString();
            if (classString.length() > 0) {
                Intent sendClassString = new Intent();
                sendClassString.putExtra("class name", classString);
                setResult(RESULT_OK, sendClassString);
                super.finish();
            }
            else {
                Log.i("Error", "Enter Text Please");
            }
        }
    }
}
