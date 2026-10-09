package com.mycompany.hospedagempets;

import com.mycompany.hospedagempets.controller.HospedagemController;
import com.mycompany.hospedagempets.services.HospedagemService;
import com.mycompany.hospedagempets.view.HospedagemView;

/*
1. criar HospedagemView
2. criar HospedagemService
3. criar HospedagemController usando os dois
4. mostrar a View
*/
public class HospedagemPets {

    public static void main(String[] args) {
        HospedagemView view = new HospedagemView();
        HospedagemService service = new HospedagemService();
        HospedagemController controller = new HospedagemController(view, service);
        view.setVisible(true);
    }
}
