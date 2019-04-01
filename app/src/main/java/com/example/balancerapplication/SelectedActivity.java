package com.example.balancerapplication;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

public class SelectedActivity extends AppCompatActivity {

    List<String> selectedUnits = new ArrayList<>();
    List<String> selectedModifiers = new ArrayList<>();
    List<String> selectedEnvironments = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_selected);

        Bundle arguments = getIntent().getExtras();
        if(arguments != null){
            selectedUnits = (List<String>) arguments.getSerializable("selectedUnits");
            selectedModifiers = (List<String>) arguments.getSerializable("selectedModifiers");
            selectedEnvironments = (List<String>) arguments.getSerializable("selectedEnvironments");
        }

        LinearLayout unitsLayout = findViewById(R.id.units_layout);
        LinearLayout modifiersLayout = findViewById(R.id.modifiers_layout);
        LinearLayout environmentsLayout = findViewById(R.id.environments_layout);

        // Вывод на экран всех выбранных юнитов
        for(String unit : selectedUnits){
            TextView tv = new TextView(this);
            setTextViewProperties(tv);
            tv.setText(unit);
            unitsLayout.addView(tv);
        }

        for(String modifier : selectedModifiers){
            TextView tv = new TextView(this);
            setTextViewProperties(tv);
            tv.setText(modifier);
            modifiersLayout.addView(tv);
        }

        for(String environment : selectedEnvironments){
            TextView tv = new TextView(this);
            setTextViewProperties(tv);
            tv.setText(environment);
            environmentsLayout.addView(tv);
        }
    }

    private void setTextViewProperties(TextView cb){
        cb.setLayoutParams(new LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.MATCH_PARENT,
                LinearLayout.LayoutParams.WRAP_CONTENT));
        cb.setTextSize(18);
        cb.setPadding(10,10,10,10);
    }
}
