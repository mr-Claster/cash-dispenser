package springboot.cashdispenser.service.impl;

import jakarta.transaction.Transactional;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import org.springframework.stereotype.Service;
import springboot.cashdispenser.model.Bills;
import springboot.cashdispenser.model.Card;
import springboot.cashdispenser.model.Par;
import springboot.cashdispenser.service.BillsService;
import springboot.cashdispenser.service.CardService;
import springboot.cashdispenser.service.CashDispenserService;
import springboot.cashdispenser.service.ParService;

@Service
public class CashDispenserServiceImpl implements CashDispenserService {
    private final CardService cardService;
    private final BillsService billsService;
    private final ParService parService;

    public CashDispenserServiceImpl(CardService cardService,
                                    BillsService billsService,
                                    ParService parService) {
        this.cardService = cardService;
        this.billsService = billsService;
        this.parService = parService;
    }

    @Override
    @Transactional
    public void putMoneyOnCard(String cardNumber, List<Bills> billsList) {
        checkPar(billsList);
        Card card = cardService.getByCardNumber(cardNumber);
        long sum = billsList.stream()
                .mapToLong(bill ->
                        (long) bill.getPar().getValue() * bill.getNumber()
                ).sum();
        card.setAmount(card.getAmount().add(BigDecimal.valueOf(sum)));
        cardService.save(card);
        addListOfBills(billsList);
    }

    @Override
    @Transactional
    public List<Bills> getMoneyFromCard(Card inputCard, Integer amount) {
        cardService.checkCard(inputCard);
        cardService.checkAmountOnCard(inputCard, BigDecimal.valueOf(amount));
        inputCard.setAmount(inputCard.getAmount().subtract(BigDecimal.valueOf(amount)));
        cardService.save(inputCard);
        List<Bills> billsList = billsService.getAll();
        List<Bills> output = new ArrayList<>();
        for (Bills bills : billsList) {
            int numberBillsOfOnePar = amount / bills.getPar().getValue();
            if (bills.getNumber() >= numberBillsOfOnePar) {
                amount -= numberBillsOfOnePar * bills.getPar().getValue();
                Bills outputMoneyStack
                        = new Bills(numberBillsOfOnePar, bills.getPar().getValue());
                output.add(outputMoneyStack);
                bills.setNumber(bills.getNumber() - numberBillsOfOnePar);
            } else {
                amount -= bills.getNumber() * bills.getPar().getValue();
                output.add(bills.clone());
                bills.setNumber(0);
            }
            if (amount == 0) {
                break;
            }
        }
        if (amount > 0) {
            throw new RuntimeException("there is not enough money in the ATM");
        }
        billsList.forEach(billsService::update);
        return output;
    }

    @Override
    @Transactional
    public void addListOfBills(List<Bills> billsList) {
        checkPar(billsList);
        List<Bills> billsListFromDB = billsService.getAll();
        for (Bills billsFromDB : billsListFromDB) {
            for (Bills bills: billsList) {
                if (billsFromDB.getPar().getValue().equals(bills.getPar().getValue())) {
                    billsFromDB.setNumber(billsFromDB.getNumber() + bills.getNumber());
                    billsList.remove(bills);
                    break;
                }
            }
        }
        billsService.saveAll(billsListFromDB);
    }

    public void checkPar(List<Bills> listBills) {
        List<Par> checkList = parService.getAll();
        for (Bills bills : listBills) {
            if (checkList.stream()
                    .noneMatch(o -> o.getValue().equals(bills.getPar().getValue()))) {
                throw new RuntimeException("unsuitable bill\n  suitable bills: " + checkList);
            }
        }
    }
}
