package com.stackroute.userservice.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.stackroute.userservice.model.BookDetails;
import com.stackroute.userservice.service.BookKeeperService;

@RestController
public class FavoriteBooksController {
	@Autowired
	private BookKeeperService bookKeeperService;
	
	@PostMapping("/save")
	
	public BookDetails SaveBook(@RequestBody BookDetails bookdetails) {
		bookKeeperService.saveBook(bookdetails);
		return bookdetails;		
	}
	@GetMapping("/isFavorite")
	
	public boolean isFavoriteBook(@RequestParam String url) {
		boolean isFavorite = false;
		isFavorite = bookKeeperService.isFavorite(url);
		return isFavorite;	
	}
	
	@GetMapping("/list")
	
	public List<BookDetails> getAllBook() {
		return bookKeeperService.getAllBooks();		
	}
	@DeleteMapping("/delete")
	
	public String deleteBook(@RequestParam String url) {
		
		return bookKeeperService.deleteBook(url);		
	}

}
