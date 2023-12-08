package controller;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.Vector;

import model.Pc;
import model.PcModel;

public class PcController {

	PcModel pcModel = new PcModel();
	
	public List<Pc> viewAllPc() {
		List<Pc> pcData = new Vector<Pc>();
		
		ResultSet rs = pcModel.viewAllPc();
		
		try {
			while(rs.next()) {
				Integer pcId = rs.getInt("PcId");
				String pcStatus = rs.getString("PcStatus");
				String pcAvailability = rs.getString("PcAvailability");
				pcData.add(new Pc(pcId, pcStatus, pcAvailability));
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return pcData;
	}
	
}
