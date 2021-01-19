package template.demo.dto;

import com.querydsl.core.annotations.QueryProjection;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Data
@Getter @Setter
@NoArgsConstructor
public class StockDailyDto {
    private int stockDailyId;
    private String stockDate;
    private int price;

    @QueryProjection
    public StockDailyDto(int stockDailyId, String stockDate, int price){
        this.stockDailyId = stockDailyId;
        this.stockDate = stockDate;
        this.price = price;
    }
}
