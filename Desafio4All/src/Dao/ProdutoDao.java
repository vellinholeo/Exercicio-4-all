/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Dao;

import Model.Produto;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author vellinholeo
 */
public class ProdutoDao {

    Connection connection;

    public ProdutoDao() throws ClassNotFoundException {
        this.connection = new ConnectionFactory().getConnection();
    }

    public void adiciona(Produto produto) {
        String sql = "insert into produtos " + "(nome,preco,imagem)" + " values (?,?,?)";
        try {
            // prepared statement para inserção
            PreparedStatement stmt = connection.prepareStatement(sql);
            // seta os valores
            stmt.setString(1, produto.getNome());
            stmt.setDouble(2, produto.getPreco());
            stmt.setString(3, produto.getImagem());
            // executa
            stmt.execute();
            stmt.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public List<Produto> lista() throws SQLException, IOException {
        List<Produto> produto = new ArrayList<>();
        PreparedStatement stmt = this.connection.prepareStatement("select * from produtos");
        ResultSet rs = stmt.executeQuery();

        while (rs.next()) {
            // criando o objeto Contato
            Produto prod = new Produto();
            prod.setId(rs.getInt("id"));
            prod.setImagem(rs.getString("imagem"));
            prod.setNome(rs.getString("nome"));
            prod.setPreco(rs.getDouble("preco"));
            // adicionando o objeto à lista
            produto.add(prod);
        }

        rs.close();
        stmt.close();

        return produto;
    }

    public void altera(Produto produto) {
        String sql = "update produtos set nome=?, preco=?" + " where id=?";
        try {
            PreparedStatement stmt = connection.prepareStatement(sql);
            stmt.setString(1, produto.getNome());
            stmt.setDouble(2, produto.getPreco());
            stmt.setLong(3, produto.getId());
            stmt.execute();
            stmt.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
