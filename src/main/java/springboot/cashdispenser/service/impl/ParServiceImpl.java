package springboot.cashdispenser.service.impl;

import jakarta.annotation.PostConstruct;
import java.util.List;
import org.springframework.stereotype.Service;
import springboot.cashdispenser.model.Par;
import springboot.cashdispenser.repository.ParRepository;
import springboot.cashdispenser.service.ParService;

@Service
public class ParServiceImpl implements ParService {
    private final ParRepository parRepository;

    public ParServiceImpl(ParRepository parRepository) {
        this.parRepository = parRepository;
    }

    @PostConstruct
    public void init() {
        saveAll(List.of(new Par(100),
                new Par(200),
                new Par(500)));
    }

    @Override
    public List<Par> getAll() {
        return parRepository.findAll();
    }

    @Override
    public Par findParByPar(Integer par) {
        return parRepository.findParByPar(par);
    }

    @Override
    public Par save(Par par) {
        return parRepository.save(par);
    }

    @Override
    public List<Par> saveAll(List<Par> pars) {
        return parRepository.saveAll(pars);
    }
}
