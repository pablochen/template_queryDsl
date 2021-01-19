package template.demo.entity;

import com.querydsl.jpa.impl.JPAQueryFactory;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Commit;

import javax.persistence.EntityManager;
import javax.transaction.Transactional;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static template.demo.entity.QStockDaily.stockDaily;

@SpringBootTest
@Transactional
@Commit
class StockDailyTest {
    @Autowired
    EntityManager em;
    JPAQueryFactory jpaQueryFactory;

    @BeforeEach
    public void stockDailyBasic(){

        jpaQueryFactory = new JPAQueryFactory(em);

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

    }

    @Test
    public void stockDailySelect(){
        List<StockDaily> results = jpaQueryFactory
                .selectFrom(stockDaily)
                .where(stockDaily.code.eq("263690"))
                .orderBy(stockDaily.date.desc())
                .fetch();

        StockDaily stockDaily1 = results.get(0);
        StockDaily stockDaily2 = results.get(1);
        StockDaily stockDaily3 = results.get(2);

        assertThat(stockDaily1.getHighPrice()).isEqualTo(16250);
        assertThat(stockDaily2.getStartPrice()).isEqualTo(16850);
        assertThat(stockDaily3.getQty()).isEqualTo(212558);
    }

    // stockDaily는 수정/삭제되지 않음.


}