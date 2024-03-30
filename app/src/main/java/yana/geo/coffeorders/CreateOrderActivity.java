package yana.geo.coffeorders;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.CheckBox;
import android.widget.RadioButton;
import android.widget.Spinner;
import android.widget.TextView;

public class CreateOrderActivity extends AppCompatActivity {

    private TextView textViewAboutOrder;
    private TextView textViewAboutAddition;
    private CheckBox checkBoxMilk;
    private CheckBox checkBoxSugar;
    private CheckBox checkBoxLemon;
    private CheckBox checkBoxCream;
    private Spinner spinnerTea;
    private Spinner spinnerCoffee;

    private String name;
    private String password;

    private String drink;
    private StringBuilder builderAddition;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_order);
        Intent intent = getIntent();
        if (intent.hasExtra("name") && intent.hasExtra("password")) {
            name = intent.getStringExtra("name");
            password = intent.getStringExtra("password");
        }
        else {
            name = getString(R.string.default_name);
            password = getString(R.string.default_password);
        }

        drink = getString(R.string.teaButton);

        textViewAboutOrder = findViewById(R.id.textViewAboutOrder);
        String welcome = String.format(getString(R.string.question_about_order), name);
        textViewAboutOrder.setText(welcome);

        textViewAboutAddition = findViewById(R.id.textViewAboutAddition);
        String addition = String.format(getString(R.string.question_about_addition), drink);
        textViewAboutAddition.setText(addition);

        checkBoxMilk = findViewById(R.id.checkBoxMilk);
        checkBoxSugar = findViewById(R.id.checkBoxSugar);
        checkBoxLemon = findViewById(R.id.checkBoxLemon);
        checkBoxCream = findViewById(R.id.checkBoxCream);
        spinnerTea = findViewById(R.id.spinnerChooseTea);
        spinnerCoffee = findViewById(R.id.spinnerChooseCoffee);
        builderAddition = new StringBuilder();

    }

    public void changeDrinkOnClick(View view) {
        RadioButton button = (RadioButton) view;
        int id = button.getId();
        if (id == R.id.radioButtonTea){
            drink = getString(R.string.teaButton);
            spinnerTea.setVisibility(View.VISIBLE);
            spinnerCoffee.setVisibility(View.INVISIBLE);
            checkBoxLemon.setVisibility(View.VISIBLE);
            checkBoxCream.setVisibility(View.INVISIBLE);
        }
        else if (id == R.id.radioButtonCoffee){
            drink = getString(R.string.coffeeButton);
            spinnerCoffee.setVisibility(View.VISIBLE);
            spinnerTea.setVisibility(View.INVISIBLE);
            checkBoxLemon.setVisibility(View.INVISIBLE);
            checkBoxCream.setVisibility(View.VISIBLE);
        }
        String Addition = String.format(getString(R.string.question_about_addition), drink);
        textViewAboutAddition.setText(Addition);
    }

    public void sendOrderOnClick(View view) {
        builderAddition.setLength(0);
        if (checkBoxMilk.isChecked()){
            builderAddition.append(getString(R.string.checkbox_milk)).append(" ");
        }
        if (checkBoxSugar.isChecked()){
            builderAddition.append(getString(R.string.checkbox_sugar)).append(" ");
        }
        if (checkBoxLemon.isChecked() && drink.equals(getString(R.string.teaButton))){
            builderAddition.append(getString(R.string.checkbox_lemon)).append(" ");
        }
        if (checkBoxCream.isChecked() && drink.equals(getString(R.string.coffeeButton))){
            builderAddition.append(getString(R.string.checkbox_cream)).append(" ");
        }
        String drinkType = "";
        if (drink.equals(getString(R.string.teaButton))){
            drinkType = spinnerTea.getSelectedItem().toString();
        }
        else if(drink.equals(getString(R.string.coffeeButton))){
            drinkType = spinnerCoffee.getSelectedItem().toString();
        }

        String order = String.format(getString(R.string.order), name,password,drink, drinkType);
        String additions;
        if (builderAddition.length() >0){
            additions = getString(R.string.additions) + builderAddition.toString();
        }
        else{
            additions = "No additions";
        }
        String fullOrder = order + additions;
        Intent intent = new Intent(this, OrderDetailsActivity.class);
        intent.putExtra("order", fullOrder);
        startActivity(intent);
    }
}