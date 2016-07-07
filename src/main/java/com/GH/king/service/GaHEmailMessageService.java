package com.GH.king.service;

import com.GH.king.domain.GaHEmail;
import org.springframework.core.io.FileSystemResource;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.mail.javamail.MimeMessageHelper;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import java.io.File;


/**
 * Created by alex on 2016/3/9.
 */
public class GaHEmailMessageService {

    private SimpleMailMessage simpleMailMessage;

    private JavaMailSenderImpl javaMailSender;

    private MimeMessage mimeMessage;

    public GaHEmailMessageService() {
    }

    public GaHEmailMessageService(JavaMailSenderImpl javaMailSender) {
        this.javaMailSender = javaMailSender;
    }

    public SimpleMailMessage setMessage(GaHEmail email){
        simpleMailMessage=new SimpleMailMessage();
        simpleMailMessage.setFrom("shdxgy_go@163.com");

        String receiver=email.getSendTo();
        String receivers[]=receiver.split(";");
        simpleMailMessage.setTo(receivers);
        simpleMailMessage.setSubject(email.getSubject());
        simpleMailMessage.setText(email.getContent());
        return simpleMailMessage;
    }

    public void sendMimeMessage(GaHEmail email){
        mimeMessage = this.javaMailSender.createMimeMessage();
        try {
            MimeMessageHelper messageHelper=new MimeMessageHelper(mimeMessage,true,"utf-8");
            messageHelper.setFrom("shdxgy_go@163.com");
            String receiver=email.getSendTo();
            String receivers[]=receiver.split(";");
            messageHelper.setTo(receivers);

            messageHelper.setSubject(email.getSubject());
            messageHelper.setText(email.getContent(),true);//Set the second Param to True
            FileSystemResource res= new FileSystemResource(new File("D://log.jpg"));
            messageHelper.addInline("companyLog",res);
            javaMailSender.send(mimeMessage);

        } catch (MessagingException e) {
            e.printStackTrace();
        }
    }
}
