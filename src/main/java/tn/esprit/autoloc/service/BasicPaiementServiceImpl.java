package tn.esprit.autoloc.service;

import org.springframework.stereotype.Service;
import tn.esprit.autoloc.domain.Paiement;
import tn.esprit.autoloc.repository.IPaiementRepository;

import java.util.List;

@Service
public class BasicPaiementServiceImpl implements IPaiementService {

    private final IPaiementRepository repository;

    public BasicPaiementServiceImpl(IPaiementRepository repository) {
        this.repository = repository;
    }

    @Override
    public Paiement findById(Long id) {
        return repository.findById(id)
                .orElseThrow(() -> new RuntimeException("Paiement introuvable"));
    }

    @Override
    public List<Paiement> findAll() {
        return repository.findAll();
    }

}
