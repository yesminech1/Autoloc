package tn.esprit.autoloc.web;

import jakarta.validation.Validation;
import jakarta.validation.Validator;
import jakarta.validation.ValidatorFactory;
import org.junit.jupiter.api.Test;
import tn.esprit.autoloc.web.dto.ClientDto;
import tn.esprit.autoloc.web.dto.ContratDto;
import tn.esprit.autoloc.web.dto.EmployeDto;
import tn.esprit.autoloc.web.dto.EquipementDto;
import tn.esprit.autoloc.web.dto.PaiementDto;
import tn.esprit.autoloc.web.dto.ReservationDto;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertFalse;

class DtoConstraintTests {

    @Test
    void otherEntityDtosDeclareServerSideConstraints() {
        try (ValidatorFactory factory = Validation.buildDefaultValidatorFactory()) {
            Validator validator = factory.getValidator();
            List<Object> invalidDtos = List.of(
                    new ClientDto(),
                    new ContratDto(),
                    new EmployeDto(),
                    new EquipementDto(),
                    new PaiementDto(),
                    new ReservationDto()
            );

            invalidDtos.forEach(dto -> assertFalse(validator.validate(dto).isEmpty(),
                    dto.getClass().getSimpleName() + " doit avoir des validations"));
        }
    }
}
