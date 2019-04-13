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

import com.example.balancerapplication.Filler.Entity;
import com.example.balancerapplication.Filler.Modifier;
import com.example.balancerapplication.Filler.Environment;
import com.example.balancerapplication.java_processing.NodeStorage;

import java.util.ArrayList;
import java.util.List;

public class SelectionViewActivity extends AppCompatActivity {

    List<Entity> units;
    List<Modifier> modifiers;
    List<Environment> environments;

    ArrayList<Entity> selectedUnits = new ArrayList<>();
    ArrayList<Modifier> selectedModifiers = new ArrayList<>();
    ArrayList<Environment> selectedEnvironments = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_selection_view);

        // create Filler object
        Filler filler = new Filler(NodeStorage.INSTANCE);

        // get lists of entities, modifiers and environments from filler
        units = filler.getEntitiesObj();
        modifiers = filler.getModifiersObj();
        environments = filler.getEnvironmentObj();

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
        for(Entity unit : units){
            cbPanel = inflater.inflate(R.layout.text_cb_panel, null);
            textViewName = cbPanel.findViewById(R.id.name);
            textViewName.setText(unit.getName());
            cb = cbPanel.findViewById(R.id.checkBox);
            cb.setTag(unit);
            cb.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
                @Override
                public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                    if(isChecked){
                        selectedUnits.add((Entity) buttonView.getTag());
                    }
                    else {
                        selectedUnits.remove((Entity) buttonView.getTag());
                    }
                }
            });
            unitsLayout.addView(cbPanel);
        }

        // Вывод на экран всех модификаторов
        for(Modifier modifier : modifiers){
            cbPanel = inflater.inflate(R.layout.text_cb_panel, null);
            textViewName = cbPanel.findViewById(R.id.name);
            textViewName.setText(modifier.getName());
            cb = cbPanel.findViewById(R.id.checkBox);
            cb.setTag(modifier);
            cb.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
                @Override
                public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                    if(isChecked){
                        selectedModifiers.add((Modifier) buttonView.getTag());
                    }
                    else {
                        selectedModifiers.remove((Modifier) buttonView.getTag());
                    }
                }
            });
            modifiersLayout.addView(cbPanel);
        }

        // Вывод на экран всех окружений
        for(Environment environment : environments){
            cbPanel = inflater.inflate(R.layout.text_cb_panel, null);
            textViewName = cbPanel.findViewById(R.id.name);
            textViewName.setText(environment.getName());
            cb = cbPanel.findViewById(R.id.checkBox);
            cb.setTag(environment);
            cb.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
                @Override
                public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                    if(isChecked){
                        selectedEnvironments.add((Environment) buttonView.getTag());
                    }
                    else {
                        selectedEnvironments.remove((Environment) buttonView.getTag());
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
