package com.example.finalproject.web;

import com.example.finalproject.service.DormitoryService;
import com.example.finalproject.service.ReservedRoomService;
import com.example.finalproject.service.ResidentService;
import com.example.finalproject.service.RoomService;
import com.example.finalproject.model.Resident;
import com.example.finalproject.model.Room;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;


@Controller
@RequestMapping("/reservation")
@AllArgsConstructor
public class ReservationController {
    private DormitoryService dormitoryService;
    private RoomService roomService;
    private ResidentService residentService;
    private ReservedRoomService reservedRoomService;


    /**
     * Displays the reservation menu page.
     *
     * @param model the model to be populated with data
     * @return the view name for the reservation menu page
     */
    @GetMapping("/menu")
    public String showMenu(Model model) {
        model.addAttribute("user", residentService.getResidentsByEmail("adpodol@gmail.com"));
        return "reservationMenu";
    }

    /**
     * Displays the page for selecting a dormitory for reservation.
     *
     * @param model the model to be populated with data
     * @return the view name for the dormitory selection page
     */
    @GetMapping("/selectDorm")
    public String startReservation(Model model) {
        model.addAttribute("user", residentService.getResidentsByEmail("adpodol@gmail.com"));
        model.addAttribute("dorms", dormitoryService.getAllDormitories());
        return "reservationSelectingDorm";
    }

    /**
     * Displays the page for selecting a room for reservation.
     *
     * @param selectedValue the selected dormitory's ID
     * @param model the model to be populated with data
     * @return the view name for the room selection page
     */
    @GetMapping("/selectRoom")
    public String continueReservation(@RequestParam("dormSelect") Long selectedValue, Model model) {
        model.addAttribute("dorm", dormitoryService.getDormitoryById(selectedValue));
        model.addAttribute("rooms", dormitoryService.getRoomsOfDormitoryById(selectedValue));
        return "reservationSelectingRoom";
    }

    /**
     * Displays the reservation process page.
     *
     * @param model the model to be populated with data
     * @param id the ID of the selected room
     * @return the view name for the reservation process page
     */
    @GetMapping("/process/{id}")
    public String roomReservation(Model model, @PathVariable Long id) {
        model.addAttribute("room", roomService.getRoomById(id));
        model.addAttribute("user", residentService.getResidentsByEmail("adpodol@gmail.com"));
        return "reservationProcess";
    }

    /**
     * Makes a reservation and redirects to the menu page.
     *
     * @param model the model to be populated with data
     * @param selectedRoom the ID of the selected room
     * @param startDate the start date of the reservation
     * @param endDate the end date of the reservation
     * @return the redirect URL to the reservation menu page
     */
    @GetMapping("/process/complete")
    public String continueReservation(Model model, @RequestParam("roomId") Long selectedRoom, @RequestParam("form-start-date") String startDate, @RequestParam("form-end-date") String endDate) {
        Resident resident = residentService.getResidentsByEmail("adpodol@gmail.com");
        Room room = roomService.getRoomById(selectedRoom);
        model.addAttribute("user", resident);
        reservedRoomService.makeReservation(startDate, endDate, resident, room);
        return "redirect:/reservation/menu";
    }
}
