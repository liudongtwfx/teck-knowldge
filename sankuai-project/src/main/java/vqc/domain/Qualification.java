package vqc.domain;

import lombok.Data;

import java.util.Date;
import java.util.List;

@Data
public class Qualification {
    private Long qualificationMetaId;
    private String qualificationName;
    private List<String> url;
    private Boolean longTerm;
    private Date expireTime;
}
