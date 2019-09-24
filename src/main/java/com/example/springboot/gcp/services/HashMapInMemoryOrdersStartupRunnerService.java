package com.example.springboot.gcp.services;

import com.example.springboot.gcp.model.Order;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Service;
import java.util.Date;
import java.util.UUID;

@Service
@Slf4j
public class HashMapInMemoryOrdersStartupRunnerService implements ApplicationRunner {


// This will run on application startup
// for setting up some data on startup


    private final HashMapInMemoryOrdersDbService hashMapInMemoryOrdersDbService;

    HashMapInMemoryOrdersStartupRunnerService(HashMapInMemoryOrdersDbService hashMapInMemoryOrdersDbService) {
        this.hashMapInMemoryOrdersDbService = hashMapInMemoryOrdersDbService;
    }

    @Override
    public void run(ApplicationArguments args) throws Exception {
        hashMapInMemoryOrdersDbService.deleteAllOrders();
        Order order1 = new Order();
        order1.setCustomerId(UUID.randomUUID().toString());
        order1.setFirstName("Mukesh");
        order1.setLastName("Kumar");

           /* SimpleDateFormat sdfAmerica = new SimpleDateFormat("dd-M-yyyy hh:mm:ss a");
            sdfAmerica.setTimeZone(TimeZone.getTimeZone("America/Los_Angeles"));
             String dateTime = sdfAmerica.format(new Date());
*/
        order1.setOrderDate(new Date());

        Order order2 = new Order();
        order2.setCustomerId(UUID.randomUUID().toString());
        order2.setFirstName("Zippy");
        order2.setLastName("Kumar");
        order2.setOrderDate(new Date());

        String newOrderId = hashMapInMemoryOrdersDbService.createOrder(order1);
        log.info("Order created, order Id:  {}", newOrderId);
        newOrderId = hashMapInMemoryOrdersDbService.createOrder(order2);
        log.info("Order created, order Id:  {}", newOrderId);

    }
}

