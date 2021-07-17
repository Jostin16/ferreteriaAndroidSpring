package idat.com.appferreteria.view;

import android.os.Bundle;
import android.widget.ListView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import idat.com.minimarketapispring.R;

public class CarritoActivity extends AppCompatActivity {

    ListView listVproductos;
    String valor_id,valor_nombre,valor_marca,valor_precio,valor_stock;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_carrito);
        valor_id = getIntent().getStringExtra("pnombre");
        valor_nombre = getIntent().getStringExtra("pnombre");
        valor_marca = getIntent().getStringExtra("pmarca");
        valor_precio = getIntent().getStringExtra("pprecio");
        valor_stock = getIntent().getStringExtra("pstock");
        Toast.makeText(this, valor_nombre, Toast.LENGTH_SHORT).show();

    }




}