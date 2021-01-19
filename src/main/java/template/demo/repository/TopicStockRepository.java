package template.demo.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import template.demo.entity.TopicStock;

public interface TopicStockRepository extends JpaRepository<TopicStock, Integer>, TopicStockRepositoryCustom {
}
