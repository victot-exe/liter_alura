package com.victot_exe.liter_alura;

import com.victot_exe.liter_alura.ui.Menu;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class LiterAluraApplication implements CommandLineRunner {


	private final Menu menu;

	public LiterAluraApplication(Menu menu){
		this.menu = menu;
	}

	public static void main(String[] args) {
		SpringApplication.run(LiterAluraApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {

		menu.exibirMenu();
	}
}
