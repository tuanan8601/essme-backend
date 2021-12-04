package org.vietsearch.essme.model.answer_question;

import java.util.List;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.google.api.client.util.DateTime;
import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;
import org.springframework.format.annotation.DateTimeFormat;

@Data
@Document("answer_question")
public class Question {

	@JsonProperty("createdAt")
	@Field("createdAt")
	@DateTimeFormat
	private String createdAt;

	@JsonProperty("Description")
	@Field("Description")
	private String description;

	@JsonProperty("Customer_id")
	@Field("Customer_id")
	private String customerId;

	@JsonProperty("answers")
	private List<Answer> answers;

	@JsonProperty("Title")
	@Field("Title")
	private String title;

	@Id
	@JsonProperty(value = "_id", access = JsonProperty.Access.READ_ONLY)
	private String _id;

	@JsonProperty("Topic")
	@Field("Topic")
	private List<String> topic;

	@JsonProperty("vote")
	@Field("vote")
	private int vote;

	@JsonProperty("updatedAt")
	@Field("updatedAt")
	@DateTimeFormat
	private String updatedAt;
}