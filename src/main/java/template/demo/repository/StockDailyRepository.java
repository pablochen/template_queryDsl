package template.demo.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import template.demo.dto.StockDailyCond;
import template.demo.dto.StockDailyDto;
import template.demo.entity.StockDaily;

import java.util.List;

public interface StockDailyRepository extends JpaRepository<StockDaily, Integer>, StockDailyRepositoryCustom {
    List<StockDaily> findByCode(String stockCode);
    Page<StockDailyDto> search(StockDailyCond stockDailyCond, Pageable pageable);
}
