package br.com.es3_lanchonete.dao;

public enum SQLs {
    INSERT_PEDIDO("insert into pedido(nomeCliente, valor, numMesa, status, observacao) values (?, ?, ?, ?, ?)"), 
    LISTALL_PEDIDO("select * from pedido"),
    DELETE_PEDIDO("delete from pedido where idpedido=? "),
    UPDATE_PEDIDO("update pedido set nomeCliente=?, valor=?, numMesa=?, status=?, observacao=? where idcliente=?"),
    FINDID_PEDIDO("select * from pedido where idpedido=?");
  
    private final String sql;
    SQLs(String sql){
        this.sql = sql; 
    
    }

    public String getSql() {
        return sql;
    }    
}