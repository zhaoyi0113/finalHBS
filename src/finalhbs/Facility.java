/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package finalhbs;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Collection;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Transient;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author joey
 */
@Entity
@Table(name = "FACILITY")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Facility.findAll", query = "SELECT f FROM Facility f")
    , @NamedQuery(name = "Facility.findByFacilityNumber", query = "SELECT f FROM Facility f WHERE f.facilityNumber = :facilityNumber")
    , @NamedQuery(name = "Facility.findByFacilityDescription", query = "SELECT f FROM Facility f WHERE f.facilityDescription = :facilityDescription")})
public class Facility implements Serializable {

    @Transient
    private PropertyChangeSupport changeSupport = new PropertyChangeSupport(this);

    private static final long serialVersionUID = 1L;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Id
    @Basic(optional = false)
    @Column(name = "FACILITY_NUMBER")
    private BigDecimal facilityNumber;
    @Column(name = "FACILITY_DESCRIPTION")
    private String facilityDescription;
    @ManyToMany(mappedBy = "facilityCollection")
    private Collection<Room> roomCollection;

    public Facility() {
    }

    public Facility(BigDecimal facilityNumber) {
        this.facilityNumber = facilityNumber;
    }

    public BigDecimal getFacilityNumber() {
        return facilityNumber;
    }

    public void setFacilityNumber(BigDecimal facilityNumber) {
        BigDecimal oldFacilityNumber = this.facilityNumber;
        this.facilityNumber = facilityNumber;
        changeSupport.firePropertyChange("facilityNumber", oldFacilityNumber, facilityNumber);
    }

    public String getFacilityDescription() {
        return facilityDescription;
    }

    public void setFacilityDescription(String facilityDescription) {
        String oldFacilityDescription = this.facilityDescription;
        this.facilityDescription = facilityDescription;
        changeSupport.firePropertyChange("facilityDescription", oldFacilityDescription, facilityDescription);
    }

    @XmlTransient
    public Collection<Room> getRoomCollection() {
        return roomCollection;
    }

    public void setRoomCollection(Collection<Room> roomCollection) {
        this.roomCollection = roomCollection;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (facilityNumber != null ? facilityNumber.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Facility)) {
            return false;
        }
        Facility other = (Facility) object;
        if ((this.facilityNumber == null && other.facilityNumber != null) || (this.facilityNumber != null && !this.facilityNumber.equals(other.facilityNumber))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "finalhbs.Facility[ facilityNumber=" + facilityNumber + " ]";
    }

    public void addPropertyChangeListener(PropertyChangeListener listener) {
        changeSupport.addPropertyChangeListener(listener);
    }

    public void removePropertyChangeListener(PropertyChangeListener listener) {
        changeSupport.removePropertyChangeListener(listener);
    }
    
}
