package br.com.es3_lanchonete.dao;

import java.sql.*;
import java.util.LinkedList;
import java.util.List;
import br.com.es3_lanchonete.model.Pedido;

public class PedidoDAO implements GenericDAO<Pedido> {

    @Override
    public int insert(Pedido obj) {
        int chavePrimaria = -1;
        try (Connection connection = new ConnectionFactory().getConnection();
                PreparedStatement stmt
                = connection.prepareStatement(SQLs.INSERT_PEDIDO.getSql(),
                        Statement.RETURN_GENERATED_KEYS)) {
            stmt.setString(1, obj.getNomeCliente());
            stmt.setDouble(2, obj.getValor());
            stmt.setInt(3, obj.getNumMesa());
            stmt.setString(4, obj.getStatus());
            stmt.setString(5,  obj.getObservacao());
            stmt.setInt(6, obj.getIdPedido());
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
    public List<Pedido> listAll() {
        List<Pedido> lista = new LinkedList<>();

        try (Connection connection = new ConnectionFactory().getConnection();
                PreparedStatement stmt
                = connection.prepareStatement(SQLs.LISTALL_PEDIDO.getSql())) {

            System.out.println("Conexão aberta!");
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                int idPedido = rs.getInt("idPedido");
                String nomeCliente = rs.getString("nomeCliente");
                double valor = rs.getDouble("valor");
                int numMesa = rs.getInt("numMesa");
                String status = rs.getString("status");
                String observacao = rs.getString("observacao");
                lista.add(new Pedido(idPedido, nomeCliente, valor, numMesa, status, observacao));
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
    public int update(Pedido obj) {
        int retorno = -1;
        try (Connection connection = new ConnectionFactory().getConnection();
                PreparedStatement stmt
                = connection.prepareStatement(SQLs.UPDATE_PEDIDO.getSql())) {
            stmt.setString(1, obj.getNomeCliente());
            stmt.setDouble(2, obj.getValor());
            stmt.setInt(3, obj.getNumMesa());
            stmt.setString(4, obj.getStatus());
            stmt.setString(5,  obj.getObservacao());
            stmt.setInt(6, obj.getIdPedido());
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
    public int delete(Pedido obj) {
        int retorno = -1;
        try (Connection connection = new ConnectionFactory().getConnection();
                PreparedStatement stmt
                = connection.prepareStatement(SQLs.DELETE_PEDIDO.getSql())) {
            stmt.setInt(1, obj.getIdPedido());
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
    public Pedido findByID(int id) {
        Pedido obj = null;
        try (Connection connection = new ConnectionFactory().getConnection();
                PreparedStatement stmt
                = connection.prepareStatement(SQLs.FINDID_PEDIDO.getSql())) {
            stmt.setInt(1, id);

            System.out.println("Conexão aberta!");

            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
            	int idPedido = rs.getInt("idPedido");
                String nomeCliente = rs.getString("nomeCliente");
                //Itens itens = rs.get??
                double valor = rs.getDouble("valor");
                int numMesa = rs.getInt("numMesa");
                String status = rs.getString("status");
                String observacao = rs.getString("observacao");
                obj = new Pedido(idPedido, /*itens,*/nomeCliente, valor, numMesa, status, observacao);
            }

        } catch (SQLException e) {
            System.out.println("Exceção SQL - findById");
        } catch (Exception e) {
            System.out.println("Exceção no código!- findById");
        }
        return obj;
    }

}