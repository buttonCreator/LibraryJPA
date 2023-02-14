package out.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import out.models.Book;
import out.models.Person;
import out.services.BookService;
import out.services.PeopleService;

import javax.validation.Valid;

@Controller
@RequestMapping("/books")
public class BooksController {

    private final BookService bookService;
    private final PeopleService peopleService;

    @Autowired
    public BooksController(BookService bookService, PeopleService peopleService) {
        this.bookService = bookService;
        this.peopleService = peopleService;
    }

    @GetMapping()
    public String index(Model model,
                        @RequestParam(name = "page", required = false, defaultValue = "") Integer page,
                        @RequestParam(name = "books_per_page", required = false, defaultValue = "") Integer books_per_page,
                        @RequestParam(name = "sort_by_year", required = false, defaultValue = "") Boolean sort_by_year) {

        model.addAttribute("books", bookService.getBooks(page, books_per_page, sort_by_year));
        return "books/index";

    }

    @GetMapping("/{id}")
    public String show(@PathVariable("id") int id, Model model, @ModelAttribute("person") Person person) {

        model.addAttribute("book", bookService.getBook(id));

        Person bookOwner = bookService.getBookOwner(id);

        if (bookOwner != null)
            model.addAttribute("owner", bookOwner);
        else
            model.addAttribute("people", peopleService.getPeople());


        return "books/show";

    }

    @PatchMapping("/{id}/assign")
    public String addPerson(@PathVariable("id") int id, @ModelAttribute("person") Person person) {

        bookService.addId_person(id, person);

        return "redirect:/books/" + id;

    }

    @PatchMapping("{id}/release")
    public String release(@PathVariable("id") int id) {

        bookService.release(id);

        return "redirect:/books/" + id;

    }

    @GetMapping("/new")
    public String newPerson(@ModelAttribute("book") Book book) {
        return "books/new";
    }

    @PostMapping()
    public String create(@ModelAttribute("book") @Valid Book book, BindingResult bindingResult) {

        if (bindingResult.hasErrors())
            return "books/new";

        bookService.save(book);
        return "redirect:/books";
    }

    @GetMapping("/{id}/edit")
    public String edit(@PathVariable("id") int id, Model model) {

        model.addAttribute("book", bookService.getBook(id));
        return "books/edit";
    }

    @PatchMapping("/{id}")
    public String update(@ModelAttribute("book") @Valid Book book, BindingResult bindingResult, @PathVariable("id") int id) {

        if (bindingResult.hasErrors())
            return "books/edit";

        bookService.update(book, id);
        return "redirect:/books";

    }

    @DeleteMapping("/{id}")
    public String delete(@PathVariable("id") int id) {

        bookService.delete(id);
        return "redirect:/books";

    }

}
