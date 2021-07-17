package idat.com.appferreteria.view;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;

import com.google.android.material.textfield.TextInputLayout;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;

import java.sql.Date;
import java.sql.Time;
import idat.com.appferreteria.MainActivity;
import idat.com.appferreteria.model.Usuario;
import idat.com.minimarketapispring.databinding.ActivityLoginBinding;
import idat.com.appferreteria.utils.DateSerializer;
import idat.com.appferreteria.utils.TimeSerializer;
import idat.com.appferreteria.viewmodel.UsuarioMySqlViewModel;

public class LoginActivity extends AppCompatActivity{

    private ActivityLoginBinding binding;
    private EditText edtMail, edtPassword;
    private Button btnIniciarSesion;
    private UsuarioMySqlViewModel viewModel;
    private TextInputLayout txtInputUsuario, txtInputPassword;
    private TextView textregistrar;
    private Usuario usuario = new Usuario();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityLoginBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        textregistrar = binding.textregistrar;
        this.initViewModel();
        this.init();
        getSupportActionBar().hide();
        binding.textregistrar.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(LoginActivity.this, Registrar_Activity.class);
                startActivity(intent);
            }
        });
    }

    private void initViewModel() {
        viewModel = new ViewModelProvider(this).get(UsuarioMySqlViewModel.class);
    }

    private void init() {
        edtMail = binding.edtMail;
        edtPassword = binding.edtPassword;
        btnIniciarSesion = binding.btnIniciarSesion;
        btnIniciarSesion.setOnClickListener(v -> {
            viewModel.login(edtMail.getText().toString(), edtPassword.getText().toString()).observe(this,response->{
                if (response.getRpta() == 1){
                    Toast.makeText(this,response.getMessage(),Toast.LENGTH_SHORT).show();
                    String nomyape = response.getBody().getNombres()+" "+response.getBody().getApellidos();
                    String email = response.getBody().getEmail();
                    Usuario umsql = response.getBody();
                    SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(this);
                    SharedPreferences.Editor editor = preferences.edit(); //almacenar el usuario en sesión
                    final Gson gson = new GsonBuilder()
                            .registerTypeAdapter(Date.class, new DateSerializer())
                            .registerTypeAdapter(Time.class,new TimeSerializer())
                            .create();
                    editor.putString("UsuarioJson", gson.toJson(umsql, new TypeToken<Usuario>(){}.getType()));
                    editor.apply();
                    edtMail.setText("");
                    edtPassword.setText("");
                    Intent intent = new Intent(this,MainActivity.class);
                    intent.putExtra("nomyape", nomyape);
                    intent.putExtra("email", email);
                    startActivity(intent);
                }else{
                    Toast.makeText(this,response.getMessage(),Toast.LENGTH_SHORT).show();
                }
            });
        });
    }
}