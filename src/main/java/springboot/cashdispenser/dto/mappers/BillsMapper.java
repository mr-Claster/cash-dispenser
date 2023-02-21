package springboot.cashdispenser.dto.mappers;

import springboot.cashdispenser.dto.request.BillsRequestDto;
import springboot.cashdispenser.dto.response.BillsResponseDto;
import springboot.cashdispenser.model.Bills;

public interface BillsMapper {
    Bills toModel(BillsRequestDto dto);

    BillsResponseDto toDto(Bills bills);
}
