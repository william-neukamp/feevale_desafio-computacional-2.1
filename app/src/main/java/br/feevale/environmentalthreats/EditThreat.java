package br.feevale.environmentalthreats;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

public class EditThreat extends AppCompatActivity {

    EditText txtAddress, txtDate, txtDescription;
    EnvironmentalThreatSQLiteDatabase db;
    EnvironmentalThreat current;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_threat);

        txtAddress = findViewById(R.id.txtAddress);
        txtDate = findViewById(R.id.txtDate);
        txtDescription = findViewById(R.id.txtDescription);

        db = new EnvironmentalThreatSQLiteDatabase(getBaseContext());

        Long id = getIntent().getLongExtra("ID", 0);
        current = db.selectThreat(id);

        txtAddress.setText(current.getAddress());
        txtDate.setText(current.getDate());
        txtDescription.setText(current.getDescription());
    }

    public void onClickEdit(View v){
        current.setAddress(txtAddress.getText().toString());
        current.setDate(txtDate.getText().toString());
        current.setDescription(txtDescription.getText().toString());
        db.updateThreat(current);
        finish();
    }


}
