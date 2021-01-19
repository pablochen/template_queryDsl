package template.demo.dto;

import com.querydsl.core.annotations.QueryProjection;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Data
@Getter @Setter
@NoArgsConstructor
public class TopicStockDto {
    private int topicStockId;
    private int topicId;
    private String stockCode;
    private String stockName;

    @QueryProjection
    public TopicStockDto(int topicStockId, int topicId, String stockCode, String stockName){
        this.topicStockId = topicStockId;
        this.topicId = topicId;
        this.stockCode = stockCode;
        this.stockName = stockName;
    }

}
