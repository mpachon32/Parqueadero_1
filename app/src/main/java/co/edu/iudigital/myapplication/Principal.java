package co.edu.iudigital.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

public class Principal extends AppCompatActivity implements AdapterView.OnItemSelectedListener{

    String user;
    Spinner spin;
    //Enlazar el elemento visual TextView
    TextView username;
    ListView lst;
    String[] cursos= {"Estructura de datos", "POO I", "Big Data", "Móviles"};
    String[] lenguajes= {"PHP", "Java", "Kotlin", "JavaScript","Node.js","C#"};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_principal);

        //Obtener los datos que trae el putExtra
        //user = getIntent().getExtras().getString("usuario");

        //Enlazar el elemento visual con su id declarado en el activity
        spin = findViewById(R.id.cmbCursos);
        lst = findViewById(R.id.lista);

        //Adaptar el array al spinner
        ArrayAdapter adapter = new ArrayAdapter(this, android.R.layout.simple_spinner_item,cursos);

        spin.setOnItemSelectedListener(this);
        spin.setAdapter(adapter);

        //Adaptar el array a la lista
        ArrayAdapter adaptador = new ArrayAdapter(this, android.R.layout.simple_list_item_1,lenguajes);
        lst.setAdapter(adaptador);

        lst.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {

                Toast.makeText(Principal.this,"Usted seleccionó el lenguaje: "+adapterView.getItemAtPosition(i).toString(),Toast.LENGTH_LONG).show();
            }
        });

        //username.setText(user);

    }

    @Override
    public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
        String item = adapterView.getItemAtPosition(i).toString();
        Toast.makeText(this, "Usted seleccionó: "+item,Toast.LENGTH_LONG).show();

    }

    @Override
    public void onNothingSelected(AdapterView<?> adapterView) {

    }
}