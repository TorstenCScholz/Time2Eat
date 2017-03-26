package de.doaschdn;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Comparator;
import java.util.Optional;

@SpringBootApplication
@Controller
public class Time2EatApplication {
    private static final Logger log = LoggerFactory.getLogger(Time2EatApplication.class);

    @Autowired
    CustomerRepository customerRepository;

//	@RequestMapping(value = "/customers/{firstName}/{lastName}", method = RequestMethod.POST)
//	public void newCustomer(@RequestParam String firstName, @RequestParam String lastName) {
//		customerRepository.save(new Customer(firstName, lastName));
//	}

    // TODO: I want different routes for there (e.g. /customers and /customers/1)
    @RequestMapping(value = "/customers", method = RequestMethod.GET)
    public String showCustomer(@RequestParam(value = "id", required = false) Long id, Model model) {
        if (id == null) {
            return showCustomers(model);
        }

        Optional<Customer> customerOptional = customerRepository.findById(id);

        if (!customerOptional.isPresent()) {
            return "customer/404";
        }

        model.addAttribute("customer", customerOptional.get());
        return "customer/customer";
    }

    public String showCustomers(Model model) {
        model.addAttribute("customers", customerRepository.findAll());
        model.addAttribute("customerComparator", (Comparator<Customer>) (a, b) -> a.getLastName().compareTo(b.getLastName()));

        return "customer/customers";
    }

    // http://stackoverflow.com/a/25362790/5376375
//	@RequestMapping(value = "/error")
//    public String showError(@RequestParam Long customerId, Model model) {
//        return "error";
//    }

    public static void main(String[] args) {
        SpringApplication.run(Time2EatApplication.class, args);
    }

//	@Bean
//	public CommandLineRunner demo(CustomerRepository customerRepository) {
//		return (args) -> {
//			// save a couple of customers
//			customerRepository.save(new Customer("Jack", "Bauer"));
//			customerRepository.save(new Customer("Chloe", "O'Brian"));
//			customerRepository.save(new Customer("Kim", "Bauer"));
//			customerRepository.save(new Customer("David", "Palmer"));
//			customerRepository.save(new Customer("Michelle", "Dessler"));
//
//			// fetch all customers
//			log.info("Customers found with findAll():");
//			log.info("-------------------------------");
//			for (Customer customer : customerRepository.findAll()) {
//				log.info(customer.toString());
//			}
//			log.info("");
//
//			// fetch an individual customer by ID
//			Customer customer = customerRepository.findOne(1L);
//			log.info("Customer found with findOne(1L):");
//			log.info("--------------------------------");
//			log.info(customer.toString());
//			log.info("");
//
//			// fetch customers by last name
//			log.info("Customer found with findByLastName('Bauer'):");
//			log.info("--------------------------------------------");
//			for (Customer bauer : customerRepository.findByLastName("Bauer")) {
//				log.info(bauer.toString());
//			}
//			log.info("");
//
//			asStream(customerRepository.findAll())
//					.map(customer1 -> customer1.getFirstName() + " " + customer1.getLastName())
//					.forEach(log::info);
//		};
//	}
}
