package com.nhnacademy.day3.properties;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;

@Getter
@Setter
@ConfigurationProperties(prefix = "message")
public class MessageProperties {
    String cooking;
    String delivery;
}
