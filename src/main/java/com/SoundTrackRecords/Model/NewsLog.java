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

@Entity
@ToString
@NoArgsConstructor
@Data
@Table(name="newslog")
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class NewsLog implements Serializable{
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String photo;
    private String title;
    private String note;
    private Date dateandtime; 
}
