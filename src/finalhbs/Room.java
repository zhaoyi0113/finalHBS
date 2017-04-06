/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package finalhbs;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;
import java.io.Serializable;
import java.math.BigInteger;
import java.util.Collection;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Transient;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author Marwah
 */
@Entity
@Table(name = "ROOM", catalog = "", schema = "S27143392")
@NamedQueries({
    @NamedQuery(name = "Room.findAll", query = "SELECT r FROM Room r")
    , @NamedQuery(name = "Room.findByRoomNumber", query = "SELECT r FROM Room r WHERE r.roomPK.roomNumber = :roomNumber")
    , @NamedQuery(name = "Room.findByHotelId", query = "SELECT r FROM Room r WHERE r.roomPK.hotelId = :hotelId")
    , @NamedQuery(name = "Room.findByRoomType", query = "SELECT r FROM Room r WHERE r.roomType = :roomType")
    , @NamedQuery(name = "Room.findByRoomPrice", query = "SELECT r FROM Room r WHERE r.roomPrice = :roomPrice")
    , @NamedQuery(name = "Room.findByGuestCapacity", query = "SELECT r FROM Room r WHERE r.guestCapicity = :guestCapicity")
    , @NamedQuery(name = "Room.findByRoomDescription", query = "SELECT r FROM Room r WHERE r.roomDescription = :roomDescription")})
public class Room implements Serializable {

    @Basic(optional = false)
    @Column(name = "GUEST_CAPICITY")
    private BigInteger guestCapicity;
    @JoinTable(name = "ROOM_FACILITY", joinColumns = {
        @JoinColumn(name = "ROOM_NUMBER", referencedColumnName = "ROOM_NUMBER")
        , @JoinColumn(name = "HOTEL_ID", referencedColumnName = "HOTEL_ID")}, inverseJoinColumns = {
        @JoinColumn(name = "FACILITY_NUMBER", referencedColumnName = "FACILITY_NUMBER")})
    @ManyToMany
    private Collection<Facility> facilityCollection;

    @Transient
    private PropertyChangeSupport changeSupport = new PropertyChangeSupport(this);

    private static final long serialVersionUID = 1L;
    @EmbeddedId
    protected RoomPK roomPK;
    @Basic(optional = false)
    @Column(name = "ROOM_TYPE")
    private String roomType;
    @Basic(optional = false)
    @Column(name = "ROOM_PRICE")
    private String roomPrice;
    @Column(name = "ROOM_DESCRIPTION")
    private String roomDescription;

    public Room() {
    }

    public Room(RoomPK roomPK) {
        this.roomPK = roomPK;
    }

    public Room(RoomPK roomPK, String roomType, String roomPrice) {
        this.roomPK = roomPK;
        this.roomType = roomType;
        this.roomPrice = roomPrice;
    }

    public Room(BigInteger roomNumber, BigInteger hotelId) {
        this.roomPK = new RoomPK(roomNumber, hotelId);
    }

    public RoomPK getRoomPK() {
        return roomPK;
    }

    public void setRoomPK(RoomPK roomPK) {
        this.roomPK = roomPK;
    }

    public String getRoomType() {
        return roomType;
    }

    public void setRoomType(String roomType) {
        String oldRoomType = this.roomType;
        this.roomType = roomType;
        changeSupport.firePropertyChange("roomType", oldRoomType, roomType);
    }


    public String getRoomPrice() {
        return roomPrice;
    }

    public void setRoomPrice(String roomPrice) {
        String oldRoomPrice = this.roomPrice;
        this.roomPrice = roomPrice;
        changeSupport.firePropertyChange("roomPrice", oldRoomPrice, roomPrice);
    }

    public String getRoomDescription() {
        return roomDescription;
    }

    public void setRoomDescription(String roomDescription) {
        String oldRoomDescription = this.roomDescription;
        this.roomDescription = roomDescription;
        changeSupport.firePropertyChange("roomDescription", oldRoomDescription, roomDescription);
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (roomPK != null ? roomPK.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Room)) {
            return false;
        }
        Room other = (Room) object;
        if ((this.roomPK == null && other.roomPK != null) || (this.roomPK != null && !this.roomPK.equals(other.roomPK))) {
            return false;
        }
        return true;
    }


    @Override
    public String toString() {
        return "finalhbs.Room[ roomPK=" + roomPK + " ]";
    }

    public void addPropertyChangeListener(PropertyChangeListener listener) {
        changeSupport.addPropertyChangeListener(listener);
    }

    public void removePropertyChangeListener(PropertyChangeListener listener) {
        changeSupport.removePropertyChangeListener(listener);
    }

    public BigInteger getGuestCapicity() {
        return guestCapicity;
    }

    public void setGuestCapicity(BigInteger guestCapicity) {
        BigInteger oldGuestCapicity = this.guestCapicity;
        this.guestCapicity = guestCapicity;
        changeSupport.firePropertyChange("guestCapicity", oldGuestCapicity, guestCapicity);
    }

    @XmlTransient
    public Collection<Facility> getFacilityCollection() {
        return facilityCollection;
    }

    public void setFacilityCollection(Collection<Facility> facilityCollection) {
        this.facilityCollection = facilityCollection;
    }
    
}
