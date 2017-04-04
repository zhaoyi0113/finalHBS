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
@Table(name = "MEMBERSHIP", catalog = "", schema = "S27143392")
@NamedQueries({
    @NamedQuery(name = "Membership.findAll", query = "SELECT m FROM Membership m")
    , @NamedQuery(name = "Membership.findByMembershipTier", query = "SELECT m FROM Membership m WHERE m.membershipTier = :membershipTier")
    , @NamedQuery(name = "Membership.findByTierCredit", query = "SELECT m FROM Membership m WHERE m.tierCredit = :tierCredit")
    , @NamedQuery(name = "Membership.findByDiscount", query = "SELECT m FROM Membership m WHERE m.discount = :discount")
    , @NamedQuery(name = "Membership.findByReward", query = "SELECT m FROM Membership m WHERE m.reward = :reward")})
public class Membership implements Serializable {

    @Transient
    private PropertyChangeSupport changeSupport = new PropertyChangeSupport(this);

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @Column(name = "MEMBERSHIP_TIER")
    private String membershipTier;
    @Basic(optional = false)
    @Column(name = "TIER_CREDIT")
    private BigInteger tierCredit;
    @Column(name = "DISCOUNT")
    private String discount;
    @Column(name = "REWARD")
    private String reward;

    public Membership() {
    }

    public Membership(String membershipTier) {
        this.membershipTier = membershipTier;
    }

    public Membership(String membershipTier, BigInteger tierCredit) {
        this.membershipTier = membershipTier;
        this.tierCredit = tierCredit;
    }

    public String getMembershipTier() {
        return membershipTier;
    }

    public void setMembershipTier(String membershipTier) {
        String oldMembershipTier = this.membershipTier;
        this.membershipTier = membershipTier;
        changeSupport.firePropertyChange("membershipTier", oldMembershipTier, membershipTier);
    }

    public BigInteger getTierCredit() {
        return tierCredit;
    }

    public void setTierCredit(BigInteger tierCredit) {
        BigInteger oldTierCredit = this.tierCredit;
        this.tierCredit = tierCredit;
        changeSupport.firePropertyChange("tierCredit", oldTierCredit, tierCredit);
    }

    public String getDiscount() {
        return discount;
    }

    public void setDiscount(String discount) {
        String oldDiscount = this.discount;
        this.discount = discount;
        changeSupport.firePropertyChange("discount", oldDiscount, discount);
    }

    public String getReward() {
        return reward;
    }

    public void setReward(String reward) {
        String oldReward = this.reward;
        this.reward = reward;
        changeSupport.firePropertyChange("reward", oldReward, reward);
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (membershipTier != null ? membershipTier.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Membership)) {
            return false;
        }
        Membership other = (Membership) object;
        if ((this.membershipTier == null && other.membershipTier != null) || (this.membershipTier != null && !this.membershipTier.equals(other.membershipTier))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "finalhbs.Membership[ membershipTier=" + membershipTier + " ]";
    }

    public void addPropertyChangeListener(PropertyChangeListener listener) {
        changeSupport.addPropertyChangeListener(listener);
    }

    public void removePropertyChangeListener(PropertyChangeListener listener) {
        changeSupport.removePropertyChangeListener(listener);
    }
    
}
