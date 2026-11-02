package net.ettaqy.ebankservice.feign;

import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import net.ettaqy.ebankservice.model.Customer;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;


//permet à ebank-service de communiquer avec customer-service(consomme api de customer-service)
//il faut activer @EnableFeignClients dans ebank
@FeignClient(name= "customer-service")
public interface CustomerRestClient {
    //si le service n'est pas disponible
    @CircuitBreaker(name= "customerService",fallbackMethod = "getDefaultCustomer")
    //envoyer une requette http de type get vers customer-service
    @GetMapping("/customers/{id}")
    Customer getCustomerById(@PathVariable Long id);

    //id le 1er
    default Customer getDefaultCustomer(Long id, Exception e){
    return new Customer(id, "NoName", "noEmail");
    }
}