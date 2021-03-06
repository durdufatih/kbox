package com.kbox.task.domain.userview;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.util.Date;


@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserViewEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String activeUser;

    private String viewerUser;

    private Date viewTime;

    private boolean showStatus;


}
