package yana.geo.coffeorders;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

public class OrderDetailsActivity extends AppCompatActivity {

    TextView textViewOrder;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_order_details);
        textViewOrder =findViewById(R.id.textViewOrder);
        Intent intent = getIntent();
        if (intent.hasExtra("order")){
            String order = intent.getStringExtra("order");
            textViewOrder.setText (order);
        }
        else{
            Intent backToLogin = new Intent (this, MainActivity.class);
            startActivity(backToLogin);
        }
    }
}