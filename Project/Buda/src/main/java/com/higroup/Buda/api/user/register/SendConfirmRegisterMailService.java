package com.higroup.Buda.api.user.register;

import com.higroup.Buda.entities.MailConfirmationToken;
import com.higroup.Buda.entities.User;
import com.higroup.Buda.entities.enumeration.MailTokenType;
import com.higroup.Buda.repositories.MailConfirmationTokenRepository;
import com.higroup.Buda.repositories.UserRepository;
import com.higroup.Buda.services.EmailService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import javax.transaction.Transactional;
import java.time.LocalDateTime;
import java.util.UUID;

@Service
public class SendConfirmRegisterMailService {
    private final MailConfirmationTokenRepository mailConfirmationTokenRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private EmailService emailService;

    @Autowired
    public SendConfirmRegisterMailService(MailConfirmationTokenRepository mailConfirmationTokenRepository) {
        this.mailConfirmationTokenRepository = mailConfirmationTokenRepository;
    }

    @Transactional
    public void sendMailConfirmationTo(String email) {
        User user = userRepository
            .findUserByEmail(email)
            .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "User not found"));
        MailConfirmationToken confirmationToken = this.buildConfirmationTokenFor(user);
        confirmationToken.setMailTokenType(MailTokenType.REGISTER);
        String confirmUrl = "http://localhost:8080/api/user/register/confirm?token=" + confirmationToken.getToken();
        emailService.send(
            email,
            "Please activate your BUDA account",
            this.buildAccountActivationEmail(email, confirmUrl)
        );
        confirmationToken.setTargetEmail(email);
        this.save(confirmationToken);
    }

    @Transactional
    public void sendMailForgotPassword(String email) {
        User user = userRepository
                .findUserByEmail(email)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "User not found"));
        MailConfirmationToken confirmationToken = this.buildConfirmationTokenFor(user);

        String confirmUrl = "http://localhost:8080/api/user/password/forgot/confirm?token=" + confirmationToken.getToken();
        emailService.send(email, "Forgot password", this.buildAccountConfirmationEmail(confirmUrl));
        this.save(confirmationToken);
    }

    @Transactional
    public void save(MailConfirmationToken token) {
        this.mailConfirmationTokenRepository.save(token);
    }

    public MailConfirmationToken getToken(String token) {
        return mailConfirmationTokenRepository
            .findByToken(token)
            .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Confirmation token not found"));
    }

    @Transactional
    public void setConfirmedAt(String token) {
        mailConfirmationTokenRepository.updateConfirmedAt(token, LocalDateTime.now());
    }

    private MailConfirmationToken buildConfirmationTokenFor(User user) {
        String confirmEmailToken = UUID.randomUUID().toString();
        return new MailConfirmationToken(
            confirmEmailToken,
            LocalDateTime.now(), LocalDateTime.now().plusMinutes(15),
            user
        );
    }

    private String buildAccountActivationEmail(String username, String link) {
        return "<div style=\"font-family:Helvetica,Arial,sans-serif;font-size:16px;margin:0;color:#0b0c0c\">\n" +
            "\n" +
            "<span style=\"display:none;font-size:1px;color:#fff;max-height:0\"></span>\n" +
            "\n" +
            "  <table role=\"presentation\" width=\"100%\" style=\"border-collapse:collapse;min-width:100%;width:100%!important\" cellpadding=\"0\" cellspacing=\"0\" border=\"0\">\n" +
            "    <tbody><tr>\n" +
            "      <td width=\"100%\" height=\"53\" bgcolor=\"#0b0c0c\">\n" +
            "        \n" +
            "        <table role=\"presentation\" width=\"100%\" style=\"border-collapse:collapse;max-width:580px\" cellpadding=\"0\" cellspacing=\"0\" border=\"0\" align=\"center\">\n" +
            "          <tbody><tr>\n" +
            "            <td width=\"70\" bgcolor=\"#0b0c0c\" valign=\"middle\">\n" +
            "                <table role=\"presentation\" cellpadding=\"0\" cellspacing=\"0\" border=\"0\" style=\"border-collapse:collapse\">\n" +
            "                  <tbody><tr>\n" +
            "                    <td style=\"padding-left:10px\">\n" +
            "                  \n" +
            "                    </td>\n" +
            "                    <td style=\"font-size:28px;line-height:1.315789474;Margin-top:4px;padding-left:10px\">\n" +
            "                      <span style=\"font-family:Helvetica,Arial,sans-serif;font-weight:700;color:#ffffff;text-decoration:none;vertical-align:top;display:inline-block\">Confirm your email</span>\n" +
            "                    </td>\n" +
            "                  </tr>\n" +
            "                </tbody></table>\n" +
            "              </a>\n" +
            "            </td>\n" +
            "          </tr>\n" +
            "        </tbody></table>\n" +
            "        \n" +
            "      </td>\n" +
            "    </tr>\n" +
            "  </tbody></table>\n" +
            "  <table role=\"presentation\" class=\"m_-6186904992287805515content\" align=\"center\" cellpadding=\"0\" cellspacing=\"0\" border=\"0\" style=\"border-collapse:collapse;max-width:580px;width:100%!important\" width=\"100%\">\n" +
            "    <tbody><tr>\n" +
            "      <td width=\"10\" height=\"10\" valign=\"middle\"></td>\n" +
            "      <td>\n" +
            "        \n" +
            "                <table role=\"presentation\" width=\"100%\" cellpadding=\"0\" cellspacing=\"0\" border=\"0\" style=\"border-collapse:collapse\">\n" +
            "                  <tbody><tr>\n" +
            "                    <td bgcolor=\"#1D70B8\" width=\"100%\" height=\"10\"></td>\n" +
            "                  </tr>\n" +
            "                </tbody></table>\n" +
            "        \n" +
            "      </td>\n" +
            "      <td width=\"10\" valign=\"middle\" height=\"10\"></td>\n" +
            "    </tr>\n" +
            "  </tbody></table>\n" +
            "\n" +
            "\n" +
            "\n" +
            "  <table role=\"presentation\" class=\"m_-6186904992287805515content\" align=\"center\" cellpadding=\"0\" cellspacing=\"0\" border=\"0\" style=\"border-collapse:collapse;max-width:580px;width:100%!important\" width=\"100%\">\n" +
            "    <tbody><tr>\n" +
            "      <td height=\"30\"><br></td>\n" +
            "    </tr>\n" +
            "    <tr>\n" +
            "      <td width=\"10\" valign=\"middle\"><br></td>\n" +
            "      <td style=\"font-family:Helvetica,Arial,sans-serif;font-size:19px;line-height:1.315789474;max-width:560px\">\n" +
            "        \n" +
            "            <p style=\"Margin:0 0 20px 0;font-size:19px;line-height:25px;color:#0b0c0c\">Hi " + username + ",</p><p style=\"Margin:0 0 20px 0;font-size:19px;line-height:25px;color:#0b0c0c\"> Thank you for registering. Please click on the below link to activate your account: </p><blockquote style=\"Margin:0 0 20px 0;border-left:10px solid #b1b4b6;padding:15px 0 0.1px 15px;font-size:19px;line-height:25px\"><p style=\"Margin:0 0 20px 0;font-size:19px;line-height:25px;color:#0b0c0c\"> <a href=\"" + link + "\">Activate Now</a> </p></blockquote>\n Link will expire in 15 minutes. <p>See you soon</p>" +
            "        \n" +
            "      </td>\n" +
            "      <td width=\"10\" valign=\"middle\"><br></td>\n" +
            "    </tr>\n" +
            "    <tr>\n" +
            "      <td height=\"30\"><br></td>\n" +
            "    </tr>\n" +
            "  </tbody></table><div class=\"yj6qo\"></div><div class=\"adL\">\n" +
            "\n" +
            "</div></div>";
    }

    private String buildAccountConfirmationEmail(String link)
    {
        return link;
    }
}