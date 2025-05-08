package com.btl.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.btl.entities.SlideImage;
import com.btl.repositories.SlideImageRepository;

@Service
public class SlideImageService {
	@Autowired
	SlideImageRepository slideImageRepository;
	
	public List<SlideImage> getAll(){
		return slideImageRepository.findAll();
	}
}