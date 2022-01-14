package org.springframework.samples.petclinic.feeding;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class FeedingService {
	
	@Autowired
	FeedingRepository fr;
	
	@Transactional(readOnly = true)
    public List<Feeding> getAll(){
        return fr.findAll();
    }
	
	@Transactional(readOnly = true)
    public List<FeedingType> getAllFeedingTypes(){
        return fr.findAllFeedingTypes();
    }

	@Transactional(readOnly = true)
    public FeedingType getFeedingType(String typeName) {
        return fr.getFeedingType(typeName);
    }

	@Transactional
    public Feeding save(Feeding f) throws UnfeasibleFeedingException {
        return fr.save(f);       
    }

    
}
