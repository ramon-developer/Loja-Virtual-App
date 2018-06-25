package br.com.marbetramon.carstoreapp.model;

import com.google.gson.annotations.SerializedName;
import java.io.Serializable;

public class Cars implements Serializable {

    public boolean selected;

    @SerializedName("id")
    private int id;

    @SerializedName("nome")
    private String nome;

    @SerializedName("descricao")
    private String descricao;

    @SerializedName("marca")
    private String marca;

    @SerializedName("quantidade")
    private int quantidade;

    @SerializedName("preco")
    private int preco;

    @SerializedName("imagem")
    private String imagem;

    public Cars(int id, String nome, String descricao, String marca, int quantidade, int preco, String image) {
        this.id = id;
        this.nome = nome;
        this.descricao = descricao;
        this.marca = marca;
        this.quantidade = quantidade;
        this.preco = preco;
        this.imagem = image;
    }

    public int getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public String getMarca() {
        return marca;
    }

    public void setMarca(String marca) {
        this.marca = marca;
    }

    public int getQuantidade() {
        return quantidade;
    }

    public void setQuantidade(Cars cars, int quantidade) {
        this.quantidade = quantidade;
    }

    public int getPreco() {
        return preco;
    }

    public void setPreco(int preco) {
        this.preco = preco;
    }

    public String getImagem() {
        return imagem;
    }

    public void setImage(String imagem) {
        this.imagem = imagem;
    }

}

