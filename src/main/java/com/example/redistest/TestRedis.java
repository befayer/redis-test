package com.example.redistest;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.condition.ConditionalOnExpression;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.PropertySource;
import org.springframework.data.redis.repository.configuration.EnableRedisRepositories;
import com.example.redistest.entities.*;
import com.example.redistest.repositories.*;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@SpringBootApplication
@EnableRedisRepositories(basePackages = "com.example.redistest.repositories")
@PropertySource(value = "classpath:application-redis.properties")
public class TestRedis {

    public static void main(String[] args) {
        SpringApplication.run(TestRedis.class, args);
    }

    @Bean
    @ConditionalOnExpression("${redis.runner.enabled:true}")
    CommandLineRunner commandLineRunner(AccountRepository accountRepository,
                                        BankRepository bankRepository,
                                        ClientRepository clientRepository,
                                        CurrencyRepository currencyRepository) {
        return args -> {
            bankRepository.deleteAll();
            clientRepository.deleteAll();
            currencyRepository.deleteAll();
            accountRepository.deleteAll();

            Client clientKalinin = new Client();
            clientKalinin.setId(UUID.randomUUID());
            clientKalinin.setFirstName("Alexandr");
            clientKalinin.setLastName("Kalinin");

            Client clientTyshkun = new Client();
            clientTyshkun.setId(UUID.randomUUID());
            clientTyshkun.setFirstName("Andrew");
            clientTyshkun.setLastName("Tyshkun");

            Client clientVlasov = new Client();
            clientVlasov.setId(UUID.randomUUID());
            clientVlasov.setFirstName("George");
            clientVlasov.setLastName("Vlasov");

            Client clientKiseleva = new Client();
            clientKiseleva.setId(UUID.randomUUID());
            clientKiseleva.setFirstName("Snezhana");
            clientKiseleva.setLastName("Kiseleva");

            Currency currencyRUB = new Currency();
            currencyRUB.setId(UUID.randomUUID());
            currencyRUB.setNameCurrency("RUB");

            Currency currencyEUR = new Currency();
            currencyEUR.setId(UUID.randomUUID());
            currencyEUR.setNameCurrency("EUR");

            Bank bankSamara = new Bank();
            bankSamara.setId("043601607");
            bankSamara.setBankName("ПОВОЛЖСКИЙ БАНК ПАО СБЕРБАНК");
            bankSamara.setBankCity("Самара");

            Bank bankMoscow = new Bank();
            bankMoscow.setId("044525225");
            bankMoscow.setBankName("ПАО СБЕРБАНК");
            bankMoscow.setBankCity("Москва");

            Account account1 = new Account();
            account1.setId("25632145874563225698");
            account1.setBalance(2000);
            account1.setBank(bankSamara);
            account1.setCurrency(currencyRUB);
            account1.setClient(clientKalinin);

            Account account2 = new Account();
            account2.setId("23659874123698563257");
            account2.setBalance(1000000);
            account2.setBank(bankMoscow);
            account2.setCurrency(currencyEUR);
            account2.setClient(clientTyshkun);

            System.out.printf("Client: %s has been saved %n", clientRepository.save(clientKalinin));
            System.out.printf("Client: %s has been saved %n", clientRepository.save(clientTyshkun));
            System.out.printf("Client: %s has been saved %n", clientRepository.save(clientVlasov));
            System.out.printf("Client: %s has been saved %n", clientRepository.save(clientKiseleva));

            System.out.printf("Currency: %s has been saved %n", currencyRepository.save(currencyRUB));
            System.out.printf("Currency: %s has been saved %n", currencyRepository.save(currencyEUR));

            System.out.printf("Bank: %s has been saved %n", bankRepository.save(bankSamara));
            System.out.printf("Bank: %s has been saved %n", bankRepository.save(bankMoscow));

            System.out.printf("Account: %s has been saved %n", accountRepository.save(account1));
            System.out.printf("Account: %s has been saved %n", accountRepository.save(account2));
            account2.setBalance(999999);
            System.out.printf("Account: %s has been saved %n", accountRepository.save(account2));



            System.out.println("\nClients:");
            clientRepository.findAll().iterator().forEachRemaining(
                    System.out::println
            );

            System.out.println("\nAccounts:");
            accountRepository.findAll().iterator().forEachRemaining(
                    System.out::println
            );

            System.out.println("\nBanks:");
            bankRepository.findAll().iterator().forEachRemaining(
                    System.out::println
            );

            System.out.println("\nCurrencies:");
            currencyRepository.findAll().iterator().forEachRemaining(
                    System.out::println
            );

            Account retrievedAccount = accountRepository.findById("23659874123698563257").get();
            System.out.println("\nAccount with id: " + retrievedAccount.getId());
            System.out.println("Balance: " + retrievedAccount.getBalance());
            System.out.println("Bank name: " + retrievedAccount.getBank().getBankName());
            System.out.println("Bank city: " + retrievedAccount.getBank().getBankCity());
            System.out.println("Currency name: " + retrievedAccount.getCurrency().getNameCurrency());
            System.out.println("Client firstname: " + retrievedAccount.getClient().getFirstName());
            System.out.println("Client lastname: " + retrievedAccount.getClient().getLastName());

            List<Client> clientList = new ArrayList<>();
            System.out.println("\nThis is list of client, but in other form...");
            clientRepository.findAll().forEach(clientList::add);
            for (Client clients: clientList) {
                System.out.println("Firstname: " + clients.getFirstName());
                System.out.println("Lastname: " + clients.getLastName());
                System.out.println(" ");
            }
        };
    }
}
