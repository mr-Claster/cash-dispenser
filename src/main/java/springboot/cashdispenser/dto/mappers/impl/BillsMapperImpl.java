package springboot.cashdispenser.dto.mappers.impl;

import org.springframework.stereotype.Component;
import springboot.cashdispenser.dto.mappers.BillsMapper;
import springboot.cashdispenser.dto.request.BillsRequestDto;
import springboot.cashdispenser.dto.response.BillsResponseDto;
import springboot.cashdispenser.model.Bills;

@Component
public class BillsMapperImpl implements BillsMapper {
    @Override
    public Bills toModel(BillsRequestDto dto) {
        return new Bills(dto.getNumber(), dto.getPar());
    }

    @Override
    public BillsResponseDto toDto(Bills bills) {
        BillsResponseDto billsResponseDto = new BillsResponseDto();
        billsResponseDto.setPar(bills.getPar().getValue());
        billsResponseDto.setNumber(bills.getNumber());
        return billsResponseDto;
    }
}
