package template.demo.repository;

import com.querydsl.core.types.dsl.BooleanExpression;
import com.querydsl.jpa.impl.JPAQueryFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import template.demo.dto.QTopicDto;
import template.demo.dto.TopicCond;
import template.demo.dto.TopicDto;

import javax.persistence.EntityManager;
import java.util.List;

import static org.springframework.util.StringUtils.hasText;
import static template.demo.entity.QTopic.topic;

public class TopicRepositoryImpl implements TopicRepositoryCustom {

    private final JPAQueryFactory queryFactory;

    public TopicRepositoryImpl(EntityManager em){
        this.queryFactory = new JPAQueryFactory(em);
    }

    @Override
    public Page<TopicDto> search(TopicCond topicCond, Pageable pageable) {
        List<TopicDto> content = queryFactory
                .select(new QTopicDto(topic.id, topic.name))
                .from(topic)
                .where(
                        topicIdEq(topicCond.getTopicId()),
                        topicNameEq(topicCond.getTopicName()),
                        topicAlive()
                )
                .orderBy(topic.id.asc())
                .offset(pageable.getOffset())
                .limit(pageable.getPageSize())
                .fetch();

        long total = queryFactory
                .select(new QTopicDto(topic.id, topic.name))
                .from(topic)
                .where(
                        topicIdEq(topicCond.getTopicId()),
                        topicNameEq(topicCond.getTopicName()),
                        topicAlive()
                )
                .fetchCount();

        return new PageImpl<>(content, pageable, total);
    }

    public BooleanExpression topicIdEq(Integer topicId){
        return (topicId != null && topicId != 0) ? topic.id.eq(topicId) : null;
    }

    public BooleanExpression topicNameEq(String topicName){
        return hasText(topicName) ? topic.name.eq(topicName) : null;
    }

    public BooleanExpression topicAlive(){
        return topic.useYn.eq("Y");
    }

}
