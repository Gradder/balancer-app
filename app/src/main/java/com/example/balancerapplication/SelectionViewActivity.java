package com.example.balancerapplication;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.LinearLayout;

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
        units.add("unit 2assf");
        units.add("unit 3 AsdDAHJKSD");
        units.add("unit 4 :D");

        modifiers.add("modifier 1");
        modifiers.add("modifier 2");
        modifiers.add("modifier 3");

        environments.add("environment 1");
        environments.add("environment 2");

        LinearLayout unitsLayout = findViewById(R.id.units_layout);
        LinearLayout modifiersLayout = findViewById(R.id.modifiers_layout);
        LinearLayout environmentsLayout = findViewById(R.id.environments_layout);

        // Вывод на экран всех юнитов
        for(String unit : units){
            CheckBox cb = new CheckBox(this);
            setCheckBoxProperties(cb);
            cb.setText(unit);
            cb.setTag(unit); // присваиваем соответствующий элемент списка как tag
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
            unitsLayout.addView(cb);
        }

        // Вывод на экран всех модификаторов
        for(String modifier : modifiers){
            CheckBox cb = new CheckBox(this);
            setCheckBoxProperties(cb);
            cb.setText(modifier);
            cb.setTag(modifier); // присваиваем соответствующий элемент списка как tag
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
            modifiersLayout.addView(cb);
        }

        // Вывод на экран всех окружений
        for(String enviroment : environments){
            CheckBox cb = new CheckBox(this);
            setCheckBoxProperties(cb);
            cb.setText(enviroment);
            cb.setTag(enviroment); // присваиваем соответствующий элемент списка как tag
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
            environmentsLayout.addView(cb);
        }
    }

    // Все свойства CheckBox кроме текста
    private void setCheckBoxProperties(CheckBox cb){
        cb.setLayoutParams(new LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.MATCH_PARENT,
                LinearLayout.LayoutParams.WRAP_CONTENT));
        cb.setTextSize(18);
        cb.setPadding(10,10,10,10);
    }

    public void onButtonClick(View v) {
        Intent intent = new Intent(this, SelectedActivity.class);
        intent.putExtra("selectedUnits", selectedUnits);
        intent.putExtra("selectedModifiers", selectedModifiers);
        intent.putExtra("selectedEnvironments", selectedEnvironments);
        startActivity(intent);
    }
}
