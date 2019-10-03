package com.stackroute.favouriteservice.service;


import java.io.IOException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.stackroute.favouriteservice.dao.BookDao;
import com.stackroute.favouriteservice.model.BookDetails;
import com.stackroute.favouriteservice.model.BookDetailsDto;

@Service
public class BookKeeperService {

	@Autowired
	private BookDao bookDao;
	
	public void saveBook(BookDetailsDto bdd) {
		ObjectMapper mapper = new ObjectMapper();
			try {
				System.out.println("bdd"+bdd);
				BookDetails bd = mapper.readValue(mapper.writeValueAsString(bdd), BookDetails.class);
				System.out.println(bd);
				BookDetails bdExist = bookDao.findByUrl(bd.getUrl());
				if(bdExist == null) {
					bookDao.save(bd);
				}
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
	}
	public List<BookDetails> getAllBooks() {
		List<BookDetails> bd = (List<BookDetails>) bookDao.findAll();
		return bd;
		
	}
	public String deleteBook(String url) {
		BookDetails bd = bookDao.findByUrl(url);
		String out="Not Found";
		if(bd != null) {
			out= "deleted";
			bookDao.delete(bd);
		}
		return out;
	}
	public boolean isFavorite(String url) {
		BookDetails bd = bookDao.findByUrl(url);
		return (bd != null);
	}
	
}
