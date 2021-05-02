package br.gov.sp.fatec.uplanitspringbootapp.entity;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import java.util.Set;
import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Table(name = "up_subscription")
public class Subscription {

    @Id
    @Column(name = "up_subscription_name")
    private String subscription;
    
    @ManyToMany(fetch = FetchType.LAZY, mappedBy = "subscriptions")
    @JsonIgnore
    private Set<User> users;

    public String getSubscriptions(){
        return this.subscription;
    }

    public void setSubscriptions(String subscription){
        this.subscription = subscription;
    }    

    public Set<User> getUser(){
        return this.users;
    }

    public void setUser(Set<User> users){
        this.users = users;
    }  
}
