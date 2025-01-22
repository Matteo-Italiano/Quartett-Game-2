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
    List<Car> leftCarList = new ArrayList<>();
    List<Car> rightCarList = new ArrayList<>();
    Car leftCar = null;
    Car rightCar = null;

    public ViewController() {
        setup();
    }

    private void setup(){
        leftCarList.clear();
        rightCarList.clear();

        leftCarList.add(new Car("images/bolt-action sniper rifle blue .jpg", "Bolt-Action Sniper Rifle", "Scharfschützengewehr", 31.5, 105, 1, 0.3, 3, 105 ));
        leftCarList.add(new Car("images/FAMAS (Burst Assault Rifle) gray.jpeg", "Burst Assault Rifle", "Sturmgewehr", 109.5, 27, 30, 4.06, 2.75, 27 ));
        leftCarList.add(new Car("images/pump shotgun lila.jpg", "Shrotflinte", "", 83.3, 119, 5, 0.7, 4.2, 10 ));

        //Objekt direkt erstellen
        rightCarList.add(new Car("images/rocket launcher gold .jpg", "Rocket Launcher ", "Racketenwerfer", 97.5, 130, 1, 0.75, 2.52, 330 ));
        rightCarList.add(new Car("images/scar assault rifle gold .jpg", "Scar", "Sturmgewehr", 198, 36, 30, 5.5, 2.2, 36 ));
        rightCarList.add(new Car("images/TAC SMG.png", "Taktische Maschienenpistole", "Maschienenpistole", 170, 17, 30, 10, 2.3, 17));
    }

    @GetMapping("/")
    public String redirect() {
        Car leftCar = null;
        Car rightCar = null;
        Car middleCar = null;
        return "redirect:/cardGameView";
    }

    @GetMapping("/cardGameView")
    public String showView(Model model) {
        model.addAttribute("leftCar", leftCar);
        model.addAttribute("rightCar", rightCar);
        model.addAttribute("numberLeft", leftCarList.size());
        model.addAttribute("numberRight", rightCarList.size());
        return "cardGameForm";
    }

    @PostMapping(value = "/cardGameView", params = {"showButton=showLeft"})
    public String showLeftCard(Model model) {
        if(leftCarList.isEmpty()){
            leftCar = null;
        }else{
            leftCar = leftCarList.get(0);
        }
        model.addAttribute("leftCar", leftCar);
        model.addAttribute("rightCar", rightCar);
        model.addAttribute("numberLeft", leftCarList.size());
        model.addAttribute("numberRight", rightCarList.size());
        return "cardGameForm";
    }

    @PostMapping(value = "/cardGameView", params = {"moveButton=moveToLeft"})
    public String moveCardToLeft(Model model) {
        if(rightCarList.isEmpty()){
            //do nothing
        }else{
            //Verliererkarte hinzufügen
            leftCarList.add(rightCarList.remove(0));
            //Siegerkarte nach hinten schieben
            leftCarList.add(leftCarList.remove(0));
        }
        leftCar = null;
        rightCar = null;
        model.addAttribute("leftCar", leftCar);
        model.addAttribute("rightCar", rightCar);
        model.addAttribute("numberLeft", leftCarList.size());
        model.addAttribute("numberRight", rightCarList.size());
        return "cardGameForm";
    }

    @PostMapping(value = "/cardGameView", params = {"showButton=showRight"})
    public String showRightCard(Model model) {
        if(rightCarList.isEmpty()){
            rightCar = null;
        }else{
            rightCar = rightCarList.get(0);
        }
        model.addAttribute("leftCar", leftCar);
        model.addAttribute("rightCar", rightCar);
        model.addAttribute("numberLeft", leftCarList.size());
        model.addAttribute("numberRight", rightCarList.size());
        return "cardGameForm";
    }

    @PostMapping(value = "/cardGameView", params = {"moveButton=moveToRight"})
    public String moveCardToRight(Model model) {
        if (leftCarList.isEmpty()) {
            //do nothing
        } else {
            //Verliererkarte hinzufügen
            rightCarList.add(leftCarList.remove(0));
            //Siegerkarte nach hinten schieben
            rightCarList.add(rightCarList.remove(0));
        }
        leftCar = null;
        rightCar = null;
        model.addAttribute("leftCar", leftCar);
        model.addAttribute("rightCar", rightCar);
        model.addAttribute("numberLeft", leftCarList.size());
        model.addAttribute("numberRight", rightCarList.size());
        return "cardGameForm";
    }

    @PostMapping(value = "/cardGameView", params = {"showButton=reset"})
    public String resetView(Model model) {
        setup();
        leftCar = null;
        rightCar = null;
        model.addAttribute("leftCar", leftCar);
        model.addAttribute("rightCar", rightCar);
        model.addAttribute("numberLeft", leftCarList.size());
        model.addAttribute("numberRight", rightCarList.size());
        return "cardGameForm";
    }

}
