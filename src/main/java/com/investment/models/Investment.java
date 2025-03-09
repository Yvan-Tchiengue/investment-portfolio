package com.investment.models;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;

@Entity
public class Investment {
    @Id 
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String assetName; // Nom de l’actif (Ex: Apple, Bitcoin)
    private String assetType; // Type (Stock, Crypto, Bond)
    private double amountInvested;
    private double currentValue;
    
    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

	public String getAssetName() {
		return assetName;
	}

	public void setAssetName(String assetName) {
		this.assetName = assetName;
	}

	public String getAssetType() {
		return assetType;
	}

	public void setAssetType(String assetType) {
		this.assetType = assetType;
	}

	public double getAmountInvested() {
		return amountInvested;
	}

	public void setAmountInvested(double amountInvested) {
		this.amountInvested = amountInvested;
	}

	public double getCurrentValue() {
		return currentValue;
	}

	public void setCurrentValue(double currentValue) {
		this.currentValue = currentValue;
	}
}
