
package ma.gcb.entities;

import com.fasterxml.jackson.annotation.*;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Collection;
import java.util.Date;

@Entity
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name = "typeCompte", discriminatorType = DiscriminatorType.STRING, length = 2)
@JsonIdentityInfo(generator = ObjectIdGenerators.IntSequenceGenerator.class)
@JsonTypeInfo(use = JsonTypeInfo.Id.NAME, include = JsonTypeInfo.As.PROPERTY, property = "typeCompte")
@JsonSubTypes({ @JsonSubTypes.Type(name = "CC", value = CompteCourant.class), @JsonSubTypes.Type(name = "CE", value = CompteEpargne.class) })
public abstract class Compte implements Serializable
{
    @Id
    private String codeCompte;
    private Date dateCreation;
    private double solde;
    @ManyToOne
    @JsonIgnore
    @JoinColumn(name = "Code_client")
    private Client client;
    @OneToMany(mappedBy = "compte", cascade = { CascadeType.REMOVE })
    @JsonIgnore
    private Collection<Operation> operations;
    
    public Compte() {
    }
    
    public Compte(final String codeCompte, final Date dateCreation, final double solde, final Client client) {
        this.codeCompte = codeCompte;
        this.dateCreation = dateCreation;
        this.solde = solde;
        this.client = client;
    }
    
    public Compte(final String codeCompte, final Client client) {
        this.codeCompte = codeCompte;
        this.dateCreation = new Date();
        this.solde = 0.0;
        this.client = client;
    }
    
    public String getCodeCompte() {
        return this.codeCompte;
    }
    
    public void setCodeCompte(final String codeCompte) {
        this.codeCompte = codeCompte;
    }
    
    public Date getDateCreation() {
        return this.dateCreation;
    }
    
    public void setDateCreation(final Date dateCreation) {
        this.dateCreation = dateCreation;
    }
    
    public double getSolde() {
        return this.solde;
    }
    
    public void setSolde(final double solde) {
        this.solde = solde;
    }
    
    @JsonIgnore
    public Client getClient() {
        return this.client;
    }
    
    @JsonSetter
    public void setClient(final Client client) {
        this.client = client;
    }
    
    @JsonIgnore
    public Collection<Operation> getOperations() {
        return (Collection<Operation>)this.operations;
    }
    
    @JsonSetter
    public void setOperations(final Collection<Operation> operations) {
        this.operations = operations;
    }
    
    @Override
    public String toString() {
        return "Compte{codeCompte='" + this.codeCompte + '\'' + ", dateCreation=" + this.dateCreation + ", solde=" + this.solde + ", client=" + this.client + ", operations=" + this.operations + '}';
    }
}
