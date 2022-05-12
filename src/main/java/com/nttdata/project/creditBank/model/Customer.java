package com.nttdata.project.creditBank.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.DocumentReference;
import org.springframework.data.mongodb.core.mapping.Field;

import java.util.List;

//@Getter
//@Setter
@Builder
@Data
@AllArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
@Document(collection = "customers")
public class Customer {
    @Id
    private String id;
    private String type;
    private String names;
    private String surnames;
    @Field(name = "doc_number")
    private String docNumber;
    private int age;
//    @JsonIgnore
//    @DocumentReference
//    private List<Account> accounts;
//    @JsonIgnore
//    @Field(name = "credit_card")
//    @DocumentReference
//    private CreditCard creditCard;
}
