package template.demo.repository;

import com.querydsl.jpa.impl.JPAQueryFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import template.demo.dto.QStockDailyDto;
import template.demo.dto.StockDailyCond;
import template.demo.dto.StockDailyDto;

import javax.persistence.EntityManager;
import java.util.List;

import static template.demo.entity.QStockDaily.stockDaily;

public class StockDailyRepositoryImpl implements StockDailyRepositoryCustom {

    private final JPAQueryFactory queryFactory;

    public StockDailyRepositoryImpl(EntityManager em){
        this.queryFactory = new JPAQueryFactory(em);
    }

    @Override
    public Page<StockDailyDto> search(StockDailyCond stockDailyCond, Pageable pageable) {

        List<StockDailyDto> content = queryFactory
                .select(new QStockDailyDto(stockDaily.id, stockDaily.date, stockDaily.endPrice))
                .from(stockDaily)
                .where(stockDaily.code.eq(stockDailyCond.getStockCode()))
                .orderBy(stockDaily.date.desc())
                .offset(pageable.getOffset())
                .limit(pageable.getPageSize())
                .fetch();

        long total = queryFactory
                .select(new QStockDailyDto(stockDaily.id, stockDaily.date, stockDaily.endPrice))
                .from(stockDaily)
                .where(stockDaily.code.eq(stockDailyCond.getStockCode()))
                .fetchCount();

        return new PageImpl<>(content, pageable, total);
    }

}
