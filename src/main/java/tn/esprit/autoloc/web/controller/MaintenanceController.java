package tn.esprit.autoloc.web.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import tn.esprit.autoloc.service.IMaintenanceService;
import tn.esprit.autoloc.web.dto.MaintenanceDto;
import tn.esprit.autoloc.web.mapper.MaintenanceMapper;

import java.util.List;

@RestController
@RequestMapping("/maintenances")
@RequiredArgsConstructor
public class MaintenanceController {

    private final IMaintenanceService maintenanceService;
    private final MaintenanceMapper maintenanceMapper;

    @GetMapping
    public List<MaintenanceDto> findAll() {
        return maintenanceMapper.toDtos(maintenanceService.findAll());
    }

    @GetMapping("/{id}")
    public MaintenanceDto findById(@PathVariable Long id) {
        return maintenanceMapper.toDto(maintenanceService.findById(id));
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public MaintenanceDto create(@Valid @RequestBody MaintenanceDto maintenanceDto) {
        return maintenanceMapper.toDto(
                maintenanceService.create(maintenanceMapper.toEntity(maintenanceDto)));
    }

    @PutMapping("/{id}")
    public MaintenanceDto update(@PathVariable Long id,
                                 @Valid @RequestBody MaintenanceDto maintenanceDto) {
        var maintenance = maintenanceService.findById(id);
        maintenanceMapper.updateEntity(maintenanceDto, maintenance);
        return maintenanceMapper.toDto(maintenanceService.update(id, maintenance));
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteById(@PathVariable Long id) { maintenanceService.deleteById(id); }
}
