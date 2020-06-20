package livemarket.business.kotlin.app.repository;


import livemarket.business.kotlin.app.models.Transaction;
import org.springframework.data.mongodb.repository.MongoRepository;


public interface TransactionDao extends MongoRepository<Transaction, String> {

}
