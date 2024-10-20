package com.techelevator.dao;

import com.techelevator.exception.DaoException;
import com.techelevator.model.DrugClass;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.jdbc.CannotGetJdbcConnectionException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.rowset.SqlRowSet;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class JdbcDrugClassDao implements DrugClassDao {

    private final JdbcTemplate template;

    public JdbcDrugClassDao(JdbcTemplate template) {
        this.template = template;
    }

    @Override
    public List<DrugClass> getAllDrugClasses() {
        List<DrugClass> drugClasses = new ArrayList<>();
        String sql = "SELECT * FROM class ORDER BY class_name";

        try {
            SqlRowSet results = template.queryForRowSet(sql);
            while (results.next()) {
                DrugClass drugClass = mapRowToDrugClass(results);
                drugClasses.add(drugClass);
            }
        }catch (CannotGetJdbcConnectionException e) {
            throw new DaoException("Unable to connect to server or database", e);
        }
        return drugClasses;
    }

    @Override
    public DrugClass getDrugClassById(int drugClassId) {
        DrugClass drugClass = null;
        String sql = "SELECT * FROM class WHERE class_id = ?";
        try {
            SqlRowSet results = template.queryForRowSet(sql, drugClassId);
            if (results.next()) {
                drugClass = mapRowToDrugClass(results);
            }
        }catch (CannotGetJdbcConnectionException e) {
            throw new DaoException("Unable to connect to server or database", e);
        }
        return drugClass;
    }

    @Override
    public DrugClass createDrugClass(DrugClass drugClass) {
        DrugClass createdClass = null;
        String insertDrugClassSql = "INSERT INTO class " +
                "(class_name) " +
                "VALUES (?) " +
                "RETURNING class_id";

        try {
            Integer classId = template.queryForObject(insertDrugClassSql, Integer.class,
                    drugClass.getName());
            createdClass = getDrugClassById(classId);
        } catch (CannotGetJdbcConnectionException e) {
            throw new DaoException("Unable to connect to server or database", e);
        } catch (DataIntegrityViolationException e) {
            throw new DaoException("Data integrity violation", e);
        }
        return createdClass;
    }

    @Override
    public DrugClass updateDrugClass(int classId, DrugClass updatedClass) {
        String updatedClassSql = "UPDATE class SET class_name = ? WHERE class_id = ?";
        try {
            template.update(updatedClassSql, updatedClass.getName());
        } catch (CannotGetJdbcConnectionException e) {
            throw new DaoException("Unable to connect to server or database", e);
        } catch (DataIntegrityViolationException e) {
            throw new DaoException("Data integrity violation", e);
        }
        return getDrugClassById(updatedClass.getId());
    }

    @Override
    public void deleteDrugClass(int classId) {
        String deleteClassSql = "DELETE FROM class WHERE class_id = ?";
        try {
            template.update(deleteClassSql, classId);
        } catch (CannotGetJdbcConnectionException e) {
            throw new DaoException("Unable to connect to server or database", e);
        } catch (DataIntegrityViolationException e) {
            throw new DaoException("Data integrity violation", e);
        }
    }

    private DrugClass mapRowToDrugClass(SqlRowSet rs) {
        DrugClass drug = new DrugClass();
        drug.setId(rs.getInt("class_id"));
        drug.setName(rs.getString("class_name"));
        return drug;
    }
}
