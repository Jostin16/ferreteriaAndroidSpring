package idat.com.appferreteria.view;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import idat.com.minimarketapispring.databinding.ActivityDetalleProductoBinding;

public class DetalleProductoActivity extends AppCompatActivity {

    private ActivityDetalleProductoBinding binding;

    TextView tvnombre, tvmarca, tvprecio, tvstock, tvcantidadelegida;
    ImageView ivfoto;
    Button btnsumar, btnrestar, btnComprar;
    String id;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityDetalleProductoBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        tvstock = binding.idtvstock;
        tvmarca = binding.idtvmarca;
        tvnombre = binding.idtvnombreproducto;
        tvprecio = binding.idtvprecio;
        ivfoto = binding.imageView;
        tvcantidadelegida = binding.idtvcantidadelegida;
        btnsumar = binding.idbtnsumar;
        btnrestar = binding.idbtnrestar;
        btnComprar = binding.idbtnComprar;

        String valor_id = getIntent().getStringExtra("id");
        String valor_nombre = getIntent().getStringExtra("nombreproducto");
        String valor_marca = getIntent().getStringExtra("marca");
        String valor_precio = getIntent().getStringExtra("precio");
        String valor_stock = getIntent().getStringExtra("stock");
        String valor_url = getIntent().getStringExtra("imagen");
        id =valor_id;
        tvnombre.setText(valor_nombre);
        tvmarca.setText(valor_marca);
        tvprecio.setText(valor_precio);
        tvstock.setText(valor_stock);


        btnrestar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View var1) {
                int resta = Integer.parseInt(tvcantidadelegida.getText().toString())-1;
                tvcantidadelegida.setText(String.valueOf(resta));
                if (resta <= 0){
                    btnrestar.setEnabled(true);
                    tvcantidadelegida.setText(String.valueOf(1));
                    Toast.makeText(DetalleProductoActivity.this, "Cantidad mínima es 1", Toast.LENGTH_SHORT).show();
                }else{
                    btnrestar.setEnabled(true);
                }
            }
        });

        btnsumar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View var1) {
                int suma = Integer.parseInt(tvcantidadelegida.getText().toString())+1;
                int stock = Integer.parseInt(tvstock.getText().toString());
                tvcantidadelegida.setText(String.valueOf(suma));
                if (suma>=1 && suma<=stock){
                    btnrestar.setEnabled(true);
                    btnsumar.setEnabled(true);
                }else{
                    Toast.makeText(DetalleProductoActivity.this, "Stock alcanzado", Toast.LENGTH_SHORT).show();
                    tvcantidadelegida.setText(String.valueOf(suma-1));
                    btnsumar.setEnabled(true);
                }
            }
        });

        btnComprar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View var1) {
                if (Integer.parseInt((String) tvstock.getText())<=0){
                    tvstock.setText("0");
                    tvcantidadelegida.setText("0");
                    btnComprar.setEnabled(false);
                    Toast.makeText(DetalleProductoActivity.this, "Lo sentimos, no hay stock disponible", Toast.LENGTH_SHORT).show();
                }else{
                    btnComprar.setEnabled(true);
                    int restareleccion = (Integer.parseInt((String) tvstock.getText()))-(Integer.parseInt((String) tvcantidadelegida.getText()));
                    tvstock.setText(String.valueOf(restareleccion));
                    Toast.makeText(DetalleProductoActivity.this, "Producto comprado y descontado", Toast.LENGTH_SHORT).show();
                }

            }
        });
    }
}