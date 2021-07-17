package idat.com.appferreteria.view;

import android.os.Bundle;
import android.view.View;

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
import idat.com.appferreteria.model.Categorias;
import idat.com.minimarketapispring.R;
import idat.com.minimarketapispring.databinding.ActivityCategoriaBinding;
import idat.com.appferreteria.view.adapter.CategoriaAdapter;

public class CategoriaActivity extends AppCompatActivity {

    DrawerLayout drawerLayout;
    private ActivityCategoriaBinding binding;
    private CategoriaAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityCategoriaBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        adapter = new CategoriaAdapter( this);
        binding.rvcategorias.setLayoutManager(
                new GridLayoutManager(CategoriaActivity.this, 2)
        );
        binding.rvcategorias.setAdapter(adapter);
        obtenerCategoria(new Constantes().URL_BASE_CATEGORIA);
        drawerLayout = findViewById(R.id.drawer_layout);
        getSupportActionBar().hide();
    }

    private void obtenerCategoria(String url_base_categoria) {
        RequestQueue colapeticiones= Volley.newRequestQueue(this);
        JsonArrayRequest jsonArrayRequest = new JsonArrayRequest(
                Request.Method.GET,
                url_base_categoria,
                null,
                new Response.Listener<JSONArray>() {
                    @Override
                    public void onResponse(JSONArray response) {
                        try {
                            ArrayList<Categorias> miListaCategoria = new ArrayList<>();
                            for (int i = 0; i < response.length(); i++) {
                                JSONObject jsonObject = response.getJSONObject(i);
                                Categorias nuevaCategoria = new Categorias(
                                        jsonObject.getInt("id"),
                                        jsonObject.getString("descripcion"),
                                        jsonObject.getString("url")
                                );
                                miListaCategoria.add(nuevaCategoria);
                            }
                            adapter.agregarLista(miListaCategoria);
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
        recreate();
    }

    public void ClickProductos(View view){
        MainActivity.redirecActivity(this, ProductoActivity.class);
    }

    public void ClickLogout(View view){
        MainActivity.logout(this);
    }

    @Override
    public void onPause() {
        super.onPause();
        MainActivity.closeDrawer(drawerLayout);
    }
}
