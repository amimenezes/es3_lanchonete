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
                = connection.prepareStatement(SQLs.INSERT_Pedido.getSql(),
                        Statement.RETURN_GENERATED_KEYS)) {
            System.out.println("Conexão aberta!");
            stmt.setString(1, obj.getIdPedido());
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
    public List<Pedido> listAll() {
        List<Pedido> lista = new LinkedList<>();

        try (Connection connection = new ConnectionFactory().getConnection();
                PreparedStatement stmt
                = connection.prepareStatement(SQLs.LISTALL_Pedido.getSql())) {

            System.out.println("Conexão aberta!");
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                int idPedido = rs.getInt("idPedido");
                String nome = rs.getString("nome");
                String email = rs.getString("email");
                lista.add(new Pedido(idPedido, nome, email));
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
                = connection.prepareStatement(SQLs.UPDATE_Pedido.getSql())) {
            stmt.setString(1, obj.getNome());
            stmt.setString(2, obj.getEmail());
            stmt.setInt(3, obj.getIdPedido());
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
                = connection.prepareStatement(SQLs.DELETE_Pedido.getSql())) {
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
                = connection.prepareStatement(SQLs.FINDID_Pedido.getSql())) {
            stmt.setInt(1, id);

            System.out.println("Conexão aberta!");

            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                String nome = rs.getString("nome");
                String endereco = rs.getString("email");
                obj = new Pedido(nome, endereco);
            }

        } catch (SQLException e) {
            System.out.println("Exceção SQL - findById");
        } catch (Exception e) {
            System.out.println("Exceção no código!- findById");
        }
        return obj;
    }

	@Override
	public int update(Pedido obj) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int delete(Pedido obj) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public Pedido findByID(int id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Pedido findByID(int id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Pedido findByID(int id) {
		// TODO Auto-generated method stub
		return null;
	}

}
