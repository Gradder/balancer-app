package com.example.balancerapplication;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
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

        // Вывод сообщения при пустом списке
        if(selectedUnits.isEmpty()){
            unitsLayout.addView(createEmptyListTextView("No units selected"));
        }
        if(selectedModifiers.isEmpty()){
            modifiersLayout.addView(createEmptyListTextView("No modifiers selected"));
        }
        if(selectedEnvironments.isEmpty()){
            environmentsLayout.addView(createEmptyListTextView("No environments selected"));
        }

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

    private void setTextViewProperties(TextView tv){
        tv.setLayoutParams(new LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.MATCH_PARENT,
                LinearLayout.LayoutParams.WRAP_CONTENT));
        tv.setTextSize(18);
        tv.setPadding(12,12,12,12);
    }

    private TextView createEmptyListTextView(String text){
        TextView textView = new TextView(this);
        textView.setLayoutParams(new LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.MATCH_PARENT,
                LinearLayout.LayoutParams.WRAP_CONTENT));
        textView.setTextSize(18);
        textView.setTextAlignment(View.TEXT_ALIGNMENT_CENTER);
        textView.setPadding(10,10,10,10);
        textView.setText(text);
        return textView;
    }
}
