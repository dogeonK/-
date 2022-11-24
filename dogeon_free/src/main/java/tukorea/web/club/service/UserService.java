package tukorea.web.club.service;

import java.sql.SQLException;
import java.util.ArrayList;

import tukorea.web.club.domain.UserVO;
import tukorea.web.club.persistence.UserDAO;

public class UserService {

	UserDAO dao = new UserDAO();

	public ArrayList<UserVO> getAllUser() {
		ArrayList<UserVO> userList = dao.getUserList();
		return userList;
	}

	public boolean deleteUser(String strId) {
		if (dao.delete(strId))
			return true;
		else
			return false;
	}

	public UserVO readUser(String strId) {
		return dao.read(strId);
	}

	public boolean addUser(UserVO vo) {
		if (dao.add(vo))
			return true;
		else
			return false;
	}

	public boolean updateUser(UserVO vo) {
		if (dao.update(vo))
			return true;
		else
			return false;
	}

	public boolean loginUser(String strId, String strPwd) {
		try {
			if (dao.read(strId).getPasswd().equals(strPwd)) {
				return true;
			}
			else return false;
		} catch (NullPointerException e) {
			return false;
		}
	}

	public boolean isAdmin(String strId) {
		if (dao.read(strId).getUsertype() == 0) {
			return true;
		} else
			return false;
	}
}
