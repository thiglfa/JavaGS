package com.example.mottu_springboot.controller;



import com.example.mottu_springboot.model.Moto;
import com.example.mottu_springboot.repository.MotoRepository;
import jakarta.validation.Valid;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/motos")
public class MotoController {
    private final MotoRepository repo;
    public MotoController(MotoRepository repo){ this.repo = repo; }

    @GetMapping
    public String list(Model m){
        m.addAttribute("motos", repo.findAll());
        return "motos/list";
    }

    @GetMapping("/novo")
    public String novo(Model m){
        m.addAttribute("moto", new Moto());
        return "motos/form";
    }

    @PostMapping("/salvar")
    public String salvar(@Valid @ModelAttribute Moto moto, BindingResult br){
        if(br.hasErrors()) return "motos/form";
        repo.save(moto);
        return "redirect:/motos";
    }

    @GetMapping("/editar/{id}")
    public String editar(@PathVariable Long id, Model m){
        m.addAttribute("moto", repo.findById(id).orElse(new Moto()));
        return "motos/form";
    }

    @PostMapping("/excluir/{id}")
    public String excluir(@PathVariable Long id){
        repo.deleteById(id);
        return "redirect:/motos";
    }

    @GetMapping("/{id}")
    public String details(@PathVariable Long id, Model m){
        m.addAttribute("moto", repo.findById(id).orElse(null));
        return "motos/details";
    }
}

