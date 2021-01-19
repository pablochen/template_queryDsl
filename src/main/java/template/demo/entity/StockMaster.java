package template.demo.entity;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;


@Entity
@Getter @Setter
@NoArgsConstructor @AllArgsConstructor
@SequenceGenerator(name="STOCK_MASTER_SEQ_GEN", sequenceName="STOCK_MASTER_SEQ", initialValue=1, allocationSize=1)
public class StockMaster {
    @Id
    @Column(name = "STOCK_MASTER_ID")
    @GeneratedValue(strategy=GenerationType.SEQUENCE, generator="STOCK_MASTER_SEQ_GEN")
    private int id;

    @Column(name = "STOCK_CODE")
    private String code;

    @Column(name = "STOCK_NAME")
    private String name;

    @Column(name = "USE_YN")
    private String useYn;

    public StockMaster(String stockCode, String stockName){
        this.code = stockCode;
        this.name = stockName;
        this.useYn = "Y";
    }
}
