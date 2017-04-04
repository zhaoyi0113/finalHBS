/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package finalhbs;

import java.io.Serializable;
import java.math.BigInteger;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Embeddable;

/**
 *
 * @author Marwah
 */
@Embeddable
public class BookingPK implements Serializable {

    @Basic(optional = false)
    @Column(name = "BOOKING_NUMBER")
    private BigInteger bookingNumber;
    @Basic(optional = false)
    @Column(name = "HOTEL_ID")
    private BigInteger hotelId;
    @Basic(optional = false)
    @Column(name = "ROOM_NUMBER")
    private BigInteger roomNumber;

    public BookingPK() {
    }

    public BookingPK(BigInteger bookingNumber, BigInteger hotelId, BigInteger roomNumber) {
        this.bookingNumber = bookingNumber;
        this.hotelId = hotelId;
        this.roomNumber = roomNumber;
    }

    public BigInteger getBookingNumber() {
        return bookingNumber;
    }

    public void setBookingNumber(BigInteger bookingNumber) {
        this.bookingNumber = bookingNumber;
    }

    public BigInteger getHotelId() {
        return hotelId;
    }

    public void setHotelId(BigInteger hotelId) {
        this.hotelId = hotelId;
    }

    public BigInteger getRoomNumber() {
        return roomNumber;
    }

    public void setRoomNumber(BigInteger roomNumber) {
        this.roomNumber = roomNumber;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (bookingNumber != null ? bookingNumber.hashCode() : 0);
        hash += (hotelId != null ? hotelId.hashCode() : 0);
        hash += (roomNumber != null ? roomNumber.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof BookingPK)) {
            return false;
        }
        BookingPK other = (BookingPK) object;
        if ((this.bookingNumber == null && other.bookingNumber != null) || (this.bookingNumber != null && !this.bookingNumber.equals(other.bookingNumber))) {
            return false;
        }
        if ((this.hotelId == null && other.hotelId != null) || (this.hotelId != null && !this.hotelId.equals(other.hotelId))) {
            return false;
        }
        if ((this.roomNumber == null && other.roomNumber != null) || (this.roomNumber != null && !this.roomNumber.equals(other.roomNumber))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "finalhbs.BookingPK[ bookingNumber=" + bookingNumber + ", hotelId=" + hotelId + ", roomNumber=" + roomNumber + " ]";
    }
    
}
