package springboot.cashdispenser.service.impl;

import org.springframework.stereotype.Service;
import springboot.cashdispenser.model.MoneyStack;
import springboot.cashdispenser.repository.MoneyStackRepository;
import springboot.cashdispenser.service.MoneyStackService;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class MoneyStackServiceImpl implements MoneyStackService {
    private final MoneyStackRepository moneyStackRepository;

    public MoneyStackServiceImpl(MoneyStackRepository moneyStackRepository) {
        this.moneyStackRepository = moneyStackRepository;
    }

    @Override
    public MoneyStack save(MoneyStack moneyStack) {
        return moneyStackRepository.saveAndFlush(moneyStack);
    }

    @Override
    public MoneyStack update(MoneyStack moneyStack) {
        return moneyStackRepository.saveAndFlush(moneyStack);
    }

    @Override
    public List<MoneyStack> saveAll(List<MoneyStack> moneyStackList) {
        return moneyStackRepository.saveAll(moneyStackList);
    }

    @Override
    public List<MoneyStack> getAll() {
        return moneyStackRepository.findAll();
    }

    @Override
    public void addMoney(List<MoneyStack> acceptedMoneyStacks) {
        List<MoneyStack> moneyStacks = getAll();
        List<MoneyStack> acceptedSortedMoneyStacks = acceptedMoneyStacks.stream()
                .sorted(Comparator.comparing(MoneyStack::getPar).reversed())
                .collect(Collectors.toList());
        for (MoneyStack moneyStack : moneyStacks) {
            for (MoneyStack acceptedMoneyStack: acceptedSortedMoneyStacks) {
                if (moneyStack.getPar().equals(acceptedMoneyStack.getPar())) {
                    moneyStack.setNumber(moneyStack.getNumber() + acceptedMoneyStack.getNumber());
                    acceptedSortedMoneyStacks.remove(acceptedMoneyStack);
                    break;
                }
                if (acceptedMoneyStack.getPar() < moneyStack.getPar()) {
                    break;
                }
            }
        }
        saveAll(moneyStacks);
        saveAll(acceptedSortedMoneyStacks);
    }
}
