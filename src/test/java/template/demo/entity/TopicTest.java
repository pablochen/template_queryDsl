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
import static template.demo.entity.QTopic.topic;


@SpringBootTest
@Transactional
@Commit
class TopicTest {

    @Autowired
    EntityManager em;
    JPAQueryFactory jpaQueryFactory;

    @BeforeEach
    public void topicBasic() {
        jpaQueryFactory = new JPAQueryFactory(em);

        Topic topic1 = new Topic("노바벡스");
        Topic topic2 = new Topic("테슬라");
        Topic topic3 = new Topic("애플카");
        Topic topic4 = new Topic("쿠팡");

        em.persist(topic1);
        em.persist(topic2);
        em.persist(topic3);
        em.persist(topic4);

        em.flush();
        em.clear();
    }

    @Test
    public void topicSelect() {
        List<Topic> results = jpaQueryFactory
                .selectFrom(topic)
                .where(topic.id.goe(2))
                .orderBy(topic.id.asc())
                .fetch();

        Topic topic1 = results.get(0);
        Topic topic2 = results.get(1);
        Topic topic3 = results.get(2);

        assertThat(topic1.getName()).isEqualTo("테슬라");
        assertThat(topic2.getName()).isEqualTo("애플카");
        assertThat(topic3.getName()).isEqualTo("쿠팡");
    }

    @Test
    public void topicUpdate() {
        jpaQueryFactory
                .update(topic)
                .set(topic.name, "테슬라1")
                .where(topic.name.eq("테슬라"))
                .execute();

        Topic result = jpaQueryFactory
                .selectFrom(topic)
                .where(topic.name.contains("테슬라"))
                .fetchOne();

        assertThat(result.getName()).isEqualTo("테슬라1");
    }


}