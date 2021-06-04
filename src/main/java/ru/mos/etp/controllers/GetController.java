package ru.mos.etp.controllers;

import io.swagger.annotations.*;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import ru.mos.etp.model.CsvRecord;
import ru.mos.etp.dto.Record;
import ru.mos.etp.exceptions.CustomException;
import ru.mos.etp.responses.ErrorResponse;
import ru.mos.etp.responses.OkResponse;
import ru.mos.etp.service.DataLoader;
import ru.mos.etp.service.RecordFinder;
import ru.mos.etp.util.ResponsePreparer;

import java.util.List;

@RestController
@Api(tags = "DelayCounter")
public class GetController {

    private final DataLoader loader;
    private final RecordFinder counter;

    GetController(DataLoader loader,RecordFinder counter){
        this.loader = loader;
        this.counter = counter;
    }

    @ApiOperation(value = "Method return count of the records where difference between put and created columns more than delay.")
    @ApiResponses(value = {
        @ApiResponse(code = 200, message = "Success", response = OkResponse.class),
        @ApiResponse(code = 400, message = "Fail", response = ErrorResponse.class),
        @ApiResponse(code = 500, message = "Fail", response = ErrorResponse.class)
    })
    @GetMapping(value = "/stat")
    public ResponseEntity<OkResponse> postApplications(
            @ApiParam(hidden = true)
            @RequestHeader HttpHeaders headers,
            @ApiParam(name = "delay", value = "delay between put and create",example = "1000", required = true)
            @RequestParam("delay") long delay
            ) throws CustomException {

        List<CsvRecord> finList = counter.getCountByDelay(delay,loader.getRecordsFromCSV());
        List<Record> records = ResponsePreparer.prepareRecordForResponse(finList);

        return new ResponseEntity<>(new OkResponse(String.valueOf(finList.size()), records),HttpStatus.OK);
    }

}
