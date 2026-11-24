package tn.esprit.autoloc.web.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import org.mapstruct.ReportingPolicy;
import tn.esprit.autoloc.domain.Maintenance;
import tn.esprit.autoloc.web.dto.MaintenanceDto;

import java.util.List;

@Mapper(componentModel = "spring", uses = VehiculeMapper.class,
        unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface MaintenanceMapper {

    @Mapping(target = "vehiculeId", source = "vehicule.idVehicule")
    MaintenanceDto toDto(Maintenance maintenance);

    List<MaintenanceDto> toDtos(List<Maintenance> maintenances);

    @Mapping(target = "idMaintenance", ignore = true)
    @Mapping(target = "vehicule", source = "vehiculeId", qualifiedByName = "toVehiculeReference")
    Maintenance toEntity(MaintenanceDto maintenanceDto);

    @Mapping(target = "idMaintenance", ignore = true)
    @Mapping(target = "vehicule", source = "vehiculeId", qualifiedByName = "toVehiculeReference")
    void updateEntity(MaintenanceDto maintenanceDto, @MappingTarget Maintenance maintenance);
}
