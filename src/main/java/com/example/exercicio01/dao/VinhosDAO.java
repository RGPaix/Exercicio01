package com.example.exercicio01.dao;

import com.example.exercicio01.config.ConnectionFactory;
import com.example.exercicio01.model.Vinho;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.sql.*;

public class VinhosDAO implements iVinhosDAO {
    @Override
    public Vinho create(Vinho vinho) {
        try (Connection connection = ConnectionFactory.getConnection()) {
            String query = "INSERT INTO vinhos (nome_do_vinho, tipo, safra, preco) VALUES (?, ?, ?, ?)";
            PreparedStatement ps = connection.prepareStatement(query, Statement.RETURN_GENERATED_KEYS);
            ps.setString(1, vinho.getNomeDoVinho());
            ps.setString(2, vinho.getTipo().name());
            ps.setInt(3, vinho.getSafra());

            BigDecimal precoCorrigido = vinho.getPreco().setScale(2, RoundingMode.HALF_UP);
            ps.setBigDecimal(4, precoCorrigido);

            System.out.println("SQL gerado: " + ps.toString());

            int affectedRows = ps.executeUpdate();
            if (affectedRows == 0) {
                throw new SQLException("A inserção falhou, nenhum registro foi adicionado.");
            }

            try (ResultSet rs = ps.getGeneratedKeys()) {
                if (rs.next()) {
                    vinho.setId(rs.getLong(1));
                } else {
                    throw new SQLException("A inserção falhou, nenhum ID foi retornado.");
                }
            }
        } catch (SQLException e) {
            throw new RuntimeException("Erro ao inserir vinho: " + e.getMessage(), e);
        }
        return vinho;
    }
}
