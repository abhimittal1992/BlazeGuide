package bob.com.example.root.blazeguide;

import android.content.Context;
import android.os.Build;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Gravity;
import android.view.View;
import android.view.WindowManager;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;
import android.view.View.OnClickListener;


public class BuildTimeCalculator extends AppCompatActivity implements OnClickListener{

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if(Build.VERSION.SDK_INT>= Build.VERSION_CODES.LOLLIPOP) {
            getWindow().setStatusBarColor(getResources().getColor(android.R.color.black));
        }
        setContentView(R.layout.activity_build_calculator);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);



        Button button = (Button) findViewById(R.id.calculate);
        button.setOnClickListener(this);

    }

    @Override
    public void onClick(View v) {
        InputMethodManager imm = (InputMethodManager)getSystemService(Context.INPUT_METHOD_SERVICE);
        imm.hideSoftInputFromWindow(v.getWindowToken(),
                InputMethodManager.RESULT_UNCHANGED_SHOWN);
        EditText originaltimeText  = (EditText) findViewById(R.id.originalTime);
        EditText boostText  = (EditText) findViewById(R.id.boost);

        if(!originaltimeText.getText().toString().trim().equals("") && !boostText.getText().toString().trim().equals("")){
            int originaltimeInt= Integer.parseInt(originaltimeText.getText().toString());
            float boostInt= Integer.parseInt(boostText.getText().toString());
            float  boostPercentage = 1+boostInt/100;
            int result = Math.round(originaltimeInt/boostPercentage);

            TextView buildResult = (TextView)findViewById(R.id.buidResult);
            buildResult.setText(result+" days :)");
            buildResult.setVisibility(View.VISIBLE);


        }else{
            Toast toast = Toast.makeText(v.getContext(),"Please enter proper value :)", Toast.LENGTH_SHORT);
            toast.show();
        }

    }
}
