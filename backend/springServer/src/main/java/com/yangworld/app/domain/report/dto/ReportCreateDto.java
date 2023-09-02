package com.yangworld.app.domain.report.dto;

import javax.validation.constraints.NotBlank;

import com.yangworld.app.domain.report.entity.Report;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ReportCreateDto {
	private int id;
	
	@NotBlank(message="신고할사람 입력")
	private int reportedId;
	@NotBlank(message="신고사유 입력")
	private String content;
	
	public Report toReport() {
		return Report.builder()
				.reportedId(reportedId)
				.content(content)
				.build();
	}
	
}
