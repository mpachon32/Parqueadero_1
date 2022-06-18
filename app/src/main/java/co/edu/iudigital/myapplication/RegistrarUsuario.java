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

public class RegistrarUsuario extends AppCompatActivity {

    EditText documento, usuario, password;
    Button registrar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registrar_usuario);

        documento = findViewById(R.id.txtDocumento);
        usuario = findViewById(R.id.txtUsuario);
        password = findViewById(R.id.txtPassword);
    }

    public void Registrar (View view){
        AdminDataBase admin = new AdminDataBase(getApplicationContext(), "Actividad", 1);
        SQLiteDatabase db = admin.getWritableDatabase();

        String doc = documento.getText().toString();
        String usu = usuario.getText().toString();
        String pas = password.getText().toString();

        ContentValues valores = new ContentValues();
        valores.put("doc", doc);
        valores.put("usuario", usu);
        valores.put("password", pas);

        long newRowId = db.insert("administradores",null,valores);

        if (newRowId==-1){
            Toast.makeText(this, "Error guardando datos", Toast.LENGTH_LONG).show();
        }else {
            Toast.makeText(this, "Datos guardados", Toast.LENGTH_LONG).show();
        }
        documento.setText("");
        usuario.setText("");
        password.setText("");
    }


    public void Consultar (View view){
        AdminDataBase admin = new AdminDataBase(getApplicationContext(), "Actividad", 1);
        SQLiteDatabase db = admin.getWritableDatabase();
        String id = documento.getText().toString();

        if (!id.isEmpty()){
            Cursor fila =db.rawQuery("select * from administradores where doc="+id,null);
            if (fila.moveToFirst()){
                usuario.setText(fila.getString(1));
                password.setText(fila.getString(2));
            }else {
                documento.setText("");
                usuario.setText("");
                password.setText("");
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
        String pla = usuario.getText().toString();
        String ent = password.getText().toString();

        if (!doc.isEmpty()) {

            //Toast.makeText(this, "if 1", Toast.LENGTH_LONG).show();

            ContentValues valores = new ContentValues();
            valores.put("usuario", pla);
            valores.put("password", ent);
            int i = db.update("administradores",valores,"doc="+doc,null);

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
        usuario.setText("");
        password.setText("");
    }
    public void Eliminar (View view){
        AdminDataBase admin = new AdminDataBase(getApplicationContext(), "Actividad", 1);
        SQLiteDatabase db = admin.getWritableDatabase();

        String id = documento.getText().toString();
        if (!id.isEmpty()){
            int i = db.delete("administradores","doc="+id,null);
            if(i!=0){

                documento.setText("");
                usuario.setText("");
                password.setText("");
                Toast.makeText(this,"Vehículo eliminado exitosamente",Toast.LENGTH_LONG).show();
            }else{
                Toast.makeText(this,"Vehículo no existe",Toast.LENGTH_LONG).show();
            }
        }else{
            Toast.makeText(getApplicationContext(),"Ingrese el documento a eliminar", Toast.LENGTH_LONG).show();
        }
    }

}