package template.demo.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Getter @Setter
@NoArgsConstructor @AllArgsConstructor
@SequenceGenerator(name="TOPIC_SEQ_GEN", sequenceName="TOPIC_SEQ", initialValue=1, allocationSize=1)
public class Topic {

    @Id
    @Column(name = "TOPIC_ID")
    @GeneratedValue(strategy=GenerationType.SEQUENCE, generator="TOPIC_SEQ_GEN")
    private int id;

    @Column(name = "TOPIC_NAME")
    private String name;

    private String useYn;

    public Topic (String topicName){
        this.name = topicName;
        this.useYn = "Y";
    }

}

