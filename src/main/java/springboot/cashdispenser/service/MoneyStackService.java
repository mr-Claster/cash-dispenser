package springboot.cashdispenser.service;

import springboot.cashdispenser.model.MoneyStack;
import java.util.List;

public interface MoneyStackService {
    MoneyStack save(MoneyStack moneyStack);

    MoneyStack update(MoneyStack moneyStack);

    List<MoneyStack> saveAll(List<MoneyStack> moneyStackList);

    List<MoneyStack> getAll();

    void addMoney(List<MoneyStack> listOfMoney);
}
