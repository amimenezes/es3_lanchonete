package br.com.es3_lanchonete.dao;

public enum SQLs {
    INSERT_CLIENTE("insert into cliente(nome, email) values (?, ?)"), 
    LISTALL_CLIENTE("select * from cliente"),
    DELETE_CLIENTE("delete from cliente where idcliente=? "),
    UPDATE_CLIENTE("update cliente set nome=?, email=? where idcliente=?"),
    FINDID_CLIENTE("select * from cliente where idcliente=?");
  
    private final String sql;
    SQLs(String sql){
        this.sql = sql; 
    
    }

    public String getSql() {
        return sql;
    }    
}