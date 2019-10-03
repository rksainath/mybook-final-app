package com.stackroute.userservice.service;

import java.io.IOException;
import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.stackroute.userservice.model.BookDetails;

@Service
public class BookKeeperService {

		
	public void saveBook(BookDetails bdd) {
		RestTemplate restTemplate = new RestTemplate();
		restTemplate.postForEntity("http://localhost:8765/save", bdd, String.class);
	}
	public List<BookDetails> getAllBooks() {
		RestTemplate restTemplate = new RestTemplate();
		ResponseEntity<String> bdd = restTemplate.getForEntity("http://localhost:8765/list", String.class);
		ObjectMapper mapper = new ObjectMapper();
		List<BookDetails> bd = null;
		try {
			bd = mapper.readValue(bdd.getBody(), List.class);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return bd;
		
	}
	public String deleteBook(String url) {
		RestTemplate restTemplate = new RestTemplate();
		restTemplate.delete("http://localhost:8765/delete?url="+url);
		return "deleted";
	}
	public boolean isFavorite(String url) {
		RestTemplate restTemplate = new RestTemplate();
		ResponseEntity<Boolean> bd = restTemplate.getForEntity("http://localhost:8765/isFavorite?url="+url, Boolean.class);
		return bd.getBody();
	}
	
}
