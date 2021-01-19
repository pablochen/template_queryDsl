package template.demo.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import template.demo.dto.TopicCond;
import template.demo.dto.TopicDto;

public interface TopicRepositoryCustom{
    public Page<TopicDto> search(TopicCond topicCond, Pageable pageable);
}
