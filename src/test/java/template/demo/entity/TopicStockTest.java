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
import static template.demo.entity.QTopicStock.topicStock;

@SpringBootTest
@Transactional
@Commit
class TopicStockTest {
    @Autowired
    EntityManager em;
    JPAQueryFactory jpaQueryFactory;

    @BeforeEach
    public void topicStockBasic() {
        jpaQueryFactory = new JPAQueryFactory(em);

        Topic topic = new Topic("노바벡스");
        em.persist(topic);

        TopicStock stock1 = new TopicStock(topic.getId(), "285130"); // SK케미칼
        TopicStock stock2 = new TopicStock(topic.getId(), "263690"); // 디알젬
        TopicStock stock3 = new TopicStock(topic.getId(), "217600"); // 켐온
        TopicStock stock4 = new TopicStock(topic.getId(), "006120"); // SK디스커버리
        TopicStock stock5 = new TopicStock(topic.getId(), "271980"); // 제일약품

        em.persist(stock1);
        em.persist(stock2);
        em.persist(stock3);
        em.persist(stock4);
        em.persist(stock5);

        em.flush();
        em.clear();

        Assertions.assertThat(stock1.getTopicId()).isEqualTo(topic.getId());
        Assertions.assertThat(stock2.getTopicId()).isEqualTo(topic.getId());
        Assertions.assertThat(stock3.getTopicId()).isEqualTo(topic.getId());
        Assertions.assertThat(stock4.getTopicId()).isEqualTo(topic.getId());
        Assertions.assertThat(stock5.getTopicId()).isEqualTo(topic.getId());
    }

    @Test
    public void topickStockSelect() {

        List<TopicStock> results = jpaQueryFactory
                .selectFrom(topicStock)
                .where(topicStock.topicId.eq(1))
                .orderBy(topicStock.id.asc())
                .fetch();

        TopicStock stock1 = results.get(0);
        TopicStock stock2 = results.get(1);
        TopicStock stock3 = results.get(2);

        assertThat(stock1.getCode()).isEqualTo("285130");
        assertThat(stock2.getCode()).isEqualTo("263690");
        assertThat(stock3.getCode()).isEqualTo("217600");

    }

    @Test
    public void topicStockUpdate() {
        jpaQueryFactory
                .update(topicStock)
                .set(topicStock.code, "217601")
                .where(
                        topicStock.topicId.eq(1),
                        topicStock.code.eq("217600")
                )
                .execute();

        TopicStock result = jpaQueryFactory
                .selectFrom(topicStock)
                .where(topicStock.id.eq(3))
                .fetchOne();

        assertThat(result.getCode()).isEqualTo("217601");
    }

}