package br.com.es3_lanchonete.dao;

public enum SQLs {
    INSERT_PEDIDO("insert into pedido(nomeCliente, valor, numMesa, status, observacao) values (?, ?, ?, ?, ?)"), 
    LISTALL_PEDIDO("select * from pedido"),
    DELETE_PEDIDO("delete from pedido where idpedido=? "),
    UPDATE_PEDIDO("update pedido set nomeCliente=?, valor=?, numMesa=?, status=?, observacao=? where idcliente=?"),
    FINDID_PEDIDO("select * from pedido where idpedido=?"),
    INSERT_PRODUTO("insert into produto(nome, precoUnitario, quantidade) values (?, ?, ?)"), 
    LISTALL_PRODUTO("select * from produto"),
    DELETE_PRODUTO("delete from produto where idProduto=? "),
    UPDATE_PRODUTO("update produto set nome=?, precoUnitario=?, quantidade=? where idProduto=?"),
    FINDID_PRODUTO("select * from produto where idProduto=?"),
    INSERT_FUNCIONARIO("insert into funcionario(nome, cpf, cargo) values (?, ?, ?)"), 
    LISTALL_FUNCIONARIO("select * from funcionario"),
    DELETE_FUNCIONARIO("delete from funcionario where idFuncionario=? "),
    UPDATE_FUNCIONARIO("update funcionario set nome=?, cpf=?, cargo=? where idFuncionario=?"),
    FINDID_FUNCIONARIO("select * from funcionario where idFuncionario=?");
  
    private final String sql;
    SQLs(String sql){
        this.sql = sql; 
    
    }

    public String getSql() {
        return sql;
    }    
}