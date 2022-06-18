package co.edu.iudigital.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.ContentValues;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class RegistrarVehiculo extends AppCompatActivity {

    EditText documento, placa, entrada, celda, salida;
    Button registrar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registrar_vehiculo);

        documento = findViewById(R.id.txtDocumento);
        placa = findViewById(R.id.txtPlaca);
        entrada = findViewById(R.id.txtEntrada);
        celda = findViewById(R.id.txtCelda);
        salida = findViewById(R.id.txtSalida);
    }

    public void Registrar (View view){
        AdminDataBase admin = new AdminDataBase(getApplicationContext(), "Actividad", 1);
        SQLiteDatabase db = admin.getWritableDatabase();

        String doc = documento.getText().toString();
        String pla = placa.getText().toString();
        String ent = entrada.getText().toString();
        String cel = celda.getText().toString();
        String sal = salida.getText().toString();

        ContentValues valores = new ContentValues();
        valores.put("doc", doc);
        valores.put("placa", pla);
        valores.put("entrada", ent);
        valores.put("celda", cel);
        valores.put("salida", sal);

        long newRowId = db.insert("parking",null,valores);

        if (newRowId==-1){
            Toast.makeText(this, "Error guardando datos", Toast.LENGTH_LONG).show();
        }else {
            Toast.makeText(this, "Datos guardados", Toast.LENGTH_LONG).show();
        }
        documento.setText("");
        placa.setText("");
        entrada.setText("");
        celda.setText("");
        salida.setText("");
    }


    public void Consultar (View view){
        //consultar
        AdminDataBase admin = new AdminDataBase(getApplicationContext(), "Actividad", 1);
        SQLiteDatabase db = admin.getWritableDatabase();
        String id = documento.getText().toString();

        if (!id.isEmpty()){
            Cursor fila =db.rawQuery("select * from parking where doc="+id,null);
            if (fila.moveToFirst()){
                placa.setText(fila.getString(1));
                entrada.setText(fila.getString(2));
                celda.setText(fila.getString(3));
                salida.setText(fila.getString(4));
            }else {
                documento.setText("");
                placa.setText("");
                entrada.setText("");
                celda.setText("");
                salida.setText("");
                Toast.makeText(getApplicationContext(),"Vehículo no existe", Toast.LENGTH_LONG).show();
            }
        }else{
            Toast.makeText(getApplicationContext(),"Ingrese un documento para la búsqueda", Toast.LENGTH_LONG).show();
        }

    }
    public void Actualizar  (View view){
        AdminDataBase admin = new AdminDataBase(getApplicationContext(), "Actividad", 1);
        SQLiteDatabase db = admin.getWritableDatabase();

        String doc = documento.getText().toString();
        String pla = placa.getText().toString();
        String ent = entrada.getText().toString();
        String cel = celda.getText().toString();
        String sal = salida.getText().toString();

        if (!doc.isEmpty()) {

            //Toast.makeText(this, "if 1", Toast.LENGTH_LONG).show();

            ContentValues valores = new ContentValues();
            valores.put("placa", pla);
            valores.put("entrada", ent);
            valores.put("celda", cel);
            valores.put("salida", sal);

            int i = db.update("parking",valores,"doc="+doc,null);

            if(i!=0){
                documento.setText("");
                Toast.makeText(this,"Vehículo actualizado exitosamente",Toast.LENGTH_LONG).show();
            }else{
                Toast.makeText(this,"Vehículo no existe",Toast.LENGTH_LONG).show();
            }
        }else{
            Toast.makeText(getApplicationContext(),"Ingrese el documento a actualizar", Toast.LENGTH_LONG).show();
        }
        documento.setText("");
        placa.setText("");
        entrada.setText("");
        celda.setText("");
        salida.setText("");
    }
    public void Eliminar (View view){
        AdminDataBase admin = new AdminDataBase(getApplicationContext(), "Actividad", 1);
        SQLiteDatabase db = admin.getWritableDatabase();

        String id = documento.getText().toString();
        if (!id.isEmpty()){
            int i = db.delete("parking","doc="+id,null);
            if(i!=0){
                documento.setText("");
                placa.setText("");
                entrada.setText("");
                celda.setText("");
                salida.setText("");
                Toast.makeText(this,"Vehículo eliminado exitosamente",Toast.LENGTH_LONG).show();
            }else{
                Toast.makeText(this,"Vehículo no existe",Toast.LENGTH_LONG).show();
            }
        }else{
            Toast.makeText(getApplicationContext(),"Ingrese el documento a eliminar", Toast.LENGTH_LONG).show();
        }

    }
    public void RegistrarPago (View view){
        Intent intent = new Intent(RegistrarVehiculo.this,RegistrarPago.class);
        startActivity(intent);
        Toast.makeText(RegistrarVehiculo.this,"Pantalla de pago",Toast.LENGTH_LONG).show();
    }
}