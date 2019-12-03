package com.example.currencyConverter.entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "exchange")
public class Exchange {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "exchange_id")
	private int exchangeId;

	@Column(name = "value")
	private Double value;

	@Column(name = "baseCurrency")
	private String baseCurrency;

	@Column(name = "targetCurrency")
	private String targetCurrency;

	@Column(name = "baseMid")
	private Double baseMid;

	@Column(name = "targetMid")
	private Double targetMid;

	@Column(name = "finalValue")
	private Double finalValue;

	public Double getBaseMid() {
		return baseMid;
	}

	public void setBaseMid(Double baseMid) {
		this.baseMid = baseMid;
	}

	public Double getTargetMid() {
		return targetMid;
	}

	public void setTargetMid(Double targetMid) {
		this.targetMid = targetMid;
	}

	public Double getFinalValue() {
		return finalValue;
	}

	public void setFinalValue(Double finalValue) {
		this.finalValue = finalValue;
	}

	public Exchange() {
	}

	public int getExchangeId() {
		return exchangeId;
	}

	public void setExchangeId(int exchangeId) {
		this.exchangeId = exchangeId;
	}

	public Double getValue() {
		return value;
	}

	public void setValue(Double value) {
		this.value = value;
	}

	public String getBaseCurrency() {
		return baseCurrency;
	}

	public void setBaseCurrency(String baseCurrency) {
		this.baseCurrency = baseCurrency;
	}

	public String getTargetCurrency() {
		return targetCurrency;
	}

	public void setTargetCurrency(String targetCurrency) {
		this.targetCurrency = targetCurrency;
	}

}
