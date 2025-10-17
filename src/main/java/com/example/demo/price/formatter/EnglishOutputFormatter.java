package com.example.demo.price.formatter;

import com.example.demo.price.dto.Price;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;

@Component
@Profile("eng")
public class EnglishOutputFormatter implements OutPutFormatter{

    public String format(Price price, int usage) {
        return "city: %s, sector: %s, unit price(won): %d, bill total(won): %d"
                .formatted(
                        price.getCity(),
                        price.getSector(),
                        price.getUnitPrice(),
                        price.getUnitPrice() * usage
                );
    }

}
