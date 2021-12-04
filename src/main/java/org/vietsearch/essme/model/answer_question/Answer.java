package org.vietsearch.essme.model.answer_question;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.google.api.client.util.DateTime;
import lombok.Data;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;
import org.springframework.format.annotation.DateTimeFormat;

@Data
public class Answer {

	@JsonProperty("createdAt")
	@Field("createdAt")
	@DateTimeFormat
	private DateTime createdAt;

	@JsonProperty("answer")
	@Field("answer")
	private String answer;

	@JsonProperty("expert_id")
	@Field("expert_id")
	private String expertId;

	@JsonProperty("vote")
	@Field("vote")
	private int vote;

	@JsonProperty("updatedAt")
	@Field("updatedAt")
	@DateTimeFormat
	private DateTime updatedAt;
}