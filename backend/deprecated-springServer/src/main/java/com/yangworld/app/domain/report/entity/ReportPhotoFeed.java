package com.yangworld.app.domain.report.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

@Data
@SuperBuilder
@AllArgsConstructor
@NoArgsConstructor
public class ReportPhotoFeed extends Report{
	
	private int photoFeedId;

	public int getReportId() {
		return this.getId();
	}
}
