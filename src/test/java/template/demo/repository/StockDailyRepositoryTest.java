package template.demo.repository;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Commit;
import template.demo.entity.StockDaily;

import javax.persistence.EntityManager;
import javax.transaction.Transactional;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
@Transactional
@Commit
class StockDailyRepositoryTest {

    @Autowired
    EntityManager em;

    @Autowired
    StockDailyRepository stockDailyRepository;

    @BeforeEach
    public void stockDailyBasic(){

        StockDaily stockDaily1 = new StockDaily("285130", "20210115", 356000, 349500, 362500, 346500, 194901 );
        StockDaily stockDaily2 = new StockDaily("285130", "20210114", 359000, 356000, 370000, 361000, 145147 );
        StockDaily stockDaily3 = new StockDaily("285130", "20210113", 365500, 361000, 370000, 361000, 118455 );
        StockDaily stockDaily4 = new StockDaily("263690", "20210115", 16000, 16200, 16250, 15850, 86136 );
        StockDaily stockDaily5 = new StockDaily("263690", "20210114", 16850, 16000, 16850, 15950, 117902 );
        StockDaily stockDaily6 = new StockDaily("263690", "20210113", 16350, 15750, 16400, 15500, 212558 );

        stockDailyRepository.save(stockDaily1);
        stockDailyRepository.save(stockDaily2);
        stockDailyRepository.save(stockDaily3);
        stockDailyRepository.save(stockDaily4);
        stockDailyRepository.save(stockDaily5);
        stockDailyRepository.save(stockDaily6);

    }


    @Test
    public void stockDailySelect(){
        List<StockDaily> result = stockDailyRepository.findByCode("285130");
        assertThat(result.size()).isEqualTo(3);

        List<StockDaily> results = stockDailyRepository.findAll();
        assertThat(results.size()).isEqualTo(6);
    }


}