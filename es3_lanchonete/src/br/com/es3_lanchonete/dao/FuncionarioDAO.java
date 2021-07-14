package br.com.es3_lanchonete.dao;

import java.sql.*;
import java.util.LinkedList;
import java.util.List;
import br.com.es3_lanchonete.model.Funcionario;

public class FuncionarioDAO implements GenericDAO<Funcionario> {

    @Override
    public int insert(Funcionario obj) {
        int chavePrimaria = -1;
        try (Connection connection = new ConnectionFactory().getConnection();
                PreparedStatement stmt
                = connection.prepareStatement(SQLs.INSERT_FUNCIONARIO.getSql(),
                        Statement.RETURN_GENERATED_KEYS)) {
            stmt.setString(1, obj.getNome());
            stmt.setString(2, obj.getCpf());
            stmt.setString(3,  obj.getCargo());
            stmt.setInt(4, obj.getIdFuncionario());
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
    public List<Funcionario> listAll() {
        List<Funcionario> lista = new LinkedList<>();

        try (Connection connection = new ConnectionFactory().getConnection();
                PreparedStatement stmt
                = connection.prepareStatement(SQLs.LISTALL_FUNCIONARIO.getSql())) {

            System.out.println("Conexão aberta!");
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                int idFuncionario = rs.getInt("idFuncionario");
                String nome = rs.getString("nome");               
                String cpf = rs.getString("cpf");
                String cargo = rs.getString("cargo");
                lista.add(new Funcionario(idFuncionario, nome, cpf, cargo));
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
    public int update(Funcionario obj) {
        int retorno = -1;
        try (Connection connection = new ConnectionFactory().getConnection();
                PreparedStatement stmt
                = connection.prepareStatement(SQLs.UPDATE_FUNCIONARIO.getSql())) {
            stmt.setString(1, obj.getNome());
            stmt.setString(2, obj.getCpf());
            stmt.setString(3,  obj.getCargo());
            stmt.setInt(4, obj.getIdFuncionario());
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
    public int delete(Funcionario obj) {
        int retorno = -1;
        try (Connection connection = new ConnectionFactory().getConnection();
                PreparedStatement stmt
                = connection.prepareStatement(SQLs.DELETE_FUNCIONARIO.getSql())) {
            stmt.setInt(1, obj.getIdFuncionario());
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
    public Funcionario findByID(int id) {
        Funcionario obj = null;
        try (Connection connection = new ConnectionFactory().getConnection();
                PreparedStatement stmt
                = connection.prepareStatement(SQLs.FINDID_FUNCIONARIO.getSql())) {
            stmt.setInt(1, id);

            System.out.println("Conexão aberta!");

            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
            	int idFuncionario = rs.getInt("idFuncionario");
                String nome = rs.getString("nome");
                String cpf = rs.getString("cpf");
                String cargo = rs.getString("cargo");
                obj = new Funcionario(idFuncionario, nome, cpf, cargo);
            }

        } catch (SQLException e) {
            System.out.println("Exceção SQL - findById");
        } catch (Exception e) {
            System.out.println("Exceção no código!- findById");
        }
        return obj;
    }

}