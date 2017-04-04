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
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Transient;

/**
 *
 * @author Marwah
 */
@Entity
@Table(name = "BOOKING", catalog = "", schema = "S27143392")
@NamedQueries({
    @NamedQuery(name = "Booking.findAll", query = "SELECT b FROM Booking b")
    , @NamedQuery(name = "Booking.findByBookingNumber", query = "SELECT b FROM Booking b WHERE b.bookingPK.bookingNumber = :bookingNumber")
    , @NamedQuery(name = "Booking.findByCustomerNumber", query = "SELECT b FROM Booking b WHERE b.customerNumber = :customerNumber")
    , @NamedQuery(name = "Booking.findByHotelId", query = "SELECT b FROM Booking b WHERE b.bookingPK.hotelId = :hotelId")
    , @NamedQuery(name = "Booking.findByRoomNumber", query = "SELECT b FROM Booking b WHERE b.bookingPK.roomNumber = :roomNumber")
    , @NamedQuery(name = "Booking.findByCheckinDate", query = "SELECT b FROM Booking b WHERE b.checkinDate = :checkinDate")
    , @NamedQuery(name = "Booking.findByCheckoutDate", query = "SELECT b FROM Booking b WHERE b.checkoutDate = :checkoutDate")
    , @NamedQuery(name = "Booking.findByContactPerson", query = "SELECT b FROM Booking b WHERE b.contactPerson = :contactPerson")
    , @NamedQuery(name = "Booking.findByContactEmail", query = "SELECT b FROM Booking b WHERE b.contactEmail = :contactEmail")
    , @NamedQuery(name = "Booking.findByTotalAmount", query = "SELECT b FROM Booking b WHERE b.totalAmount = :totalAmount")
    , @NamedQuery(name = "Booking.findByDiscountAmount", query = "SELECT b FROM Booking b WHERE b.discountAmount = :discountAmount")
    , @NamedQuery(name = "Booking.findByPaymentStatus", query = "SELECT b FROM Booking b WHERE b.paymentStatus = :paymentStatus")})
public class Booking implements Serializable {

    @Transient
    private PropertyChangeSupport changeSupport = new PropertyChangeSupport(this);

    private static final long serialVersionUID = 1L;
    @EmbeddedId
    protected BookingPK bookingPK;
    @Basic(optional = false)
    @Column(name = "CUSTOMER_NUMBER")
    private BigInteger customerNumber;
    @Column(name = "CHECKIN_DATE")
    private String checkinDate;
    @Column(name = "CHECKOUT_DATE")
    private String checkoutDate;
    @Basic(optional = false)
    @Column(name = "CONTACT_PERSON")
    private String contactPerson;
    @Basic(optional = false)
    @Column(name = "CONTACT_EMAIL")
    private String contactEmail;
    @Basic(optional = false)
    @Column(name = "TOTAL_AMOUNT")
    private BigInteger totalAmount;
    @Column(name = "DISCOUNT_AMOUNT")
    private BigInteger discountAmount;
    @Column(name = "PAYMENT_STATUS")
    private String paymentStatus;

    public Booking() {
    }

    public Booking(BookingPK bookingPK) {
        this.bookingPK = bookingPK;
    }

    public Booking(BookingPK bookingPK, BigInteger customerNumber, String contactPerson, String contactEmail, BigInteger totalAmount) {
        this.bookingPK = bookingPK;
        this.customerNumber = customerNumber;
        this.contactPerson = contactPerson;
        this.contactEmail = contactEmail;
        this.totalAmount = totalAmount;
    }

    public Booking(BigInteger bookingNumber, BigInteger hotelId, BigInteger roomNumber) {
        this.bookingPK = new BookingPK(bookingNumber, hotelId, roomNumber);
    }

    public BookingPK getBookingPK() {
        return bookingPK;
    }

    public void setBookingPK(BookingPK bookingPK) {
        this.bookingPK = bookingPK;
    }

    public BigInteger getCustomerNumber() {
        return customerNumber;
    }

    public void setCustomerNumber(BigInteger customerNumber) {
        BigInteger oldCustomerNumber = this.customerNumber;
        this.customerNumber = customerNumber;
        changeSupport.firePropertyChange("customerNumber", oldCustomerNumber, customerNumber);
    }

    public String getCheckinDate() {
        return checkinDate;
    }

    public void setCheckinDate(String checkinDate) {
        String oldCheckinDate = this.checkinDate;
        this.checkinDate = checkinDate;
        changeSupport.firePropertyChange("checkinDate", oldCheckinDate, checkinDate);
    }

    public String getCheckoutDate() {
        return checkoutDate;
    }

    public void setCheckoutDate(String checkoutDate) {
        String oldCheckoutDate = this.checkoutDate;
        this.checkoutDate = checkoutDate;
        changeSupport.firePropertyChange("checkoutDate", oldCheckoutDate, checkoutDate);
    }

    public String getContactPerson() {
        return contactPerson;
    }

    public void setContactPerson(String contactPerson) {
        String oldContactPerson = this.contactPerson;
        this.contactPerson = contactPerson;
        changeSupport.firePropertyChange("contactPerson", oldContactPerson, contactPerson);
    }

    public String getContactEmail() {
        return contactEmail;
    }

    public void setContactEmail(String contactEmail) {
        String oldContactEmail = this.contactEmail;
        this.contactEmail = contactEmail;
        changeSupport.firePropertyChange("contactEmail", oldContactEmail, contactEmail);
    }

    public BigInteger getTotalAmount() {
        return totalAmount;
    }

    public void setTotalAmount(BigInteger totalAmount) {
        BigInteger oldTotalAmount = this.totalAmount;
        this.totalAmount = totalAmount;
        changeSupport.firePropertyChange("totalAmount", oldTotalAmount, totalAmount);
    }

    public BigInteger getDiscountAmount() {
        return discountAmount;
    }

    public void setDiscountAmount(BigInteger discountAmount) {
        BigInteger oldDiscountAmount = this.discountAmount;
        this.discountAmount = discountAmount;
        changeSupport.firePropertyChange("discountAmount", oldDiscountAmount, discountAmount);
    }

    public String getPaymentStatus() {
        return paymentStatus;
    }

    public void setPaymentStatus(String paymentStatus) {
        String oldPaymentStatus = this.paymentStatus;
        this.paymentStatus = paymentStatus;
        changeSupport.firePropertyChange("paymentStatus", oldPaymentStatus, paymentStatus);
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (bookingPK != null ? bookingPK.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Booking)) {
            return false;
        }
        Booking other = (Booking) object;
        if ((this.bookingPK == null && other.bookingPK != null) || (this.bookingPK != null && !this.bookingPK.equals(other.bookingPK))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "finalhbs.Booking[ bookingPK=" + bookingPK + " ]";
    }

    public void addPropertyChangeListener(PropertyChangeListener listener) {
        changeSupport.addPropertyChangeListener(listener);
    }

    public void removePropertyChangeListener(PropertyChangeListener listener) {
        changeSupport.removePropertyChangeListener(listener);
    }
    
}
