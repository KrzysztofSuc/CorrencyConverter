package com.example.currencyConverter.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.currencyConverter.entities.Exchange;

@Repository
public interface ExchangeRepository extends JpaRepository<Exchange, Integer> {

}
