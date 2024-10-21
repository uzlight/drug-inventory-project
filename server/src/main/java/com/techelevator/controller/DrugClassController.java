package com.techelevator.controller;

import com.techelevator.dao.DrugClassDao;
import com.techelevator.model.DrugClass;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin
@RequestMapping("/drugClass")
@PreAuthorize("permitAll")
public class DrugClassController {

    private final DrugClassDao drugClassDao;


    public DrugClassController(DrugClassDao drugClassDao) {
        this.drugClassDao = drugClassDao;
    }

    @RequestMapping(path = "", method = RequestMethod.GET)
    public List<DrugClass> getAllDrugClasses () {
        return drugClassDao.getAllDrugClasses();
    }

    @PreAuthorize("hasRole('USER')")
    @GetMapping(path = "/{classId}")
    public DrugClass getDrugClass(@PathVariable int classId) {
        return drugClassDao.getDrugClassById(classId);
    }

    @PreAuthorize("hasAnyRole('ADMIN', 'USER')")
    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping
    public DrugClass createDrugClass(@RequestBody DrugClass drugClass) {
        return drugClassDao.createDrugClass(drugClass);
    }

    @PreAuthorize("hasAnyRole('ADMIN', 'USER')")
    @PutMapping(path = "/{drugClassId}")
    public DrugClass updateDrugClass(@PathVariable int drugClassId, @RequestBody DrugClass drugClass) {
        return drugClassDao.updateDrugClass(drugClassId, drugClass);
    }

    @PreAuthorize("hasAnyRole('ADMIN', 'USER')")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    @DeleteMapping(path = "/{drugClassId}")
    public void deleteDrugClass (@PathVariable int drugClassId) {
        drugClassDao.deleteDrugClass(drugClassId);
    }



}
