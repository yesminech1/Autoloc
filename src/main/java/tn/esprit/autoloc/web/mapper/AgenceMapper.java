package tn.esprit.autoloc.web.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import org.mapstruct.Named;
import org.mapstruct.ReportingPolicy;
import tn.esprit.autoloc.domain.Agence;
import tn.esprit.autoloc.web.dto.AgenceDto;

import java.util.List;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface AgenceMapper {

    AgenceDto toDto(Agence agence);

    List<AgenceDto> toDtos(List<Agence> agences);

    @Mapping(target = "idAgence", ignore = true)
    @Mapping(target = "vehicules", ignore = true)
    @Mapping(target = "employes", ignore = true)
    Agence toEntity(AgenceDto agenceDto);

    @Mapping(target = "idAgence", ignore = true)
    @Mapping(target = "vehicules", ignore = true)
    @Mapping(target = "employes", ignore = true)
    void updateEntity(AgenceDto agenceDto, @MappingTarget Agence agence);

    @Named("toAgenceReference")
    default Agence toAgenceReference(Long agenceId) {
        if (agenceId == null) {
            return null;
        }
        Agence agence = new Agence();
        agence.setIdAgence(agenceId);
        return agence;
    }
}
