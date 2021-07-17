package idat.com.appferreteria.view;


import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

import idat.com.minimarketapispring.R;

public class Registrar_Activity extends AppCompatActivity {
    String URL_REGISTRAR = "http://192.168.0.179:9999/api/usuario/insertarUsuario";
    EditText txtNombre, txtApellido, txtDepartamento,txtDistrito,txtDireccion
            ,txtEmail,txtProvincia,txtContraseña,txtTipodedoc,txtNumero;
    Button btnRegistrar, btnIngresar;
    
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registrar);

        txtNombre = findViewById(R.id.txtNombre);
        txtApellido =findViewById(R.id.txtApellido);
        txtDepartamento=findViewById(R.id.txtDepartamento);
        txtDistrito =findViewById(R.id.txtDistrito);
        txtDireccion =findViewById(R.id.txtDireccion);
        txtEmail =findViewById(R.id.txtEmail);
        txtProvincia =findViewById(R.id.txtProvincia);
        txtTipodedoc = findViewById(R.id.txtTipodedoc);
        txtContraseña =findViewById(R.id.txtContraseña);
        txtNumero =findViewById(R.id.txtNumero);
        btnIngresar =findViewById(R.id.btningresar);
        btnRegistrar =findViewById(R.id.btnregistrar);
        
        btnRegistrar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (verificar()){
                    registrar();
                    Toast.makeText(Registrar_Activity.this,"Usuario registrado",Toast.LENGTH_LONG).show();
                    limpiarCajas();
                }else{
                    Toast.makeText(Registrar_Activity.this,"Completa los campos",Toast.LENGTH_LONG).show();
                }

            }
        });
        btnIngresar.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Registrar_Activity.this, LoginActivity.class);
                startActivity(intent);
            }
        });
    }

        private void registrar() {
            RequestQueue colaPeticiones = Volley.newRequestQueue(this);
            Map<String, String> parametros = new HashMap<>();
            parametros.put("nombres", txtNombre.getText().toString());
            parametros.put("apellidos", txtApellido.getText().toString());
            parametros.put("departamento", txtDepartamento.getText().toString());
            parametros.put("distrito", txtDistrito.getText().toString());
            parametros.put("direccionEnvio", txtDireccion.getText().toString());
            parametros.put("email", txtEmail.getText().toString());
            parametros.put("provincia", txtProvincia.getText().toString());
            parametros.put("tipoDoc", txtTipodedoc.getText().toString());
            parametros.put("password", txtContraseña.getText().toString());
            parametros.put("numDoc", txtNumero.getText().toString());
            JSONObject jsonObjectParametro = new JSONObject(parametros);
            JsonObjectRequest request = new JsonObjectRequest(
                    Request.Method.POST,
                    URL_REGISTRAR,
                    jsonObjectParametro,
                    new Response.Listener<JSONObject>() {
                        @Override
                        public void onResponse(JSONObject response) {
                            startActivity(new Intent(Registrar_Activity.this, LoginActivity.class));
                        }
                    },
                    new Response.ErrorListener() {
                        @Override
                        public void onErrorResponse(VolleyError error) {
                        }
                    }
            );
            colaPeticiones.add(request);
        }

    private void limpiarCajas() {
        txtNombre.setText("");
        txtApellido.setText("");
        txtDepartamento.setText("");
        txtDistrito.setText("");
        txtDireccion.setText("");
        txtEmail.setText("");
        txtProvincia.setText("");
        txtTipodedoc.setText("");
        txtContraseña.setText("");
        txtNumero.setText("");
    }
    public Boolean verificar(){
        if (txtNombre.getText().toString().equals("") ||
                txtApellido.getText().toString().equals("")||
        txtDepartamento.getText().toString().equals("")||
        txtDistrito.getText().toString().equals("")||
        txtDireccion.getText().toString().equals("")||
        txtEmail.getText().toString().equals("")||
        txtProvincia.getText().toString().equals("")||
        txtTipodedoc.getText().toString().equals("")||
        txtContraseña.getText().toString().equals("")||
        txtNumero.getText().toString().equals("")){
            return  false;
        }else{
            return true;
        }
    }

}