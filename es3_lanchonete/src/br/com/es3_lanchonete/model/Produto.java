package br.com.es3_lanchonete.model;

import java.io.Serializable;
import java.util.List;
import java.util.Objects;

import br.com.es3_lanchonete.dao.ProdutoDAO;

public class Produto implements Serializable{
	
	private int idProduto;
	private String nome;
	private double precoUnitario;
	private int quantidade;
	public int getIdProduto() {
		return idProduto;
	}
	public void setIdProduto(int idProduto) {
		this.idProduto = idProduto;
	}
	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
	public double getPrecoUnitario() {
		return precoUnitario;
	}
	public void setPrecoUnitario(double precoUnitario) {
		this.precoUnitario = precoUnitario;
	}
	public int getQuantidade() {
		return quantidade;
	}
	public void setQuantidade(int quantidade) {
		this.quantidade = quantidade;
	}
	
	public Produto(int idProduto, String nome, double precoUnitario, int quantidade) {
		
		this.idProduto = idProduto;
		this.nome = nome;
		this.precoUnitario = precoUnitario;
		this.quantidade = quantidade;
	}
	
	public  List<Produto> listAll() { return new ProdutoDAO().listAll();}
    public int insert() {
        return new ProdutoDAO().insert(this);
    }
    public int delete(){
        return new ProdutoDAO().delete(this);
    }
    public int update() {
         return new ProdutoDAO().update(this);
    }

}
