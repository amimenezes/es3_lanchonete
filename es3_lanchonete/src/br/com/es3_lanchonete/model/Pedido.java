package br.com.es3_lanchonete.model;

import br.com.es3_lanchonete.dao.PedidoDAO;
import java.io.Serializable;
import java.util.List;
import java.util.Objects;

public class Pedido implements Serializable {

    private int idPedido;
    //private Itens itens[];
    private String nomeCliente;
    private double valor;
    private int numMesa;
    private String status;
    private String observacao;
    

    public Pedido() {
    }


    public Pedido(int idPedido, /*Itens itens, */String nomeCliente, double valor, int numMesa, String status, String observacao) {
        this.idPedido = idPedido;
        //this.itens = itens;
        this.nomeCliente = nomeCliente;
        this.valor = valor;
        this.numMesa = numMesa;
        this.status = status;
        this.observacao = observacao;
    }    

    public int getIdPedido() {
		return idPedido;
	}

	public void setIdPedido(int idPedido) {
		this.idPedido = idPedido;
	}

	public String getNomeCliente() {
		return nomeCliente;
	}

	public void setNomeCliente(String nomeCliente) {
		this.nomeCliente = nomeCliente;
	}

	public double getValor() {
		return valor;
	}

	public void setValor(double valor) {
		this.valor = valor;
	}

	public int getNumMesa() {
		return numMesa;
	}

	public void setNumMesa(int numMesa) {
		this.numMesa = numMesa;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getObservacao() {
		return observacao;
	}

	public void setObservacao(String observacao) {
		this.observacao = observacao;
	}

	
	/*@Override
    public int hashCode() {
        int hash = 7;
        hash = 29 * hash + Objects.hashCode(this.nomeCliente);
        hash = 29 * hash + Objects.hashCode(this.valor);
        hash = 29 * hash + Objects.hashCode(this.numMesa);
        hash = 29 * hash + Objects.hashCode(this.status);
        hash = 29 * hash + Objects.hashCode(this.observacao);
        return hash;
    }*/
	
	/*@Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Pedido other = (Pedido) obj;
        if (!Objects.equals(this.nome, other.nome)) {
            return false;
        }
        if (!Objects.equals(this.email, other.email)) {
            return false;
        }
        return true;
    }*/
    
    public  List<Pedido> listAll() { return new PedidoDAO().listAll();}
    public int insert() {
        return new PedidoDAO().insert(this);
    }
    public int delete(){
        return new PedidoDAO().delete(this);
    }    
    public int update() {
         return new PedidoDAO().update(this);
    }
}
