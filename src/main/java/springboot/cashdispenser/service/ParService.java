package springboot.cashdispenser.service;

import java.util.List;
import springboot.cashdispenser.model.Par;

public interface ParService {
    List<Par> getAll();

    Par findParByPar(Integer par);

    Par save(Par par);

    List<Par> saveAll(List<Par> pars);
}
