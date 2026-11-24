package tn.esprit.autoloc.web.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import tn.esprit.autoloc.calcul.VehiculeOffre;
import tn.esprit.autoloc.service.IVehiculeService;
import tn.esprit.autoloc.web.dto.VehiculeDto;
import tn.esprit.autoloc.web.mapper.VehiculeMapper;

import java.util.List;

@RestController
@RequestMapping("/vehicules")
@RequiredArgsConstructor
public class VehiculeController {

    private final IVehiculeService vehiculeService;
    private final VehiculeMapper vehiculeMapper;

    @GetMapping
    public List<VehiculeDto> findAll() {
        return vehiculeMapper.toDtos(vehiculeService.findAll());
    }

    @GetMapping("/{id}")
    public VehiculeDto findById(@PathVariable Long id) {
        return vehiculeMapper.toDto(vehiculeService.findById(id));
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public VehiculeDto create(@Valid @RequestBody VehiculeDto vehiculeDto) {
        return vehiculeMapper.toDto(vehiculeService.create(vehiculeMapper.toEntity(vehiculeDto)));
    }

    @PutMapping("/{id}")
    public VehiculeDto update(@PathVariable Long id, @Valid @RequestBody VehiculeDto vehiculeDto) {
        var vehicule = vehiculeService.findById(id);
        vehiculeMapper.updateEntity(vehiculeDto, vehicule);
        return vehiculeMapper.toDto(vehiculeService.update(id, vehicule));
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteById(@PathVariable Long id) {
        vehiculeService.deleteById(id);
    }

    @GetMapping("/{id}/tarif")
    public double calculerTarif(
            @PathVariable Long id,
            @RequestParam int jours,
            @RequestParam VehiculeOffre offre) {
        return vehiculeService.calculerTarif(vehiculeService.findById(id), jours, offre);
    }
    @PutMapping("/{id}/desaffectation")
    public VehiculeDto desaffecte(@PathVariable Long id) {
        return vehiculeMapper.toDto(vehiculeService.desaffecte(id));
    }

}
