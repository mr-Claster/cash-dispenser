package springboot.cashdispenser.service.impl;

import jakarta.annotation.PostConstruct;
import java.util.List;
import java.util.stream.Collectors;
import org.springframework.stereotype.Service;
import springboot.cashdispenser.model.Bills;
import springboot.cashdispenser.model.Par;
import springboot.cashdispenser.repository.BillsRepository;
import springboot.cashdispenser.service.BillsService;
import springboot.cashdispenser.service.ParService;

@Service
public class BillsServiceImpl implements BillsService {
    private final BillsRepository billsRepository;
    private final ParService parService;

    public BillsServiceImpl(BillsRepository billsRepository,
                            ParService parService) {
        this.billsRepository = billsRepository;
        this.parService = parService;
    }

    @PostConstruct
    public void init() {
        List<Par> all = parService.getAll();
        saveAll(all.stream().map(p -> {
            Bills bills = new Bills();
            bills.setPar(p);
            bills.setNumber(0);
            return bills;
        }).collect(Collectors.toList()));
    }

    @Override
    public Bills save(Bills bills) {
        return billsRepository.save(bills);
    }

    @Override
    public Bills update(Bills bills) {
        return billsRepository.save(bills);
    }

    @Override
    public List<Bills> saveAll(List<Bills> billsList) {
        return billsRepository.saveAll(billsList);
    }

    @Override
    public List<Bills> getAll() {
        return billsRepository.findAll();
    }
}
