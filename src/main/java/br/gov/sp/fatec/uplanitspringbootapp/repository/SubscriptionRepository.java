package br.gov.sp.fatec.uplanitspringbootapp.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import br.gov.sp.fatec.uplanitspringbootapp.entity.Subscription;

public interface SubscriptionRepository extends JpaRepository<Subscription, String>{

    public Subscription findBySubscription(String subscription);   

}
