package com.jd.backend.rest.counter;

import com.jd.backend.rest.model.counter.Counter;
import com.jd.backend.rest.model.counter.ICounterCommandRepository;
import com.jd.backend.rest.model.counter.ICounterQueryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.sql.Timestamp;

@RestController
public class CountersRestController {

    @Autowired
    private ICounterQueryRepository counterQueryRepository;
    @Autowired
    private ICounterCommandRepository counterCommandRepository;

    @GetMapping("/counter/get")
    public Counter getCounter(@RequestParam("id") String id) {
        return counterQueryRepository.getOne(id).orElse(new Counter());
    }

    @PostMapping("/counter/increment")
    public String incrementCounter(@RequestBody IncrementCounterParameters parameters) {
        final var id = parameters.getId();
        final var now = new Timestamp(System.currentTimeMillis());
        counterCommandRepository.incrementCounter(id, now);
        return "DONE";
    }

    @PostMapping("/counter/increment/run")
    public String incrementCounterRun(@RequestBody IncrementCounterRunParameters parameters) {
        new Thread(() -> {
            final var id = parameters.getId();
            final var limit = parameters.getLimit();

            for (int i=0; i<limit; i++) {
                var now = new Timestamp(System.currentTimeMillis());
                counterCommandRepository.incrementCounter(id, now);
            }
        }).start();

        return "STARTED";
    }

}
