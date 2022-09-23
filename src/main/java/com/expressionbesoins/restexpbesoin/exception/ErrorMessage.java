package com.expressionbesoins.restexpbesoin.exception;

import lombok.*;
import lombok.experimental.FieldDefaults;

import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
@Builder
public class ErrorMessage {
    String message;
    Integer status;
    Date timestamp;
}
