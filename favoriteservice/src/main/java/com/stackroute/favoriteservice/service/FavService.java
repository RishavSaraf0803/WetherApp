package com.stackroute.favoriteservice.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.stackroute.favoriteservice.model.FavModel;
import com.stackroute.favoriteservice.repository.FavRepository;


@Service
public class FavService {

	@Autowired
	private FavRepository repository;
	private FavModel fav = null;
	public FavService(FavRepository repository) {
		this.repository = repository;
	}
	
	public boolean addCity(String userId, String cityName) {
		if(repository.existsById(userId)) {
			fav = repository.findById(userId).get();
			List<String> list = fav.getList();
			list.add(cityName);
			fav.setList(list);
			if(repository.save(fav) != null)
				return true;
			else
				return false;
		}
		else {
			fav = new FavModel();
			fav.setUserId(userId);
			List<String> list = new ArrayList<> ();
			list.add(cityName);
			fav.setList(list);
			if(repository.insert(fav) != null)
				return true;
			else
				return false;
		}
	}
	
	public List<String> getAllCities(String userId) {
		boolean flag = repository.existsById(userId);
		if(flag) {
			fav = repository.findById(userId).get();
			List<String> list = fav.getList();
			return list;
		}
		else {
			List<String> list = new ArrayList<> ();
			list.clear();
			return list;
		}
	}
	
	public boolean deleteCity(String userId, String city) {
		fav = repository.findById(userId).get();
		List<String> list = fav.getList();
		for(int i = 0; i < list.size(); i++) {
			if(list.get(i).equalsIgnoreCase(city)) {
				list.remove(i);
				fav.setList(list);
				repository.save(fav);
				return true;
			}
		}
		return false;
	}
	
}
