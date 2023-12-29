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
public class ReportDm extends Report{
    private int dmId;
    
    public int getReportId() {
        return this.getId();
    }
}

