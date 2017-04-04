/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package finalhbs;

import oracle.jdbc.OracleDriver;

import javax.swing.*;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.AbstractTableModel;
import java.math.BigInteger;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Collection;
import java.util.Iterator;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * @author Marwah
 */
public class Hotel extends javax.swing.JFrame {

    private Connection conn;
    private Connection conn2;
    private HotelTableModel tableModel;
    private RoomTableModel roomTableModel;
    private CustomerTableModel customerTableModel;
    private GuestTableModel guestTableModel;
    private MembershipTableModel membershipTableModel;
    private BookingTableModel bookingTableModel;
    private PaymentTableModel paymentTableModel;

    /**
     * Creates new form Hotel
     */
    public Hotel() {
        initComponents();

        try {
            DriverManager.registerDriver(new OracleDriver());
            conn = DriverManager.getConnection("jdbc:oracle:thin:@hippo.its.monash.edu.au:1521:FIT5148a", "S27143392", "student");
            conn2 = DriverManager.getConnection("jdbc:oracle:thin:@hippo.its.monash.edu.au:1521:FIT5148B", "S27143392", "student");
            System.out.println("Connected to Oracle");
        } catch (SQLException f) {
            System.out.println("error in connection");
        }
        this.initializeData();
        this.addActionListeners();
    }

    private void initializeData() {
        tableModel = new HotelTableModel();
        this.jTableHotel.setModel(tableModel);
        roomTableModel = new RoomTableModel();
        this.jTableRoom.setModel(roomTableModel);
        customerTableModel = new CustomerTableModel();
        this.jTableCustomer.setModel(customerTableModel);
        guestTableModel = new GuestTableModel();
        this.jTableGuest.setModel(guestTableModel);
        membershipTableModel = new MembershipTableModel();
        this.jTableMembership.setModel(membershipTableModel);
        bookingTableModel = new BookingTableModel();
        this.jTableBooking.setModel(bookingTableModel);
        paymentTableModel = new PaymentTableModel();
        this.jTablePayment.setModel(paymentTableModel);
        for (Iterator<Hotel_1> iterator = hotel_1List.iterator(); iterator.hasNext(); ) {
            Hotel_1 hotel = iterator.next();
            tfRoomHotelId.addItem(hotel.getHotelId().intValue() + "");
            tfBookingHotelID.addItem(hotel.getHotelId().intValue() + "");
        }
    }

