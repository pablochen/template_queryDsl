package template.demo.repository;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import template.demo.dto.TopicCond;
import template.demo.dto.TopicDto;
import template.demo.entity.Topic;

import javax.persistence.EntityManager;
import javax.transaction.Transactional;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
@Transactional
class TopicRepositoryCustomTest {

    @Autowired
    EntityManager em;

    @Autowired
    TopicRepository topicRepository;

    @BeforeEach
    public void topicCustomBasic() {

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
    public void topicCustomSearch() {

        TopicCond topicCond = new TopicCond();
        PageRequest pageRequest = PageRequest.of(0,3);
        Page<TopicDto> results = topicRepository.search(topicCond, pageRequest);
        assertThat(results.getSize()).isEqualTo(3);
        assertThat(results.getContent()).extracting("topicName").contains("노바벡스");

    }

    @Test
    public void topicCustomSearchPagingComplex() {

        TopicCond topicCond = new TopicCond();
        PageRequest pageRequest = PageRequest.of(0,3);
        Page<TopicDto> results = topicRepository.search(topicCond, pageRequest);
        assertThat(results.getSize()).isEqualTo(3);
        assertThat(results.getContent()).extracting("topicName").contains("노바벡스");

    }

}