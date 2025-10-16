package com.example.demo.price.formatter;

import com.example.demo.price.dto.Price;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.stereotype.Component;

@Component

public class KoreanOutputFormatter {

    public String format(Price price, int usage) {
        return null;
    }
}
