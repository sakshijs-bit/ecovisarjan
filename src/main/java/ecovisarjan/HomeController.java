package ecovisarjan;

import ecovisarjan.model.Idol;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.UUID;
import java.util.Map;
import java.util.HashMap;

@Controller
public class HomeController {

    private final Map<String, Idol> idols = new HashMap<>();

    @GetMapping("/")
    public String home() {
        return "index";
    }

    @GetMapping("/register")
    public String register() {
        return "register";
    }

    @PostMapping("/register")
    public String registerIdol(
            @RequestParam String devoteeName,
            @RequestParam String mobile,
            @RequestParam String material,
            @RequestParam String size,
            @RequestParam String location,
            @RequestParam String visarjanDate,
            Model model) {

        String idolId = "EV-" +
                UUID.randomUUID()
                        .toString()
                        .substring(0, 6)
                        .toUpperCase();

        Idol idol = new Idol(
                idolId,
                devoteeName,
                mobile,
                material,
                size,
                location,
                visarjanDate,
                "Registered"
        );

        idols.put(idolId, idol);

        model.addAttribute("idol", idol);

        return "registration-success";
    }

    @GetMapping("/track")
    public String track(
            @RequestParam(required = false) String id,
            Model model) {

        if (id != null && !id.isBlank()) {

            Idol idol = idols.get(id.toUpperCase());

            if (idol != null) {

                model.addAttribute("idol", idol);

            } else {

                model.addAttribute(
                        "error",
                        "Idol ID not found. Please check your ID."
                );
            }
        }

        return "track";
    }

    @GetMapping("/about")
    public String about() {
        return "about";
    }
}