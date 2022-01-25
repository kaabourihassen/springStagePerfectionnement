package org.gestion.bp.web;

import org.gestion.bp.entities.Magazin;
import org.gestion.bp.exception.RessourceNotFoundException;
import org.gestion.bp.service.MagazinService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/magazins")
public class MagazinController {
    @Autowired
    MagazinService magazinService;

    @GetMapping("")
    public List<Magazin> getAllMagazins(){
        return magazinService.getAllMagazins();
    }
    @GetMapping("/{magazinId}")
    public Magazin getOneMagazin(@PathVariable Long magazinId) throws RessourceNotFoundException {
        return magazinService.getOneMagazin(magazinId);
    }
    @PostMapping("")
    public Magazin createMagazin(@RequestBody Magazin magazin){
        return magazinService.createMagazin(magazin);
    }
    @PutMapping("/updateMagazin/{magazinId}")
    public Magazin updateMagazin(@PathVariable Long magazinId,@RequestBody Magazin magazin) throws RessourceNotFoundException {
        return magazinService.UpdateMagazin(magazinId,magazin);
    }
    @DeleteMapping("/{magazinId}")
    public void deleteMagazin(@PathVariable Long magazinId){
        magazinService.deleteMagazin(magazinId);
    }

}
