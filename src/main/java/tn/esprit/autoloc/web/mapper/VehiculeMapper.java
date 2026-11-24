package tn.esprit.autoloc.web.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import org.mapstruct.Named;
import org.mapstruct.ReportingPolicy;
import tn.esprit.autoloc.domain.Agence;
import tn.esprit.autoloc.domain.Vehicule;
import tn.esprit.autoloc.web.dto.VehiculeDto;

import java.util.List;

@Mapper(componentModel = "spring", uses = AgenceMapper.class,
        unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface VehiculeMapper {

    @Mapping(target = "agenceId", source = "agence.idAgence")
    VehiculeDto toDto(Vehicule vehicule);

    List<VehiculeDto> toDtos(List<Vehicule> vehicules);

    @Mapping(target = "idVehicule", ignore = true)
    @Mapping(target = "agence", source = "agenceId", qualifiedByName = "toAgenceReference")
    @Mapping(target = "reservations", ignore = true)
    @Mapping(target = "maintenances", ignore = true)
    @Mapping(target = "equipements", ignore = true)
    Vehicule toEntity(VehiculeDto vehiculeDto);

    @Mapping(target = "idVehicule", ignore = true)
    @Mapping(target = "agence", source = "agenceId", qualifiedByName = "toAgenceReference")
    @Mapping(target = "reservations", ignore = true)
    @Mapping(target = "maintenances", ignore = true)
    @Mapping(target = "equipements", ignore = true)
    void updateEntity(VehiculeDto vehiculeDto, @MappingTarget Vehicule vehicule);

    @Named("toVehiculeReference")
    default Vehicule toVehiculeReference(Long vehiculeId) {
        if (vehiculeId == null) {
            return null;
        }
        Vehicule vehicule = new Vehicule();
        vehicule.setIdVehicule(vehiculeId);
        return vehicule;
    }
}
