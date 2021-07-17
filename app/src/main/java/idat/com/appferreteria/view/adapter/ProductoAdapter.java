package idat.com.appferreteria.view.adapter;

import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import com.bumptech.glide.Glide;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import idat.com.appferreteria.model.Producto;
import idat.com.minimarketapispring.databinding.ItemProductoBinding;
import idat.com.appferreteria.view.DetalleProductoActivity;

public class ProductoAdapter extends RecyclerView.Adapter<ProductoAdapter.ViewHolder>{

    private ArrayList<Producto> listProducto;
    private Context context;
    private ArrayList<Producto> listaOriginal;

    public ProductoAdapter(Context context){
        this.context = context;
        this.listProducto = new ArrayList<>();
        this.listaOriginal = new ArrayList<>();
    }


    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        ItemProductoBinding recyclerBinding = ItemProductoBinding.inflate(layoutInflater,parent,false);
        return new ViewHolder(recyclerBinding);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        final Producto objProducto = listProducto.get(position);
        holder.recyclerBinding.tvnombre.setText(objProducto.getNombre());
        holder.recyclerBinding.tvmarca.setText("Marca: "+objProducto.getMarca());
        holder.recyclerBinding.tvprecio.setText("Precio: "+objProducto.getPrecio().toString());
        holder.recyclerBinding.tvcantidad.setText("Cantidad: "+objProducto.getCantidad().toString());
        Glide.with(context).load(objProducto.getUrl()).into(holder.recyclerBinding.imagenProducto);
        System.out.println(holder.recyclerBinding.imagenProducto);
        holder.recyclerBinding.idCardProducto.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View var1) {
                //System.out.println(objProducto.getId());
                Intent intent = new Intent(context, DetalleProductoActivity.class);
                intent.putExtra("id",objProducto.getId() );
                intent.putExtra("nombreproducto",objProducto.getNombre() );
                intent.putExtra("marca",objProducto.getMarca() );
                intent.putExtra("stock",objProducto.getCantidad().toString() );
                intent.putExtra("precio",objProducto.getPrecio().toString() );
                intent.putExtra("imagen", objProducto.getUrl());
                context.startActivity(intent);
            }
        });
    }

    public void filtrado(String txtbuscar){
        int longitud=txtbuscar.length();
        if(longitud == 0){
            listProducto.clear();
            listProducto.addAll(listaOriginal);
        } else {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
                List<Producto> productos = listProducto.stream().filter(i -> i.getNombre().toLowerCase().contains(txtbuscar.toLowerCase())).collect(Collectors.toList());
                listProducto.clear();
                listProducto.addAll(productos);
            }else{
                listProducto.clear();
                for(Producto p: listaOriginal){
                    if(p.getNombre().toLowerCase().contains(txtbuscar.toLowerCase())){
                        listProducto.add(p);
                    }
                }
            }
        }
        notifyDataSetChanged();
    }

    public void agregarLista(ArrayList<Producto> listaProducto){
        listProducto.addAll(listaProducto);
        listaOriginal.addAll(listProducto);
        notifyDataSetChanged();
    }

    @Override
    public int getItemCount() {
        return listProducto.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        ItemProductoBinding recyclerBinding;
        public ViewHolder(@NonNull ItemProductoBinding itemView) {
            super(itemView.getRoot());
            recyclerBinding = itemView;
        }
    }
}