    /**
     * add action listeners on components
     */
    private void addActionListeners() {
        this.jTableHotel.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        this.jTableHotel.getSelectionModel().addListSelectionListener(new ListSelectionListener() {
            @Override
            public void valueChanged(ListSelectionEvent e) {
                int index = jTableHotel.getSelectedRow();
                if (index < 0) {
                    return;
                }
                Hotel_1 hotel_1 = hotel_1List.get(index);
                tfHotelId.setText(hotel_1.getHotelId() + "");
                tfHotelNAme.setText(hotel_1.getHotelName());
                tfHotelTier.setSelectedItem(hotel_1.getHotelTier());
                tfHotelconsyear.setText(hotel_1.getConstructionYear());
                tfHotelCapacity.setText(hotel_1.getRoomCapacity() + "");
                tfHotelContactNo.setText(hotel_1.getContactNumber());
                tfHotelEmail.setText(hotel_1.getEmailAddress());
                tfHotelAddress.setText(hotel_1.getAddress());
                tfHotelCity.setText(hotel_1.getCity());
                tfHotelCountry.setText(hotel_1.getCountry());
            }
        });

        this.jTableRoom.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        this.jTableRoom.getSelectionModel().addListSelectionListener(new ListSelectionListener() {
            @Override
            public void valueChanged(ListSelectionEvent e) {
                int index = jTableRoom.getSelectedRow();
                if (index < 0) {
                    return;
                }
                Room room = roomList.get(index);
                tfRoomId.setText(room.getRoomPK().getRoomNumber().intValue() + "");
                tfRoomDesc.setText(room.getRoomDescription());
                tfRoomPrice.setText(room.getRoomPrice() + "");
                tfRoomCapacity.setSelectedItem(room.getGuestCapicity() + "");
                tfRoomHotelId.setSelectedItem(room.getRoomPK().getHotelId() + "");
            }
        });

        this.jTableCustomer.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        this.jTableCustomer.getSelectionModel().addListSelectionListener(new ListSelectionListener() {
            @Override
            public void valueChanged(ListSelectionEvent e) {
                int index = jTableCustomer.getSelectedRow();
                if (index < 0) {
                    return;
                }
                Customer customer = customerList.get(index);
                tfCustomerID.setText(customer.getCustomerNumber().toString());
                tfCustomerTitle.setText(customer.getTitle());
                tfCustomerFName.setText(customer.getFirstName());
                tfCustomerLName.setText(customer.getLastName());
                tfCustomerContactNo.setText(customer.getPhoneNumber());
                tfCustomerDOB.setText(customer.getDob());
                tfCustomerEmail.setText(customer.getEmailAddress());
                tfCustomerCredit.setText(customer.getMembershipCredit());
                comboCustomerMemberTier.setSelectedItem(customer.getMembershipTier());
                tfCustomerPostCode.setText(customer.getPostalCode());
                tfCustomerStreet.setText(customer.getStreet());
                tfCustomerCity.setText(customer.getCity());
                tfCustomerCuntry.setText(customer.getCountry());

            }

        });

        this.jTableGuest.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        this.jTableGuest.getSelectionModel().addListSelectionListener(new ListSelectionListener() {
            @Override
            public void valueChanged(ListSelectionEvent e) {
                int index = jTableGuest.getSelectedRow();
                if (index < 0) {
                    return;
                }
                Guest guest = guestList.get(index);
                tfGuestID.setText(guest.getGuestNumber().toString());
                tfGuestTitle.setText(guest.getTitle());
                tfGuestFName.setText(guest.getFirstName());
                tfGuestLName.setText(guest.getLastName());
                tfGuestContactNo.setText(guest.getPhoneNumber().toString());
                tfGuestDOB.setText(guest.getDob());
                tfGuestEmail.setText(guest.getEmailAddress());
                tfGuestPostCode.setText(guest.getPostalCode());
                tfGuestStreet.setText(guest.getStreet());
                tfGuestCity.setText(guest.getCity());
                tfGuestCuntry.setText(guest.getCountry());
            }
        });

        this.jTableMembership.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        this.jTableMembership.getSelectionModel().addListSelectionListener(new ListSelectionListener() {
            @Override
            public void valueChanged(ListSelectionEvent e) {
                int index = jTableMembership.getSelectedRow();
                if (index < 0) {
                    return;
                }
                Membership membership = membershipList.get(index);
                tfMembershipCredit.setText(membership.getTierCredit().toString());
                tfMembershipDiscount.setText(membership.getDiscount());
                ;
                tfMembershipRewards.setText(membership.getReward());
                ;
                tfMembershipTier.setText(membership.getTierCredit().toString());
                ;

            }
        });

        this.jTableBooking.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        this.jTableBooking.getSelectionModel().addListSelectionListener(new ListSelectionListener() {
            @Override
            public void valueChanged(ListSelectionEvent e) {
                int index = jTableBooking.getSelectedRow();
                if (index < 0) {
                    return;
                }
                Booking booking = bookingList.get(index);
                tfBookingCheckInDate.setText(booking.getCheckinDate());
                tfBookingCheckOutDate.setText(booking.getCheckoutDate());
                tfBookingContactEmail.setText(booking.getContactEmail());
                tfBookingContactPerson.setText(booking.getContactPerson());
                tfBookingCustNo.setSelectedItem(booking.getContactPerson());
                tfBookingDiscount.setText(booking.getDiscountAmount().toString());
                tfBookingHotelID.setSelectedItem(booking.getBookingPK().getHotelId() + "");
                tfBookingNumber.setText(booking.getBookingPK().getBookingNumber().toString());
                tfBookingPaymentStatus.setSelectedItem(booking.getPaymentStatus());
                tfBookingTotalAmount.setText(booking.getTotalAmount().toString());

            }
        });

        this.jTablePayment.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        this.jTablePayment.getSelectionModel().addListSelectionListener(new ListSelectionListener() {
            @Override
            public void valueChanged(ListSelectionEvent e) {
                int index = jTablePayment.getSelectedRow();
                if (index < 0) {
                    return;
                }
                Payment payment = paymentList.get(index);
                tfPaymentAmount.setText(payment.getPaymentAmount().toString());
                tfPaymentBookingNo.setSelectedItem(payment.getBookingNumber());
                ;
                tfPaymentDate.setText(payment.getPaymentDate());
                ;
                tfPaymentHotelID.setSelectedItem(payment.getHotelId());
                ;
                tfPaymentMethod.setSelectedItem(payment.getPaymentMethod());
                ;
                tfPaymentNo.setText(payment.getPaymentNumber().toString());
                ;
                tfPaymentRoomNo.setSelectedItem(payment.getRoomNumber());
                ;

            }
        });

        for (Iterator<Facility> iterator = facilityList.iterator(); iterator.hasNext(); ) {
            this.tfRoomFacilityCombo.addItem(iterator.next().getFacilityDescription());
        }
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {
        bindingGroup = new org.jdesktop.beansbinding.BindingGroup();

        entityManager = java.beans.Beans.isDesignTime() ? null : javax.persistence.Persistence.createEntityManagerFactory("jdbc:oracle:thin:@hippo.its.monash.edu:1521:FIT5148APU").createEntityManager();
        hotel_1Query = java.beans.Beans.isDesignTime() ? null : entityManager.createQuery("SELECT h FROM Hotel_1 h");
        hotel_1List = java.beans.Beans.isDesignTime() ? java.util.Collections.emptyList() : hotel_1Query.getResultList();
        hotel_1Query1 = java.beans.Beans.isDesignTime() ? null : entityManager.createQuery("SELECT h FROM Hotel_1 h");
        hotel_1List1 = java.beans.Beans.isDesignTime() ? java.util.Collections.emptyList() : hotel_1Query1.getResultList();
        entityManager0 = java.beans.Beans.isDesignTime() ? null : javax.persistence.Persistence.createEntityManagerFactory("jdbc:oracle:thin:@hippo.its.monash.edu:1521:FIT5148BPU").createEntityManager();
        roomQuery = java.beans.Beans.isDesignTime() ? null : entityManager0.createQuery("SELECT r FROM Room r");
        roomList = java.beans.Beans.isDesignTime() ? java.util.Collections.emptyList() : roomQuery.getResultList();
        roomQuery1 = java.beans.Beans.isDesignTime() ? null : entityManager0.createQuery("SELECT r FROM Room r");
        roomList1 = java.beans.Beans.isDesignTime() ? java.util.Collections.emptyList() : roomQuery1.getResultList();
        roomQuery2 = java.beans.Beans.isDesignTime() ? null : entityManager0.createQuery("SELECT r FROM Room r");
        roomList2 = java.beans.Beans.isDesignTime() ? java.util.Collections.emptyList() : roomQuery2.getResultList();
        roomQuery3 = java.beans.Beans.isDesignTime() ? null : entityManager0.createQuery("SELECT r FROM Room r");
        roomList3 = java.beans.Beans.isDesignTime() ? java.util.Collections.emptyList() : roomQuery3.getResultList();
        roomQuery4 = java.beans.Beans.isDesignTime() ? null : entityManager0.createQuery("SELECT r FROM Room r");
        roomList4 = java.beans.Beans.isDesignTime() ? java.util.Collections.emptyList() : roomQuery4.getResultList();
        roomQuery5 = java.beans.Beans.isDesignTime() ? null : entityManager0.createQuery("SELECT r FROM Room r");
        roomList5 = java.beans.Beans.isDesignTime() ? java.util.Collections.emptyList() : roomQuery5.getResultList();
        customerQuery = java.beans.Beans.isDesignTime() ? null : entityManager0.createQuery("SELECT c FROM Customer c");
        customerList = java.beans.Beans.isDesignTime() ? java.util.Collections.emptyList() : customerQuery.getResultList();
        entityManager1 = java.beans.Beans.isDesignTime() ? null : javax.persistence.Persistence.createEntityManagerFactory("jdbc:oracle:thin:@hippo.its.monash.edu:1521:FIT5148APU").createEntityManager();
        customerQuery1 = java.beans.Beans.isDesignTime() ? null : entityManager0.createQuery("SELECT c FROM Customer c");
        customerList1 = java.beans.Beans.isDesignTime() ? java.util.Collections.emptyList() : customerQuery1.getResultList();
        guestQuery = java.beans.Beans.isDesignTime() ? null : entityManager0.createQuery("SELECT g FROM Guest g");
        guestList = java.beans.Beans.isDesignTime() ? java.util.Collections.emptyList() : guestQuery.getResultList();
        membershipQuery = java.beans.Beans.isDesignTime() ? null : entityManager0.createQuery("SELECT m FROM Membership m");
        membershipList = java.beans.Beans.isDesignTime() ? java.util.Collections.emptyList() : membershipQuery.getResultList();
        bookingQuery = java.beans.Beans.isDesignTime() ? null : entityManager0.createQuery("SELECT b FROM Booking b");
        bookingList = java.beans.Beans.isDesignTime() ? java.util.Collections.emptyList() : bookingQuery.getResultList();
        paymentQuery = java.beans.Beans.isDesignTime() ? null : entityManager0.createQuery("SELECT p FROM Payment p");
        paymentList = java.beans.Beans.isDesignTime() ? java.util.Collections.emptyList() : paymentQuery.getResultList();
        facilityQuery = java.beans.Beans.isDesignTime() ? null : entityManager0.createQuery("SELECT f FROM Facility f");
        facilityList = java.beans.Beans.isDesignTime() ? java.util.Collections.emptyList() : facilityQuery.getResultList();
        ;


        jTabbedPane1 = new javax.swing.JTabbedPane();
        jPanel24 = new javax.swing.JPanel();
        jPanel1 = new javax.swing.JPanel();
        jPanel2 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        tfHotelId = new javax.swing.JTextField();
        tfHotelNAme = new javax.swing.JTextField();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        tfHotelCapacity = new javax.swing.JTextField();
        jLabel4 = new javax.swing.JLabel();
        tfHotelconsyear = new javax.swing.JTextField();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        tfHotelCountry = new javax.swing.JTextField();
        jLabel7 = new javax.swing.JLabel();
        tfHotelContactNo = new javax.swing.JTextField();
        tfHotelCity = new javax.swing.JTextField();
        jLabel8 = new javax.swing.JLabel();
        tfHotelEmail = new javax.swing.JTextField();
        jLabel9 = new javax.swing.JLabel();
        tfHotelAddress = new javax.swing.JTextField();
        jLabel10 = new javax.swing.JLabel();
        tfHotelTier = new javax.swing.JComboBox<>();
        jButtonInsertHotel = new javax.swing.JButton();
        jButtonUpdate = new javax.swing.JButton();
        jButtonDelete = new javax.swing.JButton();
        hotelFieldsErrorMessage = new javax.swing.JLabel();
        hotelErrorMessage = new javax.swing.JLabel();
        jPanel3 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTableHotel = new javax.swing.JTable();
        jLabel12 = new javax.swing.JLabel();
        tfHotelTiercombo = new javax.swing.JComboBox<>();
        jButtonSearchHotelBy = new javax.swing.JButton();
        jPanel4 = new javax.swing.JPanel();
        jPanel5 = new javax.swing.JPanel();
        jLabel13 = new javax.swing.JLabel();
        tfRoomId = new javax.swing.JTextField();
        tfRoomDesc = new javax.swing.JTextField();
        jLabel14 = new javax.swing.JLabel();
        jLabel16 = new javax.swing.JLabel();
        tfRoomPrice = new javax.swing.JTextField();
        jLabel17 = new javax.swing.JLabel();
        jButtonInsertRoom = new javax.swing.JButton();
        jButtonUpdateRoom = new javax.swing.JButton();
        jButtonDeleteRoom = new javax.swing.JButton();
        hotelFieldsErrorMessage1 = new javax.swing.JLabel();
        RoomErrorMessage = new javax.swing.JLabel();
        jLabel36 = new javax.swing.JLabel();
        tfRoomHotelId = new javax.swing.JComboBox<>();
        jLabel46 = new javax.swing.JLabel();
        jComboBox2 = new javax.swing.JComboBox<>();
        tfRoomCapacity = new javax.swing.JComboBox<>();
        jPanel6 = new javax.swing.JPanel();
        jLabel24 = new javax.swing.JLabel();
        tfRoomFacilityCombo = new javax.swing.JComboBox<>();
        jButtonSearchRoomBy = new javax.swing.JButton();
        jScrollPane3 = new javax.swing.JScrollPane();
        jTableRoom = new javax.swing.JTable();
        jPanel7 = new javax.swing.JPanel();
        jPanel8 = new javax.swing.JPanel();
        jLabel15 = new javax.swing.JLabel();
        tfCustomerID = new javax.swing.JTextField();
        tfCustomerTitle = new javax.swing.JTextField();
        jLabel18 = new javax.swing.JLabel();
        jLabel19 = new javax.swing.JLabel();
        tfCustomerLName = new javax.swing.JTextField();
        jLabel20 = new javax.swing.JLabel();
        tfCustomerFName = new javax.swing.JTextField();
        jLabel21 = new javax.swing.JLabel();
        jLabel22 = new javax.swing.JLabel();
        tfCustomerCuntry = new javax.swing.JTextField();
        jLabel25 = new javax.swing.JLabel();
        tfCustomerContactNo = new javax.swing.JTextField();
        tfCustomerCity = new javax.swing.JTextField();
        jLabel26 = new javax.swing.JLabel();
        tfCustomerEmail = new javax.swing.JTextField();
        jLabel27 = new javax.swing.JLabel();
        tfCustomerStreet = new javax.swing.JTextField();
        jLabel28 = new javax.swing.JLabel();
        comboCustomerMemberTier = new javax.swing.JComboBox<>();
        jButtonInsertCutomer = new javax.swing.JButton();
        jButtonUpdateCutomer = new javax.swing.JButton();
        jButtonDeleteCutomer = new javax.swing.JButton();
        hotelFieldsErrorMessage2 = new javax.swing.JLabel();
        jLabel29 = new javax.swing.JLabel();
        tfCustomerPostCode = new javax.swing.JTextField();
        jLabel31 = new javax.swing.JLabel();
        tfCustomerCredit = new javax.swing.JTextField();
        jLabel32 = new javax.swing.JLabel();
        tfCustomerDOB = new javax.swing.JTextField();
        jLabel33 = new javax.swing.JLabel();
        jPanel9 = new javax.swing.JPanel();
        jScrollPane2 = new javax.swing.JScrollPane();
        jTableCustomer = new javax.swing.JTable();
        jLabel30 = new javax.swing.JLabel();
        tfHotelTiercombo1 = new javax.swing.JComboBox<>();
        jButtonSearchCutomerBy = new javax.swing.JButton();
        jPanel10 = new javax.swing.JPanel();
        jPanel11 = new javax.swing.JPanel();
        jLabel34 = new javax.swing.JLabel();
        tfGuestID = new javax.swing.JTextField();
        tfGuestTitle = new javax.swing.JTextField();
        jLabel35 = new javax.swing.JLabel();
        tfGuestLName = new javax.swing.JTextField();
        jLabel37 = new javax.swing.JLabel();
        tfGuestFName = new javax.swing.JTextField();
        jLabel38 = new javax.swing.JLabel();
        jLabel39 = new javax.swing.JLabel();
        tfGuestCuntry = new javax.swing.JTextField();
        jLabel40 = new javax.swing.JLabel();
        tfGuestContactNo = new javax.swing.JTextField();
        tfGuestCity = new javax.swing.JTextField();
        jLabel41 = new javax.swing.JLabel();
        tfGuestEmail = new javax.swing.JTextField();
        jLabel42 = new javax.swing.JLabel();
        tfGuestStreet = new javax.swing.JTextField();
        jLabel43 = new javax.swing.JLabel();
        jButtonInsertCutomer1 = new javax.swing.JButton();
        jButtonUpdateCutomer1 = new javax.swing.JButton();
        jButtonDeleteCutomer1 = new javax.swing.JButton();
        hotelFieldsErrorMessage3 = new javax.swing.JLabel();
        jLabel44 = new javax.swing.JLabel();
        tfGuestPostCode = new javax.swing.JTextField();
        jLabel45 = new javax.swing.JLabel();
        tfGuestDOB = new javax.swing.JTextField();
        jLabel47 = new javax.swing.JLabel();
        jPanel12 = new javax.swing.JPanel();
        jScrollPane4 = new javax.swing.JScrollPane();
        jTableGuest = new javax.swing.JTable();
        jLabel48 = new javax.swing.JLabel();
        jButtonSearchCutomerBy1 = new javax.swing.JButton();
        tfSearchGuestByName = new javax.swing.JTextField();
        jPanel13 = new javax.swing.JPanel();
        jPanel14 = new javax.swing.JPanel();
        jLabel49 = new javax.swing.JLabel();
        tfMembershipTier = new javax.swing.JTextField();
        tfMembershipCredit = new javax.swing.JTextField();
        jLabel50 = new javax.swing.JLabel();
        tfMembershipRewards = new javax.swing.JTextField();
        jLabel51 = new javax.swing.JLabel();
        tfMembershipDiscount = new javax.swing.JTextField();
        jLabel52 = new javax.swing.JLabel();
        jButtonInsertMembership = new javax.swing.JButton();
        jButtonUpdateMembership = new javax.swing.JButton();
        jButtonDeleteMembership = new javax.swing.JButton();
        hotelFieldsErrorMessage4 = new javax.swing.JLabel();
        jPanel15 = new javax.swing.JPanel();
        jScrollPane5 = new javax.swing.JScrollPane();
        jTableMembership = new javax.swing.JTable();
        jLabel61 = new javax.swing.JLabel();
        jButtonSearchMembershipBy = new javax.swing.JButton();
        jPanel16 = new javax.swing.JPanel();
        jPanel18 = new javax.swing.JPanel();
        jScrollPane6 = new javax.swing.JScrollPane();
        jTableBooking = new javax.swing.JTable();
        jTabbedPane2 = new javax.swing.JTabbedPane();
        jPanel19 = new javax.swing.JPanel();
        jPanel17 = new javax.swing.JPanel();
        jLabel53 = new javax.swing.JLabel();
        tfBookingNumber = new javax.swing.JTextField();
        jLabel54 = new javax.swing.JLabel();
        jButtonInsertMembership1 = new javax.swing.JButton();
        jButtonUpdateMembership1 = new javax.swing.JButton();
        jButtonDeleteMembership1 = new javax.swing.JButton();
        hotelFieldsErrorMessage5 = new javax.swing.JLabel();
        jLabel56 = new javax.swing.JLabel();
        tfBookingHotelID = new javax.swing.JComboBox<>();
        tfBookingRoomNo = new javax.swing.JComboBox<>();
        tfBookingCustNo = new javax.swing.JComboBox<>();
        jLabel57 = new javax.swing.JLabel();
        jLabel55 = new javax.swing.JLabel();
        tfBookingCheckInDate = new javax.swing.JTextField();
        tfBookingCheckOutDate = new javax.swing.JTextField();
        jLabel58 = new javax.swing.JLabel();
        tfBookingContactPerson = new javax.swing.JTextField();
        jLabel59 = new javax.swing.JLabel();
        tfBookingContactEmail = new javax.swing.JTextField();
        jLabel60 = new javax.swing.JLabel();
        tfBookingTotalAmount = new javax.swing.JTextField();
        jLabel63 = new javax.swing.JLabel();
        tfBookingDiscount = new javax.swing.JTextField();
        jLabel64 = new javax.swing.JLabel();
        tfBookingPaymentStatus = new javax.swing.JComboBox<>();
        jLabel65 = new javax.swing.JLabel();
        jPanel20 = new javax.swing.JPanel();
        jButtonSearchMembershipBy1 = new javax.swing.JButton();
        jLabel11 = new javax.swing.JLabel();
        jTextField1 = new javax.swing.JTextField();
        jLabel23 = new javax.swing.JLabel();
        jTextField2 = new javax.swing.JTextField();
        jLabel74 = new javax.swing.JLabel();
        jLabel75 = new javax.swing.JLabel();
        jTextField3 = new javax.swing.JTextField();
        jLabel76 = new javax.swing.JLabel();
        jLabel77 = new javax.swing.JLabel();
        jTextField5 = new javax.swing.JTextField();
        jTextField6 = new javax.swing.JTextField();
        jLabel78 = new javax.swing.JLabel();
        jLabel79 = new javax.swing.JLabel();
        jComboBox1 = new javax.swing.JComboBox<>();
        jPanel21 = new javax.swing.JPanel();
        jPanel22 = new javax.swing.JPanel();
        jLabel62 = new javax.swing.JLabel();
        tfPaymentNo = new javax.swing.JTextField();
        jLabel66 = new javax.swing.JLabel();
        jButtonInsertMembership2 = new javax.swing.JButton();
        jButtonUpdateMembership2 = new javax.swing.JButton();
        jButtonDeleteMembership2 = new javax.swing.JButton();
        hotelFieldsErrorMessage6 = new javax.swing.JLabel();
        tfPaymentBookingNo = new javax.swing.JComboBox<>();
        jLabel68 = new javax.swing.JLabel();
        tfPaymentHotelID = new javax.swing.JComboBox<>();
        tfPaymentRoomNo = new javax.swing.JComboBox<>();
        jLabel67 = new javax.swing.JLabel();
        tfPaymentDate = new javax.swing.JTextField();
        jLabel69 = new javax.swing.JLabel();
        jLabel70 = new javax.swing.JLabel();
        jLabel71 = new javax.swing.JLabel();
        tfPaymentAmount = new javax.swing.JTextField();
        tfPaymentMethod = new javax.swing.JComboBox<>();
        jLabel72 = new javax.swing.JLabel();
        jLabel73 = new javax.swing.JLabel();
        jPanel23 = new javax.swing.JPanel();
        jScrollPane7 = new javax.swing.JScrollPane();
        jTablePayment = new javax.swing.JTable();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        javax.swing.GroupLayout jPanel24Layout = new javax.swing.GroupLayout(jPanel24);
        jPanel24.setLayout(jPanel24Layout);
        jPanel24Layout.setHorizontalGroup(
                jPanel24Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGap(0, 1392, Short.MAX_VALUE)
        );
        jPanel24Layout.setVerticalGroup(
                jPanel24Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGap(0, 640, Short.MAX_VALUE)
        );

        jTabbedPane1.addTab("Main screen", jPanel24);

        jLabel1.setText("Hotel ID:");

        org.jdesktop.beansbinding.Binding binding = org.jdesktop.beansbinding.Bindings.createAutoBinding(org.jdesktop.beansbinding.AutoBinding.UpdateStrategy.READ_WRITE, jTableHotel, org.jdesktop.beansbinding.ELProperty.create("${selectedElement.hotelId}"), tfHotelId, org.jdesktop.beansbinding.BeanProperty.create("text"));
        bindingGroup.addBinding(binding);

        tfHotelId.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                tfHotelIdActionPerformed(evt);
            }
        });

        binding = org.jdesktop.beansbinding.Bindings.createAutoBinding(org.jdesktop.beansbinding.AutoBinding.UpdateStrategy.READ_WRITE, jTableHotel, org.jdesktop.beansbinding.ELProperty.create("${selectedElement.hotelName}"), tfHotelNAme, org.jdesktop.beansbinding.BeanProperty.create("text"));
        bindingGroup.addBinding(binding);

        jLabel2.setText("Hotel name:");

        jLabel3.setText("Hotel Tier:");

        binding = org.jdesktop.beansbinding.Bindings.createAutoBinding(org.jdesktop.beansbinding.AutoBinding.UpdateStrategy.READ_WRITE, jTableHotel, org.jdesktop.beansbinding.ELProperty.create("${selectedElement.roomCapacity}"), tfHotelCapacity, org.jdesktop.beansbinding.BeanProperty.create("text"));
        bindingGroup.addBinding(binding);

        jLabel4.setText("Room capacity:");

        binding = org.jdesktop.beansbinding.Bindings.createAutoBinding(org.jdesktop.beansbinding.AutoBinding.UpdateStrategy.READ_WRITE, jTableHotel, org.jdesktop.beansbinding.ELProperty.create("${selectedElement.constructionYear}"), tfHotelconsyear, org.jdesktop.beansbinding.BeanProperty.create("text"));
        bindingGroup.addBinding(binding);

        jLabel5.setText("Construction year:");

        jLabel6.setText("Address:");

        binding = org.jdesktop.beansbinding.Bindings.createAutoBinding(org.jdesktop.beansbinding.AutoBinding.UpdateStrategy.READ_WRITE, jTableHotel, org.jdesktop.beansbinding.ELProperty.create("${selectedElement.country}"), tfHotelCountry, org.jdesktop.beansbinding.BeanProperty.create("text"));
        bindingGroup.addBinding(binding);

        jLabel7.setText("Country:");

        binding = org.jdesktop.beansbinding.Bindings.createAutoBinding(org.jdesktop.beansbinding.AutoBinding.UpdateStrategy.READ_WRITE, jTableHotel, org.jdesktop.beansbinding.ELProperty.create("${selectedElement.contactNumber}"), tfHotelContactNo, org.jdesktop.beansbinding.BeanProperty.create("text"));
        bindingGroup.addBinding(binding);

        binding = org.jdesktop.beansbinding.Bindings.createAutoBinding(org.jdesktop.beansbinding.AutoBinding.UpdateStrategy.READ_WRITE, jTableHotel, org.jdesktop.beansbinding.ELProperty.create("${selectedElement.city}"), tfHotelCity, org.jdesktop.beansbinding.BeanProperty.create("text"));
        bindingGroup.addBinding(binding);

        jLabel8.setText("City: ");

        binding = org.jdesktop.beansbinding.Bindings.createAutoBinding(org.jdesktop.beansbinding.AutoBinding.UpdateStrategy.READ_WRITE, jTableHotel, org.jdesktop.beansbinding.ELProperty.create("${selectedElement.emailAddress}"), tfHotelEmail, org.jdesktop.beansbinding.BeanProperty.create("text"));
        bindingGroup.addBinding(binding);

        jLabel9.setText("Email:");

        binding = org.jdesktop.beansbinding.Bindings.createAutoBinding(org.jdesktop.beansbinding.AutoBinding.UpdateStrategy.READ_WRITE, jTableHotel, org.jdesktop.beansbinding.ELProperty.create("${selectedElement.address}"), tfHotelAddress, org.jdesktop.beansbinding.BeanProperty.create("text"));
        bindingGroup.addBinding(binding);

        jLabel10.setText("Contcat no:");

        tfHotelTier.setModel(new javax.swing.DefaultComboBoxModel<>(new String[]{"1 star", "2 star", "3 star", "4 star", "5 star"}));
        tfHotelTier.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                tfHotelTierActionPerformed(evt);
            }
        });

        jButtonInsertHotel.setText("Insert");
        jButtonInsertHotel.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonInsertHotelActionPerformed(evt);
            }
        });

        jButtonUpdate.setText("Update");
        jButtonUpdate.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonUpdateActionPerformed(evt);
            }
        });

        jButtonDelete.setText("Delete");
        jButtonDelete.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonDeleteActionPerformed(evt);
            }
        });

        hotelErrorMessage.setText(".");

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
                jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(jPanel2Layout.createSequentialGroup()
                                .addContainerGap()
                                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addGroup(jPanel2Layout.createSequentialGroup()
                                                .addComponent(jLabel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                                .addComponent(tfHotelId, javax.swing.GroupLayout.PREFERRED_SIZE, 355, javax.swing.GroupLayout.PREFERRED_SIZE))
                                        .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                                                .addComponent(jLabel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                                .addComponent(tfHotelNAme, javax.swing.GroupLayout.PREFERRED_SIZE, 355, javax.swing.GroupLayout.PREFERRED_SIZE))
                                        .addGroup(jPanel2Layout.createSequentialGroup()
                                                .addComponent(jLabel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                                .addGap(18, 18, 18)
                                                .addComponent(tfHotelTier, javax.swing.GroupLayout.PREFERRED_SIZE, 352, javax.swing.GroupLayout.PREFERRED_SIZE))
                                        .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                                                .addComponent(jLabel5, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                                .addComponent(tfHotelconsyear, javax.swing.GroupLayout.PREFERRED_SIZE, 355, javax.swing.GroupLayout.PREFERRED_SIZE))
                                        .addGroup(jPanel2Layout.createSequentialGroup()
                                                .addComponent(jLabel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                                .addComponent(tfHotelCapacity, javax.swing.GroupLayout.PREFERRED_SIZE, 355, javax.swing.GroupLayout.PREFERRED_SIZE))
                                        .addGroup(jPanel2Layout.createSequentialGroup()
                                                .addComponent(jLabel10, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                                .addComponent(tfHotelContactNo, javax.swing.GroupLayout.PREFERRED_SIZE, 355, javax.swing.GroupLayout.PREFERRED_SIZE))
                                        .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                                                .addComponent(jLabel9, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                                .addComponent(tfHotelEmail, javax.swing.GroupLayout.PREFERRED_SIZE, 355, javax.swing.GroupLayout.PREFERRED_SIZE))
                                        .addGroup(jPanel2Layout.createSequentialGroup()
                                                .addComponent(jLabel6, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                                .addComponent(tfHotelAddress, javax.swing.GroupLayout.PREFERRED_SIZE, 355, javax.swing.GroupLayout.PREFERRED_SIZE))
                                        .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                                                .addComponent(jLabel8, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                                .addComponent(tfHotelCity, javax.swing.GroupLayout.PREFERRED_SIZE, 355, javax.swing.GroupLayout.PREFERRED_SIZE))
                                        .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                                                .addComponent(jLabel7, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                                        .addComponent(hotelFieldsErrorMessage)
                                                        .addComponent(tfHotelCountry, javax.swing.GroupLayout.DEFAULT_SIZE, 355, Short.MAX_VALUE)
                                                        .addGroup(jPanel2Layout.createSequentialGroup()
                                                                .addComponent(jButtonInsertHotel, javax.swing.GroupLayout.PREFERRED_SIZE, 99, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                                .addGap(27, 27, 27)
                                                                .addComponent(jButtonUpdate, javax.swing.GroupLayout.PREFERRED_SIZE, 99, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                                                .addComponent(jButtonDelete, javax.swing.GroupLayout.PREFERRED_SIZE, 99, javax.swing.GroupLayout.PREFERRED_SIZE))
                                                        .addComponent(hotelErrorMessage, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
                                .addContainerGap())
        );
        jPanel2Layout.setVerticalGroup(
                jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(jPanel2Layout.createSequentialGroup()
                                .addGap(57, 57, 57)
                                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                        .addComponent(jLabel1)
                                        .addComponent(tfHotelId, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                        .addComponent(jLabel2)
                                        .addComponent(tfHotelNAme, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addComponent(jLabel3)
                                        .addComponent(tfHotelTier, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                        .addComponent(jLabel5)
                                        .addComponent(tfHotelconsyear, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                        .addComponent(jLabel4)
                                        .addComponent(tfHotelCapacity, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                        .addComponent(jLabel10)
                                        .addComponent(tfHotelContactNo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                        .addComponent(jLabel9)
                                        .addComponent(tfHotelEmail, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                        .addComponent(jLabel6)
                                        .addComponent(tfHotelAddress, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                        .addComponent(jLabel8)
                                        .addComponent(tfHotelCity, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                        .addComponent(jLabel7)
                                        .addComponent(tfHotelCountry, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(18, 18, 18)
                                .addComponent(hotelFieldsErrorMessage)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(hotelErrorMessage)
                                .addGap(18, 18, 18)
                                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                        .addComponent(jButtonInsertHotel)
                                        .addComponent(jButtonUpdate)
                                        .addComponent(jButtonDelete))
                                .addGap(53, 53, 53))
        );

        org.jdesktop.swingbinding.JTableBinding jTableBinding = org.jdesktop.swingbinding.SwingBindings.createJTableBinding(org.jdesktop.beansbinding.AutoBinding.UpdateStrategy.READ_WRITE, hotel_1List, jTableHotel);
        org.jdesktop.swingbinding.JTableBinding.ColumnBinding columnBinding = jTableBinding.addColumnBinding(org.jdesktop.beansbinding.ELProperty.create("${hotelId}"));
        columnBinding.setColumnName("Hotel Id");
        columnBinding.setColumnClass(java.math.BigDecimal.class);
        columnBinding = jTableBinding.addColumnBinding(org.jdesktop.beansbinding.ELProperty.create("${hotelName}"));
        columnBinding.setColumnName("Hotel Name");
        columnBinding.setColumnClass(String.class);
        columnBinding = jTableBinding.addColumnBinding(org.jdesktop.beansbinding.ELProperty.create("${hotelTier}"));
        columnBinding.setColumnName("Hotel Tier");
        columnBinding.setColumnClass(String.class);
        columnBinding = jTableBinding.addColumnBinding(org.jdesktop.beansbinding.ELProperty.create("${constructionYear}"));
        columnBinding.setColumnName("Construction Year");
        columnBinding.setColumnClass(String.class);
        columnBinding = jTableBinding.addColumnBinding(org.jdesktop.beansbinding.ELProperty.create("${roomCapacity}"));
        columnBinding.setColumnName("Room Capacity");
        columnBinding.setColumnClass(java.math.BigInteger.class);
        columnBinding = jTableBinding.addColumnBinding(org.jdesktop.beansbinding.ELProperty.create("${contactNumber}"));
        columnBinding.setColumnName("Contact Number");
        columnBinding.setColumnClass(String.class);
        columnBinding = jTableBinding.addColumnBinding(org.jdesktop.beansbinding.ELProperty.create("${emailAddress}"));
        columnBinding.setColumnName("Email Address");
        columnBinding.setColumnClass(String.class);
        columnBinding = jTableBinding.addColumnBinding(org.jdesktop.beansbinding.ELProperty.create("${address}"));
        columnBinding.setColumnName("Address");
        columnBinding.setColumnClass(String.class);
        columnBinding = jTableBinding.addColumnBinding(org.jdesktop.beansbinding.ELProperty.create("${city}"));
        columnBinding.setColumnName("City");
        columnBinding.setColumnClass(String.class);
        columnBinding = jTableBinding.addColumnBinding(org.jdesktop.beansbinding.ELProperty.create("${country}"));
        columnBinding.setColumnName("Country");
        columnBinding.setColumnClass(String.class);
        bindingGroup.addBinding(jTableBinding);
        jTableBinding.bind();

        jScrollPane1.setViewportView(jTableHotel);

        jLabel12.setText("Search by type: ");

        tfHotelTiercombo.setModel(new javax.swing.DefaultComboBoxModel<>(new String[]{"1 star", "2 star", "3 star", "4 star", "5 star"}));

        jButtonSearchHotelBy.setText("Search");
        jButtonSearchHotelBy.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonSearchHotelByActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
                jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 814, Short.MAX_VALUE)
                        .addGroup(jPanel3Layout.createSequentialGroup()
                                .addContainerGap()
                                .addComponent(jLabel12)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(tfHotelTiercombo, javax.swing.GroupLayout.PREFERRED_SIZE, 352, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(jButtonSearchHotelBy)
                                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel3Layout.setVerticalGroup(
                jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createSequentialGroup()
                                .addContainerGap()
                                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                        .addComponent(jLabel12)
                                        .addComponent(tfHotelTiercombo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addComponent(jButtonSearchHotelBy))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 497, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
                jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(jPanel1Layout.createSequentialGroup()
                                .addContainerGap()
                                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
                jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                                .addContainerGap()
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                        .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                        .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                .addContainerGap())
        );

        jTabbedPane1.addTab("Manage Hotel", jPanel1);

        jLabel13.setText("Room ID:");

        tfRoomId.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                tfRoomIdActionPerformed(evt);
            }
        });

        jLabel14.setText("Room Desc:");

        jLabel16.setText("Room capacity:");

        jLabel17.setText("Room Price:");

        jButtonInsertRoom.setText("Insert");
        jButtonInsertRoom.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonInsertRoomActionPerformed(evt);
            }
        });

        jButtonUpdateRoom.setText("Update");
        jButtonUpdateRoom.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonUpdateRoomActionPerformed(evt);
            }
        });

        jButtonDeleteRoom.setText("Delete");
        jButtonDeleteRoom.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonDeleteRoomActionPerformed(evt);
            }
        });

        RoomErrorMessage.setText(".");

        jLabel36.setText("Hotel ID:");

        tfRoomHotelId.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                tfRoomHotelIdActionPerformed(evt);
            }
        });

        jLabel46.setText("Room Type: ");
        jLabel46.setToolTipText("");

        jComboBox2.setModel(new javax.swing.DefaultComboBoxModel<>(new String[]{"Single", "Double"}));

        tfRoomCapacity.setModel(new javax.swing.DefaultComboBoxModel<>(new String[]{"1", "2", "3", "4", "5", "6", "7", "8", "9", "10"}));
        tfRoomCapacity.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                tfRoomCapacityActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel5Layout = new javax.swing.GroupLayout(jPanel5);
        jPanel5.setLayout(jPanel5Layout);
        jPanel5Layout.setHorizontalGroup(
                jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(jPanel5Layout.createSequentialGroup()
                                .addContainerGap()
                                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addGroup(jPanel5Layout.createSequentialGroup()
                                                .addComponent(jLabel13, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                                .addComponent(tfRoomId, javax.swing.GroupLayout.PREFERRED_SIZE, 355, javax.swing.GroupLayout.PREFERRED_SIZE))
                                        .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel5Layout.createSequentialGroup()
                                                .addComponent(jLabel36, javax.swing.GroupLayout.PREFERRED_SIZE, 117, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addGap(7, 7, 7)
                                                .addComponent(tfRoomHotelId, javax.swing.GroupLayout.PREFERRED_SIZE, 355, javax.swing.GroupLayout.PREFERRED_SIZE))
                                        .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                .addComponent(hotelFieldsErrorMessage1)
                                                .addGroup(jPanel5Layout.createSequentialGroup()
                                                        .addComponent(jButtonInsertRoom, javax.swing.GroupLayout.PREFERRED_SIZE, 99, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                        .addGap(27, 27, 27)
                                                        .addComponent(jButtonUpdateRoom, javax.swing.GroupLayout.PREFERRED_SIZE, 99, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                        .addGap(31, 31, 31)
                                                        .addComponent(jButtonDeleteRoom, javax.swing.GroupLayout.PREFERRED_SIZE, 99, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                        .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel5Layout.createSequentialGroup()
                                                .addComponent(jLabel17, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                                .addComponent(tfRoomPrice, javax.swing.GroupLayout.PREFERRED_SIZE, 355, javax.swing.GroupLayout.PREFERRED_SIZE))
                                        .addGroup(jPanel5Layout.createSequentialGroup()
                                                .addComponent(jLabel16, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                                .addComponent(tfRoomCapacity, javax.swing.GroupLayout.PREFERRED_SIZE, 355, javax.swing.GroupLayout.PREFERRED_SIZE))
                                        .addGroup(jPanel5Layout.createSequentialGroup()
                                                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                        .addGroup(jPanel5Layout.createSequentialGroup()
                                                                .addComponent(jLabel46, javax.swing.GroupLayout.PREFERRED_SIZE, 117, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                                .addGap(7, 7, 7))
                                                        .addGroup(jPanel5Layout.createSequentialGroup()
                                                                .addComponent(jLabel14, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)))
                                                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                        .addComponent(tfRoomDesc)
                                                        .addGroup(jPanel5Layout.createSequentialGroup()
                                                                .addComponent(jComboBox2, javax.swing.GroupLayout.PREFERRED_SIZE, 355, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                                .addGap(0, 0, Short.MAX_VALUE))))
                                        .addComponent(RoomErrorMessage, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 355, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addContainerGap())
        );
        jPanel5Layout.setVerticalGroup(
                jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(jPanel5Layout.createSequentialGroup()
                                .addGap(57, 57, 57)
                                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                        .addComponent(jLabel13)
                                        .addComponent(tfRoomId, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(18, 18, 18)
                                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                        .addComponent(jLabel36)
                                        .addComponent(tfRoomHotelId, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                        .addComponent(jLabel46)
                                        .addComponent(jComboBox2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                        .addComponent(jLabel14)
                                        .addComponent(tfRoomDesc, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                        .addComponent(jLabel17)
                                        .addComponent(tfRoomPrice, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                        .addComponent(jLabel16)
                                        .addComponent(tfRoomCapacity, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(131, 131, 131)
                                .addComponent(hotelFieldsErrorMessage1)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 69, Short.MAX_VALUE)
                                .addComponent(RoomErrorMessage)
                                .addGap(18, 18, 18)
                                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                        .addComponent(jButtonInsertRoom)
                                        .addComponent(jButtonUpdateRoom)
                                        .addComponent(jButtonDeleteRoom))
                                .addGap(53, 53, 53))
        );

        jLabel24.setText("Search by Facility: ");

        tfRoomFacilityCombo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                tfRoomFacilityComboActionPerformed(evt);
            }
        });

        jButtonSearchRoomBy.setText("Search");
        jButtonSearchRoomBy.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonSearchRoomByActionPerformed(evt);
            }
        });

        jTableBinding = org.jdesktop.swingbinding.SwingBindings.createJTableBinding(org.jdesktop.beansbinding.AutoBinding.UpdateStrategy.READ_WRITE, roomList, jTableRoom);
        columnBinding = jTableBinding.addColumnBinding(org.jdesktop.beansbinding.ELProperty.create("${roomPK}"));
        columnBinding.setColumnName("Room PK");
        columnBinding.setColumnClass(finalhbs.RoomPK.class);
        columnBinding = jTableBinding.addColumnBinding(org.jdesktop.beansbinding.ELProperty.create("${roomDescription}"));
        columnBinding.setColumnName("Room Description");
        columnBinding.setColumnClass(String.class);
        columnBinding = jTableBinding.addColumnBinding(org.jdesktop.beansbinding.ELProperty.create("${roomPrice}"));
        columnBinding.setColumnName("Room Price");
        columnBinding.setColumnClass(String.class);
        columnBinding = jTableBinding.addColumnBinding(org.jdesktop.beansbinding.ELProperty.create("${roomType}"));
        columnBinding.setColumnName("Room Type");
        columnBinding.setColumnClass(String.class);
        bindingGroup.addBinding(jTableBinding);
        jTableBinding.bind();

        jScrollPane3.setViewportView(jTableRoom);

        javax.swing.GroupLayout jPanel6Layout = new javax.swing.GroupLayout(jPanel6);
        jPanel6.setLayout(jPanel6Layout);
        jPanel6Layout.setHorizontalGroup(
                jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(jPanel6Layout.createSequentialGroup()
                                .addContainerGap()
                                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addComponent(jScrollPane3, javax.swing.GroupLayout.DEFAULT_SIZE, 820, Short.MAX_VALUE)
                                        .addGroup(jPanel6Layout.createSequentialGroup()
                                                .addComponent(jLabel24)
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                .addComponent(tfRoomFacilityCombo, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                                .addComponent(jButtonSearchRoomBy)
                                                .addContainerGap())))
        );
        jPanel6Layout.setVerticalGroup(
                jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel6Layout.createSequentialGroup()
                                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                        .addComponent(jButtonSearchRoomBy)
                                        .addComponent(tfRoomFacilityCombo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addComponent(jLabel24))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 487, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(45, 45, 45))
        );

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
                jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(jPanel4Layout.createSequentialGroup()
                                .addContainerGap()
                                .addComponent(jPanel5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(jPanel6, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addContainerGap())
        );
        jPanel4Layout.setVerticalGroup(
                jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel4Layout.createSequentialGroup()
                                .addContainerGap()
                                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                        .addComponent(jPanel6, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                        .addComponent(jPanel5, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                .addContainerGap())
        );

        jTabbedPane1.addTab("Manage Room", jPanel4);

        jLabel15.setText("Customer ID:");

        jLabel18.setText("Title:");

        jLabel19.setText("Membership Tier:");

        jLabel20.setText("Last name:");

        jLabel21.setText("First name:");

        jLabel22.setText("Street:");

        jLabel25.setText("Country:");

        jLabel26.setText("City: ");

        jLabel27.setText("Email:");

        jLabel28.setText("Contcat no:");

        comboCustomerMemberTier.setModel(new javax.swing.DefaultComboBoxModel<>(new String[]{"Platinum", "Gold", "Bronze"}));
        comboCustomerMemberTier.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                comboCustomerMemberTierActionPerformed(evt);
            }
        });

        jButtonInsertCutomer.setText("Insert");
        jButtonInsertCutomer.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonInsertCutomerActionPerformed(evt);
            }
        });

        jButtonUpdateCutomer.setText("Update");
        jButtonUpdateCutomer.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonUpdateCutomerActionPerformed(evt);
            }
        });

        jButtonDeleteCutomer.setText("Delete");
        jButtonDeleteCutomer.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonDeleteCutomerActionPerformed(evt);
            }
        });

        jLabel29.setText(".");

        jLabel31.setText("Post code:");

        jLabel32.setText("Credit:");

        jLabel33.setText("DOB:");

        javax.swing.GroupLayout jPanel8Layout = new javax.swing.GroupLayout(jPanel8);
        jPanel8.setLayout(jPanel8Layout);
        jPanel8Layout.setHorizontalGroup(
                jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(jPanel8Layout.createSequentialGroup()
                                .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addGroup(jPanel8Layout.createSequentialGroup()
                                                .addComponent(jLabel19, javax.swing.GroupLayout.PREFERRED_SIZE, 133, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                .addComponent(comboCustomerMemberTier, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                        .addGroup(jPanel8Layout.createSequentialGroup()
                                                .addContainerGap()
                                                .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                        .addGroup(jPanel8Layout.createSequentialGroup()
                                                                .addComponent(jLabel15, javax.swing.GroupLayout.PREFERRED_SIZE, 1, Short.MAX_VALUE)
                                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                                                .addComponent(tfCustomerID, javax.swing.GroupLayout.PREFERRED_SIZE, 355, javax.swing.GroupLayout.PREFERRED_SIZE))
                                                        .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel8Layout.createSequentialGroup()
                                                                .addComponent(jLabel18, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                                                .addComponent(tfCustomerTitle, javax.swing.GroupLayout.PREFERRED_SIZE, 355, javax.swing.GroupLayout.PREFERRED_SIZE))
                                                        .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel8Layout.createSequentialGroup()
                                                                .addComponent(jLabel21, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                                                .addComponent(tfCustomerFName, javax.swing.GroupLayout.PREFERRED_SIZE, 355, javax.swing.GroupLayout.PREFERRED_SIZE))
                                                        .addGroup(jPanel8Layout.createSequentialGroup()
                                                                .addComponent(jLabel20, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                                                .addComponent(tfCustomerLName, javax.swing.GroupLayout.PREFERRED_SIZE, 355, javax.swing.GroupLayout.PREFERRED_SIZE))
                                                        .addGroup(jPanel8Layout.createSequentialGroup()
                                                                .addComponent(jLabel28, javax.swing.GroupLayout.DEFAULT_SIZE, 92, Short.MAX_VALUE)
                                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                                                .addComponent(tfCustomerContactNo, javax.swing.GroupLayout.PREFERRED_SIZE, 355, javax.swing.GroupLayout.PREFERRED_SIZE))
                                                        .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel8Layout.createSequentialGroup()
                                                                .addComponent(jLabel27, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                                                .addComponent(tfCustomerEmail, javax.swing.GroupLayout.PREFERRED_SIZE, 355, javax.swing.GroupLayout.PREFERRED_SIZE))
                                                        .addGroup(jPanel8Layout.createSequentialGroup()
                                                                .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                                        .addComponent(hotelFieldsErrorMessage2)
                                                                        .addComponent(jLabel29))
                                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                                .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                                        .addGroup(jPanel8Layout.createSequentialGroup()
                                                                                .addComponent(jLabel22, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                                                                .addComponent(tfCustomerStreet, javax.swing.GroupLayout.PREFERRED_SIZE, 355, javax.swing.GroupLayout.PREFERRED_SIZE))
                                                                        .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel8Layout.createSequentialGroup()
                                                                                .addComponent(jLabel26, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                                                                .addComponent(tfCustomerCity, javax.swing.GroupLayout.PREFERRED_SIZE, 355, javax.swing.GroupLayout.PREFERRED_SIZE))
                                                                        .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel8Layout.createSequentialGroup()
                                                                                .addComponent(jLabel25, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                                                                .addComponent(tfCustomerCuntry, javax.swing.GroupLayout.PREFERRED_SIZE, 355, javax.swing.GroupLayout.PREFERRED_SIZE))))
                                                        .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel8Layout.createSequentialGroup()
                                                                .addGap(9, 9, 9)
                                                                .addComponent(jLabel31, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                                .addComponent(tfCustomerPostCode, javax.swing.GroupLayout.PREFERRED_SIZE, 355, javax.swing.GroupLayout.PREFERRED_SIZE))
                                                        .addGroup(jPanel8Layout.createSequentialGroup()
                                                                .addComponent(jLabel32, javax.swing.GroupLayout.PREFERRED_SIZE, 1, Short.MAX_VALUE)
                                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                                                .addComponent(tfCustomerCredit, javax.swing.GroupLayout.PREFERRED_SIZE, 355, javax.swing.GroupLayout.PREFERRED_SIZE))
                                                        .addGroup(jPanel8Layout.createSequentialGroup()
                                                                .addComponent(jLabel33, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                                                .addComponent(tfCustomerDOB, javax.swing.GroupLayout.PREFERRED_SIZE, 355, javax.swing.GroupLayout.PREFERRED_SIZE))))
                                        .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel8Layout.createSequentialGroup()
                                                .addGap(0, 0, Short.MAX_VALUE)
                                                .addComponent(jButtonInsertCutomer, javax.swing.GroupLayout.PREFERRED_SIZE, 99, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addGap(27, 27, 27)
                                                .addComponent(jButtonUpdateCutomer, javax.swing.GroupLayout.PREFERRED_SIZE, 99, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addGap(31, 31, 31)
                                                .addComponent(jButtonDeleteCutomer, javax.swing.GroupLayout.PREFERRED_SIZE, 99, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                .addContainerGap())
        );
        jPanel8Layout.setVerticalGroup(
                jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(jPanel8Layout.createSequentialGroup()
                                .addContainerGap()
                                .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                        .addComponent(jLabel15)
                                        .addComponent(tfCustomerID, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                        .addComponent(jLabel18)
                                        .addComponent(tfCustomerTitle, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                        .addComponent(jLabel21)
                                        .addComponent(tfCustomerFName, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                        .addComponent(jLabel20)
                                        .addComponent(tfCustomerLName, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                        .addComponent(jLabel28)
                                        .addComponent(tfCustomerContactNo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                        .addComponent(jLabel33)
                                        .addComponent(tfCustomerDOB, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(14, 14, 14)
                                .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                        .addComponent(jLabel27)
                                        .addComponent(tfCustomerEmail, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                        .addComponent(jLabel32)
                                        .addComponent(tfCustomerCredit, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addComponent(jLabel19)
                                        .addComponent(comboCustomerMemberTier, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addGroup(jPanel8Layout.createSequentialGroup()
                                                .addGap(78, 78, 78)
                                                .addComponent(hotelFieldsErrorMessage2)
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                                .addComponent(jLabel29))
                                        .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel8Layout.createSequentialGroup()
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                                .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                                        .addComponent(jLabel31)
                                                        .addComponent(tfCustomerPostCode, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                                        .addComponent(jLabel22)
                                                        .addComponent(tfCustomerStreet, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                                        .addComponent(jLabel26)
                                                        .addComponent(tfCustomerCity, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                                        .addComponent(jLabel25)
                                                        .addComponent(tfCustomerCuntry, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))))
                                .addGap(18, 18, Short.MAX_VALUE)
                                .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                        .addComponent(jButtonInsertCutomer)
                                        .addComponent(jButtonUpdateCutomer)
                                        .addComponent(jButtonDeleteCutomer))
                                .addGap(86, 86, 86))
        );

        jScrollPane2.setViewportView(jTableCustomer);

        jLabel30.setText("Search by membership: ");

        jButtonSearchCutomerBy.setText("Search");
        jButtonSearchCutomerBy.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonSearchCutomerByActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel9Layout = new javax.swing.GroupLayout(jPanel9);
        jPanel9.setLayout(jPanel9Layout);
        jPanel9Layout.setHorizontalGroup(
                jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 852, Short.MAX_VALUE)
                        .addGroup(jPanel9Layout.createSequentialGroup()
                                .addContainerGap()
                                .addComponent(jLabel30)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(tfHotelTiercombo1, javax.swing.GroupLayout.PREFERRED_SIZE, 352, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(jButtonSearchCutomerBy)
                                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel9Layout.setVerticalGroup(
                jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel9Layout.createSequentialGroup()
                                .addContainerGap()
                                .addGroup(jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                        .addComponent(jLabel30)
                                        .addComponent(tfHotelTiercombo1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addComponent(jButtonSearchCutomerBy))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 497, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        javax.swing.GroupLayout jPanel7Layout = new javax.swing.GroupLayout(jPanel7);
        jPanel7.setLayout(jPanel7Layout);
        jPanel7Layout.setHorizontalGroup(
                jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(jPanel7Layout.createSequentialGroup()
                                .addContainerGap()
                                .addComponent(jPanel8, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(jPanel9, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addContainerGap())
        );
        jPanel7Layout.setVerticalGroup(
                jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel7Layout.createSequentialGroup()
                                .addContainerGap()
                                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                        .addComponent(jPanel9, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                        .addComponent(jPanel8, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                .addContainerGap())
        );

        jTabbedPane1.addTab("Manage Customer", jPanel7);

        jLabel34.setText("Guest ID:");

        jLabel35.setText("Title:");

        jLabel37.setText("Last name:");

        jLabel38.setText("First name:");

        jLabel39.setText("Street:");

        jLabel40.setText("Country:");

        jLabel41.setText("City: ");

        jLabel42.setText("Email:");

        jLabel43.setText("Contcat no:");

        jButtonInsertCutomer1.setText("Insert");
        jButtonInsertCutomer1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonInsertCutomer1ActionPerformed(evt);
            }
        });

        jButtonUpdateCutomer1.setText("Update");
        jButtonUpdateCutomer1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonUpdateCutomer1ActionPerformed(evt);
            }
        });

        jButtonDeleteCutomer1.setText("Delete");
        jButtonDeleteCutomer1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonDeleteCutomer1ActionPerformed(evt);
            }
        });

        jLabel44.setText(".");

        jLabel45.setText("Post code:");

        jLabel47.setText("DOB:");

        javax.swing.GroupLayout jPanel11Layout = new javax.swing.GroupLayout(jPanel11);
        jPanel11.setLayout(jPanel11Layout);
        jPanel11Layout.setHorizontalGroup(
                jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(jPanel11Layout.createSequentialGroup()
                                .addGroup(jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addGroup(jPanel11Layout.createSequentialGroup()
                                                .addContainerGap()
                                                .addGroup(jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                        .addGroup(jPanel11Layout.createSequentialGroup()
                                                                .addComponent(jLabel34, javax.swing.GroupLayout.PREFERRED_SIZE, 109, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 16, Short.MAX_VALUE)
                                                                .addComponent(tfGuestID, javax.swing.GroupLayout.PREFERRED_SIZE, 355, javax.swing.GroupLayout.PREFERRED_SIZE))
                                                        .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel11Layout.createSequentialGroup()
                                                                .addComponent(jLabel35, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                                                .addComponent(tfGuestTitle, javax.swing.GroupLayout.PREFERRED_SIZE, 355, javax.swing.GroupLayout.PREFERRED_SIZE))
                                                        .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel11Layout.createSequentialGroup()
                                                                .addComponent(jLabel38, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                                                .addComponent(tfGuestFName, javax.swing.GroupLayout.PREFERRED_SIZE, 355, javax.swing.GroupLayout.PREFERRED_SIZE))
                                                        .addGroup(jPanel11Layout.createSequentialGroup()
                                                                .addComponent(jLabel37, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                                                .addComponent(tfGuestLName, javax.swing.GroupLayout.PREFERRED_SIZE, 355, javax.swing.GroupLayout.PREFERRED_SIZE))
                                                        .addGroup(jPanel11Layout.createSequentialGroup()
                                                                .addComponent(jLabel43, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                                                .addComponent(tfGuestContactNo, javax.swing.GroupLayout.PREFERRED_SIZE, 355, javax.swing.GroupLayout.PREFERRED_SIZE))
                                                        .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel11Layout.createSequentialGroup()
                                                                .addComponent(jLabel42, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                                                .addComponent(tfGuestEmail, javax.swing.GroupLayout.PREFERRED_SIZE, 355, javax.swing.GroupLayout.PREFERRED_SIZE))
                                                        .addGroup(jPanel11Layout.createSequentialGroup()
                                                                .addGroup(jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                                        .addComponent(hotelFieldsErrorMessage3)
                                                                        .addComponent(jLabel44))
                                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                                .addGroup(jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                                        .addGroup(jPanel11Layout.createSequentialGroup()
                                                                                .addComponent(jLabel39, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                                                                .addComponent(tfGuestStreet, javax.swing.GroupLayout.PREFERRED_SIZE, 355, javax.swing.GroupLayout.PREFERRED_SIZE))
                                                                        .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel11Layout.createSequentialGroup()
                                                                                .addComponent(jLabel41, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                                                                .addComponent(tfGuestCity, javax.swing.GroupLayout.PREFERRED_SIZE, 355, javax.swing.GroupLayout.PREFERRED_SIZE))
                                                                        .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel11Layout.createSequentialGroup()
                                                                                .addComponent(jLabel40, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                                                                .addComponent(tfGuestCuntry, javax.swing.GroupLayout.PREFERRED_SIZE, 355, javax.swing.GroupLayout.PREFERRED_SIZE))))
                                                        .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel11Layout.createSequentialGroup()
                                                                .addGap(9, 9, 9)
                                                                .addComponent(jLabel45, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                                .addComponent(tfGuestPostCode, javax.swing.GroupLayout.PREFERRED_SIZE, 355, javax.swing.GroupLayout.PREFERRED_SIZE))
                                                        .addGroup(jPanel11Layout.createSequentialGroup()
                                                                .addComponent(jLabel47, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                                                .addComponent(tfGuestDOB, javax.swing.GroupLayout.PREFERRED_SIZE, 355, javax.swing.GroupLayout.PREFERRED_SIZE))))
                                        .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel11Layout.createSequentialGroup()
                                                .addGap(0, 0, Short.MAX_VALUE)
                                                .addComponent(jButtonInsertCutomer1, javax.swing.GroupLayout.PREFERRED_SIZE, 99, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addGap(27, 27, 27)
                                                .addComponent(jButtonUpdateCutomer1, javax.swing.GroupLayout.PREFERRED_SIZE, 99, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addGap(31, 31, 31)
                                                .addComponent(jButtonDeleteCutomer1, javax.swing.GroupLayout.PREFERRED_SIZE, 99, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                .addContainerGap())
        );
        jPanel11Layout.setVerticalGroup(
                jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(jPanel11Layout.createSequentialGroup()
                                .addContainerGap()
                                .addGroup(jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                        .addComponent(jLabel34)
                                        .addComponent(tfGuestID, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                        .addComponent(jLabel35)
                                        .addComponent(tfGuestTitle, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                        .addComponent(jLabel38)
                                        .addComponent(tfGuestFName, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                        .addComponent(jLabel37)
                                        .addComponent(tfGuestLName, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                        .addComponent(jLabel43)
                                        .addComponent(tfGuestContactNo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                        .addComponent(jLabel47)
                                        .addComponent(tfGuestDOB, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(14, 14, 14)
                                .addGroup(jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                        .addComponent(jLabel42)
                                        .addComponent(tfGuestEmail, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(11, 11, 11)
                                .addGroup(jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addGroup(jPanel11Layout.createSequentialGroup()
                                                .addGap(78, 78, 78)
                                                .addComponent(hotelFieldsErrorMessage3)
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                                .addComponent(jLabel44))
                                        .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel11Layout.createSequentialGroup()
                                                .addGroup(jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                                        .addComponent(jLabel45)
                                                        .addComponent(tfGuestPostCode, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                .addGroup(jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                                        .addComponent(jLabel39)
                                                        .addComponent(tfGuestStreet, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                .addGroup(jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                                        .addComponent(jLabel41)
                                                        .addComponent(tfGuestCity, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                .addGroup(jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                                        .addComponent(jLabel40)
                                                        .addComponent(tfGuestCuntry, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))))
                                .addGap(18, 18, Short.MAX_VALUE)
                                .addGroup(jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                        .addComponent(jButtonInsertCutomer1)
                                        .addComponent(jButtonUpdateCutomer1)
                                        .addComponent(jButtonDeleteCutomer1))
                                .addGap(119, 119, 119))
        );

        jScrollPane4.setViewportView(jTableGuest);

        jLabel48.setText("Search by Name: ");

        jButtonSearchCutomerBy1.setText("Search");
        jButtonSearchCutomerBy1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonSearchCutomerBy1ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel12Layout = new javax.swing.GroupLayout(jPanel12);
        jPanel12.setLayout(jPanel12Layout);
        jPanel12Layout.setHorizontalGroup(
                jPanel12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(jScrollPane4, javax.swing.GroupLayout.DEFAULT_SIZE, 834, Short.MAX_VALUE)
                        .addGroup(jPanel12Layout.createSequentialGroup()
                                .addContainerGap()
                                .addComponent(jLabel48)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(tfSearchGuestByName, javax.swing.GroupLayout.PREFERRED_SIZE, 358, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(jButtonSearchCutomerBy1)
                                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel12Layout.setVerticalGroup(
                jPanel12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel12Layout.createSequentialGroup()
                                .addContainerGap()
                                .addGroup(jPanel12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                        .addComponent(jLabel48)
                                        .addComponent(jButtonSearchCutomerBy1)
                                        .addComponent(tfSearchGuestByName, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jScrollPane4, javax.swing.GroupLayout.PREFERRED_SIZE, 497, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout jPanel10Layout = new javax.swing.GroupLayout(jPanel10);
        jPanel10.setLayout(jPanel10Layout);
        jPanel10Layout.setHorizontalGroup(
                jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(jPanel10Layout.createSequentialGroup()
                                .addContainerGap()
                                .addComponent(jPanel11, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(jPanel12, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addContainerGap())
        );
        jPanel10Layout.setVerticalGroup(
                jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(jPanel10Layout.createSequentialGroup()
                                .addComponent(jPanel12, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(0, 0, Short.MAX_VALUE))
                        .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel10Layout.createSequentialGroup()
                                .addContainerGap(103, Short.MAX_VALUE)
                                .addComponent(jPanel11, javax.swing.GroupLayout.PREFERRED_SIZE, 456, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(81, 81, 81))
        );

        jTabbedPane1.addTab("Manage Guest", jPanel10);

        jLabel49.setText("membership Tier:");

        jLabel50.setText("Tier credit:");

        jLabel51.setText("Rewards:");

        jLabel52.setText("Discount:");

        jButtonInsertMembership.setText("Insert");
        jButtonInsertMembership.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonInsertMembershipActionPerformed(evt);
            }
        });

        jButtonUpdateMembership.setText("Update");
        jButtonUpdateMembership.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonUpdateMembershipActionPerformed(evt);
            }
        });

        jButtonDeleteMembership.setText("Delete");
        jButtonDeleteMembership.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonDeleteMembershipActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel14Layout = new javax.swing.GroupLayout(jPanel14);
        jPanel14.setLayout(jPanel14Layout);
        jPanel14Layout.setHorizontalGroup(
                jPanel14Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(jPanel14Layout.createSequentialGroup()
                                .addGroup(jPanel14Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addGroup(jPanel14Layout.createSequentialGroup()
                                                .addContainerGap()
                                                .addGroup(jPanel14Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                        .addGroup(jPanel14Layout.createSequentialGroup()
                                                                .addComponent(jLabel49)
                                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                                                .addComponent(tfMembershipTier, javax.swing.GroupLayout.PREFERRED_SIZE, 355, javax.swing.GroupLayout.PREFERRED_SIZE))
                                                        .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel14Layout.createSequentialGroup()
                                                                .addComponent(jLabel50, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                                                .addComponent(tfMembershipCredit, javax.swing.GroupLayout.PREFERRED_SIZE, 355, javax.swing.GroupLayout.PREFERRED_SIZE))
                                                        .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel14Layout.createSequentialGroup()
                                                                .addComponent(jLabel52, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                                                .addComponent(tfMembershipDiscount, javax.swing.GroupLayout.PREFERRED_SIZE, 355, javax.swing.GroupLayout.PREFERRED_SIZE))
                                                        .addGroup(jPanel14Layout.createSequentialGroup()
                                                                .addComponent(jLabel51, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                                                .addComponent(tfMembershipRewards, javax.swing.GroupLayout.PREFERRED_SIZE, 355, javax.swing.GroupLayout.PREFERRED_SIZE))
                                                        .addGroup(jPanel14Layout.createSequentialGroup()
                                                                .addComponent(hotelFieldsErrorMessage4)
                                                                .addGap(0, 0, Short.MAX_VALUE))))
                                        .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel14Layout.createSequentialGroup()
                                                .addGap(0, 0, Short.MAX_VALUE)
                                                .addComponent(jButtonInsertMembership, javax.swing.GroupLayout.PREFERRED_SIZE, 99, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addGap(27, 27, 27)
                                                .addComponent(jButtonUpdateMembership, javax.swing.GroupLayout.PREFERRED_SIZE, 99, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addGap(31, 31, 31)
                                                .addComponent(jButtonDeleteMembership, javax.swing.GroupLayout.PREFERRED_SIZE, 99, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                .addContainerGap())
        );
        jPanel14Layout.setVerticalGroup(
                jPanel14Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(jPanel14Layout.createSequentialGroup()
                                .addContainerGap()
                                .addGroup(jPanel14Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                        .addComponent(jLabel49)
                                        .addComponent(tfMembershipTier, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(jPanel14Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                        .addComponent(jLabel50)
                                        .addComponent(tfMembershipCredit, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(jPanel14Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                        .addComponent(jLabel52)
                                        .addComponent(tfMembershipDiscount, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(jPanel14Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                        .addComponent(jLabel51)
                                        .addComponent(tfMembershipRewards, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(199, 199, 199)
                                .addComponent(hotelFieldsErrorMessage4)
                                .addGap(18, 18, Short.MAX_VALUE)
                                .addGroup(jPanel14Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                        .addComponent(jButtonInsertMembership)
                                        .addComponent(jButtonUpdateMembership)
                                        .addComponent(jButtonDeleteMembership))
                                .addGap(119, 119, 119))
        );

        jScrollPane5.setViewportView(jTableMembership);

        jLabel61.setText("search eligible membership by  available credits:");

        jButtonSearchMembershipBy.setText("Search");
        jButtonSearchMembershipBy.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonSearchMembershipByActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel15Layout = new javax.swing.GroupLayout(jPanel15);
        jPanel15.setLayout(jPanel15Layout);
        jPanel15Layout.setHorizontalGroup(
                jPanel15Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(jScrollPane5, javax.swing.GroupLayout.DEFAULT_SIZE, 834, Short.MAX_VALUE)
                        .addGroup(jPanel15Layout.createSequentialGroup()
                                .addContainerGap()
                                .addComponent(jLabel61)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(jButtonSearchMembershipBy)
                                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel15Layout.setVerticalGroup(
                jPanel15Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel15Layout.createSequentialGroup()
                                .addContainerGap()
                                .addGroup(jPanel15Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                        .addComponent(jLabel61)
                                        .addComponent(jButtonSearchMembershipBy))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jScrollPane5, javax.swing.GroupLayout.PREFERRED_SIZE, 497, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout jPanel13Layout = new javax.swing.GroupLayout(jPanel13);
        jPanel13.setLayout(jPanel13Layout);
        jPanel13Layout.setHorizontalGroup(
                jPanel13Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(jPanel13Layout.createSequentialGroup()
                                .addContainerGap()
                                .addComponent(jPanel14, javax.swing.GroupLayout.PREFERRED_SIZE, 510, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(jPanel15, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addContainerGap())
        );
        jPanel13Layout.setVerticalGroup(
                jPanel13Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(jPanel13Layout.createSequentialGroup()
                                .addComponent(jPanel15, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(0, 0, Short.MAX_VALUE))
                        .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel13Layout.createSequentialGroup()
                                .addContainerGap(103, Short.MAX_VALUE)
                                .addComponent(jPanel14, javax.swing.GroupLayout.PREFERRED_SIZE, 456, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(81, 81, 81))
        );

        jTabbedPane1.addTab("Manage Membership", jPanel13);

        jScrollPane6.setViewportView(jTableBooking);

        javax.swing.GroupLayout jPanel18Layout = new javax.swing.GroupLayout(jPanel18);
        jPanel18.setLayout(jPanel18Layout);
        jPanel18Layout.setHorizontalGroup(
                jPanel18Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(jScrollPane6, javax.swing.GroupLayout.DEFAULT_SIZE, 829, Short.MAX_VALUE)
        );
        jPanel18Layout.setVerticalGroup(
                jPanel18Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel18Layout.createSequentialGroup()
                                .addGap(54, 54, 54)
                                .addComponent(jScrollPane6, javax.swing.GroupLayout.PREFERRED_SIZE, 497, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jLabel53.setText("Booking number:");

        jLabel54.setText("Hotel ID:");

        jButtonInsertMembership1.setText("Insert");
        jButtonInsertMembership1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonInsertMembership1ActionPerformed(evt);
            }
        });

        jButtonUpdateMembership1.setText("Update");
        jButtonUpdateMembership1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonUpdateMembership1ActionPerformed(evt);
            }
        });

        jButtonDeleteMembership1.setText("Delete");
        jButtonDeleteMembership1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonDeleteMembership1ActionPerformed(evt);
            }
        });

        jLabel56.setText("Room number:");

        jLabel57.setText("Customer number:");

        jLabel55.setText("Check-in date");

        jLabel58.setText("Check-out date:");

        jLabel59.setText("Contact person:");

        jLabel60.setText("Contact Email:");

        jLabel63.setText("Total amount:");

        jLabel64.setText("Discount amount:");

        jLabel65.setText("Payment Status:");

        javax.swing.GroupLayout jPanel17Layout = new javax.swing.GroupLayout(jPanel17);
        jPanel17.setLayout(jPanel17Layout);
        jPanel17Layout.setHorizontalGroup(
                jPanel17Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel17Layout.createSequentialGroup()
                                .addGroup(jPanel17Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                        .addGroup(jPanel17Layout.createSequentialGroup()
                                                .addComponent(jButtonInsertMembership1, javax.swing.GroupLayout.PREFERRED_SIZE, 99, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addGap(27, 27, 27)
                                                .addComponent(jButtonUpdateMembership1, javax.swing.GroupLayout.PREFERRED_SIZE, 99, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addGap(31, 31, 31)
                                                .addComponent(jButtonDeleteMembership1, javax.swing.GroupLayout.PREFERRED_SIZE, 99, javax.swing.GroupLayout.PREFERRED_SIZE))
                                        .addGroup(jPanel17Layout.createSequentialGroup()
                                                .addComponent(jLabel63)
                                                .addGap(38, 38, 38)
                                                .addComponent(tfBookingTotalAmount))
                                        .addGroup(jPanel17Layout.createSequentialGroup()
                                                .addGroup(jPanel17Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                        .addComponent(jLabel53)
                                                        .addComponent(jLabel54, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                                        .addComponent(jLabel56, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                                        .addComponent(jLabel57))
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                .addGroup(jPanel17Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                                        .addComponent(tfBookingNumber, javax.swing.GroupLayout.DEFAULT_SIZE, 355, Short.MAX_VALUE)
                                                        .addComponent(tfBookingHotelID, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                                        .addComponent(tfBookingRoomNo, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                                        .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel17Layout.createSequentialGroup()
                                                                .addGap(1, 1, 1)
                                                                .addComponent(tfBookingCustNo, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
                                        .addGroup(jPanel17Layout.createSequentialGroup()
                                                .addGroup(jPanel17Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                        .addComponent(jLabel58)
                                                        .addComponent(jLabel59)
                                                        .addComponent(jLabel60)
                                                        .addComponent(jLabel55))
                                                .addGap(18, 18, 18)
                                                .addGroup(jPanel17Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                                        .addComponent(tfBookingContactEmail, javax.swing.GroupLayout.DEFAULT_SIZE, 354, Short.MAX_VALUE)
                                                        .addComponent(tfBookingCheckOutDate)
                                                        .addComponent(tfBookingCheckInDate)
                                                        .addComponent(tfBookingContactPerson, javax.swing.GroupLayout.Alignment.TRAILING)))
                                        .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel17Layout.createSequentialGroup()
                                                .addComponent(jLabel64)
                                                .addGap(13, 13, 13)
                                                .addComponent(tfBookingDiscount))
                                        .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel17Layout.createSequentialGroup()
                                                .addContainerGap()
                                                .addComponent(hotelFieldsErrorMessage5)
                                                .addGap(0, 0, Short.MAX_VALUE))
                                        .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel17Layout.createSequentialGroup()
                                                .addComponent(jLabel65)
                                                .addGap(30, 30, 30)
                                                .addComponent(tfBookingPaymentStatus, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                                .addGap(86, 86, 86))
        );
        jPanel17Layout.setVerticalGroup(
                jPanel17Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(jPanel17Layout.createSequentialGroup()
                                .addContainerGap()
                                .addGroup(jPanel17Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                        .addComponent(jLabel53)
                                        .addComponent(tfBookingNumber, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(18, 18, 18)
                                .addGroup(jPanel17Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                        .addComponent(tfBookingHotelID, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addComponent(jLabel54))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(jPanel17Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                        .addComponent(jLabel56)
                                        .addComponent(tfBookingRoomNo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(jPanel17Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                        .addComponent(tfBookingCustNo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addComponent(jLabel57))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addGroup(jPanel17Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                        .addComponent(jLabel55)
                                        .addComponent(tfBookingCheckInDate, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(jPanel17Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                        .addComponent(jLabel58)
                                        .addComponent(tfBookingCheckOutDate, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(jPanel17Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                        .addComponent(jLabel59)
                                        .addComponent(tfBookingContactPerson, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(jPanel17Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                        .addComponent(jLabel60)
                                        .addComponent(tfBookingContactEmail, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(jPanel17Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                        .addComponent(jLabel63)
                                        .addComponent(tfBookingTotalAmount, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(8, 8, 8)
                                .addGroup(jPanel17Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                        .addComponent(jLabel64)
                                        .addComponent(tfBookingDiscount, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(hotelFieldsErrorMessage5)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(jPanel17Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                        .addComponent(tfBookingPaymentStatus, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addComponent(jLabel65))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 45, Short.MAX_VALUE)
                                .addGroup(jPanel17Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                        .addComponent(jButtonInsertMembership1)
                                        .addComponent(jButtonUpdateMembership1)
                                        .addComponent(jButtonDeleteMembership1))
                                .addContainerGap())
        );

        javax.swing.GroupLayout jPanel19Layout = new javax.swing.GroupLayout(jPanel19);
        jPanel19.setLayout(jPanel19Layout);
        jPanel19Layout.setHorizontalGroup(
                jPanel19Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(jPanel19Layout.createSequentialGroup()
                                .addComponent(jPanel17, javax.swing.GroupLayout.PREFERRED_SIZE, 510, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(0, 0, Short.MAX_VALUE))
        );
        jPanel19Layout.setVerticalGroup(
                jPanel19Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(jPanel19Layout.createSequentialGroup()
                                .addComponent(jPanel17, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(0, 0, Short.MAX_VALUE))
        );

        jTabbedPane2.addTab("Manage Booking", jPanel19);

        jButtonSearchMembershipBy1.setText("Search");
        jButtonSearchMembershipBy1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonSearchMembershipBy1ActionPerformed(evt);
            }
        });

        jLabel11.setText("Search available room by:");

        jLabel23.setText("Check-in date:");

        jLabel74.setText("Check-out date:");

        jLabel75.setText("Occupancy:");

        jLabel76.setText("Room type:");

        jLabel77.setText("Rate range:");

        jLabel78.setText("From:");

        jLabel79.setText("To:");

        jComboBox1.setModel(new javax.swing.DefaultComboBoxModel<>(new String[]{"Single", "Double", "Suite"}));

        javax.swing.GroupLayout jPanel20Layout = new javax.swing.GroupLayout(jPanel20);
        jPanel20.setLayout(jPanel20Layout);
        jPanel20Layout.setHorizontalGroup(
                jPanel20Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(jPanel20Layout.createSequentialGroup()
                                .addContainerGap()
                                .addGroup(jPanel20Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addGroup(jPanel20Layout.createSequentialGroup()
                                                .addComponent(jLabel11)
                                                .addGap(0, 0, Short.MAX_VALUE))
                                        .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel20Layout.createSequentialGroup()
                                                .addGap(0, 13, Short.MAX_VALUE)
                                                .addGroup(jPanel20Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                                        .addComponent(jButtonSearchMembershipBy1, javax.swing.GroupLayout.Alignment.TRAILING)
                                                        .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel20Layout.createSequentialGroup()
                                                                .addComponent(jLabel23, javax.swing.GroupLayout.PREFERRED_SIZE, 125, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                                                .addComponent(jTextField1, javax.swing.GroupLayout.PREFERRED_SIZE, 327, javax.swing.GroupLayout.PREFERRED_SIZE))
                                                        .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel20Layout.createSequentialGroup()
                                                                .addComponent(jLabel74, javax.swing.GroupLayout.PREFERRED_SIZE, 125, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                                                .addComponent(jTextField2, javax.swing.GroupLayout.PREFERRED_SIZE, 327, javax.swing.GroupLayout.PREFERRED_SIZE))
                                                        .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel20Layout.createSequentialGroup()
                                                                .addComponent(jLabel75, javax.swing.GroupLayout.PREFERRED_SIZE, 125, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                                                .addComponent(jTextField3, javax.swing.GroupLayout.PREFERRED_SIZE, 327, javax.swing.GroupLayout.PREFERRED_SIZE))
                                                        .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel20Layout.createSequentialGroup()
                                                                .addGroup(jPanel20Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                                        .addGroup(jPanel20Layout.createSequentialGroup()
                                                                                .addComponent(jLabel77, javax.swing.GroupLayout.PREFERRED_SIZE, 125, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                                                .addGap(0, 0, Short.MAX_VALUE))
                                                                        .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel20Layout.createSequentialGroup()
                                                                                .addGap(0, 0, Short.MAX_VALUE)
                                                                                .addComponent(jLabel76, javax.swing.GroupLayout.PREFERRED_SIZE, 125, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                                                .addGap(18, 18, 18)
                                                                .addGroup(jPanel20Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                                                        .addGroup(jPanel20Layout.createSequentialGroup()
                                                                                .addComponent(jLabel78)
                                                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                                                .addComponent(jTextField5, javax.swing.GroupLayout.PREFERRED_SIZE, 90, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                                                .addGap(26, 26, 26)
                                                                                .addComponent(jLabel79)
                                                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                                                .addComponent(jTextField6, javax.swing.GroupLayout.PREFERRED_SIZE, 90, javax.swing.GroupLayout.PREFERRED_SIZE))
                                                                        .addComponent(jComboBox1, javax.swing.GroupLayout.PREFERRED_SIZE, 324, javax.swing.GroupLayout.PREFERRED_SIZE))))))
                                .addContainerGap())
        );
        jPanel20Layout.setVerticalGroup(
                jPanel20Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel20Layout.createSequentialGroup()
                                .addGap(27, 27, 27)
                                .addComponent(jLabel11)
                                .addGap(18, 18, 18)
                                .addGroup(jPanel20Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                        .addComponent(jTextField1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addComponent(jLabel23))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(jPanel20Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                        .addComponent(jTextField2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addComponent(jLabel74))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(jPanel20Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                        .addComponent(jTextField3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addComponent(jLabel75))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(jPanel20Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addComponent(jLabel76)
                                        .addComponent(jComboBox1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(18, 18, 18)
                                .addGroup(jPanel20Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                        .addComponent(jTextField5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addComponent(jLabel77)
                                        .addComponent(jTextField6, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addComponent(jLabel78)
                                        .addComponent(jLabel79))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 219, Short.MAX_VALUE)
                                .addComponent(jButtonSearchMembershipBy1)
                                .addContainerGap())
        );

        jTabbedPane2.addTab("Search Availability", jPanel20);

        javax.swing.GroupLayout jPanel16Layout = new javax.swing.GroupLayout(jPanel16);
        jPanel16.setLayout(jPanel16Layout);
        jPanel16Layout.setHorizontalGroup(
                jPanel16Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(jPanel16Layout.createSequentialGroup()
                                .addContainerGap()
                                .addComponent(jTabbedPane2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(jPanel18, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addContainerGap())
        );
        jPanel16Layout.setVerticalGroup(
                jPanel16Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(jPanel16Layout.createSequentialGroup()
                                .addComponent(jPanel18, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(0, 73, Short.MAX_VALUE))
                        .addGroup(jPanel16Layout.createSequentialGroup()
                                .addContainerGap()
                                .addComponent(jTabbedPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 538, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jTabbedPane1.addTab("Manage Booking", jPanel16);

        jLabel62.setText("Payment Number:");

        jLabel66.setText("Booking Number:");

        jButtonInsertMembership2.setText("Insert");
        jButtonInsertMembership2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonInsertMembership2ActionPerformed(evt);
            }
        });

        jButtonUpdateMembership2.setText("Update");
        jButtonUpdateMembership2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonUpdateMembership2ActionPerformed(evt);
            }
        });

        jButtonDeleteMembership2.setText("Delete");
        jButtonDeleteMembership2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonDeleteMembership2ActionPerformed(evt);
            }
        });

        jLabel68.setText("Hotel ID:");

        jLabel67.setText("Room number:");

        jLabel69.setText("Payment Date:");

        jLabel70.setText("Payment Method:");

        jLabel71.setText("Payment Amount:");

        tfPaymentMethod.setModel(new javax.swing.DefaultComboBoxModel<>(new String[]{"Credit card", "Cash", "PayPal"}));

        jLabel72.setText(".");

        jLabel73.setText(".");

        javax.swing.GroupLayout jPanel22Layout = new javax.swing.GroupLayout(jPanel22);
        jPanel22.setLayout(jPanel22Layout);
        jPanel22Layout.setHorizontalGroup(
                jPanel22Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(jPanel22Layout.createSequentialGroup()
                                .addContainerGap()
                                .addGroup(jPanel22Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addGroup(jPanel22Layout.createSequentialGroup()
                                                .addComponent(jLabel62)
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                                .addComponent(tfPaymentNo, javax.swing.GroupLayout.PREFERRED_SIZE, 355, javax.swing.GroupLayout.PREFERRED_SIZE))
                                        .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel22Layout.createSequentialGroup()
                                                .addComponent(jLabel66, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                .addComponent(tfPaymentBookingNo, javax.swing.GroupLayout.PREFERRED_SIZE, 355, javax.swing.GroupLayout.PREFERRED_SIZE))
                                        .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel22Layout.createSequentialGroup()
                                                .addComponent(jLabel68, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                .addComponent(tfPaymentHotelID, javax.swing.GroupLayout.PREFERRED_SIZE, 355, javax.swing.GroupLayout.PREFERRED_SIZE))
                                        .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel22Layout.createSequentialGroup()
                                                .addComponent(jLabel67)
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                                .addComponent(tfPaymentRoomNo, javax.swing.GroupLayout.PREFERRED_SIZE, 355, javax.swing.GroupLayout.PREFERRED_SIZE))
                                        .addGroup(jPanel22Layout.createSequentialGroup()
                                                .addComponent(jLabel69)
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                                .addComponent(tfPaymentDate, javax.swing.GroupLayout.PREFERRED_SIZE, 355, javax.swing.GroupLayout.PREFERRED_SIZE))
                                        .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel22Layout.createSequentialGroup()
                                                .addGap(0, 0, Short.MAX_VALUE)
                                                .addComponent(jButtonInsertMembership2, javax.swing.GroupLayout.PREFERRED_SIZE, 99, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addGap(27, 27, 27)
                                                .addComponent(jButtonUpdateMembership2, javax.swing.GroupLayout.PREFERRED_SIZE, 99, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addGap(31, 31, 31)
                                                .addComponent(jButtonDeleteMembership2, javax.swing.GroupLayout.PREFERRED_SIZE, 99, javax.swing.GroupLayout.PREFERRED_SIZE))
                                        .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel22Layout.createSequentialGroup()
                                                .addComponent(jLabel70, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                                .addGap(15, 15, 15)
                                                .addComponent(tfPaymentMethod, javax.swing.GroupLayout.PREFERRED_SIZE, 355, javax.swing.GroupLayout.PREFERRED_SIZE))
                                        .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel22Layout.createSequentialGroup()
                                                .addGroup(jPanel22Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                        .addComponent(jLabel71, javax.swing.GroupLayout.PREFERRED_SIZE, 1, Short.MAX_VALUE)
                                                        .addGroup(jPanel22Layout.createSequentialGroup()
                                                                .addComponent(hotelFieldsErrorMessage6)
                                                                .addGap(0, 0, Short.MAX_VALUE)))
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                                .addGroup(jPanel22Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                        .addComponent(jLabel73, javax.swing.GroupLayout.PREFERRED_SIZE, 62, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                        .addComponent(tfPaymentAmount, javax.swing.GroupLayout.PREFERRED_SIZE, 355, javax.swing.GroupLayout.PREFERRED_SIZE))))
                                .addContainerGap())
                        .addGroup(jPanel22Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addGroup(jPanel22Layout.createSequentialGroup()
                                        .addGap(259, 259, 259)
                                        .addComponent(jLabel72)
                                        .addContainerGap(260, Short.MAX_VALUE)))
        );
        jPanel22Layout.setVerticalGroup(
                jPanel22Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(jPanel22Layout.createSequentialGroup()
                                .addContainerGap()
                                .addGroup(jPanel22Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                        .addComponent(jLabel62)
                                        .addComponent(tfPaymentNo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(jPanel22Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                        .addComponent(jLabel66)
                                        .addComponent(tfPaymentBookingNo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addGroup(jPanel22Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                        .addComponent(tfPaymentHotelID, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addComponent(jLabel68))
                                .addGap(18, 18, 18)
                                .addGroup(jPanel22Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                        .addComponent(tfPaymentRoomNo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addComponent(jLabel67))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addGroup(jPanel22Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                        .addComponent(jLabel69)
                                        .addComponent(tfPaymentDate, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(jPanel22Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                        .addComponent(jLabel70)
                                        .addComponent(tfPaymentMethod, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(jPanel22Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                        .addComponent(jLabel71)
                                        .addComponent(tfPaymentAmount, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGroup(jPanel22Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addGroup(jPanel22Layout.createSequentialGroup()
                                                .addGap(71, 71, 71)
                                                .addComponent(hotelFieldsErrorMessage6)
                                                .addGap(18, 18, Short.MAX_VALUE))
                                        .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel22Layout.createSequentialGroup()
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                                .addComponent(jLabel73, javax.swing.GroupLayout.PREFERRED_SIZE, 69, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)))
                                .addGroup(jPanel22Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                        .addComponent(jButtonInsertMembership2)
                                        .addComponent(jButtonUpdateMembership2)
                                        .addComponent(jButtonDeleteMembership2))
                                .addGap(119, 119, 119))
                        .addGroup(jPanel22Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addGroup(jPanel22Layout.createSequentialGroup()
                                        .addGap(246, 246, 246)
                                        .addComponent(jLabel72)
                                        .addContainerGap(246, Short.MAX_VALUE)))
        );

        jScrollPane7.setViewportView(jTablePayment);

        javax.swing.GroupLayout jPanel23Layout = new javax.swing.GroupLayout(jPanel23);
        jPanel23.setLayout(jPanel23Layout);
        jPanel23Layout.setHorizontalGroup(
                jPanel23Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(jScrollPane7, javax.swing.GroupLayout.DEFAULT_SIZE, 834, Short.MAX_VALUE)
        );
        jPanel23Layout.setVerticalGroup(
                jPanel23Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel23Layout.createSequentialGroup()
                                .addGap(54, 54, 54)
                                .addComponent(jScrollPane7, javax.swing.GroupLayout.PREFERRED_SIZE, 497, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout jPanel21Layout = new javax.swing.GroupLayout(jPanel21);
        jPanel21.setLayout(jPanel21Layout);
        jPanel21Layout.setHorizontalGroup(
                jPanel21Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(jPanel21Layout.createSequentialGroup()
                                .addContainerGap()
                                .addComponent(jPanel22, javax.swing.GroupLayout.PREFERRED_SIZE, 510, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(jPanel23, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addContainerGap())
        );
        jPanel21Layout.setVerticalGroup(
                jPanel21Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(jPanel21Layout.createSequentialGroup()
                                .addComponent(jPanel23, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(0, 0, Short.MAX_VALUE))
                        .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel21Layout.createSequentialGroup()
                                .addContainerGap(103, Short.MAX_VALUE)
                                .addComponent(jPanel22, javax.swing.GroupLayout.PREFERRED_SIZE, 456, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(81, 81, 81))
        );

        jTabbedPane1.addTab("Manage Payment", jPanel21);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
                layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(jTabbedPane1)
        );
        layout.setVerticalGroup(
                layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(jTabbedPane1)
        );

        bindingGroup.bind();

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jButtonInsertHotelActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonInsertHotelActionPerformed
        // TODO add your handling code here:
        if (!tfHotelNAme.getText().trim().equals("") && !tfHotelCapacity.getText().trim().equals("")
                && !tfHotelEmail.getText().trim().equals("") && !tfHotelId.getText().trim().equals("")
                && !tfHotelContactNo.getText().trim().equals("") && !tfHotelAddress.getText().trim().equals("")
                && !tfHotelCity.getText().trim().equals("") && !tfHotelCountry.getText().trim().equals("")) {
            Statement stmt;
            try {

                stmt = conn.createStatement();
                stmt.executeUpdate("insert into hotel (HOTEL_ID, HOTEL_NAME, ROOM_CAPACITY,HOTEL_TIER, CONSTRUCTION_YEAR,EMAIL_ADDRESS, CONTACT_NUMBER, ADDRESS,CITY,COUNTRY ) VALUES ('" + tfHotelId.getText() + "','" + tfHotelNAme.getText() + "','" + tfHotelCapacity.getText() + "','" + tfHotelTier.getSelectedItem() + "','" + tfHotelconsyear.getText() + "','" + tfHotelEmail.getText() + "','" + tfHotelContactNo.getText() + "','" + tfHotelAddress.getText() + "','" + tfHotelCity.getText() + "','" + tfHotelCountry.getText() + "')");
                this.updateHotelTable();
                JOptionPane.showMessageDialog(null, "Inserted Successfully!");
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        } else {
            hotelFieldsErrorMessage.setText("No fields can be empty");
        }
    }//GEN-LAST:event_jButtonInsertHotelActionPerformed

    private void jButtonUpdateActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonUpdateActionPerformed
        // TODO add your handling code here:
        if (this.jTableHotel.getSelectedRow() < 0) {
            return;
        }
        if (!tfHotelNAme.getText().trim().equals("") && !tfHotelCapacity.getText().trim().equals("")
                && !tfHotelEmail.getText().trim().equals("") && !tfHotelId.getText().trim().equals("")
                && !tfHotelContactNo.getText().trim().equals("") && !tfHotelAddress.getText().trim().equals("")
                && !tfHotelCity.getText().trim().equals("") && !tfHotelCountry.getText().trim().equals("")) {

            Statement stmt;
            try {

                stmt = conn.createStatement();
                stmt.executeUpdate("update HOTEL set  HOTEL_NAME='" + tfHotelNAme.getText() + "', HOTEL_TIER='" + tfHotelTier.getSelectedItem().toString() + "', CONSTRUCTION_YEAR='" + tfHotelconsyear.getText() + "', ROOM_CAPACITY='" + tfHotelCapacity.getText() + "', EMAIL_ADDRESS='" + tfHotelEmail.getText() + "',CONTACT_NUMBER='" + tfHotelContactNo.getText() + "',  ADDRESS='" + tfHotelAddress.getText() + "',  CITY ='" + tfHotelCity.getText() + "',  COUNTRY='" + tfHotelCountry.getText() + "' where HOTEL_ID='" + tfHotelId.getText() + "'");
                Hotel_1 hotel = this.hotel_1List.get(this.jTableHotel.getSelectedRow());
                hotel.setHotelName(tfHotelNAme.getText());
                this.entityManager.refresh(hotel);
                updateHotelTable();

                JOptionPane.showMessageDialog(null, "Updated Successfully!");
            } catch (Exception ex) {
                ex.printStackTrace();
            }

        } else {
            hotelErrorMessage.setText("No fields can be empty");
        }
    }//GEN-LAST:event_jButtonUpdateActionPerformed

    private void updateHotelTable() {
        hotel_1Query = java.beans.Beans.isDesignTime() ? null : entityManager.createQuery("SELECT h FROM Hotel_1 h");

        hotel_1List = java.beans.Beans.isDesignTime() ? java.util.Collections.emptyList() : hotel_1Query.getResultList();
        tableModel.fireTableDataChanged();
    }

    private void updateRoomTable() {
        roomQuery = java.beans.Beans.isDesignTime() ? null : entityManager0.createQuery("SELECT r FROM Room r");
        roomList = java.beans.Beans.isDesignTime() ? java.util.Collections.emptyList() : roomQuery.getResultList();
        roomTableModel.fireTableDataChanged();
    }

    private void updateCustomerTable() {

        tableModel.fireTableDataChanged();
    }

    private void jButtonDeleteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonDeleteActionPerformed
        Statement stmt;
        try {

            stmt = conn.createStatement();
            stmt.executeUpdate("delete from HOTEL where HOTEL_ID='" + tfHotelId.getText() + "'");
            updateHotelTable();
            JOptionPane.showMessageDialog(null, "Deleted Successfully!");
        } catch (Exception ex) {
            ex.printStackTrace();
        }

    }//GEN-LAST:event_jButtonDeleteActionPerformed

    private void jButtonSearchHotelByActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonSearchHotelByActionPerformed

        try {
            // TODO add your handling code here:
            hotel_1Query = java.beans.Beans.isDesignTime() ? null : entityManager.createQuery("SELECT h FROM Hotel_1 h where h.hotelTier = '" + tfHotelTiercombo.getSelectedItem().toString() + "'");

            hotel_1List = java.beans.Beans.isDesignTime() ? java.util.Collections.emptyList() : hotel_1Query.getResultList();
            tableModel.fireTableDataChanged();

        } catch (Exception ex) {
            Logger.getLogger(Hotel.class.getName()).log(Level.SEVERE, null, ex);
        }

    }//GEN-LAST:event_jButtonSearchHotelByActionPerformed

    private void tfHotelTierActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_tfHotelTierActionPerformed
        // TODO add your handling code here:

    }//GEN-LAST:event_tfHotelTierActionPerformed

    private void jButtonInsertRoomActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonInsertRoomActionPerformed
        // TODO add your handling code here:
        if (this.jTableRoom.getSelectedRow() < 0) {
            JOptionPane.showMessageDialog(null, "No Room Selected!");
            return;
        }

        if (!tfRoomId.getText().trim().equals("") && !tfRoomDesc.getText().trim().equals("") && !tfRoomPrice.getText().trim().equals("")) {
            Room room = new Room();
            room.setRoomPK(new RoomPK(new BigInteger(tfRoomId.getText()), new BigInteger(tfRoomHotelId.getSelectedItem().toString())));
            room.setRoomType(jComboBox2.getSelectedItem().toString());
            room.setRoomDescription(tfRoomDesc.getText());
            room.setRoomPrice(tfRoomPrice.getText());
            room.setGuestCapicity(new BigInteger(tfRoomCapacity.getSelectedItem().toString()));
            Statement stmt;
            try {

                this.entityManager0.getTransaction().begin();
                this.entityManager0.persist(room);

                this.entityManager0.getTransaction().commit();
                this.updateRoomTable();
                JOptionPane.showMessageDialog(null, "Insert Successfully!");
            } catch (Exception ex) {
                ex.printStackTrace();
                JOptionPane.showMessageDialog(null, "Insert Failed!");
            }

        } else {
            RoomErrorMessage.setText("No fields can be empty");
        }

    }//GEN-LAST:event_jButtonInsertRoomActionPerformed

    private void jButtonUpdateRoomActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonUpdateRoomActionPerformed
        // TODO add your handling code here:
        if (this.jTableRoom.getSelectedRow() < 0) {
            JOptionPane.showMessageDialog(null, "No Room Selected!");
            return;
        }
        try {

            this.entityManager0.getTransaction().begin();
            Room room = this.roomList.get(this.jTableRoom.getSelectedRow());
            room.setGuestCapicity(new BigInteger(tfRoomCapacity.getSelectedItem().toString()));
            room.setRoomPrice(tfRoomPrice.getText());
            room.setRoomDescription(tfRoomDesc.getText());
            room.setRoomType(jComboBox2.getSelectedItem().toString());
            this.entityManager0.getTransaction().commit();
            this.updateRoomTable();
            JOptionPane.showMessageDialog(null, "Insert Successfully!");
        } catch (Exception ex) {
            ex.printStackTrace();
            JOptionPane.showMessageDialog(null, "Update Failed!");
        }

    }//GEN-LAST:event_jButtonUpdateRoomActionPerformed


    private void jButtonDeleteRoomActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonDeleteRoomActionPerformed
        // TODO add your handling code here:
        int index = jTableRoom.getSelectedRow();
        if (index < 0) {
            return;
        }
        try {
            this.entityManager0.getTransaction().begin();
            RoomPK roomPk = this.roomList.get(index).getRoomPK();
            Room room = this.entityManager0.find(Room.class, roomPk);
            for (Iterator<Facility> iterator = this.facilityList.iterator(); iterator.hasNext(); ) {
                Facility facility = iterator.next();
                if (facility.getRoomCollection().contains(room)) {
                    this.entityManager0.remove(facility);
                }
            }
            this.entityManager0.remove(room);
            this.entityManager0.getTransaction().commit();
            updateRoomTable();
            JOptionPane.showMessageDialog(null, "Deleted Successfully!");
        } catch (Exception ex) {
            ex.printStackTrace();
            JOptionPane.showMessageDialog(Hotel.this, "Delete Room Failed.");
        }

    }//GEN-LAST:event_jButtonDeleteRoomActionPerformed

    private void jButtonSearchRoomByActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonSearchRoomByActionPerformed
        int index = this.tfRoomFacilityCombo.getSelectedIndex();
        Facility facility = this.facilityList.get(index);
        Collection<Room> rooms = facility.getRoomCollection();
        this.roomList.clear();
        this.roomList.addAll(rooms);
        this.roomTableModel.fireTableDataChanged();
    }//GEN-LAST:event_jButtonSearchRoomByActionPerformed

    private void tfRoomFacilityComboActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_tfRoomFacilityComboActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_tfRoomFacilityComboActionPerformed

    private void jButtonSearchCutomerByActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonSearchCutomerByActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jButtonSearchCutomerByActionPerformed

    private void jButtonDeleteCutomerActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonDeleteCutomerActionPerformed
        // TODO add your handling code here:
        int index = jTableCustomer.getSelectedRow();
        Statement stmt;
        try {

            stmt = conn2.createStatement();
            stmt.executeUpdate("delete from CUSTOMER where CUSTOMER_NUMBER=" + tfCustomerID.getText());
            updateRoomTable();
            JOptionPane.showMessageDialog(null, "Deleted Successfully!");
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }//GEN-LAST:event_jButtonDeleteCutomerActionPerformed

    private void jButtonUpdateCutomerActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonUpdateCutomerActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jButtonUpdateCutomerActionPerformed

    private void jButtonInsertCutomerActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonInsertCutomerActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jButtonInsertCutomerActionPerformed

    private void comboCustomerMemberTierActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_comboCustomerMemberTierActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_comboCustomerMemberTierActionPerformed

    private void jButtonSearchCutomerBy1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonSearchCutomerBy1ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jButtonSearchCutomerBy1ActionPerformed

    private void jButtonDeleteCutomer1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonDeleteCutomer1ActionPerformed
        // TODO add your handling code here:
        int index = jTableGuest.getSelectedRow();
        Statement stmt;
        try {

            stmt = conn2.createStatement();
            stmt.executeUpdate("delete from DUEST where GUEST_NUMBER=" + tfGuestID.getText());
            updateRoomTable();
            JOptionPane.showMessageDialog(null, "Deleted Successfully!");
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }//GEN-LAST:event_jButtonDeleteCutomer1ActionPerformed

    private void jButtonUpdateCutomer1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonUpdateCutomer1ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jButtonUpdateCutomer1ActionPerformed

    private void jButtonInsertCutomer1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonInsertCutomer1ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jButtonInsertCutomer1ActionPerformed

    private void jButtonInsertMembershipActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonInsertMembershipActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jButtonInsertMembershipActionPerformed

    private void jButtonUpdateMembershipActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonUpdateMembershipActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jButtonUpdateMembershipActionPerformed

    private void jButtonDeleteMembershipActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonDeleteMembershipActionPerformed
        // TODO add your handling code here:
        int index = jTableMembership.getSelectedRow();
        Statement stmt;
        try {

            stmt = conn2.createStatement();
            stmt.executeUpdate("delete from MEMBERSHIP where MEMBERSIP_tier=" + tfMembershipTier.getText());
            updateRoomTable();
            JOptionPane.showMessageDialog(null, "Deleted Successfully!");
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }//GEN-LAST:event_jButtonDeleteMembershipActionPerformed

    private void jButtonSearchMembershipByActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonSearchMembershipByActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jButtonSearchMembershipByActionPerformed

    private void jButtonInsertMembership1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonInsertMembership1ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jButtonInsertMembership1ActionPerformed

    private void jButtonUpdateMembership1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonUpdateMembership1ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jButtonUpdateMembership1ActionPerformed

    private void jButtonDeleteMembership1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonDeleteMembership1ActionPerformed
        int index = jTableBooking.getSelectedRow();
        Statement stmt;
        try {

            stmt = conn2.createStatement();
            stmt.executeUpdate("delete from BOOKING where BOOKING_NUMBER=" + tfBookingNumber.getText());
            updateRoomTable();
            JOptionPane.showMessageDialog(null, "Deleted Successfully!");
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }//GEN-LAST:event_jButtonDeleteMembership1ActionPerformed

    private void jButtonSearchMembershipBy1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonSearchMembershipBy1ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jButtonSearchMembershipBy1ActionPerformed

    private void jButtonInsertMembership2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonInsertMembership2ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jButtonInsertMembership2ActionPerformed

    private void jButtonUpdateMembership2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonUpdateMembership2ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jButtonUpdateMembership2ActionPerformed

    private void jButtonDeleteMembership2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonDeleteMembership2ActionPerformed
        // TODO add your handling code here:
        int index = jTablePayment.getSelectedRow();
        Statement stmt;
        try {

            stmt = conn2.createStatement();
            stmt.executeUpdate("delete from PAYMENT where PAYMENT_NUMBER=" + tfPaymentNo.getText());
            updateRoomTable();
            JOptionPane.showMessageDialog(null, "Deleted Successfully!");
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }//GEN-LAST:event_jButtonDeleteMembership2ActionPerformed

    private void tfRoomHotelIdActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_tfRoomHotelIdActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_tfRoomHotelIdActionPerformed

    private void tfRoomIdActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_tfRoomIdActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_tfRoomIdActionPerformed

    private void tfHotelIdActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_tfHotelIdActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_tfHotelIdActionPerformed

    private void tfRoomCapacityActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_tfRoomCapacityActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_tfRoomCapacityActionPerformed

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(Hotel.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Hotel.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Hotel.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Hotel.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Hotel().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel RoomErrorMessage;
    private java.util.List<finalhbs.Booking> bookingList;
    private javax.persistence.Query bookingQuery;
    private javax.swing.JComboBox<String> comboCustomerMemberTier;
    private java.util.List<finalhbs.Customer> customerList;
    private java.util.List<finalhbs.Customer> customerList1;
    private javax.persistence.Query customerQuery;
    private javax.persistence.Query customerQuery1;
    private javax.persistence.EntityManager entityManager;
    private javax.persistence.EntityManager entityManager0;
    private javax.persistence.EntityManager entityManager1;
    private java.util.List<finalhbs.Guest> guestList;
    private javax.persistence.Query guestQuery;
    private javax.swing.JLabel hotelErrorMessage;
    private javax.swing.JLabel hotelFieldsErrorMessage;
    private javax.swing.JLabel hotelFieldsErrorMessage1;
    private javax.swing.JLabel hotelFieldsErrorMessage2;
    private javax.swing.JLabel hotelFieldsErrorMessage3;
    private javax.swing.JLabel hotelFieldsErrorMessage4;
    private javax.swing.JLabel hotelFieldsErrorMessage5;
    private javax.swing.JLabel hotelFieldsErrorMessage6;
    private java.util.List<finalhbs.Hotel_1> hotel_1List;
    private java.util.List<finalhbs.Hotel_1> hotel_1List1;
    private javax.persistence.Query hotel_1Query;
    private javax.persistence.Query hotel_1Query1;
    private javax.swing.JButton jButtonDelete;
    private javax.swing.JButton jButtonDeleteCutomer;
    private javax.swing.JButton jButtonDeleteCutomer1;
    private javax.swing.JButton jButtonDeleteMembership;
    private javax.swing.JButton jButtonDeleteMembership1;
    private javax.swing.JButton jButtonDeleteMembership2;
    private javax.swing.JButton jButtonDeleteRoom;
    private javax.swing.JButton jButtonInsertCutomer;
    private javax.swing.JButton jButtonInsertCutomer1;
    private javax.swing.JButton jButtonInsertHotel;
    private javax.swing.JButton jButtonInsertMembership;
    private javax.swing.JButton jButtonInsertMembership1;
    private javax.swing.JButton jButtonInsertMembership2;
    private javax.swing.JButton jButtonInsertRoom;
    private javax.swing.JButton jButtonSearchCutomerBy;
    private javax.swing.JButton jButtonSearchCutomerBy1;
    private javax.swing.JButton jButtonSearchHotelBy;
    private javax.swing.JButton jButtonSearchMembershipBy;
    private javax.swing.JButton jButtonSearchMembershipBy1;
    private javax.swing.JButton jButtonSearchRoomBy;
    private javax.swing.JButton jButtonUpdate;
    private javax.swing.JButton jButtonUpdateCutomer;
    private javax.swing.JButton jButtonUpdateCutomer1;
    private javax.swing.JButton jButtonUpdateMembership;
    private javax.swing.JButton jButtonUpdateMembership1;
    private javax.swing.JButton jButtonUpdateMembership2;
    private javax.swing.JButton jButtonUpdateRoom;
    private javax.swing.JComboBox<String> jComboBox1;
    private javax.swing.JComboBox<String> jComboBox2;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel16;
    private javax.swing.JLabel jLabel17;
    private javax.swing.JLabel jLabel18;
    private javax.swing.JLabel jLabel19;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel20;
    private javax.swing.JLabel jLabel21;
    private javax.swing.JLabel jLabel22;
    private javax.swing.JLabel jLabel23;
    private javax.swing.JLabel jLabel24;
    private javax.swing.JLabel jLabel25;
    private javax.swing.JLabel jLabel26;
    private javax.swing.JLabel jLabel27;
    private javax.swing.JLabel jLabel28;
    private javax.swing.JLabel jLabel29;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel30;
    private javax.swing.JLabel jLabel31;
    private javax.swing.JLabel jLabel32;
    private javax.swing.JLabel jLabel33;
    private javax.swing.JLabel jLabel34;
    private javax.swing.JLabel jLabel35;
    private javax.swing.JLabel jLabel36;
    private javax.swing.JLabel jLabel37;
    private javax.swing.JLabel jLabel38;
    private javax.swing.JLabel jLabel39;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel40;
    private javax.swing.JLabel jLabel41;
    private javax.swing.JLabel jLabel42;
    private javax.swing.JLabel jLabel43;
    private javax.swing.JLabel jLabel44;
    private javax.swing.JLabel jLabel45;
    private javax.swing.JLabel jLabel46;
    private javax.swing.JLabel jLabel47;
    private javax.swing.JLabel jLabel48;
    private javax.swing.JLabel jLabel49;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel50;
    private javax.swing.JLabel jLabel51;
    private javax.swing.JLabel jLabel52;
    private javax.swing.JLabel jLabel53;
    private javax.swing.JLabel jLabel54;
    private javax.swing.JLabel jLabel55;
    private javax.swing.JLabel jLabel56;
    private javax.swing.JLabel jLabel57;
    private javax.swing.JLabel jLabel58;
    private javax.swing.JLabel jLabel59;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel60;
    private javax.swing.JLabel jLabel61;
    private javax.swing.JLabel jLabel62;
    private javax.swing.JLabel jLabel63;
    private javax.swing.JLabel jLabel64;
    private javax.swing.JLabel jLabel65;
    private javax.swing.JLabel jLabel66;
    private javax.swing.JLabel jLabel67;
    private javax.swing.JLabel jLabel68;
    private javax.swing.JLabel jLabel69;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel70;
    private javax.swing.JLabel jLabel71;
    private javax.swing.JLabel jLabel72;
    private javax.swing.JLabel jLabel73;
    private javax.swing.JLabel jLabel74;
    private javax.swing.JLabel jLabel75;
    private javax.swing.JLabel jLabel76;
    private javax.swing.JLabel jLabel77;
    private javax.swing.JLabel jLabel78;
    private javax.swing.JLabel jLabel79;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel10;
    private javax.swing.JPanel jPanel11;
    private javax.swing.JPanel jPanel12;
    private javax.swing.JPanel jPanel13;
    private javax.swing.JPanel jPanel14;
    private javax.swing.JPanel jPanel15;
    private javax.swing.JPanel jPanel16;
    private javax.swing.JPanel jPanel17;
    private javax.swing.JPanel jPanel18;
    private javax.swing.JPanel jPanel19;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel20;
    private javax.swing.JPanel jPanel21;
    private javax.swing.JPanel jPanel22;
    private javax.swing.JPanel jPanel23;
    private javax.swing.JPanel jPanel24;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JPanel jPanel6;
    private javax.swing.JPanel jPanel7;
    private javax.swing.JPanel jPanel8;
    private javax.swing.JPanel jPanel9;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JScrollPane jScrollPane4;
    private javax.swing.JScrollPane jScrollPane5;
    private javax.swing.JScrollPane jScrollPane6;
    private javax.swing.JScrollPane jScrollPane7;
    private javax.swing.JTabbedPane jTabbedPane1;
    private javax.swing.JTabbedPane jTabbedPane2;
    private javax.swing.JTable jTableBooking;
    private javax.swing.JTable jTableCustomer;
    private javax.swing.JTable jTableGuest;
    private javax.swing.JTable jTableHotel;
    private javax.swing.JTable jTableMembership;
    private javax.swing.JTable jTablePayment;
    private javax.swing.JTable jTableRoom;
    private javax.swing.JTextField jTextField1;
    private javax.swing.JTextField jTextField2;
    private javax.swing.JTextField jTextField3;
    private javax.swing.JTextField jTextField5;
    private javax.swing.JTextField jTextField6;
    private java.util.List<finalhbs.Membership> membershipList;
    private javax.persistence.Query membershipQuery;
    private java.util.List<finalhbs.Payment> paymentList;
    private java.util.List<finalhbs.Facility> facilityList;
    private javax.persistence.Query paymentQuery;
    private javax.persistence.Query facilityQuery;
    private java.util.List<finalhbs.Room> roomList;
    private java.util.List<finalhbs.Room> roomList1;
    private java.util.List<finalhbs.Room> roomList2;
    private java.util.List<finalhbs.Room> roomList3;
    private java.util.List<finalhbs.Room> roomList4;
    private java.util.List<finalhbs.Room> roomList5;
    private javax.persistence.Query roomQuery;
    private javax.persistence.Query roomQuery1;
    private javax.persistence.Query roomQuery2;
    private javax.persistence.Query roomQuery3;
    private javax.persistence.Query roomQuery4;
    private javax.persistence.Query roomQuery5;
    private javax.swing.JTextField tfBookingCheckInDate;
    private javax.swing.JTextField tfBookingCheckOutDate;
    private javax.swing.JTextField tfBookingContactEmail;
    private javax.swing.JTextField tfBookingContactPerson;
    private javax.swing.JComboBox<String> tfBookingCustNo;
    private javax.swing.JTextField tfBookingDiscount;
    private javax.swing.JComboBox<String> tfBookingHotelID;
    private javax.swing.JTextField tfBookingNumber;
    private javax.swing.JComboBox<String> tfBookingPaymentStatus;
    private javax.swing.JComboBox<String> tfBookingRoomNo;
    private javax.swing.JTextField tfBookingTotalAmount;
    private javax.swing.JTextField tfCustomerCity;
    private javax.swing.JTextField tfCustomerContactNo;
    private javax.swing.JTextField tfCustomerCredit;
    private javax.swing.JTextField tfCustomerCuntry;
    private javax.swing.JTextField tfCustomerDOB;
    private javax.swing.JTextField tfCustomerEmail;
    private javax.swing.JTextField tfCustomerFName;
    private javax.swing.JTextField tfCustomerID;
    private javax.swing.JTextField tfCustomerLName;
    private javax.swing.JTextField tfCustomerPostCode;
    private javax.swing.JTextField tfCustomerStreet;
    private javax.swing.JTextField tfCustomerTitle;
    private javax.swing.JTextField tfGuestCity;
    private javax.swing.JTextField tfGuestContactNo;
    private javax.swing.JTextField tfGuestCuntry;
    private javax.swing.JTextField tfGuestDOB;
    private javax.swing.JTextField tfGuestEmail;
    private javax.swing.JTextField tfGuestFName;
    private javax.swing.JTextField tfGuestID;
    private javax.swing.JTextField tfGuestLName;
    private javax.swing.JTextField tfGuestPostCode;
    private javax.swing.JTextField tfGuestStreet;
    private javax.swing.JTextField tfGuestTitle;
    private javax.swing.JTextField tfHotelAddress;
    private javax.swing.JTextField tfHotelCapacity;
    private javax.swing.JTextField tfHotelCity;
    private javax.swing.JTextField tfHotelContactNo;
    private javax.swing.JTextField tfHotelCountry;
    private javax.swing.JTextField tfHotelEmail;
    private javax.swing.JTextField tfHotelId;
    private javax.swing.JTextField tfHotelNAme;
    private javax.swing.JComboBox<String> tfHotelTier;
    private javax.swing.JComboBox<String> tfHotelTiercombo;
    private javax.swing.JComboBox<String> tfHotelTiercombo1;
    private javax.swing.JTextField tfHotelconsyear;
    private javax.swing.JTextField tfMembershipCredit;
    private javax.swing.JTextField tfMembershipDiscount;
    private javax.swing.JTextField tfMembershipRewards;
    private javax.swing.JTextField tfMembershipTier;
    private javax.swing.JTextField tfPaymentAmount;
    private javax.swing.JComboBox<String> tfPaymentBookingNo;
    private javax.swing.JTextField tfPaymentDate;
    private javax.swing.JComboBox<String> tfPaymentHotelID;
    private javax.swing.JComboBox<String> tfPaymentMethod;
    private javax.swing.JTextField tfPaymentNo;
    private javax.swing.JComboBox<String> tfPaymentRoomNo;
    private javax.swing.JComboBox<String> tfRoomCapacity;
    private javax.swing.JTextField tfRoomDesc;
    private javax.swing.JComboBox<String> tfRoomFacilityCombo;
    private javax.swing.JComboBox<String> tfRoomHotelId;
    private javax.swing.JTextField tfRoomId;
    private javax.swing.JTextField tfRoomPrice;
    private javax.swing.JTextField tfSearchGuestByName;
    private org.jdesktop.beansbinding.BindingGroup bindingGroup;
    // End of variables declaration//GEN-END:variables

    class HotelTableModel extends AbstractTableModel {

        String columns[] = {"ID", "Name", "Tier", "Room Capacity", "Construction Year", "Address", "Country", "City", "Email", "Contact No."};

        @Override
        public int getRowCount() {
            return hotel_1List.size();
        }

        @Override
        public int getColumnCount() {
            return columns.length;
        }

        @Override
        public String getColumnName(int column) {
            return columns[column];
        }

        @Override
        public Object getValueAt(int rowIndex, int columnIndex) {
            Hotel_1 hotel_1 = hotel_1List.get(rowIndex);
            switch (columnIndex) {
                case 0:
                    return hotel_1.getHotelId();
                case 1:
                    return hotel_1.getHotelName();
                case 2:
                    return hotel_1.getHotelTier();
                case 3:
                    return hotel_1.getRoomCapacity();
                case 4:
                    return hotel_1.getConstructionYear();
                case 5:
                    return hotel_1.getAddress();
                case 6:
                    return hotel_1.getCountry();
                case 7:
                    return hotel_1.getCity();
                case 8:
                    return hotel_1.getEmailAddress();
                case 9:
                    return hotel_1.getContactNumber();
            }
            return null;
        }

    }

    class RoomTableModel extends AbstractTableModel {

        private String columns[] = {"Room ID", "Room Desc", "Room Price", "Room Capacity", "Hotel ID", "Room Type"};

        @Override
        public int getRowCount() {
            return roomList.size();
        }

        @Override
        public int getColumnCount() {
            return columns.length;
        }

        @Override
        public String getColumnName(int column) {
            return columns[column];
        }

        @Override
        public Object getValueAt(int rowIndex, int columnIndex) {
            Room room = roomList.get(rowIndex);
            switch (columnIndex) {
                case 0:
                    return room.getRoomPK().getRoomNumber();
                case 1:
                    return room.getRoomDescription();
                case 2:
                    return room.getRoomPrice();
                case 3:
                    return room.getGuestCapicity();
                case 4:
                    return room.getRoomPK().getHotelId();
                case 5:
                    return room.getRoomType();
            }
            return null;
        }
    }

    class CustomerTableModel extends AbstractTableModel {

        private String columns[] = {"CUSTOMER_NUMBER", "TITLE", "FIRST_NAME", "LAST_NAME", "PHONE_NUMBER", "DOB", "EMAIL_ADDRESS", "MEMBERSHIP_CREDIT",
                "MEMBERSHIP_TIER", "POSTAL_CODE", "STREET", "CITY", "COUNTRY"};

        @Override
        public int getRowCount() {
            return customerList.size();
        }

        @Override
        public int getColumnCount() {
            return columns.length;
        }

        @Override
        public String getColumnName(int column) {
            return columns[column];
        }

        @Override
        public Object getValueAt(int rowIndex, int columnIndex) {
            Customer customer = customerList.get(rowIndex);
            switch (columnIndex) {
                case 0:
                    return customer.getCustomerNumber();
                case 1:
                    return customer.getTitle();
                case 2:
                    return customer.getFirstName();
                case 3:
                    return customer.getLastName();
                case 4:
                    return customer.getPhoneNumber();
                case 5:
                    return customer.getDob();
                case 6:
                    return customer.getEmailAddress();
                case 7:
                    return customer.getMembershipCredit();
                case 8:
                    return customer.getMembershipTier();
                case 9:
                    return customer.getPostalCode();
                case 10:
                    return customer.getStreet();
                case 11:
                    return customer.getCity();
                case 12:
                    return customer.getCountry();
            }
            return null;
        }

    }

    class GuestTableModel extends AbstractTableModel {

        private String columns[] = {"GUEST_NUMBER", "TITLE", "FIRST_NAME", "LAST_NAME", "PHONE_NUMBER", "DOB", "EMAIL_ADDRESS",
                "POSTAL_CODE", "STREET", "CITY", "COUNTRY"};

        @Override
        public int getRowCount() {
            return guestList.size();
        }

        @Override
        public int getColumnCount() {
            return columns.length;
        }

        @Override
        public String getColumnName(int column) {
            return columns[column];
        }

        @Override
        public Object getValueAt(int rowIndex, int columnIndex) {
            Guest guest = guestList.get(rowIndex);
            switch (columnIndex) {
                case 0:
                    return guest.getGuestNumber();
                case 1:
                    return guest.getTitle();
                case 2:
                    return guest.getFirstName();
                case 3:
                    return guest.getLastName();
                case 4:
                    return guest.getPhoneNumber();
                case 5:
                    return guest.getDob();
                case 6:
                    return guest.getEmailAddress();
                case 7:
                    return guest.getPostalCode();
                case 8:
                    return guest.getStreet();
                case 9:
                    return guest.getCity();
                case 10:
                    return guest.getCountry();
            }
            return null;
        }

    }

    class MembershipTableModel extends AbstractTableModel {

        private String columns[] = {"MEMBERSHIP_TIER", "TIER_CREDIT", "DISCOUNT", "REWARD"};

        @Override
        public int getRowCount() {
            return membershipList.size();
        }

        @Override
        public int getColumnCount() {
            return columns.length;
        }

        @Override
        public String getColumnName(int column) {
            return columns[column];
        }

        @Override
        public Object getValueAt(int rowIndex, int columnIndex) {
            Membership membership = membershipList.get(rowIndex);
            switch (columnIndex) {
                case 0:
                    return membership.getMembershipTier();
                case 1:
                    return membership.getTierCredit();
                case 2:
                    return membership.getDiscount();
                case 3:
                    return membership.getReward();

            }
            return null;
        }

    }

    class BookingTableModel extends AbstractTableModel {

        private String columns[] = {"CUSTOMER_NUMBER", "CHECKIN_DATE", "CHECKOUT_DATE", "CONTACT_PERSON", "CONTACT_EMAIL", "TOTAL_AMOUNT", "DISCOUNT_AMOUNT", "PAYMENT_STATUS"};

        @Override
        public int getRowCount() {
            return bookingList.size();
        }

        @Override
        public int getColumnCount() {
            return columns.length;
        }

        @Override
        public String getColumnName(int column) {
            return columns[column];
        }

        @Override
        public Object getValueAt(int rowIndex, int columnIndex) {
            Booking booking = bookingList.get(rowIndex);
            switch (columnIndex) {
                case 0:
                    return booking.getCustomerNumber();
                case 1:
                    return booking.getCheckinDate();
                case 2:
                    return booking.getCheckoutDate();
                case 3:
                    return booking.getContactPerson();
                case 4:
                    return booking.getContactEmail();
                case 5:
                    return booking.getTotalAmount();
                case 6:
                    return booking.getDiscountAmount();
                case 7:
                    return booking.getPaymentStatus();

            }
            return null;
        }

    }

    class PaymentTableModel extends AbstractTableModel {

        private String columns[] = {"PAYMENT_NUMBER", "BOOKING_NUMBER", "HOTEL_ID", "ROOM_NUMBER", "PAYMENT_DATE", "PAYMENT_METHOD", "PAYMENT_AMOUNT"};

        @Override
        public int getRowCount() {
            return paymentList.size();
        }

        @Override
        public int getColumnCount() {
            return columns.length;
        }

        @Override
        public String getColumnName(int column) {
            return columns[column];
        }

        @Override
        public Object getValueAt(int rowIndex, int columnIndex) {
            Payment payment = paymentList.get(rowIndex);
            switch (columnIndex) {
                case 0:
                    return payment.getPaymentNumber();
                case 1:
                    return payment.getBookingNumber();
                case 2:
                    return payment.getHotelId();
                case 3:
                    return payment.getRoomNumber();
                case 4:
                    return payment.getPaymentDate();
                case 5:
                    return payment.getPaymentMethod();
                case 6:
                    return payment.getPaymentAmount();
            }
            return null;
        }

    }

}
