package br.feevale.environmentalthreats;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;
import br.feevale.environmentalthreats.Utils.DateUtils;

public class AddThreat extends AppCompatActivity {

    EnvironmentalThreatSQLiteDatabase db;
    EditText txtAddress, txtDate, txtDescription;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_threat);

        txtAddress = findViewById(R.id.txtAddress);
        txtDate = findViewById(R.id.txtDate);
        txtDescription = findViewById(R.id.txtDescription);

        db = new EnvironmentalThreatSQLiteDatabase(getBaseContext());
    }

    public void onClickAdd(View v){
        if (!DateUtils.validateDate(txtDate.getText().toString())){
            Toast.makeText(getBaseContext(), "Forneça uma data no formato dd/mm/yyyy.", Toast.LENGTH_SHORT).show();
            return;
        }
        EnvironmentalThreat et = new EnvironmentalThreat(txtDate.getText().toString(), txtDescription.getText().toString(), txtAddress.getText().toString());
        db.insertThreat(et);
        setResult(MainActivity.InternalResponse.UPDATE_ON_INSERT);
        finish();
    }
}
