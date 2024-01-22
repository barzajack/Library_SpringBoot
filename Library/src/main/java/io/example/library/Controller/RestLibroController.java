package io.example.library.Controller;

import io.example.library.Dao.LibroDao;
import io.example.library.Model.Libro;
import io.example.library.Model.LibroAPI.Item;
import io.example.library.Model.LibroAPI.Root;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;

@RestController
public class RestLibroController {

    @Autowired
    private LibroDao bookRepository;



    @GetMapping("/jsonBooks")
    public ArrayList<Libro> jsonBooks(){
        return (ArrayList<Libro>) bookRepository.findAll();
    }

    @GetMapping("/jsonBookByTitle")
    public Libro jsonBook(@RequestParam(value = "title", defaultValue = "CG4L")String title){
        return  bookRepository.findByTitolo(title);
    }

    @GetMapping("/synchronize")
    public void googleBooks(){
        RestTemplate restTemplate = new RestTemplate();
        String booksApi  = "https://www.googleapis.com/books/v1/volumes?q=\"\"&maxResults=40";
        Root root = restTemplate.getForObject(booksApi, Root.class);
        DateFormat df = new SimpleDateFormat("dd/MM/yyyy");

        for(Item item: root.items){
            Libro tempBook = new Libro(
                    item.volumeInfo.authors.get(0), item.volumeInfo.title,
                    df.format(item.volumeInfo.publishedDate.getTime()),
                    item.saleInfo.listPrice == null ?
                            0 : item.saleInfo.listPrice.amount
            );
            bookRepository.save(tempBook);
        }
    }
}
