package com.kamegatze.hospital.DTO;

public enum EStatus {
    STATUS_CREATED(202),

    STATUS_NOT_FOUND(404),

    STATUS_UPDATED(1),

    STATUS_DELETED(2),

    STATUS_RECEPTION_WRITING(3),

    STATUS_RECEPTION_CANCELED(4);



    EStatus(int i) {
    }
}
