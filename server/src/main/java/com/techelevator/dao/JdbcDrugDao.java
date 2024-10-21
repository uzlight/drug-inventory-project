package com.techelevator.dao;

import com.techelevator.exception.DaoException;
import com.techelevator.model.Drug;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.jdbc.CannotGetJdbcConnectionException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.rowset.SqlRowSet;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class JdbcDrugDao implements DrugDao{

    private final JdbcTemplate template;

    public JdbcDrugDao(JdbcTemplate jdbcTemplate) {
        this.template = jdbcTemplate;
    }

    @Override
    public List<Drug> getAllDrugs() {
        List<Drug> drugs = new ArrayList<>();
        String sql = "SELECT * FROM drug ORDER BY drug_name";

        try {
            SqlRowSet results = template.queryForRowSet(sql);
            while (results.next()) {
                Drug drug = mapRowToDrug(results);
                drugs.add(drug);
            }
        } catch (CannotGetJdbcConnectionException e) {
            throw new DaoException("Unable to connect to server or database", e);
        }
        return drugs;
    }

    @Override
    public List<Drug> getAllDrugsForClass(int classId) {
        List<Drug> drugs = new ArrayList<>();
        String sql = "SELECT * FROM drug WHERE class_id = ?";
        try {
            SqlRowSet results = template.queryForRowSet(sql, classId);
            while (results.next()) {
                Drug drug = mapRowToDrug(results);
                drugs.add(drug);
            }
        }catch (CannotGetJdbcConnectionException e) {
            throw new DaoException("Unable to connect to server or database", e);
        }
        return drugs;
    }

    @Override
    public Drug getDrugById(int drugId) {
        Drug drug = null;
        String sql = "SELECT * FROM drug WHERE drug_id = ?";
        try {
            SqlRowSet results = template.queryForRowSet(sql, drugId);
            if (results.next()) {
                drug = mapRowToDrug(results);
            }
        }catch (CannotGetJdbcConnectionException e) {
            throw new DaoException("Unable to connect to server or database", e);
        }
        return drug;
    }

    @Override
    public Drug createDrug(Drug newDrug) {
        Drug drug = null;
        String insertDrugSql = "INSERT INTO drug " +
                "(class_id, drug_name, cost, is_available) " +
                "VALUES (?, ?, ?, ?) " +
                "RETURNING drug_id";

        try {
            Integer drugId = template.queryForObject(insertDrugSql, int.class,
                    newDrug.getClassId(), newDrug.getName(),
                    newDrug.getCost(), newDrug.isAvailable());
            drug = getDrugById(drugId);
        } catch (CannotGetJdbcConnectionException e) {
            throw new DaoException("Unable to connect to server or database", e);
        } catch (DataIntegrityViolationException e) {
            throw new DaoException("Data integrity violation", e);
        }
        return drug;
    }

    @Override
    public Drug updateDrug(int drugId, Drug updatedDrug) {
        String updateDrugSql = "UPDATE drug SET class_id = ?, drug_name = ?, cost = ?, is_available = ? " +
                "WHERE drug_id = ?";
        try {
            template.update(updateDrugSql, updatedDrug.getClassId(), updatedDrug.getName(), updatedDrug.getCost(),
                    updatedDrug.isAvailable(), updatedDrug.getId());
        }catch (CannotGetJdbcConnectionException e) {
            throw new DaoException("Unable to connect to server or database", e);
        } catch (DataIntegrityViolationException e) {
            throw new DaoException("Data integrity violation", e);
        }
        return getDrugById(updatedDrug.getId());
    }

    @Override
    public void deleteDrug(int drugId) {
        String deleteDrugSql = "DELETE FROM drug WHERE drug_id = ?";
        try {
            template.update(deleteDrugSql, drugId);
        } catch (CannotGetJdbcConnectionException e) {
            throw new DaoException("Unable to connect to server or database", e);
        } catch (DataIntegrityViolationException e) {
            throw new DaoException("Data integrity violation", e);
        }
    }

    private Drug mapRowToDrug(SqlRowSet rs) {
        Drug drug = new Drug();
        drug.setId(rs.getInt("drug_id"));
        drug.setName(rs.getString("drug_name"));
        drug.setCost(rs.getBigDecimal("cost"));
        drug.setAvailable(rs.getBoolean("is_available"));
        drug.setClassId(rs.getInt("class_id"));
        return drug;
    }
}
