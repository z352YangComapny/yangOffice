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
public class ReportProfile extends Report {

	private int profileId;
	
	public int getReportId(){
		return this.getId();
	}
}
