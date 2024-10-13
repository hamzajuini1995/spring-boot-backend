package InvoiceTest;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import tn.esprit.devops_project.entities.Invoice;
import tn.esprit.devops_project.entities.Supplier;
import tn.esprit.devops_project.entities.SupplierCategory;
import tn.esprit.devops_project.repositories.InvoiceRepository;
import tn.esprit.devops_project.services.InvoiceServiceImpl;
import static org.hamcrest.CoreMatchers.is;

import java.util.*;

import static org.hamcrest.CoreMatchers.notNullValue;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.isEmptyOrNullString;
import static org.hamcrest.Matchers.nullValue;
import static org.mockito.BDDMockito.given;


@RunWith(MockitoJUnitRunner.class)
public class InvoiceServiceImplTest {

    @Mock
    InvoiceRepository invoiceRepository;

    @InjectMocks
    InvoiceServiceImpl invoiceService;

    @Test
    public void testGetAllInvoices() {
        Invoice invoice1 = new Invoice(2L, 20.0f, 300.0f, new Date(), new Date(), true, new HashSet<>(), null);
        Invoice invoice2 = new Invoice(2L, 20.0f, 300.0f, new Date(), new Date(), true, new HashSet<>(), null);
        Supplier supplier3 = new Supplier(1L, "SUP123", "Supplier 123", SupplierCategory.CONVENTIONNE, new HashSet<>());
        Invoice invoice3 = new Invoice(3L, 0.0f, 1000.0f, new Date(), new Date(), false, new HashSet<>(), supplier3);
        List<Invoice> invoices = Arrays.asList(invoice1,invoice2,invoice3);
        given(invoiceRepository.findAll()).willReturn(invoices);
        List<Invoice> invoicesResult = invoiceService.retrieveAllInvoices();
        assertThat(invoicesResult.size(), is(3));
        assertThat(invoice3, is(invoicesResult.get(2)));
    }

    @Test
    public void testCancelInvoice() {
        Invoice invoice1 = new Invoice(2L, 20.0f, 300.0f, new Date(), new Date(), false, new HashSet<>(), null);
        given(invoiceRepository.findById(2L)).willReturn(Optional.of(invoice1));
        Optional<Invoice> optionalInvoice = invoiceRepository.findById(2L);
        invoiceService.cancelInvoice(2L);
        assertThat(optionalInvoice.get(),is(notNullValue()));
        Invoice invoiceResult = optionalInvoice.get();
        assertThat(invoiceResult.getArchived(),is(true));
    }

    @Test
    public void testRetreiveInvoice() {
        Invoice invoice1 = new Invoice(2L, 20.0f, 300.0f, new Date(), new Date(), false, new HashSet<>(), null);
        given(invoiceRepository.findById(2L)).willReturn(Optional.of(invoice1));
        Invoice invoice = invoiceService.retrieveInvoice(2L);
        assertThat(invoice,is(notNullValue()));
        assertThat(invoice,is(invoice1));
    }


}
