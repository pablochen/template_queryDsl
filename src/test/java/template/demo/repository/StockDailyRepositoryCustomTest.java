package template.demo.repository;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.test.annotation.Commit;
import template.demo.dto.StockDailyCond;
import template.demo.dto.StockDailyDto;
import template.demo.entity.StockDaily;
import template.demo.entity.StockMaster;
import template.demo.entity.Topic;
import template.demo.entity.TopicStock;

import javax.persistence.EntityManager;
import javax.transaction.Transactional;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
@Transactional
@Commit
class StockDailyRepositoryCustomTest {

    @Autowired
    EntityManager em;

    @Autowired
    StockDailyRepository stockDailyRepository;

    @BeforeEach
    public void stockDailyRepositoryBasic() {

        Topic topic = new Topic("노바벡스");
        em.persist(topic);

        TopicStock stock1 = new TopicStock(topic.getId(), "285130");
        TopicStock stock2 = new TopicStock(topic.getId(), "263690");
        em.persist(stock1);
        em.persist(stock2);

        StockMaster stockMaster1 = new StockMaster("285130", "SK케미칼");
        StockMaster stockMaster2 = new StockMaster("263690", "디알젬");
        em.persist(stockMaster1);
        em.persist(stockMaster2);

        StockDaily stockDaily1 = new StockDaily("285130", "20210115", 356000, 349500, 362500, 346500, 194901 );
        StockDaily stockDaily2 = new StockDaily("285130", "20210114", 359000, 356000, 370000, 361000, 145147 );
        StockDaily stockDaily3 = new StockDaily("285130", "20210113", 365500, 361000, 370000, 361000, 118455 );
        StockDaily stockDaily4 = new StockDaily("263690", "20210115", 16000, 16200, 16250, 15850, 86136 );
        StockDaily stockDaily5 = new StockDaily("263690", "20210114", 16850, 16000, 16850, 15950, 117902 );
        StockDaily stockDaily6 = new StockDaily("263690", "20210113", 16350, 15750, 16400, 15500, 212558 );
        em.persist(stockDaily1);
        em.persist(stockDaily2);
        em.persist(stockDaily3);
        em.persist(stockDaily4);
        em.persist(stockDaily5);
        em.persist(stockDaily6);

        em.flush();
        em.clear();

    }

    @Test
    public void stockDailyRepositorySelect() {

        StockDailyCond stockDailyCond = new StockDailyCond();
        stockDailyCond.setTopicId(1);
        PageRequest pageRequest = PageRequest.of(0,20);
        Page<StockDailyDto> results = stockDailyRepository.search(stockDailyCond, pageRequest);

        assertThat(results.getContent().size()).isEqualTo(6);
        assertThat(results.getContent()).extracting("topicName").contains("노바벡스");
        assertThat(results.getContent()).extracting("stockCode").contains("263690");
        assertThat(results.getContent()).extracting("stockName").contains("SK케미칼");
        assertThat(results.getContent()).extracting("stockDate").contains("20210115");
        assertThat(results.getContent()).extracting("price").contains(16200);

    }

}