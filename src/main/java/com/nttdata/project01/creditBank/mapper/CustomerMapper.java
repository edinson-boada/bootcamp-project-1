package com.nttdata.project01.creditBank.mapper;

import com.nttdata.project01.creditBank.model.Customer;
import com.nttdata.project01.creditBank.model.Dto.CustomerDto;
import org.mapstruct.Mapper;
import org.mapstruct.NullValueCheckStrategy;
import org.mapstruct.NullValuePropertyMappingStrategy;

@Mapper(componentModel = "spring", nullValueCheckStrategy = NullValueCheckStrategy.ALWAYS,
        nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
public interface CustomerMapper {
    CustomerDto toDto(Customer customer);
}

