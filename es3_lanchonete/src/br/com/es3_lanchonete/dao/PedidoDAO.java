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
                = connection.prepareStatement(SQLs.INSERT_CLIENTE.getSql(),
                        Statement.RETURN_GENERATED_KEYS)) {
            System.out.println("Conexão aberta!");
            stmt.setString(1, obj.getNome());
            stmt.setString(2, obj.getEmail());
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
    public List<Cliente> listAll() {
        List<Cliente> lista = new LinkedList<>();

        try (Connection connection = new ConnectionFactory().getConnection();
                PreparedStatement stmt
                = connection.prepareStatement(SQLs.LISTALL_CLIENTE.getSql())) {

            System.out.println("Conexão aberta!");
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                int idcliente = rs.getInt("idcliente");
                String nome = rs.getString("nome");
                String email = rs.getString("email");
                lista.add(new Cliente(idcliente, nome, email));
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
    public int update(Cliente obj) {
        int retorno = -1;
        try (Connection connection = new ConnectionFactory().getConnection();
                PreparedStatement stmt
                = connection.prepareStatement(SQLs.UPDATE_CLIENTE.getSql())) {
            stmt.setString(1, obj.getNome());
            stmt.setString(2, obj.getEmail());
            stmt.setInt(3, obj.getIdCliente());
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
    public int delete(Cliente obj) {
        int retorno = -1;
        try (Connection connection = new ConnectionFactory().getConnection();
                PreparedStatement stmt
                = connection.prepareStatement(SQLs.DELETE_CLIENTE.getSql())) {
            stmt.setInt(1, obj.getIdCliente());
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
    public Cliente findByID(int id) {
        Cliente obj = null;
        try (Connection connection = new ConnectionFactory().getConnection();
                PreparedStatement stmt
                = connection.prepareStatement(SQLs.FINDID_CLIENTE.getSql())) {
            stmt.setInt(1, id);

            System.out.println("Conexão aberta!");

            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                String nome = rs.getString("nome");
                String endereco = rs.getString("email");
                obj = new Cliente(nome, endereco);
            }

        } catch (SQLException e) {
            System.out.println("Exceção SQL - findById");
        } catch (Exception e) {
            System.out.println("Exceção no código!- findById");
        }
        return obj;
    }

}

