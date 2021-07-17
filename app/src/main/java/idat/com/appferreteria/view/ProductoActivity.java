package idat.com.appferreteria.view;

import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.SearchView;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.recyclerview.widget.GridLayoutManager;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

import idat.com.appferreteria.MainActivity;
import idat.com.appferreteria.common.Constantes;
import idat.com.appferreteria.model.Producto;
import idat.com.minimarketapispring.R;
import idat.com.minimarketapispring.databinding.ActivityProductoBinding;
import idat.com.appferreteria.view.adapter.ProductoAdapter;

public class ProductoActivity extends AppCompatActivity implements SearchView.OnQueryTextListener{

    SearchView txtbuscar;
    DrawerLayout drawerLayout;

    private ActivityProductoBinding binding;
    private ProductoAdapter adapter;

    public static Integer categoriaid;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityProductoBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        txtbuscar = findViewById(R.id.txtbuscar);
        adapter = new ProductoAdapter(this);
        binding.rvproductos.setLayoutManager(
                new GridLayoutManager(ProductoActivity.this, 1)
        );
        binding.rvproductos.setAdapter(adapter);

        if (categoriaid==null)
            obtenerProductos(new Constantes().URL_BASE_PRODUCTO);
        else
            obtenerProductos(new Constantes().URL_PRODUCTO_CATEGORIA+categoriaid.toString());



        drawerLayout = findViewById(R.id.drawer_layout);
        getSupportActionBar().hide();

        initListener();
    }

    private void initListener(){
        txtbuscar.setOnQueryTextListener(this);
    }

    private void obtenerProductos(String url) {
        RequestQueue colapeticiones = Volley.newRequestQueue(this);
        JsonArrayRequest jsonArrayRequest = new JsonArrayRequest(
                Request.Method.GET,
                url,
                null,
                new Response.Listener<JSONArray>() {
                    @RequiresApi(api = Build.VERSION_CODES.KITKAT)
                    @Override
                    public void onResponse(JSONArray response) {
                        try {
                            ArrayList<Producto> miListaProducto = new ArrayList<>();
                            for (int i = 0; i < response.length(); i++) {
                                JSONObject jsonObject = response.getJSONObject(i);
                                Producto nuevoProducto = new Producto(
                                        jsonObject.getString("nombre"),
                                        jsonObject.getDouble("precio"),
                                        jsonObject.getInt("cantidad"),
                                        jsonObject.getString("marca"),
                                        jsonObject.getString("url")
                                );
                                miListaProducto.add(nuevoProducto);
                            };
                            adapter.agregarLista(miListaProducto);
                        } catch (JSONException ex) {

                        }
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

            }
        });
        colapeticiones.add(jsonArrayRequest);
    }


    public void ClickMenu(View view){
        MainActivity.openDrawer(drawerLayout);
    }


    public void ClickCategorias(View view){
        MainActivity.redirecActivity(this, CategoriaActivity.class);
    }

    public void ClickProductos(View view){
        recreate();
    }


    public void ClickLogout(View view){
        MainActivity.logout(this);
    }

    @Override
    public void onPause() {
        super.onPause();
        MainActivity.closeDrawer(drawerLayout);
    }

    @Override
    public boolean onQueryTextSubmit(String s) {
        return false;
    }

    @Override
    public boolean onQueryTextChange(String s) {
        adapter.filtrado(s);
        return false;
    }
}