package com.techelevator.dao;

import com.techelevator.model.DrugClass;

import java.util.List;

public interface DrugClassDao {

    List<DrugClass> getAllDrugClasses();
    DrugClass getDrugClassById(int drugClassId);
    DrugClass createDrugClass(DrugClass drugClass);
    DrugClass updateDrugClass(int classId, DrugClass updatedClass);
    void deleteDrugClass(int classId);

}
