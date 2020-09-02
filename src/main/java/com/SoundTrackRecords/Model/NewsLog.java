package com.SoundTrackRecords.Model;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import java.io.Serializable;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import java.util.Date;
import java.util.UUID;
import org.hibernate.annotations.GenericGenerator;

@Entity
@ToString
@NoArgsConstructor
@Data
@Table(name="newslog")
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class NewsLog{
    @Id
     @GeneratedValue(generator = "uuid2")
     @GenericGenerator(name = "uuid2", strategy = "uuid2")
//    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID id;
    private String photo;
    private String title;
    private String note;
    private Date dateandtime; 
}
