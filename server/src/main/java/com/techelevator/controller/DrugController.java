package com.techelevator.controller;

import com.techelevator.dao.DrugClassDao;
import com.techelevator.dao.DrugDao;
import com.techelevator.model.Drug;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin
@RequestMapping("/drug")
@PreAuthorize("permitAll")
public class DrugController {

    private final DrugDao drugDao;
    private final DrugClassDao drugClassDao;


    public DrugController(DrugDao drugDao, DrugClassDao drugClassDao) {
        this.drugDao = drugDao;
        this.drugClassDao = drugClassDao;
    }
    @RequestMapping(path = "", method = RequestMethod.GET)
    public List <Drug> getAllDrugs () {
        return drugDao.getAllDrugs();
    }

    @PreAuthorize("hasRole('ROLE_USER')")
    @GetMapping(path = "/{id}")
    public Drug getDrug(@PathVariable int id) {
        return drugDao.getDrugById(id);
    }

//    @PreAuthorize("hasRole('ROLE_USER')")
//    @GetMapping(path = "/class/{classId}")
//    public List<Drug> getDrugsByClassId(@PathVariable int classId) {
//        return drugDao.getAllDrugsForClass(classId);
//    }

    @PreAuthorize("hasRole('USER')")
    @GetMapping(path = "/{drugId}/class")
    public List<Drug> getAllDrugsForClass(@PathVariable int drugId) {
        return drugDao.getAllDrugsForClass(drugId);
    }

    @PreAuthorize("hasRole('ADMIN')")
    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping(path = "")
    public Drug createDrugForClass(@RequestBody Drug drug) {
        return drugDao.createDrug(drug);
    }

    @PreAuthorize("hasRole('ADMIN')")
    @PutMapping(path = "/{drugId}")
    public Drug updateDrug(@PathVariable int drugId, @RequestBody Drug drug) {
        return drugDao.updateDrug(drugId, drug);
    }

    @PreAuthorize("hasRole('ADMIN')")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    @DeleteMapping(path = "/{drugId}")
    public void deleteDrug (@PathVariable int drugId) {
        drugDao.deleteDrug(drugId);
    }



}