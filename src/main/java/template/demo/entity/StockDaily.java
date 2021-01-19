package template.demo.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Getter @Setter
@AllArgsConstructor @NoArgsConstructor
@SequenceGenerator(name="STOCK_DAILY_SEQ_GEN", sequenceName="STOCK_DAILY_SEQ", initialValue=1, allocationSize=1)
public class StockDaily {

    @Id
    @Column(name = "STOCK_DAILY_ID")
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator="STOCK_DAILY_SEQ")
    private int id;

    @Column(name = "STOCK_CODE")
    private String code;

    @Column(name = "STOCK_DATE")
    private String date;

    private int startPrice;
    private int endPrice;
    private int highPrice;
    private int lowPrice;

    private int qty;

    public StockDaily(String stockCode, String stockDate, int startPrice, int endPrice, int highPrice, int lowPrice, int qty){
        this.code = stockCode;
        this.date = stockDate;
        this.startPrice = startPrice;
        this.endPrice = endPrice;
        this.highPrice = highPrice;
        this.lowPrice = lowPrice;
        this.qty = qty;
    }

}
