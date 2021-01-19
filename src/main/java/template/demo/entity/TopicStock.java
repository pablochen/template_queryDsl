package template.demo.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Getter @Setter
@NoArgsConstructor @AllArgsConstructor
@SequenceGenerator(name="TOPIC_STOCK_SEQ_GEN", sequenceName="TOPIC_STOCK_SEQ", initialValue=1, allocationSize=1)
public class TopicStock {

    @Id
    @Column(name = "TOPIC_STOCK_ID")
    @GeneratedValue(strategy=GenerationType.SEQUENCE, generator="TOPIC_STOCK_SEQ_GEN")
    private int id;

    @Column(name = "TOPIC_ID")
    private int topicId;

    @Column(name = "STOCK_CODE")
    private String code;

    @Column(name = "USE_YN")
    private String useYn;

    public TopicStock(int topicId, String stockCode){
        this.topicId = topicId;
        this.code = stockCode;
        this.useYn = "Y";
    }

}
