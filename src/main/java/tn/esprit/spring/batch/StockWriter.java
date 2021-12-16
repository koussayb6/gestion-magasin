package tn.esprit.spring.batch;
import java.util.List;

import org.springframework.batch.item.ItemWriter;
import org.springframework.beans.factory.annotation.Autowired;
import lombok.extern.slf4j.Slf4j;
import tn.esprit.spring.entity.Stock;
import tn.esprit.spring.service.IserviceStock;
@Slf4j
public class StockWriter implements ItemWriter<Stock> {

    @Autowired
    private IserviceStock stockService;

    /* écrire nos données dans la base de données*/
    public void write(List<? extends Stock> stocks) {
            log.info("Enregistrement des lignes stocks dans la base de données", stocks);
            /*toDo 10*/
            
    }
}