package ch.bbw.cardgame;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.ArrayList;
import java.util.List;

/**
 * ViewController
 *    Kontrolliert Zusammenspiel mit der View
 * @author Peter Rutschmann
 * @date 26.08.2021
 */
@Controller
public class ViewController {
    List<Weapon> leftWeaponList = new ArrayList<>();
    List<Weapon> rightWeaponList = new ArrayList<>();
    Weapon leftWeapon = null;
    Weapon rightWeapon = null;

    public ViewController() {
        setup();
    }

    private void setup(){
        leftWeaponList.clear();
        rightWeaponList.clear();

        leftWeaponList.add(new Weapon("images/bolt-action sniper rifle blue .jpg", "Bolt-Action Sniper Rifle", "Scharfschützengewehr", 31.5, 105, 1, 0.3, 3, 105 ));
        leftWeaponList.add(new Weapon("images/FAMAS (Burst Assault Rifle) gray.jpeg", "Burst Assault Rifle", "Sturmgewehr", 109.5, 27, 30, 4.06, 2.75, 27 ));
        leftWeaponList.add(new Weapon("images/pump shotgun lila.jpg", "Pumpgun", "Shrotfline", 83.3, 119, 5, 0.7, 4.2, 10 ));

        //Objekt direkt erstellen
        rightWeaponList.add(new Weapon("images/rocket launcher gold .jpg", "Rocket Launcher ", "Racketenwerfer", 97.5, 130, 1, 0.75, 2.52, 330 ));
        rightWeaponList.add(new Weapon("images/scar assault rifle gold .jpg", "Scar", "Sturmgewehr", 198, 36, 30, 5.5, 2.2, 36 ));
        rightWeaponList.add(new Weapon("images/TAC SMG.png", "Taktische Maschienenpistole", "Maschienenpistole", 170, 17, 30, 10, 2.3, 17));
    }

    @GetMapping("/")
    public String redirect() {
        Weapon leftWeapon = null;
        Weapon rightWeapon = null;
        Weapon middleWeapon = null;
        return "redirect:/cardGameView";
    }

    @GetMapping("/cardGameView")
    public String showView(Model model) {
        model.addAttribute("leftWeapon", leftWeapon);
        model.addAttribute("rightWeapon", rightWeapon);
        model.addAttribute("numberLeft", leftWeaponList.size());
        model.addAttribute("numberRight", rightWeaponList.size());
        return "cardGameForm";
    }

    @PostMapping(value = "/cardGameView", params = {"showButton=showLeft"})
    public String showLeftCard(Model model) {
        if(leftWeaponList.isEmpty()){
            leftWeapon = null;
        }else{
            leftWeapon = leftWeaponList.get(0);
        }
        model.addAttribute("leftWeapon", leftWeapon);
        model.addAttribute("rightWeapon", rightWeapon);
        model.addAttribute("numberLeft", leftWeaponList.size());
        model.addAttribute("numberRight", rightWeaponList.size());

        String winnerMessage = Checkwinner();
        model.addAttribute("winnerMessage", winnerMessage);
        return "cardGameForm";
    }

    @PostMapping(value = "/cardGameView", params = {"moveButton=moveToLeft"})
    public String moveCardToLeft(Model model) {
        if(rightWeaponList.isEmpty()){
            //do nothing
        }else{
            //Verliererkarte hinzufügen
            leftWeaponList.add(rightWeaponList.remove(0));
            //Siegerkarte nach hinten schieben
            leftWeaponList.add(leftWeaponList.remove(0));
        }
        leftWeapon = null;
        rightWeapon = null;
        model.addAttribute("leftWeapon", leftWeapon);
        model.addAttribute("rightWeapon", rightWeapon);
        model.addAttribute("numberLeft", leftWeaponList.size());
        model.addAttribute("numberRight", rightWeaponList.size());

        String winnerMessage = Checkwinner();
        model.addAttribute("winnerMessage", winnerMessage);
        return "cardGameForm";
    }

    @PostMapping(value = "/cardGameView", params = {"showButton=showRight"})
    public String showRightCard(Model model) {
        if(rightWeaponList.isEmpty()){
            rightWeapon = null;
        }else{
            rightWeapon = rightWeaponList.get(0);
        }
        model.addAttribute("leftWeapon", leftWeapon);
        model.addAttribute("rightWeapon", rightWeapon);
        model.addAttribute("numberLeft", leftWeaponList.size());
        model.addAttribute("numberRight", rightWeaponList.size());

        String winnerMessage = Checkwinner();
        model.addAttribute("winnerMessage", winnerMessage);
        return "cardGameForm";
    }

    @PostMapping(value = "/cardGameView", params = {"moveButton=moveToRight"})
    public String moveCardToRight(Model model) {
        if (leftWeaponList.isEmpty()) {
            //do nothing
        } else {
            //Verliererkarte hinzufügen
            rightWeaponList.add(leftWeaponList.remove(0));
            //Siegerkarte nach hinten schieben
            rightWeaponList.add(rightWeaponList.remove(0));
        }
        leftWeapon = null;
        rightWeapon = null;
        model.addAttribute("leftWeapon", leftWeapon);
        model.addAttribute("rightWeapon", rightWeapon);
        model.addAttribute("numberLeft", leftWeaponList.size());
        model.addAttribute("numberRight", rightWeaponList.size());

        String winnerMessage = Checkwinner();
        model.addAttribute("winnerMessage", winnerMessage);
        return "cardGameForm";
    }

    @PostMapping(value = "/cardGameView", params = {"showButton=reset"})
    public String resetView(Model model) {
        setup();
        leftWeapon = null;
        rightWeapon = null;

        model.addAttribute("winnerMessage", null);
        model.addAttribute("leftWeapon", leftWeapon);
        model.addAttribute("rightWeapon", rightWeapon);
        model.addAttribute("numberLeft", leftWeaponList.size());
        model.addAttribute("numberRight", rightWeaponList.size());
        return "cardGameForm";
    }

    public String Checkwinner() {
         if (leftWeaponList.isEmpty()) {
            return "Player 2 gewinnt!";
        } else if (rightWeaponList.isEmpty()) {
            return "Player 1 gewinnt!";
        } else {
            return "";
        }
    }


}