package com.example.demo.common.config;

import com.example.demo.common.dataparser.CsvDataParser;
import com.example.demo.common.dataparser.JsonDataParser;
import com.example.demo.common.properties.FileProperties;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@RequiredArgsConstructor
public class DataParserConfig {

    private final FileProperties fileProperties;

    @Bean
    @ConditionalOnProperty(prefix = "file", name = "type", havingValue = "csv", matchIfMissing = true)
    CsvDataParser csvDataParser() { return new CsvDataParser(fileProperties); }

    @Bean
    @ConditionalOnProperty(prefix = "file", name = "type", havingValue = "json", matchIfMissing = true)
    JsonDataParser jsonDataParser() { return new JsonDataParser(fileProperties); }
}
