package com.yangworld.app.domain.report.entity;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import lombok.experimental.SuperBuilder;

@Data
@SuperBuilder
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class ReportStory extends Report{

    private int storyId;

    public int getReportId(){
        return this.getId();
    }
}
