package com.rodrigues.sucesso2017;

import android.app.Activity;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.ArrayList;

import static android.R.attr.tag;

/**
 * Created by gabri on 13/07/2017.
 */

public class ProdutoAdapter extends ArrayAdapter<Produto> {
    private static final String TAG = "Produto Adapter";

    public ProdutoAdapter(Activity contex, ArrayList<Produto> produtos) {
        super(contex, 0, produtos);
        Log.d(TAG, "Construiu");
    }

    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        View gridItemView = convertView;
        if (gridItemView == null) {
            gridItemView = LayoutInflater.from(getContext()).inflate(
                    R.layout.list_item, parent, false);
        }

        Produto correntProduto = getItem(position);

        TextView nomeP = (TextView) gridItemView.findViewById(R.id.nomeP_text_view);
        nomeP.setText(correntProduto.getmNome());

        TextView marca = (TextView) gridItemView.findViewById(R.id.marca_text_view);
        marca.setText(correntProduto.getmMarca());

        TextView preco = (TextView) gridItemView.findViewById(R.id.preco_text_view);
        preco.setText(correntProduto.getmPreco().toString());

        return gridItemView;
    }
}
