package com.example.balancerapplication;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.balancerapplication.Filler.Entity;
import com.example.balancerapplication.Filler.Modifier;
import com.example.balancerapplication.Filler.Environment;

import java.util.ArrayList;
import java.util.List;

public class SelectedActivity extends AppCompatActivity {

    List<Entity> selectedUnits = new ArrayList<>();
    List<Modifier> selectedModifiers = new ArrayList<>();
    List<Environment> selectedEnvironments = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_selected);

        // Получение данных из SelectionViewActivity
        Bundle arguments = getIntent().getExtras();
        if(arguments != null){
            selectedUnits = (List<Entity>) arguments.getSerializable("selectedUnits");
            selectedModifiers = (List<Modifier>) arguments.getSerializable("selectedModifiers");
            selectedEnvironments = (List<Environment>) arguments.getSerializable("selectedEnvironments");
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
        for(Entity unit : selectedUnits){
            TextView tv = new TextView(this);
            setTextViewProperties(tv);
            tv.setText(unit.getName());
            unitsLayout.addView(tv);
        }

        // Вывод на экран всех выбранных модификаторов
        for(Modifier modifier : selectedModifiers){
            TextView tv = new TextView(this);
            setTextViewProperties(tv);
            tv.setText(modifier.getName());
            modifiersLayout.addView(tv);
        }

        // Вывод на экран всех выбранных окружений
        for(Environment environment : selectedEnvironments){
            TextView tv = new TextView(this);
            setTextViewProperties(tv);
            tv.setText(environment.getName());
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
