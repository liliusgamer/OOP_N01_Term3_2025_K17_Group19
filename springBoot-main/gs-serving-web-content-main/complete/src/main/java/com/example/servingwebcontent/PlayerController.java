package com.example.servingwebcontent;

import com.example.servingwebcontent.model.Player;
import com.example.servingwebcontent.model.PlayerRepository;
import com.example.servingwebcontent.model.Account;
import com.example.servingwebcontent.model.AccountRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/admin/players")
public class PlayerController {
    @Autowired
    private PlayerRepository playerRepository;

    @Autowired
    private AccountRepository accountRepository;

    // Danh sách nhân vật
    @GetMapping
    public String listPlayers(Model model) {
        model.addAttribute("players", playerRepository.findAll());
        return "players";
    }

    // Trang thêm nhân vật
    @GetMapping("/add")
    public String addPlayerPage(Model model) {
        model.addAttribute("accounts", accountRepository.findAll());
        return "add-player";
    }

    // Xử lý thêm nhân vật
    @PostMapping("/add")
    public String addPlayer(@RequestParam("name") String name, @RequestParam("level") int level, @RequestParam("avatar") String avatar) {
        Player player = new Player();
        player.setName(name);
        player.setLevel(level);
        player.setAvatar(avatar);
        playerRepository.save(player);
        return "redirect:/admin/players";
    }

    // Trang sửa nhân vật
    @GetMapping("/edit")
    public String editPlayerPage(@RequestParam("id") Long id, Model model) {
        Player player = playerRepository.findById(id).orElse(null);
        model.addAttribute("player", player);
        model.addAttribute("accounts", accountRepository.findAll());
        return "edit-player";
    }

    // Xử lý cập nhật nhân vật
    @PostMapping("/edit")
    public String editPlayer(@RequestParam("id") Long id, @RequestParam("name") String name, @RequestParam("level") int level, @RequestParam("avatar") String avatar) {
        Player player = playerRepository.findById(id).orElse(null);
        if (player != null) {
            player.setName(name);
            player.setLevel(level);
            player.setAvatar(avatar);
            playerRepository.save(player);
        }
        return "redirect:/admin/players";
    }

    // Xử lý xoá nhân vật
    @PostMapping("/delete")
    public String deletePlayer(@RequestParam("id") Long id) {
        playerRepository.deleteById(id);
        return "redirect:/admin/players";
    }

    @GetMapping("/choose-character")
    public String chooseCharacterPage(@RequestParam("username") String username, Model model) {
        List<Player> players = playerRepository.findByAccount_Username(username);
        model.addAttribute("players", players);
        model.addAttribute("username", username);
        return "choose-character";
    }
} 