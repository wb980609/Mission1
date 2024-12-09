package dto;

import lombok.*;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class HistoryDTO {

    private int id;                     //id
    private String lat;                 //lat
    private String lnt;                 //lnt
    private String reg_dt;          //조회일자

}