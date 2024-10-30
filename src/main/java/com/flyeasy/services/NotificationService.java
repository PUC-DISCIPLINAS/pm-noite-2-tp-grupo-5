package com.flyeasy.services;

public class NotificationService {

    public static void enviarEmail(String destinatario, String assunto, String mensagem) {
        System.out.println("Enviando e-mail para: " + destinatario);
        System.out.println("Assunto: " + assunto);
        System.out.println("Mensagem: " + mensagem);
    }
}
