package com.example.demo.price.service;

import com.example.demo.common.dataparser.DataParser;
import com.example.demo.price.dto.Price;

import com.example.demo.price.formatter.OutPutFormatter;
import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.Objects;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import org.springframework.stereotype.Service;

@Setter
@Getter
@Service
@RequiredArgsConstructor
public class PriceService {

    private final DataParser dataParser;
    private final OutPutFormatter outPutFormatter;

    public List<String> cities() {
        List<String> cityList = dataParser.cities();
        if(cityList.isEmpty()){
            throw new IllegalArgumentException("city is Null");
        }

        Collections.sort(cityList);
        return cityList;
    }

    public List<String> sectors(String city) {
        List<String> sectorList = dataParser.sectors(city);
        if(sectorList.isEmpty()){
            throw new IllegalArgumentException("sector is null");
        }

        Collections.sort(sectorList);
        return sectorList;
    }

    public Price price(String city, String sector) {
        Price price = dataParser.price(city, sector);
        if(Objects.isNull(price)){
            throw new IllegalArgumentException("price is null");
        }
        return price;
    }

    public String billTotal(String city, String sector, int usage) {
        Price price = dataParser.price(city, sector);
        if(Objects.isNull(price)){
            throw new IllegalArgumentException("price is null");
        }

        return outPutFormatter.format(price, usage);
    }

}
