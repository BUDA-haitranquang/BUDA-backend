package com.higroup.Buda.entities;

import javax.persistence.*;

import com.higroup.Buda.entities.enumeration.MailTokenType;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Entity
@Table(name = "mail_confirmation_token")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class MailConfirmationToken {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

    @Column(nullable = false)
    private String token;

    @Column(nullable = false)
    private LocalDateTime createdAt;

    @Column(nullable = false)
    private LocalDateTime expiredAt;

    private LocalDateTime confirmedAt;

    private MailTokenType mailTokenType;

    @Column(name = "target_email")
    private String targetEmail;

    @ManyToOne
    @JoinColumn(name = "user_ID", referencedColumnName = "user_ID")
    private User user;

    public MailConfirmationToken(String token, LocalDateTime createdAt, LocalDateTime expiredAt, User user) {
        this.token = token;
        this.createdAt = createdAt;
        this.expiredAt = expiredAt;
        this.user = user;
    }

    
}