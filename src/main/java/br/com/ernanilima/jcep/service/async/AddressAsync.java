package br.com.ernanilima.jcep.service.async;

import br.com.ernanilima.jcep.domain.Address;
import br.com.ernanilima.jcep.repository.AddressRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;

@Component
public class AddressAsync {

    @Autowired
    private AddressRepository addressRepository;

    @Async
    public void asyncSaveAddress(Address address) {
        try {
            System.out.println("ini");
            Thread.sleep(10000);
            System.out.println("fin");
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
