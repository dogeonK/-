package tukorea.web.club.domain;

public class ReservationVO {

	private int reserveid;
	private String userid;
	private String movieid;
	private String moviename;
	private String seatnumber;
	
	public int getReserveid() {
		return reserveid;
	}
	public void setReserveid(int reserveid) {
		this.reserveid = reserveid;
	}
	public String getUserid() {
		return userid;
	}
	public void setUserid(String userid) {
		this.userid = userid;
	}
	public String getMovieid() {
		return movieid;
	}
	public void setMovieid(String movieid) {
		this.movieid = movieid;
	}
	public String getMoviename() {
		return moviename;
	}
	public void setMoviename(String moviename) {
		this.moviename = moviename;
	}
	public String getSeatnumber() {
		return seatnumber;
	}
	public void setSeatnumber(String seatnumber) {
		this.seatnumber = seatnumber;
	}
}
