package com.higroup.Buda.entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

public enum AppRole{
    ADMIN, MODERATOR, USER, STAFF
}
