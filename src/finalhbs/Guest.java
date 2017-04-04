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
import java.math.BigInteger;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Transient;

/**
 *
 * @author Marwah
 */
@Entity
@Table(name = "GUEST", catalog = "", schema = "S27143392")
@NamedQueries({
    @NamedQuery(name = "Guest.findAll", query = "SELECT g FROM Guest g")
    , @NamedQuery(name = "Guest.findByGuestNumber", query = "SELECT g FROM Guest g WHERE g.guestNumber = :guestNumber")
    , @NamedQuery(name = "Guest.findByTitle", query = "SELECT g FROM Guest g WHERE g.title = :title")
    , @NamedQuery(name = "Guest.findByFirstName", query = "SELECT g FROM Guest g WHERE g.firstName = :firstName")
    , @NamedQuery(name = "Guest.findByLastName", query = "SELECT g FROM Guest g WHERE g.lastName = :lastName")
    , @NamedQuery(name = "Guest.findByDob", query = "SELECT g FROM Guest g WHERE g.dob = :dob")
    , @NamedQuery(name = "Guest.findByCountry", query = "SELECT g FROM Guest g WHERE g.country = :country")
    , @NamedQuery(name = "Guest.findByCity", query = "SELECT g FROM Guest g WHERE g.city = :city")
    , @NamedQuery(name = "Guest.findByStreet", query = "SELECT g FROM Guest g WHERE g.street = :street")
    , @NamedQuery(name = "Guest.findByPostalCode", query = "SELECT g FROM Guest g WHERE g.postalCode = :postalCode")
    , @NamedQuery(name = "Guest.findByPhoneNumber", query = "SELECT g FROM Guest g WHERE g.phoneNumber = :phoneNumber")
    , @NamedQuery(name = "Guest.findByEmailAddress", query = "SELECT g FROM Guest g WHERE g.emailAddress = :emailAddress")})
public class Guest implements Serializable {

    @Transient
    private PropertyChangeSupport changeSupport = new PropertyChangeSupport(this);

    private static final long serialVersionUID = 1L;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Id
    @Basic(optional = false)
    @Column(name = "GUEST_NUMBER")
    private BigDecimal guestNumber;
    @Column(name = "TITLE")
    private String title;
    @Column(name = "FIRST_NAME")
    private String firstName;
    @Column(name = "LAST_NAME")
    private String lastName;
    @Column(name = "DOB")
    private String dob;
    @Column(name = "COUNTRY")
    private String country;
    @Column(name = "CITY")
    private String city;
    @Column(name = "STREET")
    private String street;
    @Column(name = "POSTAL_CODE")
    private String postalCode;
    @Column(name = "PHONE_NUMBER")
    private BigInteger phoneNumber;
    @Column(name = "EMAIL_ADDRESS")
    private String emailAddress;

    public Guest() {
    }

    public Guest(BigDecimal guestNumber) {
        this.guestNumber = guestNumber;
    }

    public BigDecimal getGuestNumber() {
        return guestNumber;
    }

    public void setGuestNumber(BigDecimal guestNumber) {
        BigDecimal oldGuestNumber = this.guestNumber;
        this.guestNumber = guestNumber;
        changeSupport.firePropertyChange("guestNumber", oldGuestNumber, guestNumber);
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        String oldTitle = this.title;
        this.title = title;
        changeSupport.firePropertyChange("title", oldTitle, title);
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        String oldFirstName = this.firstName;
        this.firstName = firstName;
        changeSupport.firePropertyChange("firstName", oldFirstName, firstName);
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        String oldLastName = this.lastName;
        this.lastName = lastName;
        changeSupport.firePropertyChange("lastName", oldLastName, lastName);
    }

    public String getDob() {
        return dob;
    }

    public void setDob(String dob) {
        String oldDob = this.dob;
        this.dob = dob;
        changeSupport.firePropertyChange("dob", oldDob, dob);
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        String oldCountry = this.country;
        this.country = country;
        changeSupport.firePropertyChange("country", oldCountry, country);
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        String oldCity = this.city;
        this.city = city;
        changeSupport.firePropertyChange("city", oldCity, city);
    }

    public String getStreet() {
        return street;
    }

    public void setStreet(String street) {
        String oldStreet = this.street;
        this.street = street;
        changeSupport.firePropertyChange("street", oldStreet, street);
    }

    public String getPostalCode() {
        return postalCode;
    }

    public void setPostalCode(String postalCode) {
        String oldPostalCode = this.postalCode;
        this.postalCode = postalCode;
        changeSupport.firePropertyChange("postalCode", oldPostalCode, postalCode);
    }

    public BigInteger getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(BigInteger phoneNumber) {
        BigInteger oldPhoneNumber = this.phoneNumber;
        this.phoneNumber = phoneNumber;
        changeSupport.firePropertyChange("phoneNumber", oldPhoneNumber, phoneNumber);
    }

    public String getEmailAddress() {
        return emailAddress;
    }

    public void setEmailAddress(String emailAddress) {
        String oldEmailAddress = this.emailAddress;
        this.emailAddress = emailAddress;
        changeSupport.firePropertyChange("emailAddress", oldEmailAddress, emailAddress);
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (guestNumber != null ? guestNumber.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Guest)) {
            return false;
        }
        Guest other = (Guest) object;
        if ((this.guestNumber == null && other.guestNumber != null) || (this.guestNumber != null && !this.guestNumber.equals(other.guestNumber))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "finalhbs.Guest[ guestNumber=" + guestNumber + " ]";
    }

    public void addPropertyChangeListener(PropertyChangeListener listener) {
        changeSupport.addPropertyChangeListener(listener);
    }

    public void removePropertyChangeListener(PropertyChangeListener listener) {
        changeSupport.removePropertyChangeListener(listener);
    }
    
}
