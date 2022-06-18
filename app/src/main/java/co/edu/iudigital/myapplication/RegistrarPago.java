package co.edu.iudigital.myapplication;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import co.edu.iudigital.myapplication.AdminDataBase;
import co.edu.iudigital.myapplication.R;

public class RegistrarPago extends AppCompatActivity {

    EditText documento, fecha, hora, valor;
    Button registrar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registrar_pago);

        documento = findViewById(R.id.txtDocumento);
        fecha = findViewById(R.id.txtFecha);
        hora = findViewById(R.id.txtHora);
        valor = findViewById(R.id.txtValor);
    }

    public void Registrar (View view){
        AdminDataBase admin = new AdminDataBase(getApplicationContext(), "Actividad", 1);
        SQLiteDatabase db = admin.getWritableDatabase();

        String doc = documento.getText().toString();
        String fec = fecha.getText().toString();
        String hor = hora.getText().toString();
        String val = valor.getText().toString();

        ContentValues valores = new ContentValues();
        valores.put("doc", doc);
        valores.put("fecha", fec);
        valores.put("hora", hor);
        valores.put("valor", val);

        long newRowId = db.insert("pagos",null,valores);

        if (newRowId==-1){
            Toast.makeText(this, "Error guardando datos", Toast.LENGTH_LONG).show();
        }else {
            Toast.makeText(this, "Datos guardados", Toast.LENGTH_LONG).show();
        }
        documento.setText("");
        fecha.setText("");
        hora.setText("");
        valor.setText("");
    }


    public void Consultar (View view){
        //consultar
        AdminDataBase admin = new AdminDataBase(getApplicationContext(), "Actividad", 1);
        SQLiteDatabase db = admin.getWritableDatabase();
        String id = documento.getText().toString();

        if (!id.isEmpty()){
            Cursor fila =db.rawQuery("select * from pagos where doc="+id,null);
            if (fila.moveToFirst()){
                fecha.setText(fila.getString(1));
                hora.setText(fila.getString(2));
                valor.setText(fila.getString(3));
            }else {
                documento.setText("");
                fecha.setText("");
                hora.setText("");
                valor.setText("");
                Toast.makeText(getApplicationContext(),"Pago no existe", Toast.LENGTH_LONG).show();
            }
        }else{
            Toast.makeText(getApplicationContext(),"Ingrese un documento para la búsqueda", Toast.LENGTH_LONG).show();
        }

    }
    public void Actualizar  (View view){
        AdminDataBase admin = new AdminDataBase(getApplicationContext(), "Actividad", 1);
        SQLiteDatabase db = admin.getWritableDatabase();

        String doc = documento.getText().toString();
        String fec = fecha.getText().toString();
        String hor = hora.getText().toString();
        String val = valor.getText().toString();

        if (!doc.isEmpty()) {

            //Toast.makeText(this, "if 1", Toast.LENGTH_LONG).show();

            ContentValues valores = new ContentValues();
            valores.put("fecha", fec);
            valores.put("hora", hor);
            valores.put("valor", val);

            int i = db.update("pagos",valores,"doc="+doc,null);

            if(i!=0){
                documento.setText("");
                Toast.makeText(this,"Pago actualizado exitosamente",Toast.LENGTH_LONG).show();
            }else{
                Toast.makeText(this,"Pago no existe",Toast.LENGTH_LONG).show();
            }
        }else{
            Toast.makeText(getApplicationContext(),"Ingrese el documento a actualizar", Toast.LENGTH_LONG).show();
        }
        documento.setText("");
        fecha.setText("");
        hora.setText("");
        valor.setText("");
    }
    public void Eliminar (View view){
        AdminDataBase admin = new AdminDataBase(getApplicationContext(), "Actividad", 1);
        SQLiteDatabase db = admin.getWritableDatabase();

        String id = documento.getText().toString();
        if (!id.isEmpty()){
            int i = db.delete("pagos","doc="+id,null);
            if(i!=0){
                documento.setText("");
                fecha.setText("");
                hora.setText("");
                valor.setText("");
                Toast.makeText(this,"Pago eliminado exitosamente",Toast.LENGTH_LONG).show();
            }else{
                Toast.makeText(this,"Pago no existe",Toast.LENGTH_LONG).show();
            }
        }else{
            Toast.makeText(getApplicationContext(),"Ingrese el documento a eliminar", Toast.LENGTH_LONG).show();
        }

    }
}