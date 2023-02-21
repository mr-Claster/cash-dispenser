package springboot.cashdispenser.controller;

import java.util.List;
import java.util.stream.Collectors;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import springboot.cashdispenser.dto.mappers.BillsMapper;
import springboot.cashdispenser.dto.request.BillsRequestDto;
import springboot.cashdispenser.dto.response.BillsResponseDto;
import springboot.cashdispenser.model.Card;
import springboot.cashdispenser.service.CashDispenserService;

@RestController
@RequestMapping("/cash-dispensers")
public class CashDispenserController {
    private final CashDispenserService cashDispenserService;
    private final BillsMapper billsMapper;

    public CashDispenserController(CashDispenserService cashDispenserService,
                                   BillsMapper billsMapper) {
        this.cashDispenserService = cashDispenserService;
        this.billsMapper = billsMapper;
    }

    @PostMapping("/card")
    public void putMoneyOnCard(@RequestParam String cardNumber,
                               @RequestBody List<BillsRequestDto> billsDto) {
        cashDispenserService.putMoneyOnCard(cardNumber, billsDto.stream()
                .map(billsMapper::toModel)
                .collect(Collectors.toList()));
    }

    @PostMapping("/money")
    public List<BillsResponseDto> getMoneyFromCard(@RequestBody Card card,
                                                   @RequestParam Integer amount) {
        return cashDispenserService.getMoneyFromCard(card, amount).stream()
                .map(billsMapper::toDto)
                .collect(Collectors.toList());
    }

    @PostMapping
    public void addMoney(@RequestBody List<BillsRequestDto> billsDto) {
        cashDispenserService.addListOfBills(billsDto.stream()
                .map(billsMapper::toModel)
                .collect(Collectors.toList()));
    }
}
