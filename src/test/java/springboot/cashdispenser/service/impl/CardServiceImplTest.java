package springboot.cashdispenser.service.impl;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Optional;
import org.junit.Assert;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import springboot.cashdispenser.model.Card;
import springboot.cashdispenser.repository.CardRepository;
import springboot.cashdispenser.service.CardService;

@SpringBootTest
class CardServiceImplTest {
    @MockBean
    private CardRepository cardRepository;
    @Autowired
    private CardServiceImpl cardService;

//    @Test
//    public void testTransferMoneyToAnotherCard() {
//        // Create mock objects for the dependencies
//        Card senderCard = new Card();
//        String numberOfAcceptCard = "1234567890123456";
//        BigDecimal amount = new BigDecimal(100);
//        Card acceptCard = new Card();
//        CardService cardService = Mockito.mock(CardService.class);
//        Mockito.when(cardService.getByCardNumber(numberOfAcceptCard)).thenReturn(acceptCard);
//
//        // Verify that the sender card and accept card amounts have been updated correctly
//        Mockito.verify(cardService).update(senderCard);
//        Mockito.verify(cardService).update(acceptCard);
//        Assert.assertEquals(senderCard.getAmount(), new BigDecimal(900));
//        Assert.assertEquals(acceptCard.getAmount(), new BigDecimal(100));
//
//    }
}