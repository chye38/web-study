package com.example.demo.price.formatter;

import com.example.demo.price.dto.Price;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;

@Component
@Profile("kor")
public class KoreanOutputFormatter implements OutPutFormatter{

    public String format(Price price, int usage) {
        return "지자체명: %s, 업종: %s, 구간금액(원): %d, 총금액(원): %d"
                .formatted(
                        price.getCity(),
                        price.getSector(),
                        price.getUnitPrice(),
                        price.getUnitPrice() * usage
                );
    }
}
