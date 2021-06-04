package ru.mos.etp.service;

import org.springframework.stereotype.Service;
import ru.mos.etp.model.CsvRecord;
import ru.mos.etp.props.Props;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.RecursiveTask;

@Service
public class RecordFinder {

    private final Props props;

    public RecordFinder(Props props){this.props = props;}

    public List<CsvRecord> getCountByDelay(long delay,List<CsvRecord> records){
        return joinFork(delay, records, props.getThreshold());
    }

    private List<CsvRecord> simple(long delay,List<CsvRecord> records,int stInd,int fnInd){
        List<CsvRecord> retList = new ArrayList<>();

        for(int i = stInd;i<=fnInd;++i){
            var record = records.get(i);
            long putMilli = getMilliseconds(record.put());
            long createMilli = getMilliseconds(record.created());
            long diff = Math.abs(putMilli-createMilli);
            if(diff>delay){
                retList.add(record);
            }
        }
        return retList;
    }

    private long getMilliseconds(LocalDateTime dateTime){
        ZonedDateTime zdt = ZonedDateTime.of(dateTime, ZoneId.systemDefault());
        return zdt.toInstant().toEpochMilli();
    }

    private List<CsvRecord> joinFork(long delay, List<CsvRecord> records,int threshold) {
        ForkJoinPool pool = ForkJoinPool.commonPool();
        return pool.invoke(new RecordFinderTask(records,threshold,0,records.size()-1,delay));
    }

    private class RecordFinderTask extends RecursiveTask<List<CsvRecord>> {

        private final List<CsvRecord>origList;
        private final int threshold;
        private final long delay;
        private final int stInd;
        private final int fnInd;

        RecordFinderTask(List<CsvRecord>origList,int threshold,int stInd,int fnInd,long delay){
            this.origList = origList;
            this.threshold = threshold;
            this.delay = delay;
            this.stInd = stInd;
            this.fnInd = fnInd;
        }

        @Override
        protected List<CsvRecord> compute() {
            List<CsvRecord> retList = new ArrayList<>();

            int countElem = (fnInd-stInd)+1;

            if(countElem>threshold){

                var taskFirst = new RecordFinderTask(origList,threshold,
                        stInd,stInd+(countElem/2)-1,delay);
                taskFirst.fork();

                var taskSecond = new RecordFinderTask(origList,threshold,
                        stInd+(countElem/2),fnInd,delay);
                taskSecond.fork();

                retList.addAll(taskFirst.join());
                retList.addAll(taskSecond.join());
            }else{
                retList = simple(delay,origList,stInd,fnInd);
            }

            return retList;
        }
    }
}
