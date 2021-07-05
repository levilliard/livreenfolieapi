/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entities;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author PL
 */
@Entity
@Table(name = "PAIEMENT")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Paiement.findAll", query = "SELECT p FROM Paiement p")
    , @NamedQuery(name = "Paiement.findById", query = "SELECT p FROM Paiement p WHERE p.id = :id")
    , @NamedQuery(name = "Paiement.findByMontant", query = "SELECT p FROM Paiement p WHERE p.montant = :montant")
    , @NamedQuery(name = "Paiement.findByDatepaiment", query = "SELECT p FROM Paiement p WHERE p.datepaiment = :datepaiment")})
public class Paiement implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Column(name = "ID")
    private Integer id;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Column(name = "MONTANT")
    private Double montant;
    @Column(name = "DATEPAIMENT")
    @Temporal(TemporalType.DATE)
    private Date datepaiment;
    
    private int quantity;
    
    @ManyToOne
    private Utilisateur owner;
    
    private String item;
    
    public Paiement() {
    }

    public Paiement(Integer id) {
        this.id = id;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Double getMontant() {
        return montant;
    }

    public void setMontant(Double montant) {
        this.montant = montant;
    }

    public Date getDatepaiment() {
        return datepaiment;
    }

    public void setDatepaiment(Date datepaiment) {
        this.datepaiment = datepaiment;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (id != null ? id.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Paiement)) {
            return false;
        }
        Paiement other = (Paiement) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entities.Paiement[ id=" + id + " ]";
    }
    
}
