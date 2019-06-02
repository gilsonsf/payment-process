package io.swagger.model;

import java.util.Objects;
import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.validation.Valid;

import org.springframework.validation.annotation.Validated;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonValue;

import io.swagger.annotations.ApiModelProperty;

/**
 * Payment
 */
@Validated
@javax.annotation.Generated(value = "io.swagger.codegen.languages.SpringCodegen", date = "2019-05-25T23:08:44.329Z")
@Entity(name = "TB_PAYMENT")
public class Payment {
	@JsonProperty("id")
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id = null;

	@JsonProperty("amount")
	private Double amount = null;

	/**
	 * Gets or Sets type
	 */
	public enum TypeEnum {
		CREDIT_CARD("CREDIT_CARD"),

		BOLETO("BOLETO");

		private String value;

		TypeEnum(String value) {
			this.value = value;
		}

		@Override
		@JsonValue
		public String toString() {
			return String.valueOf(value);
		}

		@JsonCreator
		public static TypeEnum fromValue(String text) {
			for (TypeEnum b : TypeEnum.values()) {
				if (String.valueOf(b.value).equals(text)) {
					return b;
				}
			}
			return null;
		}
	}

	@JsonProperty("type")
	private TypeEnum type = null;

	/**
	 * Gets or Sets status
	 */
	public enum StatusEnum {
		CREATED("CREATED"),

		IN_ANALYSIS("IN_ANALYSIS"),

		AUTHORIZED("AUTHORIZED"),

		CANCELLED("CANCELLED");

		private String value;

		StatusEnum(String value) {
			this.value = value;
		}

		@Override
		@JsonValue
		public String toString() {
			return String.valueOf(value);
		}

		@JsonCreator
		public static StatusEnum fromValue(String text) {
			for (StatusEnum b : StatusEnum.values()) {
				if (String.valueOf(b.value).equals(text)) {
					return b;
				}
			}
			return null;
		}
	}

	@JsonProperty("status")
	private StatusEnum status = null;

	@JsonProperty("card")
	@ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "CARD_ID")
	private Card card = null;

	@JsonProperty("client")
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "CLIENT_ID")
	private Client client = null;

	@JsonProperty("buyer")
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "BUYER_ID")
	private Buyer buyer = null;
	
	@OneToMany(mappedBy = "payment")
	@JsonBackReference
	private Set<PaymentHistory> paymentHistory;

	/**
	 * Get id
	 * 
	 * @return id
	 **/
	@ApiModelProperty(example = "37", value = "")

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Payment amount(Double amount) {
		this.amount = amount;
		return this;
	}

	/**
	 * Get amount
	 * 
	 * @return amount
	 **/
	@ApiModelProperty(example = "901.66", value = "")

	public Double getAmount() {
		return amount;
	}

	public void setAmount(Double amount) {
		this.amount = amount;
	}

	public Payment type(TypeEnum type) {
		this.type = type;
		return this;
	}

	/**
	 * Get type
	 * 
	 * @return type
	 **/
	@ApiModelProperty(value = "")

	public TypeEnum getType() {
		return type;
	}

	public void setType(TypeEnum type) {
		this.type = type;
	}

	public Payment status(StatusEnum status) {
		this.status = status;
		return this;
	}

	/**
	 * Get status
	 * 
	 * @return status
	 **/
	@ApiModelProperty(value = "")

	public StatusEnum getStatus() {
		return status;
	}

	public void setStatus(StatusEnum status) {
		this.status = status;
	}

	public Payment card(Card card) {
		this.card = card;
		return this;
	}

	/**
	 * Get card
	 * 
	 * @return card
	 **/
	@ApiModelProperty(value = "")

	@Valid

	public Card getCard() {
		return card;
	}

	public void setCard(Card card) {
		this.card = card;
	}

	public Payment client(Client client) {
		this.client = client;
		return this;
	}

	/**
	 * Get client
	 * 
	 * @return client
	 **/
	@ApiModelProperty(value = "")

	@Valid

	public Client getClient() {
		return client;
	}

	public void setClient(Client client) {
		this.client = client;
	}

	public Payment buyer(Buyer buyer) {
		this.buyer = buyer;
		return this;
	}

	/**
	 * Get buyer
	 * 
	 * @return buyer
	 **/
	@ApiModelProperty(value = "")

	@Valid

	public Buyer getBuyer() {
		return buyer;
	}

	public void setBuyer(Buyer buyer) {
		this.buyer = buyer;
	}

	@ApiModelProperty(example = "", hidden = true, value = "")
	public Set<PaymentHistory> getPaymentHistory() {
		return paymentHistory;
	}

	public void setPaymentHistory(Set<PaymentHistory> paymentHistory) {
		this.paymentHistory = paymentHistory;
	}

	
	@Override
	public boolean equals(java.lang.Object o) {
		if (this == o) {
			return true;
		}
		if (o == null || getClass() != o.getClass()) {
			return false;
		}
		Payment payment = (Payment) o;
		return Objects.equals(this.id, payment.id) && Objects.equals(this.amount, payment.amount)
				&& Objects.equals(this.type, payment.type) && Objects.equals(this.status, payment.status)
				&& Objects.equals(this.card, payment.card) && Objects.equals(this.client, payment.client)
				&& Objects.equals(this.buyer, payment.buyer);
	}

	@Override
	public int hashCode() {
		return Objects.hash(id, amount, type, status, card, client, buyer);
	}

	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder();
		sb.append("class Payment {\n");

		sb.append("    id: ").append(toIndentedString(id)).append("\n");
		sb.append("    amount: ").append(toIndentedString(amount)).append("\n");
		sb.append("    type: ").append(toIndentedString(type)).append("\n");
		sb.append("    status: ").append(toIndentedString(status)).append("\n");
		sb.append("    card: ").append(toIndentedString(card)).append("\n");
		sb.append("    client: ").append(toIndentedString(client)).append("\n");
		sb.append("    buyer: ").append(toIndentedString(buyer)).append("\n");
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

}
