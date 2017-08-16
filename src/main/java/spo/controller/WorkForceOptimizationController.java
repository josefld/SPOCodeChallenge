package spo.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import spo.domain.Junior;
import spo.domain.Room;
import spo.domain.Senior;
import spo.domain.Worker;

@RestController
public class WorkForceOptimizationController {

    @RequestMapping("/workForceOptimization")
    public List<Room> workForceOptimization(@RequestParam(value="rooms") Room rooms) {
    	List<Room> roomsOptimized = new ArrayList<>();
    	
    	
        return roomsOptimized;
    }
}
