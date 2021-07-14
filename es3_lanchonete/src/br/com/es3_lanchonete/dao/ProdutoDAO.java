package br.com.es3_lanchonete.dao;

import java.sql.*;
import java.util.LinkedList;
import java.util.List;
import br.com.es3_lanchonete.model.Produto;

public class ProdutoDAO implements GenericDAO<Produto> {

    @Override
    public int insert(Produto obj) {
        int chavePrimaria = -1;
        try (Connection connection = new ConnectionFactory().getConnection();
                PreparedStatement stmt
                = connection.prepareStatement(SQLs.INSERT_PRODUTO.getSql(),
                        Statement.RETURN_GENERATED_KEYS)) {
            stmt.setString(1, obj.getNome());
            stmt.setDouble(2, obj.getPrecoUnitario());
            stmt.setInt(3, obj.getQuantidade());
            stmt.setInt(4, obj.getIdProduto());
            System.out.println("Conexão aberta!");
            stmt.execute();
            System.out.println("Dados Gravados!");
            ResultSet chaves = stmt.getGeneratedKeys();
            if (chaves.next()) {
                chavePrimaria = chaves.getInt(1);
            }
            connection.close();
        } catch (SQLException e) {
            System.out.println("exceção com recursos");
        }
        return chavePrimaria;
    }

    @Override
    public List<Produto> listAll() {
        List<Produto> lista = new LinkedList<>();

        try (Connection connection = new ConnectionFactory().getConnection();
                PreparedStatement stmt
                = connection.prepareStatement(SQLs.LISTALL_PRODUTO.getSql())) {

            System.out.println("Conexão aberta!");
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                int idProduto = rs.getInt("idProduto");
                String nome = rs.getString("nome");
                double precoUnitario = rs.getDouble("precoUnitario");
                int quantidade = rs.getInt("quantidade");
                lista.add(new Produto(idProduto, nome, precoUnitario, quantidade));
            }
            connection.close();
            return lista;
        } catch (SQLException e) {
            System.out.println("Exceção SQL - listAll");
        } catch (Exception e) {
            System.out.println("Exceção no código - listAll!");
        }
        return null;
    }

    @Override
    public int update(Produto obj) {
        int retorno = -1;
        try (Connection connection = new ConnectionFactory().getConnection();
                PreparedStatement stmt
                = connection.prepareStatement(SQLs.UPDATE_PRODUTO.getSql())) {
            stmt.setString(1, obj.getNome());
            stmt.setDouble(2, obj.getPrecoUnitario());
            stmt.setInt(3, obj.getQuantidade());
            stmt.setInt(4, obj.getIdProduto());
            System.out.println("Conexão aberta!");
            if (stmt.executeUpdate()>0) {
                retorno = 1;
            }
            connection.close();
        } catch (SQLException e) {
            System.out.println("Exceção SQL - update");
        } catch (Exception e) {
            System.out.println("Exceção no código! - update");
        }
        return retorno;
    }

    @Override
    public int delete(Produto obj) {
        int retorno = -1;
        try (Connection connection = new ConnectionFactory().getConnection();
                PreparedStatement stmt
                = connection.prepareStatement(SQLs.DELETE_PRODUTO.getSql())) {
            stmt.setInt(1, obj.getIdProduto());
            if (stmt.executeUpdate()>0) {
                retorno = 1;
            }
            connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println("Exceção SQL - delete");
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("Exceção no código! - delete");
        }
        return retorno;
    }

    @Override
    public Produto findByID(int id) {
        Produto obj = null;
        try (Connection connection = new ConnectionFactory().getConnection();
                PreparedStatement stmt
                = connection.prepareStatement(SQLs.FINDID_PRODUTO.getSql())) {
            stmt.setInt(1, id);
            System.out.println("Conexão aberta!");

            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
            	int idProduto = rs.getInt("idProduto");
                String nome = rs.getString("nome");
                //Itens itens = rs.get??
                double precoUnitario = rs.getDouble("precoUnitario");
                int quantidade = rs.getInt("quantidade");
                obj = new Produto(idProduto, nome, precoUnitario, quantidade);
            }

        } catch (SQLException e) {
            System.out.println("Exceção SQL - findById");
        } catch (Exception e) {
            System.out.println("Exceção no código!- findById");
        }
        return obj;
    }

}