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
@Table(name = "PAYMENT", catalog = "", schema = "S27143392")
@NamedQueries({
    @NamedQuery(name = "Payment.findAll", query = "SELECT p FROM Payment p")
    , @NamedQuery(name = "Payment.findByPaymentNumber", query = "SELECT p FROM Payment p WHERE p.paymentNumber = :paymentNumber")
    , @NamedQuery(name = "Payment.findByBookingNumber", query = "SELECT p FROM Payment p WHERE p.bookingNumber = :bookingNumber")
    , @NamedQuery(name = "Payment.findByHotelId", query = "SELECT p FROM Payment p WHERE p.hotelId = :hotelId")
    , @NamedQuery(name = "Payment.findByRoomNumber", query = "SELECT p FROM Payment p WHERE p.roomNumber = :roomNumber")
    , @NamedQuery(name = "Payment.findByPaymentDate", query = "SELECT p FROM Payment p WHERE p.paymentDate = :paymentDate")
    , @NamedQuery(name = "Payment.findByPaymentMethod", query = "SELECT p FROM Payment p WHERE p.paymentMethod = :paymentMethod")
    , @NamedQuery(name = "Payment.findByPaymentAmount", query = "SELECT p FROM Payment p WHERE p.paymentAmount = :paymentAmount")})
public class Payment implements Serializable {

    @Transient
    private PropertyChangeSupport changeSupport = new PropertyChangeSupport(this);

    private static final long serialVersionUID = 1L;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Id
    @Basic(optional = false)
    @Column(name = "PAYMENT_NUMBER")
    private BigDecimal paymentNumber;
    @Basic(optional = false)
    @Column(name = "BOOKING_NUMBER")
    private BigInteger bookingNumber;
    @Basic(optional = false)
    @Column(name = "HOTEL_ID")
    private BigInteger hotelId;
    @Basic(optional = false)
    @Column(name = "ROOM_NUMBER")
    private BigInteger roomNumber;
    @Basic(optional = false)
    @Column(name = "PAYMENT_DATE")
    private String paymentDate;
    @Column(name = "PAYMENT_METHOD")
    private String paymentMethod;
    @Basic(optional = false)
    @Column(name = "PAYMENT_AMOUNT")
    private BigInteger paymentAmount;

    public Payment() {
    }

    public Payment(BigDecimal paymentNumber) {
        this.paymentNumber = paymentNumber;
    }

    public Payment(BigDecimal paymentNumber, BigInteger bookingNumber, BigInteger hotelId, BigInteger roomNumber, String paymentDate, BigInteger paymentAmount) {
        this.paymentNumber = paymentNumber;
        this.bookingNumber = bookingNumber;
        this.hotelId = hotelId;
        this.roomNumber = roomNumber;
        this.paymentDate = paymentDate;
        this.paymentAmount = paymentAmount;
    }

    public BigDecimal getPaymentNumber() {
        return paymentNumber;
    }

    public void setPaymentNumber(BigDecimal paymentNumber) {
        BigDecimal oldPaymentNumber = this.paymentNumber;
        this.paymentNumber = paymentNumber;
        changeSupport.firePropertyChange("paymentNumber", oldPaymentNumber, paymentNumber);
    }

    public BigInteger getBookingNumber() {
        return bookingNumber;
    }

    public void setBookingNumber(BigInteger bookingNumber) {
        BigInteger oldBookingNumber = this.bookingNumber;
        this.bookingNumber = bookingNumber;
        changeSupport.firePropertyChange("bookingNumber", oldBookingNumber, bookingNumber);
    }

    public BigInteger getHotelId() {
        return hotelId;
    }

    public void setHotelId(BigInteger hotelId) {
        BigInteger oldHotelId = this.hotelId;
        this.hotelId = hotelId;
        changeSupport.firePropertyChange("hotelId", oldHotelId, hotelId);
    }

    public BigInteger getRoomNumber() {
        return roomNumber;
    }

    public void setRoomNumber(BigInteger roomNumber) {
        BigInteger oldRoomNumber = this.roomNumber;
        this.roomNumber = roomNumber;
        changeSupport.firePropertyChange("roomNumber", oldRoomNumber, roomNumber);
    }

    public String getPaymentDate() {
        return paymentDate;
    }

    public void setPaymentDate(String paymentDate) {
        String oldPaymentDate = this.paymentDate;
        this.paymentDate = paymentDate;
        changeSupport.firePropertyChange("paymentDate", oldPaymentDate, paymentDate);
    }

    public String getPaymentMethod() {
        return paymentMethod;
    }

    public void setPaymentMethod(String paymentMethod) {
        String oldPaymentMethod = this.paymentMethod;
        this.paymentMethod = paymentMethod;
        changeSupport.firePropertyChange("paymentMethod", oldPaymentMethod, paymentMethod);
    }

    public BigInteger getPaymentAmount() {
        return paymentAmount;
    }

    public void setPaymentAmount(BigInteger paymentAmount) {
        BigInteger oldPaymentAmount = this.paymentAmount;
        this.paymentAmount = paymentAmount;
        changeSupport.firePropertyChange("paymentAmount", oldPaymentAmount, paymentAmount);
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (paymentNumber != null ? paymentNumber.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Payment)) {
            return false;
        }
        Payment other = (Payment) object;
        if ((this.paymentNumber == null && other.paymentNumber != null) || (this.paymentNumber != null && !this.paymentNumber.equals(other.paymentNumber))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "finalhbs.Payment[ paymentNumber=" + paymentNumber + " ]";
    }

    public void addPropertyChangeListener(PropertyChangeListener listener) {
        changeSupport.addPropertyChangeListener(listener);
    }

    public void removePropertyChangeListener(PropertyChangeListener listener) {
        changeSupport.removePropertyChangeListener(listener);
    }
    
}
