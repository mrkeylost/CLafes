package controller;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Vector;

import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import model.Booking;
import model.BookingModel;
import model.PcModel;
import model.TransactionModel;
import model.UserModel;

public class BookingController {

	BookingModel bookingModel = new BookingModel();
	PcModel pcModel = new PcModel();
	UserModel userModel = new UserModel();
	TransactionModel transactionModel = new TransactionModel();
	
	public void alert(String message) {
		Alert alert = new Alert(AlertType.ERROR);
		alert.setTitle("Invalid Input");
		alert.setHeaderText(message);
		alert.showAndWait();
	}
	
	public Boolean bookPc(String pcId, String userId, String date) {
		
		if(!checkPc(pcId)) {
			alert("PC is not operationable!");
			
			return false;
		}
		
		DateTimeFormatter dateFormat = DateTimeFormatter.ofPattern("yyyy-MM-dd");
		
		if(LocalDate.parse(date, dateFormat).isBefore(LocalDate.now())) {
			alert("Date cannot be less than today");
			
			return false;
		}
		
		if(!checkBookingdate(pcId, date)) {
			alert("PC already booked");
			
			return false;
		}
		
		return bookingModel.bookPc(pcId, userId, date);
	}
	
	public List<Booking> getAllPcBookedData() {
		
		List<Booking> pcBookedData = new Vector<Booking>();
		
		ResultSet rs = bookingModel.getAllPcBookedData();
		
		try {
			while(rs.next()) {
				Integer bookId = rs.getInt("BookId");
				Integer pcId = rs.getInt("PcId");
				String userName = rs.getString("UserName");
				String bookedDate = rs.getDate("BookedDate").toString();
				
				pcBookedData.add(new Booking(bookId, pcId, userName, bookedDate));
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return pcBookedData;
	}
	
	public Boolean deleteBookData(String bookId) {
		
		if(!checkBookIdValid(bookId)) {
			alert("Booking ID not found");
			
			return false;
		}
		
		if(!checkCancelBookingDate(bookId)) {
			alert("Booking date already passed today");
			
			return false;
		}
		
		return bookingModel.deleteBookData(bookId);
	}
	
	public Boolean checkBookIdValid(String bookId) {
		
		ResultSet rs = bookingModel.getAllPcBookedData();
		
		try {
			while(rs.next()) {
				Integer bookIdCheck = rs.getInt("BookId");
				
				if(bookId.equals(bookIdCheck.toString())) {
					return true;
				}
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return false;
	}
	
	public Boolean checkCancelBookingDate(String bookId) {
		
		ResultSet rs = bookingModel.getAllPcBookedData();
		
		DateTimeFormatter dateFormat = DateTimeFormatter.ofPattern("yyyy-MM-dd");
		
		try {
			while(rs.next()) {
				Integer bookIdCheck = rs.getInt("BookId");
				String bookDateCheck = rs.getDate("BookedDate").toString();
				
				if(bookId.equals(bookIdCheck.toString()) && LocalDate.parse(bookDateCheck, dateFormat).isBefore(LocalDate.now())) {
					return false;
				}
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return true;
	}
	
	public Boolean checkPc(String pcId) {
		
		ResultSet rs =  pcModel.viewAllPc();
		
		try {
			while(rs.next()) {
				Integer pcIdCheck = rs.getInt("PcId");
				String pcStatus = rs.getString("PcStatus"); 
				
				if(pcId.equals(pcIdCheck.toString()) && pcStatus.equals("Usable")) {
					return true;
				}
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return false;
	}
	
	public Boolean checkBookingdate(String id, String date) {
		
		ResultSet rs = bookingModel.getBookingDate(id, date);
		
		try {
			while(rs.next()) {
				String bookedDate = rs.getDate("BookedDate").toString();
				Integer pcId = rs.getInt("PcId");
				
				if(bookedDate.equals(date) && id.equals(pcId.toString())) {
					return false;
				}
				
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return true;
	}
	
	public Boolean finishBook(String date, Integer staffId) {
		if (!isExistBookDate(date)) {
			alert("There is No Book Data on Chosen Date!");
			return false;
		}
		
		ArrayList<Booking> finishedBookings = getFinishedBookDate(date);
		if (finishedBookings.isEmpty()) {
			alert("Chosen Date has not passed today's date!");
			return false;
		}
		
		return addTransaction(finishedBookings, staffId);
	}
	
	private Boolean isExistBookDate(String date) {
		ResultSet rs = bookingModel.getBookDateToFinish(date);
		
		try {
			while (rs.next()) {
				String bookedDate = rs.getDate("BookedDate").toString();
				
				if (bookedDate.equals(date)) return true;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return false;
	}
	
	private ArrayList<Booking> getFinishedBookDate(String date) {
		ResultSet rs = bookingModel.getAllPcBookedData();
		DateTimeFormatter dateFormat = DateTimeFormatter.ofPattern("yyyy-MM-dd");
		ArrayList<Booking> passedBookings = new ArrayList<>();
		
		try {
			while (rs.next()) {
				Integer bookId = rs.getInt("BookId");
				Integer pcId = rs.getInt("PcId");
				String userName = rs.getString("UserName");
				String bookDate = rs.getDate("BookedDate").toString();
				
				if (bookDate.equals(date) && LocalDate.parse(date, dateFormat).isBefore(LocalDate.now())) {
					Booking passedBooking = new Booking(bookId, pcId, userName, bookDate);
					passedBookings.add(passedBooking);
				}
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		bookingModel.deleteBookDataByDate(date);
		return passedBookings;
	}
	
	private Boolean addTransaction(ArrayList<Booking> passedBookings, Integer staffId) {
		ResultSet rs = userModel.getStaffData(staffId);
		
		try {
			if (rs.next()) {
				Integer userId = rs.getInt("UserId");
				String userName = rs.getString("UserName");
				String now = LocalDate.now().toString();
				
				transactionModel.addNewTransactionHeader(userId, userName, now);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		ResultSet rs2 = transactionModel.getLatestHeaderId();
		try {
			if (rs2.next()) {
				Integer transactionHeaderId = rs2.getInt("TransactionId");
				for (Booking passedBooking: passedBookings) {
					transactionModel.addTransactionDetail(transactionHeaderId, passedBooking.getPcId(), passedBooking.getUserName(), passedBooking.getBookedDate());
				}
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return true;
	}
}
