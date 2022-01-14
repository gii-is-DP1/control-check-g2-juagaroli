package org.springframework.samples.petclinic.feeding;

import java.text.ParseException;
import java.util.Collection;
import java.util.Locale;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.Formatter;
import org.springframework.samples.petclinic.pet.PetType;
import org.springframework.stereotype.Component;

@Component
public class FeedingTypeFormatter implements Formatter<FeedingType>{
	
	@Autowired
	private FeedingService fs;

    @Override
    public String print(FeedingType object, Locale locale) {
        return object.name;
    }

    @Override
    public FeedingType parse(String text, Locale locale) throws ParseException {
    	Collection<FeedingType> types = this.fs.getAllFeedingTypes();
		for (FeedingType type : types) {
			if (type.getName().equals(text)) {
				return type;
			}
		}
		throw new ParseException("type not found: " + text, 0);
    }
    
}
