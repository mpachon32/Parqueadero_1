package co.edu.iudigital.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {


    Button registrar, ingresar;
    EditText user, password;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        registrar = findViewById(R.id.btnRegistrar);
        ingresar = findViewById(R.id.btnIngresar);

        user = findViewById(R.id.txtUsuario);
        password = findViewById(R.id.txtClave);

        //IUDigital - 2022sys

        ingresar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AdminDataBase admin = new AdminDataBase(getApplicationContext(), "Actividad", 1);
                SQLiteDatabase db = admin.getWritableDatabase();

                String usuario = user.getText().toString();
                String clave = password.getText().toString();

                if (!usuario.isEmpty()){
                    Cursor fila = db.rawQuery("select * from administradores where usuario = '"+usuario+"'", null);
                    if (fila.moveToFirst()){
                        //Compara la variable usuario con la posición 1 del arreglo de columnas de la tabla usuarios que corresponde al campo username
                        //Compara la variable clave con la posición 2 del arreglo de columnas de la tabla usuarios que corresponde al campo password
                        if (usuario.equals(fila.getString(1))&& clave.equals(fila.getString(2))){
                            Intent intent = new Intent(MainActivity.this, RegistrarVehiculo.class);
                            startActivity(intent);
                            Toast.makeText(getApplicationContext(),"Bienvenido",Toast.LENGTH_LONG).show();
                        }else{
                            Toast.makeText(getApplicationContext(),"La contraseña es incorrecta",Toast.LENGTH_LONG).show();
                        }
                    }else {
                        user.setText("");
                        password.setText("");
                        Toast.makeText(getApplicationContext(),"Usuario no existe", Toast.LENGTH_LONG).show();
                    }
                }else{

                    Toast.makeText(getApplicationContext(),"Ingrese un usuario para la búsqueda", Toast.LENGTH_LONG).show();
                }

            }
        });

        registrar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this,RegistrarUsuario.class);
                startActivity(intent);
                //Toast.makeText(MainActivity.this,"La pantalla registrar no sirve",Toast.LENGTH_LONG).show();
            }
        });
    }
}