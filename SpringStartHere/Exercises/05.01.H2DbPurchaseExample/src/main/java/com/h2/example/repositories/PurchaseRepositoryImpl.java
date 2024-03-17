package com.h2.example.repositories;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import com.h2.example.exceptions.RepositoryException;
import com.h2.example.models.Purchase;

@Repository
public class PurchaseRepositoryImpl implements PurchaseRepository {

    private final JdbcTemplate jdbcTemplate;

    public PurchaseRepositoryImpl(JdbcTemplate jdbc) {
        this.jdbcTemplate = jdbc;
    }

    @Override
    public void save(Purchase purchase) throws RepositoryException {
        try {
            String sql = "INSERT INTO purchases (product, price, quantity) VALUES  (?, ?, ?)";
            jdbcTemplate.update(sql, purchase.getProduct(), purchase.getPrice(), purchase.getQuantity());
        } catch (DataAccessException e) {
            throw new RepositoryException("Failed to save purchase", e);
        }
    }

    @Override
    public void deleteById(Long id) throws RepositoryException {
        try {
            String sql = "DELETE FROM purchases WHERE id = ?";
            jdbcTemplate.update(sql, id);
        } catch (DataAccessException e) {
            throw new RepositoryException("Failed to delete purchase with id: " + id, e);
        }
    }

    @Override
    public Purchase findById(Long id) throws RepositoryException {
        try {
            String sql = "SELECT * FROM purchases WHERE id = ?";
            return jdbcTemplate.queryForObject(sql, new PurchaseMapper(), id);
        } catch (DataAccessException e) {
            throw new RepositoryException("Failed to find purchase with id: " + id, e);
        }
    }

    @Override
    public List<Purchase> findAll() throws RepositoryException {
        try {
            String sql = "SELECT * FROM purchases";
            return jdbcTemplate.query(sql, new PurchaseMapper());
        } catch (DataAccessException e) {
            throw new RepositoryException("Failed to retrieve purchases", e);
        }
    }

    private static class PurchaseMapper implements RowMapper<Purchase> {

        @Override
        @SuppressWarnings("null")
        public Purchase mapRow(ResultSet rs, int rowNum) throws SQLException {
            return new Purchase(
                    rs.getLong("id"),
                    rs.getString("product"),
                    rs.getBigDecimal("price"),
                    rs.getInt("quantity"));
        };
    }
}
