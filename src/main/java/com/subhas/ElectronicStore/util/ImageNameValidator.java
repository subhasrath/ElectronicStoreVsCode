package com.subhas.ElectronicStore.util;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

/**
 * ImageNameValidator
 */
public class ImageNameValidator implements ConstraintValidator<ImageNameValid,String>{
    private Logger logger = LoggerFactory.getLogger(ImageNameValidator.class);
    @Override
    public boolean isValid(String value, ConstraintValidatorContext context) {
        logger.info("Message is from isValid : {}",value);
        // logic
        if(value.isBlank()){
            return false;
        }
        else{
            return true;
        }
    }

}
