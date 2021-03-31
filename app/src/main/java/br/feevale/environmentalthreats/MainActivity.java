package br.feevale.environmentalthreats;

import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    ListView listThreat;
    EnvironmentalThreatSQLiteDatabase db;
    EnvironmentalThreatAdapter adapter;

    public static class InternalResponse{
        public final static int UPDATE_ON_INSERT = 1001;
        public final static int UPDATE_ON_UPDATE = 1002;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        db = new EnvironmentalThreatSQLiteDatabase(getBaseContext());

        listThreat = findViewById(R.id.listThreat);
        adapter = new EnvironmentalThreatAdapter(getBaseContext(), this.db);
        listThreat.setAdapter(adapter);

        listThreat.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                onClickEditThreat(id);
            }
        });

        listThreat.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {
                db.deleteThreat((EnvironmentalThreat) adapter.getItem(position));
                adapter.notifyDataSetChanged();
                Toast.makeText(getBaseContext(), "Ameaça deletada", Toast.LENGTH_SHORT).show();
                return true;
            }
        });
    }

    public void onClickAddThreat(View v){
        Intent intent = new Intent(this, AddThreat.class);
        startActivityForResult(intent, InternalResponse.UPDATE_ON_INSERT);
    }

    public void onClickEditThreat(Long id){
        Intent intent = new Intent(getBaseContext(), EditThreat.class);
        intent.putExtra("ID", id);
        startActivityForResult(intent, InternalResponse.UPDATE_ON_UPDATE);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == InternalResponse.UPDATE_ON_INSERT){
            adapter.notifyDataSetChanged();
            Toast.makeText(getBaseContext(), "Ameaça cadastrada!", Toast.LENGTH_SHORT).show();
            return;
        }
        if (requestCode == InternalResponse.UPDATE_ON_UPDATE){
            adapter.notifyDataSetChanged();
            Toast.makeText(getBaseContext(), "Ameaça editada!", Toast.LENGTH_SHORT).show();
            return;
        }
    }

}
