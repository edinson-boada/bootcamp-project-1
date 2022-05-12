package com.nttdata.project.creditBank.model;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

@Data
@AllArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
@Document(collection = "people")
public class Person {
    @Id
    private String id;
    @Field(name = "doc_number")
    private String docNumber;
    private String email;
    private String cellphone;
}
