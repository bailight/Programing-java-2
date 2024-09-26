package common.datamodule;

import common.TypeRegistration;

import java.io.Serializable;

public class Response implements Serializable {
    private static final long serialVersionUID = 2L;

    private final TypeRegistration registration;
    private String response;

    public Response(TypeRegistration registration) {
        this.registration = registration;
    }

    public Response(String s) {
        this.registration = TypeRegistration.AUTHORIZED;
        this.response = s;
    }

    public Response(TypeRegistration registration, String s){
        this.registration = registration;
        this.response = s;
    }

    public TypeRegistration getStatusRegistration() {
        return registration;
    }

    public String getResponse(){
        return response;
    }

    public TypeRegistration getTypeRegistration(){
        return registration;
    }

}
