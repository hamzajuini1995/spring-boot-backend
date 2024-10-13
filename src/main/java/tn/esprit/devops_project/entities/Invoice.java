package tn.esprit.devops_project.entities;

import java.io.Serializable;
import java.util.Date;
import java.util.Objects;
import java.util.Set;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;
import lombok.experimental.FieldDefaults;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class Invoice implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	Long idInvoice;
	float amountDiscount;
	float amountInvoice;
	@Temporal(TemporalType.DATE)
	Date dateCreationInvoice;
	@Temporal(TemporalType.DATE)
	Date dateLastModificationInvoice;
	Boolean archived;
	@OneToMany(mappedBy = "invoice")
	Set<InvoiceDetail> invoiceDetails;
    @ManyToOne
    @JsonIgnore
    Supplier supplier;


	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (o == null || getClass() != o.getClass()) return false;
		Invoice invoice = (Invoice) o;
		return Float.compare(invoice.amountDiscount, amountDiscount) == 0 &&
				Float.compare(invoice.amountInvoice, amountInvoice) == 0 &&
				Objects.equals(idInvoice, invoice.idInvoice) &&
				Objects.equals(dateCreationInvoice, invoice.dateCreationInvoice) &&
				Objects.equals(dateLastModificationInvoice, invoice.dateLastModificationInvoice) &&
				Objects.equals(archived, invoice.archived) &&
				Objects.equals(invoiceDetails, invoice.invoiceDetails) &&
				Objects.equals(supplier, invoice.supplier);
	}


	@Override
	public int hashCode() {
		return Objects.hash(idInvoice, amountDiscount, amountInvoice, dateCreationInvoice,
				dateLastModificationInvoice, archived, invoiceDetails, supplier);
	}

	
}
