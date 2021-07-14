package br.com.es3_lanchonete.model;

import br.com.es3_lanchonete.dao.FuncionarioDAO;
import br.com.es3_lanchonete.dao.PedidoDAO;

import java.io.Serializable;
import java.util.List;
import java.util.Objects;

public class Funcionario implements Serializable {

    private int idFuncionario;
    private String nome;
    private String cpf;
    private String cargo;
    

    public Funcionario() {
    }


    public Funcionario(int idFuncionario, /*Itens itens, */String nome, String cpf, String cargo) {
        this.idFuncionario = idFuncionario;
        this.nome = nome;
        this.cpf = cpf;
        this.cargo = cargo;
    }    

    public int getIdFuncionario() {
		return idFuncionario;
	}

	public void setIdFuncionario(int idFuncionario) {
		this.idFuncionario = idFuncionario;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getCpf() {
		return cpf;
	}

	public void setCpf(String cpf) {
		this.cpf = cpf;
	}

	public String getCargo() {
		return cargo;
	}

	public void setCargo(String cargo) {
		this.cargo = cargo;
	}

	
	/*@Override
    public int hashCode() {
        int hash = 7;
        hash = 29 * hash + Objects.hashCode(this.nome);
        hash = 29 * hash + Objects.hashCode(this.cpf);
        hash = 29 * hash + Objects.hashCode(this.cargo);
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
        final Funcionario other = (Funcionario) obj;
        if (!Objects.equals(this.nome, other.nome)) {
            return false;
        }
        if (!Objects.equals(this.email, other.email)) {
            return false;
        }
        return true;
    }*/
    
    public  List<Funcionario> listAll() { return new FuncionarioDAO().listAll();}
    public int insert() {
        return new FuncionarioDAO().insert(this);
    }
    public int delete(){
        return new FuncionarioDAO().delete(this);
    }    
    public int update(){
    	return new FuncionarioDAO().update(this);
    }
}

