package template.demo.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import template.demo.dto.TopicStockCond;
import template.demo.dto.TopicStockDto;

public interface TopicStockRepositoryCustom {
    public Page<TopicStockDto> search(TopicStockCond topicStockCond, Pageable pageable);
}
