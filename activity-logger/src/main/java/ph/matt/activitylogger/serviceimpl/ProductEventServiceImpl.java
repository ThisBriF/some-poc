package ph.matt.activitylogger.serviceimpl;

import org.springframework.stereotype.Service;

import lombok.extern.log4j.Log4j2;
import ph.matt.activitylogger.model.ProductEvent;
import ph.matt.activitylogger.service.ProductEventService;

@Service
@Log4j2
public class ProductEventServiceImpl implements ProductEventService {

    public String createProductEvent(ProductEvent event) {
        log.info("Creating product event row in DB");
        return "Yes";
    }

}
