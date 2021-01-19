package template.demo.entity;

import com.querydsl.jpa.impl.JPAQueryFactory;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Commit;

import javax.persistence.EntityManager;
import javax.transaction.Transactional;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static template.demo.entity.QStockMaster.stockMaster;

@SpringBootTest
@Transactional
@Commit
class StockMasterTest {

    @Autowired
    EntityManager em;
    JPAQueryFactory jpaQueryFactory;

    @BeforeEach
    public void stockMasterBasic() {
        jpaQueryFactory = new JPAQueryFactory(em);

        StockMaster stockMaster1 = new StockMaster("000020", "동화약품");
        StockMaster stockMaster2 = new StockMaster("000040", "KR모터스");
        StockMaster stockMaster3 = new StockMaster("000050", "경방");
        StockMaster stockMaster4 = new StockMaster("000060", "메리츠화재");
        StockMaster stockMaster5 = new StockMaster("000070", "삼양홀딩스");

        em.persist(stockMaster1);
        em.persist(stockMaster2);
        em.persist(stockMaster3);
        em.persist(stockMaster4);
        em.persist(stockMaster5);

        em.flush();
        em.clear();

        Assertions.assertThat(stockMaster1.getName()).isEqualTo("동화약품");
        Assertions.assertThat(stockMaster2.getName()).isEqualTo("KR모터스");
        Assertions.assertThat(stockMaster3.getName()).isEqualTo("경방");
        Assertions.assertThat(stockMaster4.getName()).isEqualTo("메리츠화재");
        Assertions.assertThat(stockMaster5.getName()).isEqualTo("삼양홀딩스");
    }

    @Test
    public void stockMasterSelect() {

        List<StockMaster> results = jpaQueryFactory
                .selectFrom(stockMaster)
                .where(stockMaster.code.like("00%"))
                .orderBy(stockMaster.id.asc())
                .fetch();

        StockMaster stock1 = results.get(0);
        StockMaster stock2 = results.get(1);
        StockMaster stock3 = results.get(2);

        assertThat(stock1.getCode()).isEqualTo("000020");
        assertThat(stock1.getName()).isEqualTo("동화약품");

        assertThat(stock2.getCode()).isEqualTo("000040");
        assertThat(stock2.getName()).isEqualTo("KR모터스");

        assertThat(stock3.getCode()).isEqualTo("000050");
        assertThat(stock3.getName()).isEqualTo("경방");

    }



}