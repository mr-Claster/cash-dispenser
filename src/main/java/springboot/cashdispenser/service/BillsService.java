package springboot.cashdispenser.service;

import java.util.List;
import springboot.cashdispenser.model.Bills;

public interface BillsService {
    Bills save(Bills bills);

    Bills update(Bills bills);

    List<Bills> saveAll(List<Bills> billsList);

    List<Bills> getAll();
}
