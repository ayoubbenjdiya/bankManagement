
package ma.gcb.entities;

import com.fasterxml.jackson.annotation.*;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

@Entity
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name = "typeOperation", discriminatorType = DiscriminatorType.STRING, length = 2)
@JsonIdentityInfo(generator = ObjectIdGenerators.IntSequenceGenerator.class)
@JsonTypeInfo(use = JsonTypeInfo.Id.NAME, include = JsonTypeInfo.As.PROPERTY, property = "typeOperation")
@JsonSubTypes({ @JsonSubTypes.Type(name = "OR", value = Retrait.class), @JsonSubTypes.Type(name = "OV", value = Versement.class) })
public abstract class Operation implements Serializable
{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long numero;
    private Date date;
    private double nomtant;
    @JsonIgnore
    @ManyToOne(cascade = { CascadeType.REMOVE })
    @JoinColumn(name = "codeCompte")
    private Compte compte;
    
    public Operation() {
    }
    
    public Operation(final Date date, final double nomtant, final Compte compte) {
        this.date = date;
        this.nomtant = nomtant;
        this.compte = compte;
    }
    
    public Long getNumero() {
        return this.numero;
    }
    
    public void setNumero(final Long numero) {
        this.numero = numero;
    }
    
    public Date getDate() {
        return this.date;
    }
    
    public void setDate(final Date date) {
        this.date = date;
    }
    
    public double getNomtant() {
        return this.nomtant;
    }
    
    public void setNomtant(final double nomtant) {
        this.nomtant = nomtant;
    }
    
    public Compte getCompte() {
        return this.compte;
    }
    
    public void setCompte(final Compte compte) {
        this.compte = compte;
    }
    
    @Override
    public String toString() {
        return "Operation{numero=" + this.numero + ", date=" + this.date + ", nomtant=" + this.nomtant + ", compte=" + this.compte + '}';
    }
}
