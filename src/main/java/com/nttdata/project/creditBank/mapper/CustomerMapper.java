package com.nttdata.project.creditBank.mapper;

import com.nttdata.project.creditBank.model.Customer;
import com.nttdata.project.creditBank.model.api.CustomerProductsResponse;
import org.mapstruct.Mapper;
import org.mapstruct.NullValueCheckStrategy;
import org.mapstruct.NullValuePropertyMappingStrategy;

@Mapper(componentModel = "spring", nullValueCheckStrategy = NullValueCheckStrategy.ALWAYS,
        nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
public interface CustomerMapper {
    CustomerProductsResponse toCustomerProductsResponse(Customer customer);
}
