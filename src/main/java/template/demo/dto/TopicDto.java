package template.demo.dto;

import com.querydsl.core.annotations.QueryProjection;
import lombok.*;

@Data
@Getter @Setter
@NoArgsConstructor
public class TopicDto {
    private int topicId;
    private String topicName;

    @QueryProjection
    public TopicDto(int topicId, String topicName) {
        this.topicId = topicId;
        this.topicName = topicName;
    }
}
