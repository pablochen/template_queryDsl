package template.demo.repository;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import template.demo.entity.Topic;

import javax.persistence.EntityManager;
import javax.transaction.Transactional;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
@Transactional
class TopicRepositoryTest {

    @Autowired
    EntityManager em;

    @Autowired
    TopicRepository topicRepository;

    @BeforeEach
    public void topicJpaBasic() {

        Topic topic1 = new Topic("노바벡스");
        Topic topic2 = new Topic("테슬라");
        Topic topic3 = new Topic("애플카");
        Topic topic4 = new Topic("쿠팡");

        topicRepository.save(topic1);
        topicRepository.save(topic2);
        topicRepository.save(topic3);
        topicRepository.save(topic4);

        em.flush();
        em.clear();

    }

    @Test
    public void topicJpaSelect() {

        Topic result = topicRepository.findById(3).get();
        assertThat(result.getName()).isEqualTo("애플카");

        List<Topic> results = topicRepository.findAll();
        assertThat(results.size()).isEqualTo(4);

        List<Topic> resultName = topicRepository.findByName("쿠팡");
        assertThat(resultName.get(0).getId()).isEqualTo(4);

    }

    @Test
    public void topicJpaUpdate() {

        Topic result = topicRepository.findById(1).get();
        result.setName("노바백수");
        topicRepository.save(result);

        Topic resultSave = topicRepository.findById(1).get();
        assertThat(resultSave.getName()).isEqualTo("노바백수");

    }

}