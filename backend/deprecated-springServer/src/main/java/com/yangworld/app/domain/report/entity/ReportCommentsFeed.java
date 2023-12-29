package com.yangworld.app.domain.report.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

@Data
@SuperBuilder
@AllArgsConstructor
@NoArgsConstructor
public class ReportCommentsFeed extends Report{

	private int commentsId;
	
	public int getReportId() {
		return this.getId();
	}
}
