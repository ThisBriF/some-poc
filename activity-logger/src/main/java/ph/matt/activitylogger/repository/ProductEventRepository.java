package ph.matt.activitylogger.repository;

import org.springframework.data.mongodb.repository.MongoRepository;

import ph.matt.activitylogger.model.ProductEvent;

public interface ProductEventRepository extends MongoRepository<ProductEvent, String> {

}
