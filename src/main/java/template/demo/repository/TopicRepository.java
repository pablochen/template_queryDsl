package template.demo.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import template.demo.entity.Topic;

import java.util.List;

public interface TopicRepository extends JpaRepository<Topic, Integer>, TopicRepositoryCustom {
    List<Topic> findByName(String name);
}
