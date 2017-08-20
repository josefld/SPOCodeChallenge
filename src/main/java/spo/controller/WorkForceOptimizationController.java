package spo.controller;

import java.util.HashMap;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import spo.domain.Room;
import spo.exceptions.InvalidRequestException;
import spo.utils.AbstractOptimization;

@RestController
public class WorkForceOptimizationController {
	@Resource(name = "optimizeWorkForce")
	private AbstractOptimization roomWorkForceOptimization;

	public void setRoomWorkForceOptimization(AbstractOptimization roomWorkForceOptimization) {
		this.roomWorkForceOptimization = roomWorkForceOptimization;
	}

	@RequestMapping("/workForceOptimization")
	public @ResponseBody Map[] workForceOptimization(Room rooms) {

		Map[] roomsOptimized = new HashMap[rooms.getCapacity().length];
		if (validateRequest(rooms)) {
			for (int i = 0; i < rooms.getCapacity().length; i++) {
				roomsOptimized[i] = roomWorkForceOptimization.optimze(rooms.getCapacity()[i], rooms.getSenior(),
						rooms.getJunior());
			}

		} else {
			throw new InvalidRequestException();
		}
		return roomsOptimized;
	}

	private boolean validateRequest(Room rooms) {
		boolean validRequest = false;
		if (rooms.getCapacity().length >= 1 && rooms.getJunior() > 0 && rooms.getSenior() > 0) {
			validRequest = true;
		}

		return validRequest;
	}
}
