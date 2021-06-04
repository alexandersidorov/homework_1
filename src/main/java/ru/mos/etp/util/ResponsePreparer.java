package ru.mos.etp.util;

import ru.mos.etp.model.CsvRecord;
import ru.mos.etp.dto.Record;

import java.util.ArrayList;
import java.util.List;

public class ResponsePreparer {

    public static List<Record> prepareRecordForResponse(List<CsvRecord> records){
        List<Record> retList = new ArrayList<>();
        for(var record:records)
            retList.add(new Record(
                    record.id(),
                    record.serviceNumber(),
                    record.statusCode(),
                    record.created(),
                    record.msgId(),
                    record.put(),
                    record.reasonCode(),
                    record.route(),
                    record.direction()
            ));
        return retList;
    }
}
