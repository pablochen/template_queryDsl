package template.demo.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import template.demo.dto.StockDailyCond;
import template.demo.dto.StockDailyDto;

public interface StockDailyRepositoryCustom {
    Page<StockDailyDto> search(StockDailyCond topicCond, Pageable pageable);
}
