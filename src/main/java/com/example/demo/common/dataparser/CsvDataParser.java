package com.example.demo.common.dataparser;

import com.example.demo.account.dto.Account;
import com.example.demo.common.properties.FileProperties;
import com.example.demo.price.dto.Price;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.Reader;
import java.nio.charset.StandardCharsets;
import java.util.List;
import java.util.Objects;
import lombok.RequiredArgsConstructor;
import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVParser;
import org.apache.commons.csv.CSVRecord;

@RequiredArgsConstructor
public class CsvDataParser implements DataParser{

    private final FileProperties fileProperties;

    public List<String> cities() {
        return null;
    }

    public List<String> sectors(String city) {
        return null;
    }

    public Price price(String city, String sector) {
        try(
                Reader reader = new BufferedReader(
                    new InputStreamReader(
                            getClass().getResourceAsStream("/" + fileProperties.getPricePath()), StandardCharsets.UTF_8
                    )
                );
        )
        {
            Iterable<CSVRecord> records = CSVFormat.DEFAULT
                    .builder()
                    .setHeader()
                    .setTrim(true)
                    .build()
                    .parse(reader);

            for (CSVRecord record : records) {
                if(Objects.equals(record.get("지자체명"), city) && Objects.equals(record.get("업종"), sector)){
                    Price price = new Price();
                    price.setId(Long.parseLong(record.get("순번")));
                    price.setCity(record.get("지자체명"));
                    price.setSector(record.get("업종"));
                    price.setUnitPrice(Integer.parseInt(record.get("구간금액(원)")));

                    return price;
                }
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return null;
    }

    public List<Account> accounts() {
        try(
                Reader reader = new BufferedReader(
                        new InputStreamReader(
                                getClass().getResourceAsStream("/" + fileProperties.getAccountPath()), StandardCharsets.UTF_8
                        )
                );
        )
        {
            Iterable<CSVRecord> records = CSVFormat.DEFAULT
                    .builder()
                    .setHeader()
                    .setTrim(true)
                    .build()
                    .parse(reader);

            for (CSVRecord record : records) {

            }
            return null;
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
