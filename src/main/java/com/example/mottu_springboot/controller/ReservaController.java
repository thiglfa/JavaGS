package com.example.mottu_springboot.controller;
import com.example.mottu_springboot.model.Moto;
import com.example.mottu_springboot.model.Reserva;
import com.example.mottu_springboot.model.Usuario;
import com.example.mottu_springboot.repository.MotoRepository;
import com.example.mottu_springboot.repository.ReservaRepository;
import com.example.mottu_springboot.repository.UsuarioRepository;
import jakarta.validation.Valid;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;

@Controller
@RequestMapping("/reservas")
public class ReservaController {
    private final ReservaRepository reservaRepo;
    private final MotoRepository motoRepo;
    private final UsuarioRepository usuarioRepo;

    public ReservaController(ReservaRepository r, MotoRepository m, UsuarioRepository u){
        this.reservaRepo = r; this.motoRepo = m; this.usuarioRepo = u;
    }

    @GetMapping("/novo")
    public String novaReservaForm(@RequestParam(required=false) Long motoId, Model model){
        Reserva r = new Reserva();
        if(motoId != null) r.setMoto(motoRepo.findById(motoId).orElse(null));
        model.addAttribute("reserva", r);
        model.addAttribute("motos", motoRepo.findAll());
        return "reservas/form";
    }

    @PostMapping("/salvar")
    public String salvar(@Valid Reserva reserva, BindingResult br, @AuthenticationPrincipal User user){
        if(br.hasErrors()) return "reservas/form";
        // associação do usuário autenticado
        Usuario u = usuarioRepo.findByUsername(user.getUsername()).orElse(null);
        reserva.setUsuario(u);
        reserva.setStatus("PENDENTE");
        if(reserva.getDataInicio() == null) reserva.setDataInicio(LocalDateTime.now());
        reservaRepo.save(reserva);
        // marca moto como reservada
        Moto m = reserva.getMoto();
        m.setStatus("RESERVADA");
        motoRepo.save(m);
        return "redirect:/reservas";
    }

    @GetMapping
    public String list(@AuthenticationPrincipal User user, Model model){
        Usuario u = usuarioRepo.findByUsername(user.getUsername()).orElse(null);
        if(user.getAuthorities().stream().anyMatch(a -> a.getAuthority().equals("ROLE_ADMIN"))){
            model.addAttribute("reservas", reservaRepo.findAll());
        } else {
            model.addAttribute("reservas", reservaRepo.findByUsuarioId(u.getId()));
        }
        return "reservas/list";
    }

    @PostMapping("/cancelar/{id}")
    public String cancelar(@PathVariable Long id){
        Reserva r = reservaRepo.findById(id).orElse(null);
        if(r != null){
            r.setStatus("CANCELADA");
            reservaRepo.save(r);
            Moto m = r.getMoto();
            m.setStatus("DISPONIVEL");
            motoRepo.save(m);
        }
        return "redirect:/reservas";
    }
}

