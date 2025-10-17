package com.example.demo.common.dataparser;

import com.example.demo.account.dto.Account;
import com.example.demo.common.properties.FileProperties;
import com.example.demo.price.dto.Price;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.Reader;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Objects;
import java.util.Set;
import lombok.RequiredArgsConstructor;
import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVParser;
import org.apache.commons.csv.CSVRecord;

@RequiredArgsConstructor
public class CsvDataParser implements DataParser{

    private final FileProperties fileProperties;

    public List<String> cities() {
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

            Set<String> citySet = new HashSet<>();

            for (CSVRecord record : records) {
                String city = record.get("지자체명");

                citySet.add(city);
            }

            return new ArrayList<>(citySet);

        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public List<String> sectors(String city) {
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

            Set<String> sectorSet = new HashSet<>();

            for (CSVRecord record : records) {
                if(Objects.equals(record.get("지자체명"), city)){
                    sectorSet.add(record.get("업종"));
                }
            }

            return new ArrayList<>(sectorSet);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
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

            List<Account> accountList = new ArrayList<>();

            for (CSVRecord record : records) {
                Account account = new Account();
                account.setId(Long.parseLong(record.get("아이디")));
                account.setPassword(record.get("비밀번호"));
                account.setName(record.get("이름"));

                accountList.add(account);
            }

            return accountList;
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
