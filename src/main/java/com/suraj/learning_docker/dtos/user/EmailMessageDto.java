package com.suraj.learning_docker.dtos.user;

import java.io.Serializable;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Data
@Getter
@Setter
public class EmailMessageDto implements Serializable{
    private String to;
    private String subject;
    private String body;
}
