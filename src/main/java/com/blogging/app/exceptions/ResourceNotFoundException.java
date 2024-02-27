package com.blogging.app.exceptions;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter

public class ResourceNotFoundException extends RuntimeException{

    String resourceName;
    String fieldName;
    long fieldValue;
    public ResourceNotFoundException(String resouceName, String fieldName, long fieldValue) {
        super(String.format("%s not found with %s : %s",resouceName,fieldName,fieldValue));
        this.resourceName = resouceName;
        this.fieldName = fieldName;
        this.fieldValue = fieldValue;
    }


}
