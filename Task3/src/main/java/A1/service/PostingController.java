package A1.service;

import A1.data.models.dbEntities.Posting;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.Date;
import java.util.List;

@RestController
public class PostingController {
    private final PostingService postingService;

    @Autowired
    public PostingController(PostingService postingService) {
        this.postingService = postingService;
    }

    @GetMapping(value = "/postings")
    public ResponseEntity<List<Posting>> read() {
        final List<Posting> postings = postingService.getAll();

        return postings != null && !postings.isEmpty()
                ? new ResponseEntity<>(postings, HttpStatus.OK)
                : new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @GetMapping(value = "/postings?authorized={isAuthorized}")
    public ResponseEntity<List<Posting>> read(@PathVariable(name = "isAuthorized") boolean isAuthorized)
    {
        final List<Posting> postings = postingService.getByIsAuthorized(isAuthorized);

        return postings != null && !postings.isEmpty()
                ? new ResponseEntity<>(postings, HttpStatus.OK)
                : new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @GetMapping(value = "/postings?start-date={startDate}&period={period}")
    public ResponseEntity<List<Posting>> read(@PathVariable(name = "startDate") Date startDate,
                                              @PathVariable(name = "period") String period)
    {
        final List<Posting> postings = postingService.getByPeriod(startDate, period);

        return postings != null && !postings.isEmpty()
                ? new ResponseEntity<>(postings, HttpStatus.OK)
                : new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @GetMapping(value = "/postings?authorized={isAuthorized}&start-date={startDate}&period={period}")
    public ResponseEntity<List<Posting>> read(@PathVariable(name = "isAuthorized") boolean isAuthorized,
                                              @PathVariable(name = "startDate") Date startDate,
                                              @PathVariable(name = "period") String period)
    {
        final List<Posting> postings = postingService.getByPeriodAndIsAuthorized(startDate, period, isAuthorized);

        return postings != null && !postings.isEmpty()
                ? new ResponseEntity<>(postings, HttpStatus.OK)
                : new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

}

