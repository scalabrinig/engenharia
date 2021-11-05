/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mackenzie.faturaenergia;

import com.mackenzie.faturaenergia.controller.FaturaController;
import com.mackenzie.faturaenergia.controller.LoginController;
import com.mackenzie.faturaenergia.model.Autenticador;
import com.mackenzie.faturaenergia.model.Calculadora;
import com.mackenzie.faturaenergia.model.Cliente;
import com.mackenzie.faturaenergia.model.Fatura;
import com.mackenzie.faturaenergia.model.Tecnico;
import com.mackenzie.faturaenergia.view.TelaFatura;
import com.mackenzie.faturaenergia.view.TelaLogin;

/**
 *
 * @author Gustavo
 */
public class Main {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        
        /* Criacao dos objetos MVC (Login) */
        Autenticador mAutenticador = new Autenticador();
        Fatura mFatura = new Fatura();
        Cliente mCliente = new Cliente();
        Tecnico mTecnico = new Tecnico();
        Calculadora mCalculadora = new Calculadora();
        TelaLogin vTelaLogin = new TelaLogin();
        TelaFatura vTelaFatura  = new TelaFatura();
        LoginController cLogin = new LoginController(mAutenticador, vTelaLogin);
        FaturaController cFatura = new FaturaController(mFatura,
                                                        mCliente,
                                                        mTecnico,
                                                        mCalculadora,
                                                        vTelaFatura);
        
        /*  Execucao das operacoes de Autenticacao do sistema por meio do 
            Controller de Login */
        cLogin.setLoginUser();
        cLogin.setLoginSenha();
        cLogin.realizarAutenticacao();
        
        // Permite acesso ao sistema caso autenticacao OK
        if(mAutenticador.getUserAutenticado() == true){
            cFatura.solicitarOpcaoSistema();
            if(cFatura.getOpcaoSistema() == 1){
                cFatura.setusuarioTecnico(mAutenticador.getUser());
                cFatura.setInstalacaoCliente();
                cFatura.setValorMedicao();
                cFatura.gerarFaturaCliente();
            }
        }
    }
    
}
