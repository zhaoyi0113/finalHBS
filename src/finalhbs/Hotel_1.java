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
@Table(name = "HOTEL", catalog = "", schema = "S27143392")
@NamedQueries({
    @NamedQuery(name = "Hotel_1.findAll", query = "SELECT h FROM Hotel_1 h")
    , @NamedQuery(name = "Hotel_1.findByHotelId", query = "SELECT h FROM Hotel_1 h WHERE h.hotelId = :hotelId")
    , @NamedQuery(name = "Hotel_1.findByHotelName", query = "SELECT h FROM Hotel_1 h WHERE h.hotelName = :hotelName")
    , @NamedQuery(name = "Hotel_1.findByHotelTier", query = "SELECT h FROM Hotel_1 h WHERE h.hotelTier = :hotelTier")
    , @NamedQuery(name = "Hotel_1.findByConstructionYear", query = "SELECT h FROM Hotel_1 h WHERE h.constructionYear = :constructionYear")
    , @NamedQuery(name = "Hotel_1.findByCountry", query = "SELECT h FROM Hotel_1 h WHERE h.country = :country")
    , @NamedQuery(name = "Hotel_1.findByCity", query = "SELECT h FROM Hotel_1 h WHERE h.city = :city")
    , @NamedQuery(name = "Hotel_1.findByAddress", query = "SELECT h FROM Hotel_1 h WHERE h.address = :address")
    , @NamedQuery(name = "Hotel_1.findByContactNumber", query = "SELECT h FROM Hotel_1 h WHERE h.contactNumber = :contactNumber")
    , @NamedQuery(name = "Hotel_1.findByEmailAddress", query = "SELECT h FROM Hotel_1 h WHERE h.emailAddress = :emailAddress")
    , @NamedQuery(name = "Hotel_1.findByRoomCapacity", query = "SELECT h FROM Hotel_1 h WHERE h.roomCapacity = :roomCapacity")})
public class Hotel_1 implements Serializable {

    @Transient
    private PropertyChangeSupport changeSupport = new PropertyChangeSupport(this);

    private static final long serialVersionUID = 1L;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Id
    @Basic(optional = false)
    @Column(name = "HOTEL_ID")
    private BigDecimal hotelId;
    @Basic(optional = false)
    @Column(name = "HOTEL_NAME")
    private String hotelName;
    @Column(name = "HOTEL_TIER")
    private String hotelTier;
    @Column(name = "CONSTRUCTION_YEAR")
    private String constructionYear;
    @Basic(optional = false)
    @Column(name = "COUNTRY")
    private String country;
    @Basic(optional = false)
    @Column(name = "CITY")
    private String city;
    @Column(name = "ADDRESS")
    private String address;
    @Basic(optional = false)
    @Column(name = "CONTACT_NUMBER")
    private String contactNumber;
    @Column(name = "EMAIL_ADDRESS")
    private String emailAddress;
    @Basic(optional = false)
    @Column(name = "ROOM_CAPACITY")
    private BigInteger roomCapacity;

    public Hotel_1() {
    }

    public Hotel_1(BigDecimal hotelId) {
        this.hotelId = hotelId;
    }

    public Hotel_1(BigDecimal hotelId, String hotelName, String country, String city, String contactNumber, BigInteger roomCapacity) {
        this.hotelId = hotelId;
        this.hotelName = hotelName;
        this.country = country;
        this.city = city;
        this.contactNumber = contactNumber;
        this.roomCapacity = roomCapacity;
    }

    public BigDecimal getHotelId() {
        return hotelId;
    }

    public void setHotelId(BigDecimal hotelId) {
        BigDecimal oldHotelId = this.hotelId;
        this.hotelId = hotelId;
        changeSupport.firePropertyChange("hotelId", oldHotelId, hotelId);
    }

    public String getHotelName() {
        return hotelName;
    }

    public void setHotelName(String hotelName) {
        String oldHotelName = this.hotelName;
        this.hotelName = hotelName;
        changeSupport.firePropertyChange("hotelName", oldHotelName, hotelName);
    }

    public String getHotelTier() {
        return hotelTier;
    }

    public void setHotelTier(String hotelTier) {
        String oldHotelTier = this.hotelTier;
        this.hotelTier = hotelTier;
        changeSupport.firePropertyChange("hotelTier", oldHotelTier, hotelTier);
    }

    public String getConstructionYear() {
        return constructionYear;
    }

    public void setConstructionYear(String constructionYear) {
        String oldConstructionYear = this.constructionYear;
        this.constructionYear = constructionYear;
        changeSupport.firePropertyChange("constructionYear", oldConstructionYear, constructionYear);
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

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        String oldAddress = this.address;
        this.address = address;
        changeSupport.firePropertyChange("address", oldAddress, address);
    }

    public String getContactNumber() {
        return contactNumber;
    }

    public void setContactNumber(String contactNumber) {
        String oldContactNumber = this.contactNumber;
        this.contactNumber = contactNumber;
        changeSupport.firePropertyChange("contactNumber", oldContactNumber, contactNumber);
    }

    public String getEmailAddress() {
        return emailAddress;
    }

    public void setEmailAddress(String emailAddress) {
        String oldEmailAddress = this.emailAddress;
        this.emailAddress = emailAddress;
        changeSupport.firePropertyChange("emailAddress", oldEmailAddress, emailAddress);
    }

    public BigInteger getRoomCapacity() {
        return roomCapacity;
    }

    public void setRoomCapacity(BigInteger roomCapacity) {
        BigInteger oldRoomCapacity = this.roomCapacity;
        this.roomCapacity = roomCapacity;
        changeSupport.firePropertyChange("roomCapacity", oldRoomCapacity, roomCapacity);
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (hotelId != null ? hotelId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Hotel_1)) {
            return false;
        }
        Hotel_1 other = (Hotel_1) object;
        if ((this.hotelId == null && other.hotelId != null) || (this.hotelId != null && !this.hotelId.equals(other.hotelId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "finalhbs.Hotel_1[ hotelId=" + hotelId + " ]";
    }

    public void addPropertyChangeListener(PropertyChangeListener listener) {
        changeSupport.addPropertyChangeListener(listener);
    }

    public void removePropertyChangeListener(PropertyChangeListener listener) {
        changeSupport.removePropertyChangeListener(listener);
    }
    
}
