package io.swagger.model;

import java.util.Objects;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

import org.springframework.validation.annotation.Validated;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonProperty;

import io.swagger.annotations.ApiModelProperty;

/**
 * Card
 */
@Validated
@javax.annotation.Generated(value = "io.swagger.codegen.languages.SpringCodegen", date = "2019-05-25T23:08:44.329Z")
@Entity(name = "TB_CARD")
public class Card {
	@JsonProperty("holderName")
	private String holderName = null;

	@Id
	@JsonProperty("number")
	private String number = null;

	@JsonProperty("expirationMonth")
	private Long expirationMonth = null;

	@JsonProperty("expirationYear")
	private Long expirationYear = null;

	@JsonProperty("CVV")
	private Integer CVV = null;

	@JsonProperty("brand")
	private String brand = null;

	@JsonProperty("valid")
	private Boolean valid = null;

	@Column(nullable = true)
	@OneToMany(mappedBy = "card", fetch = FetchType.EAGER)
	@JsonBackReference
	private Set<Payment> payments;

	public Card(String number, String brand, boolean valid) {
		this.number = number;
		this.brand = brand;
		this.valid = valid;
	}
	
	public Card() {}

	public Card holderName(String holderName) {
		this.holderName = holderName;
		return this;
	}

	/**
	 * Get holderName
	 * 
	 * @return holderName
	 **/
	@ApiModelProperty(example = "Giannis Antetokounmpo", value = "")

	public String getHolderName() {
		return holderName;
	}

	public void setHolderName(String holderName) {
		this.holderName = holderName;
	}

	public Card number(String number) {
		this.number = number;
		return this;
	}

	/**
	 * Get number
	 * 
	 * @return number
	 **/
	@ApiModelProperty(example = "5555666677778884", value = "")

	public String getNumber() {
		return number;
	}

	public void setNumber(String number) {
		this.number = number;
	}

	public Card expirationMonth(Long expirationMonth) {
		this.expirationMonth = expirationMonth;
		return this;
	}

	/**
	 * Get expirationMonth
	 * 
	 * @return expirationMonth
	 **/
	@ApiModelProperty(example = "12", value = "")

	public Long getExpirationMonth() {
		return expirationMonth;
	}

	public void setExpirationMonth(Long expirationMonth) {
		this.expirationMonth = expirationMonth;
	}

	public Card expirationYear(Long expirationYear) {
		this.expirationYear = expirationYear;
		return this;
	}

	/**
	 * Get expirationYear
	 * 
	 * @return expirationYear
	 **/
	@ApiModelProperty(example = "2022", value = "")

	public Long getExpirationYear() {
		return expirationYear;
	}

	public void setExpirationYear(Long expirationYear) {
		this.expirationYear = expirationYear;
	}

	public Card CVV(Integer CVV) {
		this.CVV = CVV;
		return this;
	}

	/**
	 * Get CVV
	 * 
	 * @return CVV
	 **/
	@ApiModelProperty(example = "270", value = "")

	public Integer getCVV() {
		return CVV;
	}

	public void setCVV(Integer CVV) {
		this.CVV = CVV;
	}

	public Card brand(String brand) {
		this.brand = brand;
		return this;
	}

	/**
	 * Get brand
	 * 
	 * @return brand
	 **/
	@ApiModelProperty(example = "MASTERCARD", value = "")

	public String getBrand() {
		return brand;
	}

	public void setBrand(String brand) {
		this.brand = brand;
	}

	public Card valid(Boolean valid) {
		this.valid = valid;
		return this;
	}

	/**
	 * Get valid
	 * 
	 * @return valid
	 **/
	@ApiModelProperty(example = "true", value = "")

	public Boolean isValid() {
		return valid;
	}

	public void setValid(Boolean valid) {
		this.valid = valid;
	}

	@Override
	public boolean equals(java.lang.Object o) {
		if (this == o) {
			return true;
		}
		if (o == null || getClass() != o.getClass()) {
			return false;
		}
		Card card = (Card) o;
		return Objects.equals(this.holderName, card.holderName) && Objects.equals(this.number, card.number)
				&& Objects.equals(this.expirationMonth, card.expirationMonth)
				&& Objects.equals(this.expirationYear, card.expirationYear) && Objects.equals(this.CVV, card.CVV)
				&& Objects.equals(this.brand, card.brand) && Objects.equals(this.valid, card.valid);
	}

	@Override
	public int hashCode() {
		return Objects.hash(holderName, number, expirationMonth, expirationYear, CVV, brand, valid);
	}

	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder();
		sb.append("class Card {\n");

		sb.append("    holderName: ").append(toIndentedString(holderName)).append("\n");
		sb.append("    number: ").append(toIndentedString(number)).append("\n");
		sb.append("    expirationMonth: ").append(toIndentedString(expirationMonth)).append("\n");
		sb.append("    expirationYear: ").append(toIndentedString(expirationYear)).append("\n");
		sb.append("    CVV: ").append(toIndentedString(CVV)).append("\n");
		sb.append("    brand: ").append(toIndentedString(brand)).append("\n");
		sb.append("    valid: ").append(toIndentedString(valid)).append("\n");
		sb.append("}");
		return sb.toString();
	}

	/**
	 * Convert the given object to string with each line indented by 4 spaces
	 * (except the first line).
	 */
	private String toIndentedString(java.lang.Object o) {
		if (o == null) {
			return "null";
		}
		return o.toString().replace("\n", "\n    ");
	}

	public Set<Payment> getPayments() {
		return payments;
	}

	public void setPayments(Set<Payment> payments) {
		this.payments = payments;
	}

	public Boolean getValid() {
		return valid;
	}
}
