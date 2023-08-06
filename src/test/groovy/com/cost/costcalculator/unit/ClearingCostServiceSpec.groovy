package com.cost.costcalculator.unit

import com.cost.costcalculator.dto.BinTableCardDetailsResponseDto
import com.cost.costcalculator.dto.BinTableDataDto
import com.cost.costcalculator.dto.CountryDataDto
import com.cost.costcalculator.repository.ClearingCostRepository
import com.cost.costcalculator.service.ClearingCostService
import com.cost.costcalculator.web_client.BinTableClient
import org.springframework.core.convert.ConversionService
import reactor.core.publisher.Mono
import spock.lang.Specification

class ClearingCostServiceSpec extends Specification {


    ClearingCostService service = new ClearingCostService(Mock(ClearingCostRepository),
            Mock(ConversionService), Mock(BinTableClient))

    //needs fixing
    def "calculate clearing cost"() {
        given: "the card code is us"
            CountryDataDto countryDataDto = CountryDataDto.builder().code("us").build()
            BinTableDataDto binTableDataDto = BinTableDataDto.builder().country(countryDataDto).build()

        when: "the method is called"
            def result = service.calculateClearingCost("1241234")

        then: "the bin table api will be called once"
        1 * service.binTableClient.getDetailsFromCardNumber(_) >> Mono
                .just(new BinTableCardDetailsResponseDto((short) 1, "message", binTableDataDto))
    }
}