package br.gov.sp.fatec.uplanitspringbootapp.entity;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import java.util.Set;
import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Table(name = "up_users")
public class User {

    //#region variáveis
    @Id
    @Column(name = "usr_username")
    private String username;

    @Column(name = "usr_name")
    private String name;
    
    @Column(name = "usr_surname")
    private String surname;

    @Column(name = "usr_email")
    private String email;

    @Column(name = "usr_birthday")
    private String birthday;

    @Column(name = "usr_password")
    private String password;

    @Column(name = "usr_ocupation")
    private String ocupation;

     
    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(name = "up_user_subscription",
        joinColumns = { @JoinColumn(name = "usr_username") },
        inverseJoinColumns = { @JoinColumn(name = "up_subscription_name") }
    )
    @JsonIgnore 
    
    private Set<Subscription> subscriptions;
    //#endregion
    
    //#region gets and setters
    public String getUsername(){
        return this.username;
    }

    public void setUsername(String username){
        this.username = username;
    }

    public String getName(){
        return this.name;
    }

    public void setName(String name){
        this.name = name;
    }

    public String getSurname(){
        return this.surname;
    }

    public void setSurname(String surname){
        this.surname = surname;
    }

    public String getEmail(){
        return this.email;
    }

    public void setEmail(String email){
        this.email = email;
    }

    public String getBirthday(){
        return this.birthday;
    }

    public void setBirthday(String birthday){
        this.birthday = birthday;
    }

    public String getPassword(){
        return this.password;
    }

    public void setPassword(String password){
        this.password = password;
    }

    public String getOcupation(){
        return this.ocupation;
    }

    public void setOcupation(String ocupation){
        this.ocupation = ocupation;
    }
    
    public Set<Subscription> getSubscriptions(){
        return this.subscriptions;
    }

    public void setSubscriptions(Set<Subscription> subscriptions){
        this.subscriptions = subscriptions;
    }
    //#endregion    
}
