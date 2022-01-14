package org.springframework.samples.petclinic.feeding;

import java.util.Collection;
import java.util.List;
import java.util.Map;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.samples.petclinic.pet.PetService;
import org.springframework.samples.petclinic.pet.PetType;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/feeding")
public class FeedingController {
	@Autowired
	private FeedingService fs;
	
	@Autowired
	private PetService ps;
	
	@InitBinder
	public void setAllowedFields(WebDataBinder dataBinder) {
		dataBinder.setDisallowedFields("id");
	}
	
	@GetMapping(value = "/create")
	public String createProduct(Map<String, Object> model) {
		Feeding f = new Feeding();
		List<FeedingType> feedingTypes = fs.getAllFeedingTypes();
		Collection<PetType> petTypes = ps.findPetTypes();
		model.put("feeding", f);
		model.put("feedingTypes", feedingTypes);
		model.put("pets", petTypes);
		return "feedings/createOrUpdateFeedingForm";
	}

	@PostMapping(value = "/create")
	public String saveNewProduct(@Valid Feeding f, BindingResult result) throws UnfeasibleFeedingException {
		if (result.hasErrors()) {
			return "feedings/createOrUpdateFeedingForm";
		}
		else {
			fs.save(f);
			return "redirect:/welcome";
		}
	}
    
}
