package MAIN;

import java.time.LocalDate;

public class Product {
    private String id;
    private String name;
    private double price;
    private LocalDate expirationDate;

   

 
    public Product(String id, String name, double price, LocalDate expirationDate) {
        this.id = id;
        this.name = name;
        this.price = price;
        this.expirationDate = expirationDate;
    }




	public String getId() {
		// TODO Auto-generated method stub
		return this.id;
	}




	public String getName() {
		// TODO Auto-generated method stub
		return this.name;
	}




	public double getPrice() {
		// TODO Auto-generated method stub
		return price;
	}




	public LocalDate getExpirationDate() {
		// TODO Auto-generated method stub
		return expirationDate;
	}
}
