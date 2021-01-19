package template.demo.repository;

import com.querydsl.core.types.dsl.BooleanExpression;
import com.querydsl.jpa.impl.JPAQueryFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import template.demo.dto.QTopicStockDto;
import template.demo.dto.TopicStockCond;
import template.demo.dto.TopicStockDto;

import javax.persistence.EntityManager;
import java.util.List;

import static template.demo.entity.QStockMaster.stockMaster;
import static template.demo.entity.QTopicStock.topicStock;


public class TopicStockRepositoryImpl implements TopicStockRepositoryCustom{
    private final JPAQueryFactory queryFactory;

    public TopicStockRepositoryImpl(EntityManager em){
        this.queryFactory = new JPAQueryFactory(em);
    }

    @Override
    public Page<TopicStockDto> search(TopicStockCond topicStockCond, Pageable pageable) {
        List<TopicStockDto> content = queryFactory
                .select(new QTopicStockDto(topicStock.id, topicStock.topicId, topicStock.code, stockMaster.name))
                .from(topicStock)
                .leftJoin(stockMaster)
                    .on(topicStock.code.eq(stockMaster.code))
                .where(
                        topicIdEq(topicStockCond.getTopicId()),
                        topicStock.useYn.eq("Y")
                )
                .orderBy(topicStock.id.asc())
                .offset(pageable.getOffset())
                .limit(pageable.getPageSize())
                .fetch();

        long total = queryFactory
                .select(new QTopicStockDto(topicStock.id, topicStock.topicId, topicStock.code, stockMaster.name))
                .from(topicStock)
                .leftJoin(stockMaster)
                .on(topicStock.code.eq(stockMaster.code))
                .where(
                        topicIdEq(topicStockCond.getTopicId()),
                        topicStock.useYn.eq("Y")
                )
                .fetchCount();

        return new PageImpl<>(content, pageable, total);
    }

    public BooleanExpression topicIdEq(Integer topicId){
        return (topicId != null && topicId != 0) ? topicStock.topicId.eq(topicId) : null;
    }


}
