package com.victot_exe.liter_alura;

import com.victot_exe.liter_alura.service.AuthorService;
import com.victot_exe.liter_alura.service.BookService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class LiterAluraApplication implements CommandLineRunner {

	private final BookService bookService;
	private final AuthorService authorService;

	public LiterAluraApplication(BookService bookService, AuthorService authorService) {
		this.bookService = bookService;
		this.authorService = authorService;
	}

	public static void main(String[] args) {
		SpringApplication.run(LiterAluraApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		System.out.println(bookService.getBooksByTitle("emma"));

//
//		authorService.getAutores().forEach(System.out::println);
//		authorService.getAuthorLivingInYear(1830).forEach(System.out::println);

		bookService.getBooks();



	}
}
