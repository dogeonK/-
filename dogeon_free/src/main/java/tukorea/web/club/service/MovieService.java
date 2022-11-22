package tukorea.web.club.service;

import java.util.ArrayList;

import tukorea.web.club.domain.MovieVO;
import tukorea.web.club.persistence.MovieDAO;

public class MovieService {
	MovieDAO dao = new MovieDAO();

	public ArrayList<MovieVO> getAllMovie() {
		ArrayList<MovieVO> movieList = dao.getMovieList();
		return movieList;
	}

	public boolean deleteMovie(String strId) {
		if (dao.delete(strId))
			return true;
		else
			return false;
	}

	public MovieVO readMovie(String strId) {
		return dao.read(strId);
	}

	public boolean addMovie(MovieVO vo) {
		if (dao.add(vo))
			return true;
		else
			return false;
	}

	public boolean updateMovie(MovieVO vo) {
		if (dao.update(vo))
			return true;
		else
			return false;
	}
}
