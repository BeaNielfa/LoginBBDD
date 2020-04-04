package com.example.loginbbdd.ui.Animales;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;


import com.example.loginbbdd.OnAnimalesInteractionListener;
import com.example.loginbbdd.R;

import java.util.ArrayList;

import io.realm.OrderedRealmCollection;
import io.realm.RealmChangeListener;
import io.realm.RealmList;
import io.realm.RealmResults;


public class MyAnimalRecyclerViewAdapter extends RecyclerView.Adapter<MyAnimalRecyclerViewAdapter.ViewHolder> {

    private final ArrayList<Animales> mValues;
    private final OnAnimalesInteractionListener mListener;
    private Context ctx;

    public MyAnimalRecyclerViewAdapter(Context context, ArrayList<Animales> items, OnAnimalesInteractionListener listener) {
        mValues = items;
        mListener = listener;
        ctx = context;


    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.animal_item, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, int position) {
        holder.mItem = mValues.get(position);

        holder.txtNombre.setText(holder.mItem.getNombre());
        holder.txtColor.setText(holder.mItem.getColor());
        holder.txtRaza.setText(holder.mItem.getRaza());
        /*holder.imgFoto.setText(holder.mItem.getUrlFoto());
        if(!holder.mItem.getUrlFoto().isEmpty()){
             Picasso.get()
                .load(holder.mItem.getUrlFoto())
                .into(holder.imgFoto);}*/

        holder.mView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (null != mListener) {
                    // Notify the active callbacks interface (the activity, if the
                    // fragment is attached to one) that an item has been selected.
                    mListener.onAnimalClick(holder.mItem);

                }
            }
        });

        holder.imgEditar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (null != mListener) {
                    // Notify the active callbacks interface (the activity, if the
                    // fragment is attached to one) that an item has been selected.
                    mListener.onAnimalEdit(holder.mItem);

                }
            }
        });

        holder.imgBorrar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (null != mListener) {
                    // Notify the active callbacks interface (the activity, if the
                    // fragment is attached to one) that an item has been selected.
                    mListener.onAnimalBorrar(holder.mItem);
                }
            }
        });
    }


    @Override
    public int getItemCount() {
        return mValues.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        public final View mView;
        public final TextView txtNombre;
        public final TextView txtRaza;
        public final TextView txtColor;
        public final ImageView imgFoto;
        public final ImageView imgEditar;
        public final ImageView imgBorrar;
        public Animales mItem;

        public ViewHolder(View view) {
            super(view);
            mView = view;
            txtNombre = (TextView) view.findViewById(R.id.textViewNombre);
            txtColor = (TextView) view.findViewById(R.id.textViewColor);
            txtRaza = (TextView) view.findViewById(R.id.textViewRaza);
            imgFoto = (ImageView) view.findViewById(R.id.imageViewFoto);
            imgEditar = (ImageView) view.findViewById(R.id.imageViewEditar);
            imgBorrar = (ImageView) view.findViewById(R.id.imageViewBorrar);
        }

        @Override
        public String toString() {
            return super.toString() + " '" + txtNombre.getText() + "'";
        }
    }
}


