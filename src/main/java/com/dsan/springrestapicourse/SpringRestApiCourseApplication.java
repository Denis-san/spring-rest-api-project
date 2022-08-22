package com.dsan.springrestapicourse;

import java.text.SimpleDateFormat;
import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.dsan.springrestapicourse.domain.Address;
import com.dsan.springrestapicourse.domain.Category;
import com.dsan.springrestapicourse.domain.City;
import com.dsan.springrestapicourse.domain.Client;
import com.dsan.springrestapicourse.domain.Order;
import com.dsan.springrestapicourse.domain.OrderItem;
import com.dsan.springrestapicourse.domain.Payment;
import com.dsan.springrestapicourse.domain.PaymentWithCard;
import com.dsan.springrestapicourse.domain.PaymentWithTicket;
import com.dsan.springrestapicourse.domain.Product;
import com.dsan.springrestapicourse.domain.State;
import com.dsan.springrestapicourse.domain.enums.ClientType;
import com.dsan.springrestapicourse.domain.enums.PaymentStatus;
import com.dsan.springrestapicourse.repositories.AddressRepository;
import com.dsan.springrestapicourse.repositories.CategoryRepository;
import com.dsan.springrestapicourse.repositories.CityRepository;
import com.dsan.springrestapicourse.repositories.ClientRepository;
import com.dsan.springrestapicourse.repositories.OrderItemRepository;
import com.dsan.springrestapicourse.repositories.OrderRepository;
import com.dsan.springrestapicourse.repositories.PaymentRepository;
import com.dsan.springrestapicourse.repositories.ProductRepository;
import com.dsan.springrestapicourse.repositories.StateRepository;

@SpringBootApplication
public class SpringRestApiCourseApplication implements CommandLineRunner {

	@Autowired
	private CategoryRepository categoryRepository;

	@Autowired
	private ProductRepository productRepository;

	@Autowired
	private StateRepository stateRepository;

	@Autowired
	private CityRepository cityRepository;

	@Autowired
	private ClientRepository clientRepository;

	@Autowired
	private AddressRepository addressRepository;

	@Autowired
	private OrderRepository orderRepository;

	@Autowired
	private PaymentRepository paymentRepository;

	@Autowired
	private OrderItemRepository itemRepository;

	public static void main(String[] args) {
		SpringApplication.run(SpringRestApiCourseApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {

		// Categories and products instantiations

		Category cat = new Category(null, "Computing");
		Category cat2 = new Category(null, "Office");

		Product p1 = new Product(null, "Computer", 2000.00);
		Product p2 = new Product(null, "Printer", 800.00);
		Product p3 = new Product(null, "Mouse", 80.00);

		cat.getProducts().addAll(Arrays.asList(p1, p2, p3));
		cat2.getProducts().addAll(Arrays.asList(p2));

		p1.getCategories().addAll(Arrays.asList(cat));
		p2.getCategories().addAll(Arrays.asList(cat, cat2));
		p3.getCategories().addAll(Arrays.asList(cat));

		categoryRepository.saveAll(Arrays.asList(cat, cat2));

		productRepository.saveAll(Arrays.asList(p1, p2, p3));

		// Cities and states instantiations

		State est1 = new State(null, "Minas Gerais");
		State est2 = new State(null, "São Paulo");

		City c1 = new City(null, "Uberlândia", est1);
		City c2 = new City(null, "São Paulo", est2);
		City c3 = new City(null, "Campinas", est2);

		est1.getCities().addAll(Arrays.asList(c1));
		est2.getCities().addAll(Arrays.asList(c2, c3));

		stateRepository.saveAll(Arrays.asList(est1, est2));

		cityRepository.saveAll(Arrays.asList(c1, c2, c3));

		// clients, phones and addresses

		Client cli1 = new Client(null, "Maria Silva", "maria@gmail.com", "36378912377", ClientType.PHYSICAL_PERSON);

		cli1.getPhones().addAll(Arrays.asList("27363323", "93838393"));

		Address adr1 = new Address(null, "Rua Flores", "300", "Apto 303", "Jardim", "38220834", cli1, c1);
		Address adr2 = new Address(null, "Avenida Matos", "105", "Sala 80", "Centro", "55646354", cli1, c2);

		cli1.getAddresses().addAll(Arrays.asList(adr1, adr2));

		clientRepository.saveAll(Arrays.asList(cli1));
		addressRepository.saveAll(Arrays.asList(adr1, adr2));

		// payments, orders, address...

		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy HH:mm");

		Order ped1 = new Order(null, sdf.parse("30/09/2017 10:32"), cli1, adr1);
		Order ped2 = new Order(null, sdf.parse("10/10/2017 19:35"), cli1, adr2);

		Payment pagto1 = new PaymentWithCard(null, PaymentStatus.PAID_OF, ped1, 6);
		ped1.setPayment(pagto1);

		Payment pagto2 = new PaymentWithTicket(null, PaymentStatus.PENDING, ped2, sdf.parse("20/10/2017 00:00"), null);
		ped2.setPayment(pagto2);

		cli1.getOrders().addAll(Arrays.asList(ped1, ped2));

		orderRepository.saveAll(Arrays.asList(ped1, ped2));
		paymentRepository.saveAll(Arrays.asList(pagto1, pagto2));

		// orderItems, items, products ...

		OrderItem ordIt = new OrderItem(ped1, p1, 0.00, 1, 2000.00);
		OrderItem ordIt2 = new OrderItem(ped1, p3, 0.00, 2, 80.00);
		OrderItem ordIt3 = new OrderItem(ped2, p2, 100.00, 1, 800.00);

		ped1.getItems().addAll(Arrays.asList(ordIt, ordIt2));
		ped2.getItems().addAll(Arrays.asList(ordIt3));

		p1.getItems().addAll(Arrays.asList(ordIt));
		p2.getItems().addAll(Arrays.asList(ordIt3));
		p3.getItems().addAll(Arrays.asList(ordIt2));

		itemRepository.saveAll(Arrays.asList(ordIt, ordIt2, ordIt3));

	}

}
