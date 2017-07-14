package com.rodrigues.sucesso2017;

import android.util.Log;

/**
 * Created by gabri on 13/07/2017.
 */

public class Produto {
    private static final String TAG = "Produto";

    private String mNome;

    private Double mPreco;

    private String mMarca;

    private String mCategoria;

    public Produto (String nome, Double preco, String marca, String categoria){
        mNome = nome;
        mPreco = preco;
        mMarca = marca;
        mCategoria = categoria;

        Log.d(TAG, "Construtor");
    }

    public String getmNome() {
        return mNome;
    }

    public Double getmPreco() {
        return mPreco;
    }

    public String getmMarca() {
        return mMarca;
    }

    public String getmCategoria() {
        return mCategoria;
    }
}
