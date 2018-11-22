package mitrais.yudis.library.model;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;

import org.springframework.lang.Nullable;

import com.fasterxml.jackson.annotation.JsonBackReference;

@Entity
public class Shelf {
	@Id
	private Integer id;
	private int maxCapacity;
	@Nullable
	private int currentCapacity;
	
	@JsonBackReference
	@OneToMany(mappedBy="shelf")
	private List<Book> books = new ArrayList<>();
	
	public Shelf() {
		
	}	
	
	public Shelf(int id, int maxCapacity, int currentCapacity) {
		super();
		this.id = id;
		this.maxCapacity = maxCapacity;
		this.currentCapacity = currentCapacity;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getMaxCapacity() {
		return maxCapacity;
	}

	public void setMaxCapacity(int maxCapacity) {
		this.maxCapacity = maxCapacity;
	}

	public int getCurrentCapacity() {
		return currentCapacity;
	}

	public void setCurrentCapacity(int currentCapacity) {
		this.currentCapacity = currentCapacity;
	}

	public List<Book> getBooks() {
		return books;
	}

	@Override
	public String toString() {
		return "Shelf [id=" + id + ", maxCapacity=" + maxCapacity + ", currentCapacity=" + currentCapacity + "]";
	}
}
