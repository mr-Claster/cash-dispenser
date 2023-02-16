package springboot.cashdispenser.controller;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import springboot.cashdispenser.model.MoneyStack;
import springboot.cashdispenser.service.MoneyStackService;
import java.util.List;

@RestController
@RequestMapping("/money")
public class MoneyStackController {
    private final MoneyStackService moneyStackService;

    public MoneyStackController(MoneyStackService moneyStackService) {
        this.moneyStackService = moneyStackService;
    }

    @PostMapping
    public void addMoney(List<MoneyStack> moneyStacks) {
        moneyStackService.addMoney(moneyStacks);
    }
}
