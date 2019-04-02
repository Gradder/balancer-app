package com.example.balancerapplication;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

public class SelectionViewActivity extends AppCompatActivity {

    //Временно используем тип String
    List<String> units = new ArrayList<>();
    List<String> modifiers = new ArrayList<>();
    List<String> environments = new ArrayList<>();

    ArrayList<String> selectedUnits = new ArrayList<>();
    ArrayList<String> selectedModifiers = new ArrayList<>();
    ArrayList<String> selectedEnvironments = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_selection_view);

        // Временные значения для проверки
        units.add("unit 1");
        units.add("unit 2");
        units.add("unit 3");
        units.add("unit 4 :D");
        units.add("unit 5 :D");
        units.add("unit 6 :D");
        units.add("unit 7 :D");
        units.add("unit 8 :D");
        units.add("unit 9 :D");

        modifiers.add("modifier 1");
        modifiers.add("modifier 2");
        modifiers.add("modifier 3");
        modifiers.add("modifier 4");
        modifiers.add("modifier 5");
        modifiers.add("modifier 6");

        environments.add("environment 1");
        environments.add("environment 2");
        environments.add("environment 3");

        LinearLayout unitsLayout = findViewById(R.id.units_layout);
        LinearLayout modifiersLayout = findViewById(R.id.modifiers_layout);
        LinearLayout environmentsLayout = findViewById(R.id.environments_layout);

        // Вывод сообщения при пустом списке
        if(units.isEmpty()){
            unitsLayout.addView(createEmptyListTextView("No units available"));
        }
        if(modifiers.isEmpty()){
            modifiersLayout.addView(createEmptyListTextView("No modifiers available"));
        }
        if(environments.isEmpty()){
            environmentsLayout.addView(createEmptyListTextView("No environments available"));
        }

        LayoutInflater inflater = getLayoutInflater();
        View cbPanel;
        CheckBox cb;
        TextView textViewName;

        // Вывод на экран всех юнитов
        for(String unit : units){
            cbPanel = inflater.inflate(R.layout.text_cb_panel, null);
            textViewName = cbPanel.findViewById(R.id.name);
            textViewName.setText(unit);
            cb = cbPanel.findViewById(R.id.checkBox);
            cb.setTag(unit);
            cb.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
                @Override
                public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                    if(isChecked){
                        selectedUnits.add((String)buttonView.getTag());
                    }
                    else {
                        selectedUnits.remove((String)buttonView.getTag());
                    }
                }
            });
            unitsLayout.addView(cbPanel);
        }

        // Вывод на экран всех модификаторов
        for(String modifier : modifiers){
            cbPanel = inflater.inflate(R.layout.text_cb_panel, null);
            textViewName = cbPanel.findViewById(R.id.name);
            textViewName.setText(modifier);
            cb = cbPanel.findViewById(R.id.checkBox);
            cb.setTag(modifier);
            cb.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
                @Override
                public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                    if(isChecked){
                        selectedModifiers.add((String)buttonView.getTag());
                    }
                    else {
                        selectedModifiers.remove((String)buttonView.getTag());
                    }
                }
            });
            modifiersLayout.addView(cbPanel);
        }

        // Вывод на экран всех окружений
        for(String environment : environments){
            cbPanel = inflater.inflate(R.layout.text_cb_panel, null);
            textViewName = cbPanel.findViewById(R.id.name);
            textViewName.setText(environment);
            cb = cbPanel.findViewById(R.id.checkBox);
            cb.setTag(environment);
            cb.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
                @Override
                public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                    if(isChecked){
                        selectedEnvironments.add((String)buttonView.getTag());
                    }
                    else {
                        selectedEnvironments.remove((String)buttonView.getTag());
                    }
                }
            });
            environmentsLayout.addView(cbPanel);
        }
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

    public void onButtonClick(View v) {
        Intent intent = new Intent(this, SelectedActivity.class);
        intent.putExtra("selectedUnits", selectedUnits);
        intent.putExtra("selectedModifiers", selectedModifiers);
        intent.putExtra("selectedEnvironments", selectedEnvironments);
        startActivity(intent);
    }
}
