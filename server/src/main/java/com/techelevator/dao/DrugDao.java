package com.techelevator.dao;

import com.techelevator.model.Drug;

import java.util.List;

public interface DrugDao {

    List<Drug> getAllDrugs();
    List<Drug> getAllDrugsForClass(int classId);
//    List<Drug> getDrugsByClassId(int classId);
    Drug getDrugById(int drugId);
    Drug createDrug(Drug newDrug);
    Drug updateDrug(int drugId, Drug updatedDrug);
    void deleteDrug(int drugId);

}
