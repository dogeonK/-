package tukorea.web.club.service;

import java.util.ArrayList;

import tukorea.web.club.domain.ReservationVO;
import tukorea.web.club.persistence.ReservationDAO;

public class ReservationService {

	ReservationDAO dao = new ReservationDAO();

	public ArrayList<ReservationVO> getAllReserve() {
		ArrayList<ReservationVO> reservationList = dao.getReservationList();
		return reservationList;
	}

	public boolean deleteReserve(int intId) {
		if (dao.delete(intId))
			return true;
		else
			return false;
	}

	public ReservationVO readReservation(int intId) {
		return dao.read(intId);
	}

	public boolean addReservation(ReservationVO vo) {
		if (dao.add(vo))
			return true;
		else
			return false;
	}

	public boolean updateReservation(ReservationVO vo) {
		if (dao.update(vo))
			return true;
		else
			return false;
	}

	public boolean isReserve(String movieid, int i, char c) {
		ArrayList<String> seatList = dao.reserveSeat(movieid);
		for (String seat : seatList){
			if(seat.equals(c+"-"+i)) {
				return true;
			}
		}
		return false;
	}
}
