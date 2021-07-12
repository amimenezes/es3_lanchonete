package br.com.es3_lanchonete.model;

import br.com.es3_lanchonete.dao.PedidoDAO;
import java.io.Serializable;
import java.util.List;
import java.util.Objects;

public class Cliente implements Serializable {

    private int idCliente;
    private String nome;
    private String email;

    public Cliente() {
    }

    public Cliente(int idcliente, String nome, String email) {
        this.idCliente = idcliente;
        this.nome = nome;
        this.email = email;
    }

    public Cliente(String nome, String email) {
        this(-1, nome, email);
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public int getIdCliente() {
        return idCliente;
    }

    public void setIdCliente(int idcliente) {
        this.idCliente = idcliente;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 29 * hash + Objects.hashCode(this.nome);
        hash = 29 * hash + Objects.hashCode(this.email);
        return hash;
    }

    @Override
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
        final Cliente other = (Cliente) obj;
        if (!Objects.equals(this.nome, other.nome)) {
            return false;
        }
        if (!Objects.equals(this.email, other.email)) {
            return false;
        }
        return true;
    }
    
    public  List<Cliente> listAll() { return new ClienteDAO().listAll();}
    public int insert() {
        return new ClienteDAO().insert(this);
    }
    public int delete(){
        return new ClienteDAO().delete(this);
    }
    
    public int update() {
         return new ClienteDAO().update(this);
    }
}
