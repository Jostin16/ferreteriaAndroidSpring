package idat.com.appferreteria.view.adapter;


import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;

import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;

import idat.com.appferreteria.model.Categorias;
import idat.com.minimarketapispring.databinding.ItemCategoriaBinding;
import idat.com.appferreteria.view.ProductoActivity;

public class CategoriaAdapter extends RecyclerView.Adapter<CategoriaAdapter.ViewHolder> {

    private ArrayList<Categorias> dataCategoria;
    private Context context;
    private ProductoActivity activity;

    public CategoriaAdapter(Context context){
        this.context = context;
        dataCategoria = new ArrayList<>();
        activity= new ProductoActivity();
    }

    @NotNull
    @Override
    public CategoriaAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        ItemCategoriaBinding recyclerBinding =
                ItemCategoriaBinding.inflate(layoutInflater, parent, false);
        return new ViewHolder(recyclerBinding);
    }



    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        final Categorias objCategoria = dataCategoria.get(position);
        holder.recyclerBinding.descripcionCateg.setText(objCategoria.getDescripcion());
        Glide.with(context)
                .load(objCategoria.getUrl())
                .into(holder.recyclerBinding.imagenCategoria);

        holder.recyclerBinding.idCardCategoria.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View var1) {
                System.out.println(objCategoria.getId());
                ProductoActivity.categoriaid=objCategoria.getId();
                Intent intent = new Intent(context, ProductoActivity.class);
                context.startActivity(intent);
            }
        });

    }

    public void agregarLista(ArrayList<Categorias> listaCategoria) {
        dataCategoria.addAll(listaCategoria);
        notifyDataSetChanged();
    }

    @Override
    public int getItemCount() {
        return dataCategoria.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        ItemCategoriaBinding recyclerBinding;
        public ViewHolder (@NonNull ItemCategoriaBinding itemView) {
            super(itemView.getRoot());
            recyclerBinding = itemView;

        }
    }




}
