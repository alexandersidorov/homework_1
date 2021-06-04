package ru.mos.etp.service;

import org.apache.commons.csv.CSVFormat;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import ru.mos.etp.model.CsvRecord;
import ru.mos.etp.exceptions.CustomException;
import ru.mos.etp.props.Props;

import java.io.BufferedReader;
import java.io.FileReader;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

@Service
public class DataLoader {

    private final Props props;

    public DataLoader(Props props){this.props = props;}

    public List<CsvRecord> getRecordsFromCSV() throws CustomException {
        List<CsvRecord> csvRecords = new ArrayList<>();
        try(
            var br = new BufferedReader(new FileReader(props.getPathToFile()));
            var parser = CSVFormat.DEFAULT.withDelimiter(';').withHeader().parse(br)
        ) {
            var formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss.SSSSSS");
            for(var record : parser) {
                var csvRecord = new CsvRecord(
                        Integer.parseInt(record.get("id")),
                        record.get("servicenumber"),
                        Integer.parseInt(record.get("statuscode")),
                        LocalDateTime.parse(record.get("created"),formatter),
                        record.get("msgid"),
                        LocalDateTime.parse(record.get("put"),formatter),
                        record.get("reasoncode").isEmpty()?null:Integer.parseInt(record.get("reasoncode")),
                        Boolean.parseBoolean(record.get("route")),
                        record.get("direction"));
                csvRecords.add(csvRecord);
            }
        } catch (Exception e) {
            throw new CustomException("Error with read csv", HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return csvRecords;
    }

}
