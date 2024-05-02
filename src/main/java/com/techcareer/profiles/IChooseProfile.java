package com.techcareer.profiles;


import org.springframework.stereotype.Component;
// @Component: IChooiseProfile nesnesi Spring nesnesi olması için
@Component
public interface IChooseProfile {
    public String message(String name);
}