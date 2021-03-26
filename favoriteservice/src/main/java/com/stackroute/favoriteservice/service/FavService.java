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
			repository.save(fav);
			return true;
		}
		else {
			fav = new FavModel();
			fav.setUserId(userId);
			List<String> list = new ArrayList<> ();
			list.add(cityName);
			fav.setList(list);
			repository.insert(fav);
			return true;
		}
	}
	
	public List<String> getAllCities(String userId) {
		fav = repository.findById(userId).get();
		List<String> list = fav.getList();
		return list;
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
