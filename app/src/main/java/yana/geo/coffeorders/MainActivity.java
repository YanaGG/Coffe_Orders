package yana.geo.coffeorders;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    private EditText editTextClientName;
    private EditText editTextClientPassword;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        editTextClientName = findViewById(R.id.editTextClientName);
        editTextClientPassword = findViewById(R.id.editTextClientPassword);
    }

    public void CreateOrderOnClick(View view) {
        String name = editTextClientName.getText().toString().trim();
        String password = editTextClientPassword.getText().toString().trim();
        if (!name.isEmpty() && !password.isEmpty()){
            Intent intent = new Intent(this, CreateOrderActivity.class);
            intent.putExtra("name", name);
            intent.putExtra("password", password);
            startActivity(intent);
        }
        else {
            Toast.makeText(this,R.string.toast_text_empty_fields, Toast.LENGTH_SHORT).show();
        }

    }
}