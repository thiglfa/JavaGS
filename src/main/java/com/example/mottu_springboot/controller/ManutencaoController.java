package com.example.mottu_springboot.controller;
import com.example.mottu_springboot.repository.MotoRepository;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/manutencao")
public class ManutencaoController {
    private final MotoRepository motoRepo;
    public ManutencaoController(MotoRepository motoRepo) { this.motoRepo = motoRepo; }

    @GetMapping("/solicitar/{motoId}")
    public String solicitarForm(@PathVariable Long motoId, Model m){
        m.addAttribute("moto", motoRepo.findById(motoId).orElse(null));
        return "manutencao/form";
    }

    @PostMapping("/salvar/{motoId}")
    public String salvarSolicitacao(@PathVariable Long motoId, @RequestParam String comentario){
        var moto = motoRepo.findById(motoId).orElse(null);
        if(moto != null){
            moto.setStatus("EM_REVISAO");
            motoRepo.save(moto);
            // opcional: gravar log/registro de manutenção em outra tabela
        }
        return "redirect:/motos";
    }
}

